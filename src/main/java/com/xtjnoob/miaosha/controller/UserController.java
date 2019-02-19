package com.xtjnoob.miaosha.controller;

import com.xtjnoob.miaosha.controller.viewobject.UserVO;
import com.xtjnoob.miaosha.error.BusinessExcption;
import com.xtjnoob.miaosha.error.EnumBusinessError;
import com.xtjnoob.miaosha.response.CommonReturnType;
import com.xtjnoob.miaosha.service.UserService;
import com.xtjnoob.miaosha.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/13 15:30
 * @Version 1.0
 */
@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessExcption {
        //调用service服务，获取对应id的用户对象，并返回给前端
        UserModel userModel = userService.getUserById(id);

        if (userModel == null) {
            throw new BusinessExcption(EnumBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

    // 定义exceptionhandler，来处理未被controller层处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        BusinessExcption businessExcption = (BusinessExcption) ex;
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus("fail");

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("errCode", businessExcption.getErrCode());
        responseData.put("errMsg", businessExcption.getErrMsg());

        commonReturnType.setData(responseData);
        return commonReturnType;
    }
}
