package com.xtjnoob.miaosha.service.impl;

import com.xtjnoob.miaosha.dao.UserDOMapper;
import com.xtjnoob.miaosha.dao.UserPasswordDOMapper;
import com.xtjnoob.miaosha.dataobject.UserDO;
import com.xtjnoob.miaosha.dataobject.UserPasswordDO;
import com.xtjnoob.miaosha.service.UserService;
import com.xtjnoob.miaosha.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xtjnoob
 * @Date: 2019/2/13 15:33
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

        return createUserModel(userDO,userPasswordDO);
    }

    private UserModel createUserModel(UserDO userDO, UserPasswordDO userPasswordDO) {

        if (userDO == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);

        if (userPasswordDO != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }
}
