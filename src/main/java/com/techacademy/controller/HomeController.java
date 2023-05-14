package com.techacademy.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.service.ReportsService;
import com.techacademy.service.UserDetail;


@Controller
public class HomeController {
    private final ReportsService service ;

    public HomeController(ReportsService service) {
        this.service = service;
    }


    //管理画面
    @GetMapping("/")
    public String getList(Model model,Model model2,@AuthenticationPrincipal UserDetail detail) {
        model2.addAttribute("loginUser", detail.getEmployee());
        model.addAttribute("reportsList", service.getOwnReportsList(detail.getEmployee()));
        return "home";
    }
}
