package com.zxy.springboot.controller;

import com.zxy.springboot.dao.EmployeeDao;
import com.zxy.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * @Description: 处理和员工相关的请求
 * @Author: zhangxy
 * @Date: Created in 2019/12/18
 */
@Controller
public class EmployeeController {

    @Autowired
    private  EmployeeDao employeeDao;

    /**
     * 查询所有员工，返回员工列表页面（emp/list.html)
     * @return
     */
    @GetMapping("emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        // 把查询出来的数据放在请求域进行共享
        model.addAttribute("emps","employees");
        // thymeleaf会默认拼串  classpath:/templates/xxx.html
        return "emp/list";
    }
}
