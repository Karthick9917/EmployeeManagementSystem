package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.model.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;

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

    private EmployeeService employeeService = new EmployeeService();

    /**
     * Collect the employee details from the view class and pass to the service
     *
     * @param employeeDTO - Getting the employee object
     * @return to acknowledge the view class
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) {
        return employeeService.createEmployeeDetails(employeeDTO);
    }

    /**
     * Display employee details
     *
     * @return list of all employees.
     */
    public List readEmployeeDetails() {
         return employeeService.readEmployeeDetails();
    }

    /**
     * Collect the employee name from the view class and pass to the service
     *
     * @param employeeDTOName - transfer the String value to service class
     * @return the object to view class
     */
    public EmployeeDTO findEmployeeDetails(String employeeDTOName) {
        return employeeService.findEmployeeDetails(employeeDTOName);
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
     * @param employeeDTO - transfer the object to service class
     * @return to acknowledge the view class
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeDetails(employeeDTO);
    }
}
