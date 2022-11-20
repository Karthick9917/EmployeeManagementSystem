package com.ideas2it.employeeManagementSystem.dao;

import com.ideas2it.employeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstName(String name);
    Boolean existsByPhoneNumber(Long phoneNumber);
    Boolean existsByEmail(String email);

}
