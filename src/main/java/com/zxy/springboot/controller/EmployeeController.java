package com.zxy.springboot.controller;

import com.zxy.springboot.dao.DepartmentDao;
import com.zxy.springboot.dao.EmployeeDao;
import com.zxy.springboot.pojo.Department;
import com.zxy.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @Description: 处理和员工相关的请求
 * @Author: zhangxy
 * @Date: Created in 2019/12/18
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工，返回员工列表页面（emp/list.html)
     * @return
     */
    @GetMapping("/emps")
//    public String list(Model model) {
//        Collection<Employee> employees = employeeDao.getAll();
//        // 把查询出来的数据放在请求域进行共享
//        model.addAttribute("emps","employees");
//        // thymeleaf会默认拼串  classpath:/templates/xxx.html
//        return "emp/list";
//    }

    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        Collection<Employee> employees = employeeDao.getAll();

        // 把employees对象存到mv对象中，也会把user对象存到request对象中
        mv.addObject("emps",employees);
        // 跳转到list页面
        mv.setViewName("emp/list");
        return mv;

    }

    /**
     * 员工添加
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面,查询出所有的部门信息，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
}
