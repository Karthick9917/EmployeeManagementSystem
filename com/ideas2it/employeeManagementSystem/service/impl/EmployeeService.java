package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.service.EmployeeManagement;

import java.util.ArrayList;
import java.util.List;

/*
 * Getting employee details from the controller and
 * once the operation is done.
 * it will return the acknowledgment to the controller
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeService implements EmployeeManagement {
    List<Employee> employeeList = new ArrayList<Employee>();

    /**
     * {@inheritDoc}
     */
    public boolean insertEmployeeDetails(Employee employee) {
        return employeeList.add(employee);
    }

    /**
     *{@inheritDoc}
     */
    public List showEmployeeDetails() {
        return employeeList;
    }

    /**
     * {@inheritDoc}
     */
    public Employee findEmployeeDetails(String employeeName) {
        Employee selectEmployee = null;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee searchEmployeeNameSearch = employeeList.get(i);
            if (searchEmployeeNameSearch.getName().substring(0, 3)
                    .equals(employeeName.substring(0, 3))) {
                selectEmployee = searchEmployeeNameSearch;
            }
        }
        return selectEmployee;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployeeDetails(String employeeId) {
        boolean isRemove = false;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee searchEmployeeId = employeeList.get(i);
            if (searchEmployeeId.getId().equals(employeeId)) {
                employeeList.remove(i);
                isRemove = true;
            }
        }
        return isRemove;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployeeDetails(Employee employee) {
        boolean isModify = false;

        for (int i = 0; i < employeeList.size(); i++) {
            Employee searchEmployeeId = employeeList.get(i);
            if (searchEmployeeId.getId().equals(employee.getId())) {
                employeeList.set(i, employee);
                isModify = true;
            }
        }
        return isModify;
    }
}
