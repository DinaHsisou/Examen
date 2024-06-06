package com.example.exam.service;

import com.example.exam.domain.model.Employee;

import java.util.List;



public interface EmployeeServices {
    List<Employee> getAllEmployee();
    void save(Employee employee);
    Employee getById(Long id);
    void deleteViaId(long id);
}
