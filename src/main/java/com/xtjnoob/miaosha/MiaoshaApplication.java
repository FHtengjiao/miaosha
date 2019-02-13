package com.xtjnoob.miaosha;

import com.xtjnoob.miaosha.dao.UserDOMapper;
import com.xtjnoob.miaosha.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = "com.xtjnoob.miaosha")
@MapperScan("com.xtjnoob.miaosha.dao")
public class MiaoshaApplication {

    @Autowired
    private UserDOMapper userDOMapper;

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(2);
        if (userDO == null) {
            return "该用户不存在";
        } else {
            return userDO.getName();
        }
    }

}

