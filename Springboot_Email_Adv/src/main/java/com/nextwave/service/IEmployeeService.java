package com.nextwave.service;

import com.nextwave.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEmployeeService {

    Employee signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    Optional<Employee> findById(int empId);

    List<Employee> findByName(String empName);

    Employee findByEmailId(String empEmailId);

    List<Employee> findAll();

    Employee updateData(Employee employee);

    void deleteById(int empId);

    void deleteAll();

    List<Employee> searchByAnyInput(String input);

    void saveBulkOfData(List<Employee> employeeList);


}
