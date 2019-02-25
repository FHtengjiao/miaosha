package com.xtjnoob.miaosha.controller;

import com.xtjnoob.miaosha.controller.viewobject.UserVO;
import com.xtjnoob.miaosha.error.BusinessExcption;
import com.xtjnoob.miaosha.error.EnumBusinessError;
import com.xtjnoob.miaosha.response.CommonReturnType;
import com.xtjnoob.miaosha.service.UserService;
import com.xtjnoob.miaosha.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/13 15:30
 * @Version 1.0
 */
@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    // 用户获取opt短信接口
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    @CrossOrigin
    public CommonReturnType getOtp(@RequestParam(name="telephone") String telephone) throws BusinessExcption {

        if (StringUtils.isEmpty(telephone)) {
            throw new BusinessExcption(EnumBusinessError.PARAMETER_VALIDATION_ERROE, "手机号码不能为空");
        }

        // 生成optCode
        Random random = new Random();
        int otpCode = random.nextInt(899999) + 100000;

        // 关联用户手机号
        request.getSession().setAttribute(telephone, otpCode);

        System.out.println("telephone: " + telephone + " & otpCode = " + otpCode);

        return CommonReturnType.create(null);

    }

    // 获取用户
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

    /**
     * @Description: 返回前端需要的数据模型
     * create by: xtjnoob
     * create time: 13:51 2019/2/19
     * * @param UserModel userModel
     * @return UserVO
     */
    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
