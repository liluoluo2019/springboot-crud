package com.lll.springboot.controller;/*
 @author 落落-luoluo
 @DESCRIPTION ${DESCRIPTION}
 @create 2019/9/10
*/

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password")String password,
                        Map<String,Object> map) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功 为了防止表单重复提交 重定向到页面
            return "redirect:/main.html";
        } else {
            //登录失败
            map.put("msg","用户名或者密码不正确");
            return "login.html";
        }
    }
}
