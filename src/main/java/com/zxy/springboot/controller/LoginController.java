package com.zxy.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 登录功能
 * @Author: zhangxy
 * @Date: Created in 2019/12/18
 */
@Controller
public class LoginController {

    // @RequestMapping(value = "/user/logn",method = RequestMethod.POST)
    @PostMapping(value = "/user/login") // rest风格
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {

        // 判断用户是否登录成功
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            // 登录成功，跳转页面
            // return "dashboard";
            // 如果用户登录了，将信息存在session中
            session.setAttribute("loginUser",username);
            // 防止表单重复提交，可以重定向到主页
            return "redirect:/main.html";
        } else {
            // 登录失败,在登录页面显示错误提示
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }
}
