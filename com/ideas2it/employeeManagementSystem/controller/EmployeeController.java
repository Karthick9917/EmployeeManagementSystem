package com.ideas2it.employeeManagementSystem.controller;


import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;

import java.util.List;

/*
 * Getting the all employeeDTO
 * once the operation is done
 * returning the acknowledgement.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeService();

    /**
     * passing the employeeDTO and return the acknowledgement.
     *
     * @param employeeDTO - Getting the employeeDTO object
     * @return the acknowledgement
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) {
        return employeeService.createEmployeeDetails(employeeDTO);
    }

    /**
     * Asking all employeeDTO.
     *
     * @return list of all employeesDTO.
     */
    public List readEmployeeDetails() {
         return employeeService.readEmployeeDetails();
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param employeeDTOName - transfer the String value.
     * @return the Employee DTO object
     */
    public EmployeeDTO findEmployeeDetails(String employeeDTOName) {
        return employeeService.findEmployeeDetails(employeeDTOName);
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param employeeId - transfer the String value.
     * @return the acknowledgement
     */
    public boolean deleteEmployeeDetails(String employeeId) {
        return employeeService.deleteEmployeeDetails(employeeId);
    }

    /**
     * passing the employeeDTO and return the acknowledgement.
     *
     * @param employeeDTO - Getting the employeeDTO object
     * @return the acknowledgement
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeDetails(employeeDTO);
    }
}
