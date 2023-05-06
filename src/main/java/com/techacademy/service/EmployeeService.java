package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    /** 全件検索して返す */
    public List <Employee> getEmployeeList(){
        return repository.findAll();
    }
    /** １件検索して返す */
    public Employee getEmployee(Integer id){
        return repository.findById(id).get();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Employeeの登録を行なう */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setDeleteFlag(0);
        employee.getAuthentication().setEmployee(employee);
        employee.getAuthentication().setPassword(passwordEncoder.encode(employee.getAuthentication().getPassword()));
        return repository.save(employee);
    }

    /** Employeeの更新を行なう */
    @Transactional
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee employee1 = getEmployee(id);
        employee1.setName(employee.getName());


        employee1.setUpdatedAt(LocalDateTime.now());
        return repository.save(employee1);
    }

    /** Employeeの削除を行なう */
    @Transactional
    public Employee deleteEmployee(Integer id) {
        Employee employee = getEmployee(id);
        employee.setDeleteFlag(1);
        return repository.save(employee);
    }

}