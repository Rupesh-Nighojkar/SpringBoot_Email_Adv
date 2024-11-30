package com.nextwave.service;

import com.nextwave.model.Employee;
import com.nextwave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);
        return employee != null;
    }

    @Override
    public Optional<Employee> findById(int empId) {
        return employeeRepository.findById(empId);
    }

    @Override
    public List<Employee> findByName(String empName) {
        return employeeRepository.findByEmpNameContainingIgnoreCase(empName);
    }

    @Override
    public Employee findByEmailId(String empEmailId) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getEmpEmailId().equals(empEmailId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateData(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public List<Employee> searchByAnyInput(String input) {
        return employeeRepository.searchByAnyInput(input);
    }

    @Override
    public void saveBulkOfData(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }
}
