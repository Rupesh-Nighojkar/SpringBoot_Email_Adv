package com.nextwave.repository;

import com.nextwave.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword);

    @Query("SELECT emp FROM Employee emp WHERE LOWER(emp.empName) LIKE LOWER(CONCAT('%', :empName, '%'))")
    List<Employee> findByEmpNameContainingIgnoreCase(@Param("empName") String empName);

    // Custom query for searching by any input (Employee name or emailId or id)
    @Query("SELECT emp FROM Employee emp WHERE LOWER(emp.empName) LIKE LOWER(CONCAT('%', :input, '%')) " +
            "OR LOWER(emp.empEmailId) LIKE LOWER(CONCAT('%', :input, '%')) OR CAST(emp.empId AS string) LIKE :input")
    List<Employee> searchByAnyInput(@Param("input") String input);

    // Bulk save is done using standard saveAll provided by JpaRepository
}
