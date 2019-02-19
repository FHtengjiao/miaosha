package com.xtjnoob.miaosha.error;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/14 12:59
 * @Version 1.0
 */
public interface CommonError {
    int getErrCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);
}
