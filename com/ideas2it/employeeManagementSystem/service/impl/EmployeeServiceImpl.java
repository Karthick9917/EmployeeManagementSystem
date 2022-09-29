package com.ideas2it.employeeManagementSystem.service.impl;

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
     * Add the employeeDTO to array list
     *
     * @param employeeDTO - get an employeeDTO object for create operation
     * @return The acknowledgment
     */
    boolean createEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException;

    /**
     * get the all employeeDTO from the list
     *
     * @return list of all the employeeDTO
     */
    List readEmployeeDetails() throws SQLException;

    /**
     * it will return the employeeDTO based on employee name
     *
     * @param employeeDTO_Name - receive a String value
     * @return employeeDTO by employeeDTO name
     */
    List<EmployeeDTO> findEmployeeDetails(String employeeDTO_Name) throws SQLException;

    /**
     * it will delete the unique employeeDTO based on the employeeDTO id.
     *
     * @param employeeDTO_Id - receive a String value.
     * @return the acknowledgement once the operation is done
     */

    boolean deleteEmployeeDetails(int employeeDTO_Id) throws SQLException;

    /**
     * update the employeeDTO to array list
     *
     * @param employeeDTO - get an employeeDTO object for update operation
     * @return the acknowledgement once the operation is done.
     */
    boolean updateEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException;
}