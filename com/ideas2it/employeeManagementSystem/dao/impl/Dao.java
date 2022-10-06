package com.ideas2it.employeeManagementSystem.dao.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface to declare the abstract method
 * to EmployeeDao.
 *
 * @version 1.8.0_281
 * @author	Karthick
 */
public interface Dao {

    /**
     * Add the employee details to array list
     *
     * @param employee - get an employee object for create operation
     * @return to acknowledge the controller class
     * @throws SQLException
     */
    boolean createEmployeeDetails(Employee employee)throws SQLException, EmsException;

    /**
     * get the all employees from the database
     *
     * @return list of all the employees
     * @throws SQLException
     *
     */
    List readEmployeeDetails() throws SQLException, EmsException;

    /**
     * get all the employees from the database
     *
     * @throws SQLException
     */
    List searchEmployee() throws SQLException, EmsException;
    /**
     * it will delete the unique employee details based on the employee id.
     *
     * @param employee - receive a String value from the service
     * @return the acknowledgement once the operation is done
     * @throws SQLException
     */
    boolean deleteEmployeeDetails(int employee) throws SQLException, EmsException;

    /**
     * update the employee details to array list
     *
     * @param employee - get an employee object for update operation
     * @return the acknowledgement once the operation is done.
     * @throws SQLException
     */
    boolean updateEmployeeDetails(Employee employee) throws SQLException, EmsException;
}
