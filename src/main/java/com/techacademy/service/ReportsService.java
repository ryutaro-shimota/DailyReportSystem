package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportsRepository;

@Service
public class ReportsService {
    private final ReportsRepository repository;

    public ReportsService(ReportsRepository repository) {
        this.repository = repository;
    }
    /** 全件検索して返す */
    public List <Report> getReportsList(){
        return repository.findAll();
    }
    /** １件検索して返す */
    public Report getReport(Integer id){
        return repository.findById(id).get();
    }

    /**引数に渡された従業員の日報のみを取得する*/
         public List <Report> getOwnReportsList(Employee employee){
        return repository.findByEmployee(employee);
    }

    /** 日報の登録を行なう */
    @Transactional
    public Report saveReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        return repository.save(report);
    }

    /** 日報の更新を行なう */
    @Transactional
      public Report updateReport(Integer id, Report report) {
        Report report1 = getReport(id);
        report1.setReportDate(report.getReportDate());
        report1.setTitle(report.getTitle());
        report1.setContent(report.getContent());
        report1.setUpdatedAt(LocalDateTime.now());
        return repository.save(report1);
    }
}

