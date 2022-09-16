package com.ideas2it.employeeManagementSystem.dao.impl;

import com.ideas2it.employeeManagementSystem.dao.Dao;
import com.ideas2it.employeeManagementSystem.model.Employee;

import java.util.ArrayList;
import java.util.List;

/*
 * Getting employee
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeDao implements Dao {
    List<Employee> employeeList = new ArrayList<Employee>();

    /**
     * {@inheritDoc}
     */
    public boolean createEmployeeDetails(Employee employee) {
        return employeeList.add(employee);
    }

    /**
     *{@inheritDoc}
     */
    public List readEmployeeDetails() {
        return employeeList;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployeeDetails(Employee employee) {
        return employeeList.remove(employee);
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployeeDetails(Employee employee) {
        boolean isModify = false;

        for (int i = 0; i < employeeList.size(); i++) {
            Employee searchEmployeeId = employeeList.get(i);
            if (searchEmployeeId.getId().equals(employee.getId())) {
                employeeList.set(i, employee);
                isModify = true;
            }
        }
        return isModify;
    }
}
