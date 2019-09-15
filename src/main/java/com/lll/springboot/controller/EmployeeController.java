package com.lll.springboot.controller;

import com.lll.springboot.dao.DepartmentDao;
import com.lll.springboot.dao.EmployeeDao;
import com.lll.springboot.entities.Department;
import com.lll.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees",employees);
        return "user/list";
    }

    /**
     * 跳转到员工添加页面
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddInput(Model model) {
        //给添加页面准备部门列表页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "user/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
//        System.out.println("employee = " + employee);
//        System.out.println("employeeDao = " + employeeDao);
        employeeDao.save(employee);
        //跳转到员工列表页面
        return "redirect:/emps";
    }

    /**
     * 跳转到员工修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditInput(@PathVariable("id") Integer id,Model model) {
        //给添加页面准备部门列表页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //根据传递过来的id查询出要修改的employee 然后进行数据回显
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee",employee);
        return "user/add";
    }

    /**
     * 员工修改
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String editEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 员工删除方法
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
