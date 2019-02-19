package com.xtjnoob.miaosha.controller;

import com.xtjnoob.miaosha.error.BusinessExcption;
import com.xtjnoob.miaosha.error.EnumBusinessError;
import com.xtjnoob.miaosha.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/19 13:49
 * @Version 1.0
 */
public class BaseController {

    // 定义exceptionhandler，来处理未被controller层处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {

        Map<String, Object> responseData = new HashMap<>();

        if (ex instanceof BusinessExcption) {
            BusinessExcption businessExcption = (BusinessExcption) ex;
            responseData.put("errCode", businessExcption.getErrCode());
            responseData.put("errMsg", businessExcption.getErrMsg());
        } else {
            responseData.put("errCode", EnumBusinessError.UNKNOW_ERROR.getErrCode() );
            responseData.put("errMsg", EnumBusinessError.UNKNOW_ERROR.getErrMsg());
        }

        return CommonReturnType.create(responseData, "fail");
    }
}
