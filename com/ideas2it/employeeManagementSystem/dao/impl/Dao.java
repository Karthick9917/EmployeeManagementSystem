package com.ideas2it.employeeManagementSystem.dao;

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
     */
    boolean createEmployeeDetails(Employee employee);

    /**
     * get the all employees from the list
     *
     * @return list of all the employees
     */
    List readEmployeeDetails();

    /**
     * it will delete the unique employee details based on the employee id.
     *
     * @param employee - receive a String value from the service
     * @return the acknowledgement once the operation is done
     */
    boolean deleteEmployeeDetails(Employee employee);

    /**
     * update the employee details to array list
     *
     * @param employee - get an employee object for update operation
     * @return the acknowledgement once the operation is done.
     */
    boolean updateEmployeeDetails(Employee employee);
}
