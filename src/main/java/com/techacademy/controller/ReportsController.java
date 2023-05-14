package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Report;
import com.techacademy.service.ReportsService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("reports")
public class ReportsController {
    private final ReportsService service;

    public ReportsController(ReportsService service) {
        this.service = service;
    }

    // 日報一覧画面
    @GetMapping("/list")
    public String getList(Model model,Model model2,@AuthenticationPrincipal UserDetail detail) {
        model2.addAttribute("loginUser", detail.getEmployee());
        model.addAttribute("reportsList", service.getReportsList());
        return "reports/list";
    }

    //日報登録画面
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Report report,  @AuthenticationPrincipal UserDetail detail, Model model) {
        model.addAttribute("loginUser", detail.getEmployee());
;;        return "reports/register";
    }

    //日報登録処理
    @PostMapping("/register")
    public String postRegister(Report report, @AuthenticationPrincipal UserDetail detail, Model model) {
        model.addAttribute("loginUser", detail.getEmployee());
        report.setEmployee(detail.getEmployee());
        service.saveReport(report);
        return "redirect:/reports/list";
    }

    //日報詳細画面
    @GetMapping("/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id, Model model,@AuthenticationPrincipal UserDetail detail,Model model2) {
        Report report = service.getReport(id);
        model.addAttribute("report", service.getReport(id));
        model2.addAttribute("loginUser", detail.getEmployee());
        if (detail.getEmployee().getName().equals(report.getEmployee().getName())) {
            return "reports/detail";
        } else {
            return "reports/detail2";
        }
    }

    //日報編集画面
    @GetMapping("/edit/{id}/")
    public String getEdit(@PathVariable("id") Integer id, Model model,@AuthenticationPrincipal UserDetail detail,Model model2) {
        Report report= service.getReport(id);
        model.addAttribute("report", report);
        model2.addAttribute("loginUser", detail.getEmployee());
        return "reports/edit";
    }

    //日報更新処理
    @PostMapping ("/edit/{id}/")
    public String postReport(@PathVariable("id") Integer id, Report report) {
        service.updateReport(id, report);
        return "redirect:/reports/list";
    }
}
