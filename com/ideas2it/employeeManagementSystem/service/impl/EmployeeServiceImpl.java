package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface to declare the abstract method
 * to EmployeeService
 *
 * @version 1.8.0_281
 * @author	Karthick
 */
public interface EmployeeServiceImpl {

    /**
     * return true or false based on whether the user Input is valid or not
     * @param pattern - check the user input based on the formatter.
     * @param userInput - user input to be validated.
     * @return true or false based on whether the user input is valid or not.
     */
    boolean userInputValidation(String pattern, String userInput);

    /**
     * Add the employeeDTO to array list
     *
     * @param employeeDTO - get an employeeDTO object for create operation.
     * @return The acknowledgment
     */
    boolean createEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, EmsException;

    /**
     * get the all employeeDTO from the list
     *
     * @return list of all the employeeDTO
     */
    List<EmployeeDTO> readEmployeeDetails() throws SQLException, EmsException;

    /**
     * it will return the employeeDTO based on employee name
     *
     * @param employeeDTO_Name - receive a String value
     * @return employeeDTO by employeeDTO name
     */
    List<EmployeeDTO> findEmployeeDetails(String employeeDTO_Name) throws SQLException, Exception;

    /**
     * it will delete the unique employeeDTO based on the employeeDTO id.
     *
     * @param employeeDTO_Id - receive a String value.
     * @return the acknowledgement once the operation is done
     */

    boolean deleteEmployeeDetails(int employeeDTO_Id) throws SQLException, EmsException;

    /**
     * update the employeeDTO to array list
     *
     * @param employeeDTO - get an employeeDTO object for update operation
     * @return the acknowledgement once the operation is done.
     */
    boolean updateEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException, EmsException;
}