package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.EmployeeDao.impl.EmployeeDao;
import com.ideas2it.employeeManagementSystem.model.Employee;

import java.util.List;

/*
 * Getting employee details from the controller and
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */

public class EmployeeService {

    EmployeeDao employeeDao = new EmployeeDao();
    /**
     * Collect the employee from the controller and pass to the Dao
     *
     * @param employee - Getting the employee object
     * @return to acknowledge the view class
     */
    public boolean createEmployeeDetails(Employee employee) {
        return employeeDao.insertEmployeeDetails(employee);
    }

    /**
     * Display employee details
     *
     * @return list of all employees.
     */
    public List displayEmployeeDetails() {
        return employeeDao.showEmployeeDetails();
    }

    /**
     * Collect the employee name from the controller and pass to the Dao
     *
     * @param employeeName - transfer the String value to EmployeeDao
     * @return the object to controller
     */
    public Employee findEmployeeDetails(String employeeName) {
        return employeeDao.findEmployeeDetails(employeeName);
    }

    /**
     * Collect the employee name from the controller and pass to the EmployeeDao
     *
     * @param employeeId - transfer the String value to EmployeeDao
     * @return to acknowledge the controller class
     */
    public boolean deleteEmployeeDetails(String employeeId) {
        return employeeDao.deleteEmployeeDetails(employeeId);
    }

    /**
     * Collect the employee details from the controller and pass to the Dao
     *
     * @param employee - transfer the object to EmployeeDao
     * @return to acknowledge the controller
     */
    public boolean updateEmployeeDetails(Employee employee) {
        return employeeDao.updateEmployeeDetails(employee);
    }
}
