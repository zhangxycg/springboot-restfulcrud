package com.zxy.springboot.controller;

import com.zxy.springboot.dao.DepartmentDao;
import com.zxy.springboot.dao.EmployeeDao;
import com.zxy.springboot.pojo.Department;
import com.zxy.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
     *
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
        mv.addObject("emps", employees);
        // 跳转到list页面
        mv.setViewName("emp/list");
        return mv;

    }

    /**
     * 跳转到员工添加页面
     *
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面,查询出所有的部门信息，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }


    /**
     * 员工添加
     * 页面发送emp  post请求，把数据拿过来进行添加
     * SpringMVC自动将请求参数和入参对象的属性一一绑定. 要求请求参数的名字和javaBean入参的对象里面的属性是一致的
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {

        // 来到员工列表页面
        System.out.println("保存的员工信息：" + employee);
        employeeDao.save(employee);
        // redirct 表示重定向到一个地址  /表示当前的项目路径
        // forward 表示转发到一个地址
        return "redirect:/emps";
    }

    /**
     * 来到修改页面，查询出员工数据，在页面进行回显
     * @param id
     * @param
     * @return
     */
    @GetMapping("/emp/{id}")
    public ModelAndView toEditPage(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("emp/edit");
        // 查出员工数据并保存
        Employee employee = employeeDao.get(id);
        mv.addObject("emp",employee);
        // 页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        mv.addObject("departments",departments);
        return mv;
    }

    /**
     * 员工修改
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 员工删除
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("redirect:/emps");
        employeeDao.delete(id);

        return mv;
    }

}
