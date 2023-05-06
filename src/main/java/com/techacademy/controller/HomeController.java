package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //管理画面
    @GetMapping("/")
    public String index() {
        return "reports/home";
    }
}
