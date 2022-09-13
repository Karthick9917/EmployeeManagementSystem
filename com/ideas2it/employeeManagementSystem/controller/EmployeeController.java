package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.service.impl.EmployeeService;
import com.ideas2it.employeeManagementSystem.model.Employee;
import java.util.List;

/*
 * Getting the all employee details from the database
 * once the operation is done
 * returning the acknowledgement.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();

    /**
     * Collect the employee details from the view class and pass to the service
     *
     * @param employee - Getting the employee object
     * @return to acknowledge the view class
     */
    public boolean createEmployeeDetails(Employee employee) {
        return employeeService.insertEmployeeDetails(employee);
    }

    /**
     * Display employee details
     *
     * @return list of all employees.
     */
    public List displayEmployeeDetails() {
         return employeeService.showEmployeeDetails();
    }

    /**
     * Collect the employee name from the view class and pass to the service
     *
     * @param employeeName - transfer the String value to service class
     * @return the object to view class
     */
    public Employee findEmployeeDetails(String employeeName) {
        return employeeService.findEmployeeDetails(employeeName);
    }

    /**
     * Collect the employee name from the view class and pass to the service
     *
     * @param employeeId - transfer the String value to service class
     * @return to acknowledge the view class
     */
    public boolean deleteEmployeeDetails(String employeeId) {
        return employeeService.deleteEmployeeDetails(employeeId);
    }

    /**
     * Collect the employee details from the view class and pass to the service
     *
     * @param employee - transfer the object to service class
     * @return to acknowledge the view class
     */
    public boolean updateEmployeeDetails(Employee employee) {
        return employeeService.updateEmployeeDetails(employee);
    }
}
