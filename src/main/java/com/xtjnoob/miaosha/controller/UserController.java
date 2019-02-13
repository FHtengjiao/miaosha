package com.xtjnoob.miaosha.controller;

import com.xtjnoob.miaosha.service.UserService;
import com.xtjnoob.miaosha.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public UserModel getUser(@RequestParam(name="id") Integer id) {
        //调用service服务，获取对应id的用户对象，并返回给前端
        return userService.getUserById(id);
    }
}
