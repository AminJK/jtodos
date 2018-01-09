package com.niufennan.jtodos.controller;

import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.service.UserService;
import com.niufennan.jtodos.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    public Object test(@RequestBody Map map ){

        Long userId=TokenUtil.volidateToken("token");
        if(userId==-1){
            throw  new RuntimeException("当前token已失效");
        }
        String username=map.get("username").toString();
        String password=map.get("password").toString();
        User user=new User();
        user.setId(1);
        user.setName(username);
        user.setPassword(password);
        user.setCreateTime(new Date());
        return user;
    }
}
