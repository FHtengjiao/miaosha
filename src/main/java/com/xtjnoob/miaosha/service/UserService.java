package com.xtjnoob.miaosha.service;

import com.xtjnoob.miaosha.service.model.UserModel;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/13 14:04
 * @Version 1.0
 */
public interface UserService {

    // 通过用户id获取用户对象的方法
    public UserModel getUserById(Integer id);
}
