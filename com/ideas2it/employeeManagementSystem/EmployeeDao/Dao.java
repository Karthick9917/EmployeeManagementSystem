package com.ideas2it.employeeManagementSystem.EmployeeDao;

import com.ideas2it.employeeManagementSystem.model.Employee;
import java.util.List;

/**
 * This interface to declare the abstract method
 * to employee EmployeeDao.
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
    boolean insertEmployeeDetails(Employee employee);

    /**
     * get the all employees from the list
     *
     * @return list of all the employees
     */
    List showEmployeeDetails();

    /**
     * it will return the employee details based on employee name
     *
     * @param employeeName - receive a String value from the service
     * @return employee details by employee name
     */
    Employee findEmployeeDetails(String employeeName);

    /**
     * it will delete the unique employee details based on the employee id.
     *
     * @param employeeId - receive a String value from the service
     * @return the acknowledgement once the operation is done
     */
    boolean deleteEmployeeDetails(String employeeId);

    /**
     * update the employee details to array list
     *
     * @param employee - get an employee object for update operation
     * @return the acknowledgement once the operation is done.
     */
    boolean updateEmployeeDetails(Employee employee);
}
