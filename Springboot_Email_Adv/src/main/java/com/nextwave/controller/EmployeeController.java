package com.nextwave.controller;

import com.nextwave.exception.RecordNotFoundException;
import com.nextwave.model.EmailModel;
import com.nextwave.model.Employee;
import com.nextwave.service.IEmailService;
import com.nextwave.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IEmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {

        log.info("Trying to SignUp for Employee: {}" + employee.getEmpName());
        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {

        return new ResponseEntity<>(employeeService.signIn(empEmailId, empPassword), HttpStatus.OK);
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {

        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName) {

        return new ResponseEntity<>(employeeService.findByName(empName), HttpStatus.OK);
    }

    @GetMapping("/findbyemail/{empEmailId}")
    public ResponseEntity<Employee> findByEmail(@PathVariable String empEmailId) {

        return new ResponseEntity<>(employeeService.findByEmailId(empEmailId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {

        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId, @RequestBody Employee employee) {

        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee not found with id: \" + empId"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpPassword(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return new ResponseEntity<>(employeeService.updateData(employee1), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId) {

        employeeService.deleteById(empId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {

        return new ResponseEntity<>("All employees deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/sortbyiddesc")
    public ResponseEntity<List<Employee>> sortByIdDesc() {

        List<Employee> sortByIdDesc = employeeService.findAll().stream()
                .sorted(Comparator.comparing(Employee::getEmpId).reversed())
                .toList();
        return new ResponseEntity<>(sortByIdDesc, HttpStatus.OK);
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName() {

        List<Employee> sortByName = employeeService.findAll().stream()
                .sorted(Comparator.comparing(Employee::getEmpName))
                .toList();
        return new ResponseEntity<>(sortByName, HttpStatus.OK);
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>> sortByDOB() {

        List<Employee> sortByDOB = employeeService.findAll().stream()
                .sorted(Comparator.comparing(Employee::getEmpDOB))
                .toList();
        return new ResponseEntity<>(sortByDOB, HttpStatus.OK);
    }

    @GetMapping("/searchbyanyinput/{input}")
    public ResponseEntity<List<Employee>> searchByAnyInput(@PathVariable String input) {

        return new ResponseEntity<>(employeeService.searchByAnyInput(input), HttpStatus.OK);
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employeeList) {

        employeeService.saveBulkOfData(employeeList);
        return new ResponseEntity<>("Bulk data saved successfully!", HttpStatus.OK);
    }

    @PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailModel emailModel) {

        log.info("########Sending Email to: " + emailModel.getToEmail().toString());

        log.info("########Sending Email cc: " + emailModel.getCcEmail().toString());

        log.info("########Sending Email bcc: " + emailModel.getBccEmail().toString());

        emailService.sendEmail(emailModel);
        return new ResponseEntity<>("Mail Sent Successfully!", HttpStatus.OK);
    }


}
