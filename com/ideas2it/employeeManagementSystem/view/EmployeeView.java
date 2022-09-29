package com.ideas2it.employeeManagementSystem.view;

import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.controller.EmployeeController;
import com.ideas2it.employeeManagementSystem.dto.AddressDTO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.util.EmployeeUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
 * This class which represents the getting all inputs from user
 * and give the result once the operation is completed
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeView {

    private int defaultEmployeeId = 100;
    Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    private EmployeeUtil employeeUtil = new EmployeeUtil();

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
            } catch (InputMismatchException | SQLException inputMismatchException) {
                System.out.println(EmployeeConstants.INPUT_MISMATCH_EXCEPTION);
            }
        } while (option != 6);
    }

    /**
     * Getting an employee address details from user
     *
     * @return address object
     */

    private AddressDTO addAddress() {
        System.out.print(EmployeeConstants.HOUSE_NUMBER);
        int buildingNumber = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.STREET);
        String street = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.CITY);
        String city = employeeUtil.receiveEmployeeDetail();
        scanner.nextLine();
        System.out.println(EmployeeConstants.STATE);
        String state = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.PINCODE);
        int pincode = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.ADDRESS_TYPE);
        String type = employeeUtil.receiveEmployeeDetail();
        AddressDTO addressDTO = new AddressDTO(buildingNumber, street, city,
                state, pincode, type);
        return addressDTO;
    }

    /**
     * Getting an employee details from user and transfer for creation
     *
     * Employee id will automatically generate.
     */
    public void createEmployeeDetails() throws SQLException {
        List<AddressDTO> listAddressDTO = new ArrayList<>();
        int id = defaultEmployeeId++;
        System.out.println(EmployeeConstants.FIRST_NAME);
        String firstName = employeeUtil.receiveEmployeeDetail();
        scanner.nextLine();
        System.out.println(EmployeeConstants.LAST_NAME);
        String lastName = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.DOB);
        String dateOfBirth = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.SALARY);
        int salary = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.GENDER);
        String gender = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.EMAIL);
        String email = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = employeeUtil.obtainEmployeeDetails();
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        String dateOfJoining = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.ADDRESS);
        AddressDTO addressDTO = addAddress();
        listAddressDTO.add(addressDTO);
        System.out.println(EmployeeConstants.ANOTHER_ADDRESS);
        String anotherAddress = employeeUtil.receiveEmployeeDetail();
        if (anotherAddress.equalsIgnoreCase("Y")) {
            addressDTO = addAddress();
            listAddressDTO.add(addressDTO);
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(id, firstName, lastName,
                dateOfBirth, salary, gender, email, phoneNumber,
                dateOfJoining, listAddressDTO);
        if (employeeController.createEmployeeDetails(employeeDTO)) {
            System.out.println("Record successfully created..!!!" );
        } else {
            System.out.println("create is failed !!!");
        }
    }

    /**
     * Getting the List of all employee details and display
     */
    public void displayEmployeeDetails() throws SQLException {
        List<EmployeeDTO> employeesList = employeeController.readEmployeeDetails();
        if (employeesList.size() != 0) {
            for (EmployeeDTO employeeDTO : employeesList) {
                System.out.println(employeeDTO);
            }
        } else {
            System.out.println(EmployeeConstants.ERROR_404);
        }
    }

    /**
     * display the employee details based on the employee name
     */
    public void searchEmployeeDetails() throws SQLException {
        System.out.println(EmployeeConstants.FIRST_NAME);
        String name = employeeUtil.receiveEmployeeDetail();
        List<EmployeeDTO> searchEmployee = employeeController.findEmployeeDetails(name);
        for(int i = 0; i <searchEmployee.size(); i++) {
            System.out.println(searchEmployee);
        }
    }

    /**
     * Delete the employee details based on the employee id.
     */
    public void deleteEmployeeDetails() throws SQLException {
        System.out.println(EmployeeConstants.ID + "to delete");
        int id = employeeUtil.getEmployeeDetail();

        if (employeeController.deleteEmployeeDetails(id)) {
            System.out.println("Record deleted successfully..!!");
        } else {
            System.out.println(EmployeeConstants.ERROR_404);
        }
    }

    /**
     * Update the employee Details based on the employee id.
     */
    public void updateEmployeeDetails() throws SQLException {
        List<AddressDTO> listAddressDTO = new ArrayList<>();
        System.out.println(EmployeeConstants.ID + "to update ");
        int id = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.FIRST_NAME);
        String firstName = employeeUtil.receiveEmployeeDetail();
        scanner.nextLine();
        System.out.println(EmployeeConstants.LAST_NAME);
        String lastName = employeeUtil.receiveEmployeeDetail();
        scanner.nextLine();
        System.out.println(EmployeeConstants.DOB);
        String dateOfBirth = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.SALARY);
        int salary = employeeUtil.getEmployeeDetail();
        System.out.println(EmployeeConstants.GENDER);
        String gender = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.EMAIL);
        String email = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = employeeUtil.obtainEmployeeDetails();
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        String dateOfJoining = employeeUtil.receiveEmployeeDetail();
        System.out.println(EmployeeConstants.ADDRESS);
        AddressDTO addressDTO = addAddress();
        listAddressDTO.add(addressDTO);
        System.out.println(EmployeeConstants.ANOTHER_ADDRESS);
        String anotherAddress = employeeUtil.receiveEmployeeDetail();
        if (anotherAddress.equalsIgnoreCase("Y")) {
            addressDTO = addAddress();
            listAddressDTO.add(addressDTO);
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(id, firstName, lastName,
                dateOfBirth, salary, gender, email, phoneNumber,
                dateOfJoining, listAddressDTO);

        if (employeeController.updateEmployeeDetails(employeeDTO)) {
            System.out.println("Record updated successfully");
        } else {
            System.out.println("Record not update ");
        }
    }
}