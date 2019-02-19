package com.xtjnoob.miaosha.error;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/14 13:47
 * @Version 1.0
 */
public class BusinessExcption extends Exception implements CommonError {

    private CommonError commonError;

    public BusinessExcption(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessExcption(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
