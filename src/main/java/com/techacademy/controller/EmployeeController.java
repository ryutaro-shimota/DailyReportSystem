package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // 従業員一覧画面
    @GetMapping("/list")
    public String getList(Model model, @AuthenticationPrincipal UserDetail detail) {
        model.addAttribute("loginUser", detail.getEmployee());
        model.addAttribute("employeelist", service.getEmployeeList());
        return "employee/list";
    }

    //従業員登録画面
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee,@AuthenticationPrincipal UserDetail detail, Model model) {
        model.addAttribute("loginUser", detail.getEmployee());
        return "employee/register";
    }

    //従業員登録処理
    @PostMapping("/register")
    public String postRegister(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    //従業員詳細画面
    @GetMapping("/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id, Model model,@AuthenticationPrincipal UserDetail detail) {
        model.addAttribute("loginUser", detail.getEmployee());
        model.addAttribute("employee", service.getEmployee(id));
        return "employee/detail";
    }

    //従業員編集画面
    @GetMapping("/edit/{id}/")
    public String getEdit(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal UserDetail detail) {
        model.addAttribute("loginUser", detail.getEmployee());
        Employee employee = service.getEmployee(id);
        employee.getAuthentication().setPassword("");
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    //従業員更新処理
    @PostMapping ("/edit/{id}/")
    public String postEmployee(@PathVariable("id") Integer id, Employee employee) {
        service.updateEmployee(id, employee);
        return "redirect:/employee/list";
    }

    //Employee削除処理
    @GetMapping ("/delete/{id}/")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        service.deleteEmployee(id);
        return "redirect:/employee/list";
    }

}