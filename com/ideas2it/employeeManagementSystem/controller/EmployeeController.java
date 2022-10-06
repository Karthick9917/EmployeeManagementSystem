package com.ideas2it.employeeManagementSystem.controller;


import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;

import java.sql.SQLException;
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
     * Passing the pattern and user input and return the true or
     * false once the input is valid.
     * @param pattern - for check the input.
     * @param userInput - for check the giving input is matches or not for check the input.
     * @return true for false once the input is valid.
     */
    public boolean userInputValidation(String pattern, String userInput) {
       return employeeService.userInputValidation(pattern,userInput);
    }

    /**
     * passing the employeeDTO and return the acknowledgement.
     *
     * @param employeeDTO - get an employeeDTO object for create operation
     * @return the acknowledgement
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, EmsException {
        return employeeService.createEmployeeDetails(employeeDTO);
    }

    /**
     * Asking all employeeDTO.
     *
     * @return list of all employeesDTO.
     */
    public List readEmployeeDetails() throws SQLException, EmsException {
         return employeeService.readEmployeeDetails();
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param employeeDTOName - transfer the String value.
     * @return the Employee DTO object
     */
    public List<EmployeeDTO> findEmployeeDetails(String employeeDTOName) throws SQLException, EmsException {
        return employeeService.findEmployeeDetails(employeeDTOName);
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param employeeId - transfer the String value.
     * @return the acknowledgement
     */
    public boolean deleteEmployeeDetails(int employeeId) throws SQLException, EmsException {
        return employeeService.deleteEmployeeDetails(employeeId);
    }

    /**
     * passing the employeeDTO and return the acknowledgement.
     *
     * @param employeeDTO - Getting the employeeDTO object
     * @return the acknowledgement
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, EmsException {
        return employeeService.updateEmployeeDetails(employeeDTO);
    }
}
