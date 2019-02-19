package com.xtjnoob.miaosha.error;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/14 13:00
 * @Version 1.0
 */
public enum EnumBusinessError implements CommonError {

    // 20000为用户相关错误信息
    USER_NOT_EXIST(20001,"用户不存在"),

    // 通用错误码
    PARAMETER_VALIDATION_ERROE(10001, "非法参数"),

    // 未知错误
    UNKNOW_ERROR(30001,"未知错误")
    ;

    EnumBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
