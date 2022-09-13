package com.ideas2it.employeeManagementSystem.main;

import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.view.EmployeeView;

/*
 * This is the main class to perform employee management system.
 *
 *  *@version	1.8.0_281
 *  *@author	Karthick
 */
public class EmployeeManagementSystem {

    /**
     * main Method used to call the view class
     * for create, display , search, delete
     * and update employee details operations.
     */
    public static void main(String[] args) {
        EmployeeView view = new EmployeeView();
        System.out.println(EmployeeConstants.GREETINGS);
        view.viewEmployeeManagementSystem();
    }
}
