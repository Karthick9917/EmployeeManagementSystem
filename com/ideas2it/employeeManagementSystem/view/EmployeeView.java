package com.ideas2it.employeeManagementSystem.view;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.controller.EmployeeController;
import com.ideas2it.employeeManagementSystem.dto.AddressDTO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.util.EmployeeUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                inputMismatchException.printStackTrace();
                System.out.println(EmployeeConstants.INPUT_MISMATCH_EXCEPTION);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
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
        int buildingNumber = Integer.parseInt(getUserInput(EmployeeConstants.
                DOOR_NUMBER_PATTERN,"door number..!!"));
        System.out.println(EmployeeConstants.STREET);
        String street = getUserInput(EmployeeConstants.
                STREET_PATTERN, "street name" +
                " eg: karaneeswarar koil 1st street");
        System.out.println(EmployeeConstants.CITY);
        String city = getUserInput(EmployeeConstants.
                ADDRESS_PATTERN, "city eg: chennai");
        System.out.println(EmployeeConstants.STATE);
        String state = getUserInput(EmployeeConstants.
                ADDRESS_PATTERN,"state eg: tamil nadu");
        System.out.println(EmployeeConstants.PINCODE);
        int pincode = Integer.parseInt(getUserInput(EmployeeConstants.
                PINCODE_PATTERN, "pincode eg:600004"));
        System.out.println(EmployeeConstants.ADDRESS_TYPE);
        String type = "";
        int choose = 0;
        do{
            try {
                System.out.println(EmployeeConstants.ADDRESS_TYPE_OPTION);
                scanner = new Scanner(System.in);
                choose = scanner.nextInt();
                switch (choose) {
                    case 1:
                        type = "permanent";
                        break;
                    case 2:
                        type = "temporary";
                        break;
                    default:
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(EmployeeConstants.SELECT_OPTION_ERROR
                        +EmployeeConstants.ADDRESS_TYPE_OPTION);
            }
        } while (!(choose > 0 && choose < 3 ));
        return new AddressDTO(buildingNumber, street, city,
                state, pincode, type);
    }

    /**
     * Getting the input from the user and check the input is valid or not.
     * @param pattern - Regex matcher pattern
     * @param errorMessage - if the input is not valid this error
     *                     message is display to the user.
     * @return the user input whether is it valid.
     */
    public String getUserInput(String pattern, String errorMessage) {
        boolean isValid = false;
        String userInput;
        do{
            scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if(!employeeController.userInputValidation(pattern, userInput)) {
                System.out.println(EmployeeConstants.ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return userInput;
    }

    /**
     * Getting the date from user and check the date is valid or not based
     * on the given format
     *
     * @return the parsed date after the date is valid.
     */
    public LocalDate getDate() {
        boolean isValid = false;
        String getDate;
        LocalDate parsedDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern
                (EmployeeConstants.DATE_FORMAT);
        do {
            try {
                scanner = new Scanner(System.in);
                getDate = scanner.next();
                parsedDate = LocalDate.parse(getDate, formatter);
                isValid=true;
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println(EmployeeConstants.ASKING_VALID_INPUT + "date(YYYY-MM-DD)");
            }
        } while (!isValid);
        return parsedDate;
    }

    /**
     * Getting an employee details from user and transfer for creation
     * Employee id will automatically generate.
     */
    public void createEmployeeDetails() throws SQLException {
        List<AddressDTO> listAddressDTO = new ArrayList<>();
        int id = defaultEmployeeId++;
        System.out.println(EmployeeConstants.FIRST_NAME);
        String firstName = getUserInput(EmployeeConstants.NAME_PATTERN,
                "first name (eg:karthick)");
        System.out.println(EmployeeConstants.LAST_NAME);
        String lastName = getUserInput(EmployeeConstants.
                LAST_NAME_PATTERN, "last name (eg:b or baskar)");
        System.out.println(EmployeeConstants.SALARY);
        Double salary = Double.parseDouble(getUserInput(EmployeeConstants.
                SALARY_PATTERN, "salary (eg: 34000.45)"));
        System.out.println(EmployeeConstants.DOB);
        LocalDate dateOfBirth =getDate();
        System.out.println(EmployeeConstants.GENDER);
        String gender = "";
        int choose = 0;
        do{
            try {
                System.out.println(EmployeeConstants.GENDER_OPTION);
                scanner = new Scanner(System.in);
                choose = scanner.nextInt();
                switch (choose) {
                    case 1:
                        gender = "male";
                        break;
                    case 2:
                        gender = "female";
                        break;
                    case 3:
                        gender = "others";
                        break;
                    default:
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(EmployeeConstants.SELECT_OPTION_ERROR
                        +EmployeeConstants.GENDER_OPTION);
            }
        } while (!(choose > 0 && choose < 4 ));
        System.out.println(EmployeeConstants.EMAIL);
        String email = getUserInput(EmployeeConstants.EMAIL_PATTERN, "email" +
                " eg: karthick17@gmail.com");
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = Long.parseLong(getUserInput(EmployeeConstants.
                PHONE_NUMBER_PATTERN,"phone number eg:7898765678"));
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        LocalDate dateOfJoining = getDate();
        System.out.println(EmployeeConstants.ADDRESS);
        AddressDTO addressDTO = addAddress();
        listAddressDTO.add(addressDTO);
        System.out.println(EmployeeConstants.ANOTHER_ADDRESS);
        String anotherAddress = getUserInput(EmployeeConstants.
                ANOTHER_ADDRESS_PATTERN, "input eg:y or n ");
        if (anotherAddress.equalsIgnoreCase("Y")) {
            AddressDTO tempAddressDTO = addAddress();
            listAddressDTO.add(tempAddressDTO);
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(id, firstName, lastName,
                dateOfBirth, salary, gender, email, phoneNumber,
                dateOfJoining, listAddressDTO);
        try {
            employeeController.createEmployeeDetails(employeeDTO);
            System.out.println(EmployeeConstants.SUCCESSFULL_MESSAGE + "created ");
        } catch (EmsException emsException) {
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Getting the List of all employee details and display
     */
    public void displayEmployeeDetails() throws SQLException {
        try {
            List<EmployeeDTO> employeesList = employeeController.
                    readEmployeeDetails();
            for (EmployeeDTO employeeDTO : employeesList) {
                System.out.println(employeeDTO);
            }
        } catch (EmsException emsException) {
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * display the employee details based on the employee name
     */
    public void searchEmployeeDetails() throws SQLException {
        System.out.println(EmployeeConstants.FIRST_NAME);
        String name = getUserInput(EmployeeConstants.NAME_PATTERN, "name eg: karthick");
        try {
            List<EmployeeDTO> searchEmployee = employeeController.
                    findEmployeeDetails(name);
            for (EmployeeDTO employeeDTO : searchEmployee) {
                System.out.println(employeeDTO);
                for (AddressDTO addressDTO : employeeDTO.getAddressDTO()) {
                    System.out.println(addressDTO);
                }
            }
        } catch (EmsException emsException) {
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Delete the employee details based on the employee id.
     */
    public void deleteEmployeeDetails() throws SQLException {
        System.out.println(EmployeeConstants.ID + "to delete");
        int id = Integer.parseInt(getUserInput(EmployeeConstants.
                EMPLOYEE_ID_PATTERN, "id eg: 1 or 12"));
        try {
            employeeController.deleteEmployeeDetails(id);
            System.out.println( EmployeeConstants.SUCCESSFULL_MESSAGE + "deleted");
        } catch (EmsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update the employee Details based on the employee id.
     */
    public void updateEmployeeDetails() throws SQLException {
        List<AddressDTO> listAddressDTO = new ArrayList<>();
        System.out.println(EmployeeConstants.ID + "to update ");
        int id = Integer.parseInt(getUserInput(EmployeeConstants.
                EMPLOYEE_ID_PATTERN, "id eg: 1 or 12"));
        System.out.println(EmployeeConstants.FIRST_NAME);
        String firstName = getUserInput(EmployeeConstants.NAME_PATTERN,
                "first name (eg:karthick)");
        System.out.println(EmployeeConstants.LAST_NAME);
        String lastName = getUserInput(EmployeeConstants.
                LAST_NAME_PATTERN, "last name (eg:b or baskar)");
        System.out.println(EmployeeConstants.DOB);
        LocalDate dateOfBirth = getDate();
        System.out.println(EmployeeConstants.SALARY);
        double salary = Double.parseDouble(getUserInput(EmployeeConstants.
                SALARY_PATTERN, "salary (eg: 34000.45)"));
        System.out.println(EmployeeConstants.GENDER);
        String gender = "";
        int choose = 0;
        do{
            try {
                System.out.println(EmployeeConstants.GENDER_OPTION);
                scanner = new Scanner(System.in);
                choose = scanner.nextInt();
                switch (choose) {
                    case 1:
                        gender = "male";
                        break;
                    case 2:
                        gender = "female";
                        break;
                    case 3:
                        gender = "others";
                        break;
                    default:
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(EmployeeConstants.SELECT_OPTION_ERROR
                        +EmployeeConstants.GENDER_OPTION);
            }
        } while (!(choose > 0 && choose < 4 ));
        System.out.println(EmployeeConstants.EMAIL);
        String email = getUserInput(EmployeeConstants.EMAIL_PATTERN, "email" +
                " eg: karthick17@gmail.com");
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = Long.parseLong(getUserInput(EmployeeConstants.
                PHONE_NUMBER_PATTERN,"phone number eg:7898765678"));
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        LocalDate dateOfJoining = getDate();
        System.out.println(EmployeeConstants.ADDRESS);
        AddressDTO addressDTO = addAddress();
        listAddressDTO.add(addressDTO);
        System.out.println(EmployeeConstants.ANOTHER_ADDRESS);
        String anotherAddress = getUserInput(EmployeeConstants.
                ANOTHER_ADDRESS_PATTERN, "input eg: y or n");
        if (anotherAddress.equalsIgnoreCase("Y")) {
            addressDTO = addAddress();
            listAddressDTO.add(addressDTO);
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(id, firstName, lastName,
                dateOfBirth, salary, gender, email, phoneNumber,
                dateOfJoining, listAddressDTO);
        try {
            employeeController.updateEmployeeDetails(employeeDTO);
            System.out.println( EmployeeConstants.SUCCESSFULL_MESSAGE + "updated");
        } catch (EmsException emsException) {
            System.out.println(emsException.getMessage());
        }
    }
}