package com.ideas2it.employeeManagementSystem.dao.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.model.Employee;

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
     * @throws EmsException
     */
    boolean createEmployeeDetails(Employee employee)throws EmsException;

    /**
     * get the all employees from the database
     *
     * @return list of all the employees
     * @throws EmsException
     *
     */
    List readEmployeeDetails() throws EmsException;

    /**
     * get all the employees from the database
     *
     * @throws EmsException
     */
    List searchEmployee(String firstName) throws EmsException;
    /**
     * it will delete the unique employee details based on the employee id.
     *
     * @param employee - receive a String value from the service
     * @return the acknowledgement once the operation is done
     * @throws EmsException
     */
    boolean deleteEmployeeDetails(int employee) throws EmsException;

    /**
     * update the employee details to array list
     *
     * @param employee - get an employee object for update operation
     * @return the acknowledgement once the operation is done.
     * @throws EmsException
     */
    boolean updateEmployeeDetails(Employee employee, int employeeId) throws EmsException;
}
