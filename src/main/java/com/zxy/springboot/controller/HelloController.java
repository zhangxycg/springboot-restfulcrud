package com.zxy.springboot.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zxy.springboot.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.ModalExclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/17
 */
@Controller
public class HelloController {

//    @RequestMapping({"/","/index.html"})
//    public String index() {
//        return "index";
//    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello, 扬帆向海！";
    }

    /**
     * 查出数据并展示在页面
     * @return
     */
    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
        map.put("name","<h1>扬帆向海</h1>");
        map.put("employees", Arrays.asList("程序员","架构师","项目经理"));
        return "success";
    }

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((Integer) i);
            user.setName("扬帆向海："+i);
            user.setAdderess("西安："+i);
            users.add(user);
        }
        model.addAttribute("users",users);
        return "userpage";
    }

}
