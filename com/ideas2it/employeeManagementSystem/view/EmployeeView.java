package com.ideas2it.employeeManagementSystem.view;

import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.controller.EmployeeController;
import com.ideas2it.employeeManagementSystem.employeeUtil.EmployeeUtil;
import com.ideas2it.employeeManagementSystem.model.Address;
import com.ideas2it.employeeManagementSystem.model.Employee;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 * EmployeeView class which represents the getting all inputs from user
 * and give the result once the operation is completed
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeView {

    int defaultEmployeeId = 100;
    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
    EmployeeUtil employeeUtil = new EmployeeUtil();

    /**
     * Employee management system used to
     * select an option to perform operations.
     */
    public void viewEmployeeManagementSystem () {
        int option = 0;

        do {
            try {
                System.out.println(EmployeeConstants.EMPLOYEE_MANAGEMENT_MENU);
                scanner = new Scanner(System.in);
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        this.createEmployeeDetails();
                        break;

                    case 2:
                        this.displayEmployeeDetails();
                        break;

                    case 3:
                        this.searchEmployeeDetails();
                        break;

                    case 4:
                        this.deleteEmployeeDetails();
                        break;

                    case 5:
                        this.updateEmployeeDetails();
                        break;

                    case 6:
                        System.exit(6);

                    default:
                        System.out.println(EmployeeConstants.SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(EmployeeConstants.INPUT_MISMATCH_EXCEPTION);
            }
        } while (option != 6);
    }

    /**
     * Getting an employee address details from user
     *
     * @return address object
     */

    private Address addAddress() {
        System.out.print(EmployeeConstants.HOUSE_NUMBER);
        int buildingNumber = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.STREET_NAME);
        String street = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.AREA_NAME);
        String area = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.CITY_NAME);
        String city = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.PINCODE);
        int pincode = employeeUtil.getEmployeeDetail();
        Address address = new Address(buildingNumber, street,
                area, city, pincode);
        return address;
    }

    /**
     * Getting an employee details from user and transfer for creation
     *
     * Employee id will automatically generate.
     */
    public void createEmployeeDetails() {
        String id = "I" + defaultEmployeeId++;
        System.out.println(EmployeeConstants.NAME);
        String name = employeeUtil.receiveEmployeeDetail();
        scanner.nextLine();
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = employeeUtil.obtainEmployeeDetails();
        System.out.println(EmployeeConstants.SALARY);
        int salary = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        String dateOfJoining = employeeUtil.receiveEmployeeDetail();
        Address address = addAddress();
        Employee employee = new Employee(id, name, phoneNumber, salary,
                dateOfJoining, address);

        if(employeeController.createEmployeeDetails(employee)) {
            System.out.println("Record successfully created by " + id);
        } else {
            System.out.println("create is failed !!!");
        }
    }

    /**
     * Getting the List of all employee details and display
     */
    public void displayEmployeeDetails() {
        List<Employee> employeeList = employeeController.displayEmployeeDetails();

        if (employeeList.size() != 0) {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } else {
            System.out.println(EmployeeConstants.ERROR_404);
        }
    }

    /**
     * display the employee details based on the employee name
     */
    public void searchEmployeeDetails() {
        System.out.println(EmployeeConstants.NAME);
        String name = employeeUtil.receiveEmployeeDetail();
        Employee findEmployee = employeeController.findEmployeeDetails(name);

        if (findEmployee!=null) {
            System.out.println(findEmployee);
        } else {
            System.out.println(EmployeeConstants.ERROR_404);
        }
    }

    /**
     * Delete the employee details based on the employee id.
     */
    public void deleteEmployeeDetails() {
        System.out.println(EmployeeConstants.ID);
        String id = employeeUtil.receiveEmployeeDetail();

        if (employeeController.deleteEmployeeDetails(id)) {
            System.out.println("Record deleted successfully..!!");
        } else {
            System.out.println(EmployeeConstants.ERROR_404);
        }
    }

    /**
     * Update the employee Details based on the employee id.
     */
    public void updateEmployeeDetails() {
        System.out.println(EmployeeConstants.ID);
        String id = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.NAME);
        String name = employeeUtil.receiveEmployeeDetail();
        scanner.nextLine();
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = employeeUtil.obtainEmployeeDetails();
        System.out.println(EmployeeConstants.SALARY);
        int salary = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        String dateOfJoining = employeeUtil.receiveEmployeeDetail();
        Address address = addAddress();
        Employee employee = new Employee(id, name, phoneNumber, salary, dateOfJoining, address);

        if (employeeController.updateEmployeeDetails(employee)) {
            System.out.println("Record updated successfully");
        } else {
            System.out.println("Record not update ");
        }
    }
}