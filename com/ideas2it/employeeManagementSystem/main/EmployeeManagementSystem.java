package com.ideas2it.employeeManagementSystem.main;

import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.view.EmployeeView;
import com.ideas2it.employeeManagementSystem.view.ProjectView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

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

        Logger logger = LogManager.getLogger(EmployeeView.class.getName());
        System.out.println(Constants.GREETINGS);
        int choose = 0;
        do{
            try {
                System.out.println(Constants.OPTION);
                Scanner scanner = new Scanner(System.in);
                choose = scanner.nextInt();
                switch (choose) {
                    case 1:
                        EmployeeView employeeView = new EmployeeView();
                        System.out.println(Constants.EMPLOYEE_GREETINGS);
                        employeeView.manageEmployee();
                        break;
                    case 2:
                        ProjectView projectView = new ProjectView();
                        System.out.println(Constants.PROJECT_GREETINGS);
                        projectView.manageProject();
                        break;
                    default:
                        logger.warn("invalid data");
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.INPUT_MISMATCH_EXCEPTION);
            }
        } while (!(choose > 0 && choose < 3 ));
    }
}
