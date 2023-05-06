package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("reports")
public class ReportsController {
    private final EmployeeService service;

    public ReportsController(EmployeeService service) {
        this.service = service;
    }
    //管理画面
    @GetMapping("/")
    public String index() {
        return "reports/home";
    }
    // 一覧画面
    @GetMapping("/list")
    public String getList(Model model) {
        model.addAttribute("employeelist", service.getEmployeeList());
        return "home/list";
    }



}
