package com.niufennan.jtodos.controller;

import com.niufennan.jtodos.base.BaseController;
import com.niufennan.jtodos.persistence.UserRepository;
import com.niufennan.jtodos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Object login(@RequestBody Map map) {
        String token = userService.login(map.get("username").toString(), map.get("password").toString());
        if (token.equals("")) {
            return result(200, "登录失败", "");
        }
        return result(token);
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public Object register(@RequestBody Map map){
        String token=userService.register(map.get("username").toString(), map.get("password").toString(), map.get("nickname").toString());
        if (token.equals("")) {
            return result(200, "此用户名已经使用", "");
        }
        return result(token);
    }

    @ResponseBody
    @RequestMapping(value = "/denied",method = {RequestMethod.POST,RequestMethod.GET})
    public Object denied(){
        return result(200,"没有权限","");
    }
}
