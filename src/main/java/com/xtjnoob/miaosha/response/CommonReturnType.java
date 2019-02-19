package com.xtjnoob.miaosha.response;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/14 12:46
 * @Version 1.0
 */
public class CommonReturnType {

    // 对应请求的返回处理结果，"success"和"fail"
    private String status;

    // 若status=success，则data内返回前端需要的json格式的数据
    // 若status=fail，则data内返回通用的错误格式数据
    private Object data;

    // 定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(result);
        commonReturnType.setStatus(status);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
