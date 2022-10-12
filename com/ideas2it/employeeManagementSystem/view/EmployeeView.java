package com.ideas2it.employeeManagementSystem.view;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.controller.EmployeeController;
import com.ideas2it.employeeManagementSystem.dto.AddressDTO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
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

    private static Logger logger = LogManager.getLogger(EmployeeView.class.getName());

    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();

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
                        logger.warn("invalid data");
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
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
        String buildingNumber = getUserInput(EmployeeConstants.
                DOOR_NUMBER_PATTERN,"door number..!!");
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
                        logger.warn("invalid data");
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
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
     *                       message is display to the user.
     * @return the user input whether is it valid.
     */
    public String getUserInput(String pattern, String errorMessage) {
        boolean isValid = false;
        String userInput;
        do{
            scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if(!employeeController.userInputValidation(pattern, userInput)) {
                System.out.println(EmployeeConstants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return userInput;
    }

    /**
     * Getting the input from the user and passing for the input is valid or not.
     * @param pattern - for check the given input is matches or not.
     * @param errorMessage - for Displaying the message
     *                       once the given input is invalid.
     * @return the string value once the given input is valid.
     */
    public String getEmail(String pattern, String errorMessage) {
        boolean isValid = false;
        String email;
        do {
            scanner = new Scanner(System.in);
            email = scanner.nextLine();
            if (employeeController.validateEmail(email)) {
                System.out.println("Given Email " +
                        "is already exist..!!");
                System.out.println(EmployeeConstants.
                        ASKING_VALID_INPUT + errorMessage);
            } else if (!employeeController.userInputValidation
                    (pattern, email)) {
                System.out.println(EmployeeConstants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return email;
    }

    /**
     * Getting the input from the user and passing for the input is valid or not.
     * @param pattern - for check the given input is matches or not.
     * @param errorMessage - for Displaying the message
     *                       once the given input is invalid.
     * @return the string value once the given input is valid.
     */
    public String getPhoneNumber(String pattern, String errorMessage) {
        boolean isValid = false;
        String phoneNumber;
        do{
            scanner = new Scanner(System.in);
            phoneNumber = scanner.nextLine();
            if (employeeController.validatePhoneNumber(phoneNumber)) {
                System.out.println("Given phone number " +
                        "is already exist..!!");
                System.out.println(EmployeeConstants.
                        ASKING_VALID_INPUT + errorMessage);
            } else if(!employeeController.userInputValidation(pattern, phoneNumber)) {
                System.out.println(EmployeeConstants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return phoneNumber;
    }

    /**
     * Getting the date from user and check the date is valid or not based
     * on the given format
     *
     * @return the parsed date after the date is valid.
     */
    public LocalDate getDate() {
        boolean isValid = false;
        String date = null;
        do {
            try {
                scanner = new Scanner(System.in);
                date = scanner.next();
                if(employeeController.getDate(date)){
                    System.out.println(EmployeeConstants.
                            ASKING_VALID_INPUT + "date(YYYY-MM-DD)");
                } else {
                    isValid = true;
                }
            } catch (EmsException emsException) {
                logger.error("date format mismatch");
                System.out.println(emsException.getMessage());
            }
        } while (!isValid);
        return LocalDate.parse(date);
    }

    /**
     * Getting the joining date from user and check the employee is eligible
     * or not eligible to work
     * @param dateOfJoining - for the employee is eligible or not
     * @return the parsed date after the date is valid.
     */
    public LocalDate getDateOfBirth(LocalDate dateOfJoining) {
        boolean isValid = false;
        LocalDate dateOfBirth;
        do {
            dateOfBirth = getDate();
            if (employeeController.getDateOfBirth(dateOfJoining, dateOfBirth)) {
                System.out.println("Not an eligible employee please " +
                        "enter the correct date of birth...!!");
            } else {
                isValid = true;
            }
        } while (!isValid);
        return dateOfBirth;
    }

    /**
     * Getting an employee details from user and transfer for creation
     * Employee id will automatically generate.
     */
    public void createEmployeeDetails() {
        List<AddressDTO> listAddressDTO = new ArrayList<>();
        System.out.println(EmployeeConstants.FIRST_NAME);
        String firstName = getUserInput(EmployeeConstants.NAME_PATTERN,
                "first name (eg:karthick)");
        System.out.println(EmployeeConstants.LAST_NAME);
        String lastName = getUserInput(EmployeeConstants.
                LAST_NAME_PATTERN, "last name (eg:b or baskar)");
        System.out.println(EmployeeConstants.SALARY);
        Double salary = Double.parseDouble(getUserInput(EmployeeConstants.
                SALARY_PATTERN, "salary (eg: 34000.45)"));
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        LocalDate dateOfJoining = getDate();
        System.out.println(EmployeeConstants.DOB);
        LocalDate dateOfBirth = getDateOfBirth(dateOfJoining);
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
                        logger.warn("invalid data");
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(EmployeeConstants.SELECT_OPTION_ERROR
                        +EmployeeConstants.GENDER_OPTION);
            }
        } while (!(choose > 0 && choose < 4 ));
        System.out.println(EmployeeConstants.EMAIL);
        String email = getEmail(EmployeeConstants.EMAIL_PATTERN, "email" +
                " eg: karthick17@gmail.com");
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = Long.parseLong(getPhoneNumber(EmployeeConstants.
                PHONE_NUMBER_PATTERN,"phone number eg:7898765678"));
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
        EmployeeDTO employeeDTO = new EmployeeDTO(firstName, lastName,
                dateOfBirth, salary, gender, email, phoneNumber,
                dateOfJoining, listAddressDTO);
        try {
            if (employeeController.createEmployeeDetails(employeeDTO)) {
                System.out.println(EmployeeConstants.
                        SUCCESSFULL_MESSAGE + "created ");
            } else {
                System.out.println(EmployeeConstants.NOT_ADDED_MESSAGE);
            }
        } catch (EmsException emsException) {
            logger.error("Database not connected");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Getting the List of all employee details and display
     */
    public void displayEmployeeDetails() {
        try {
            List<EmployeeDTO> employeesList = employeeController.
                    readEmployeeDetails();
            if (employeesList.isEmpty()) {
                logger.warn("No records in database");
                System.out.println(EmployeeConstants.RECORD_EMPTY_MESSAGE);
            } else {
                for (EmployeeDTO employeeDTO : employeesList) {
                    System.out.println(employeeDTO);
                }
                logger.info("Employee displayed");
            }
        } catch (EmsException emsException) {
            logger.error("Database not connected");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * display the employee details based on the employee name
     */
    public void searchEmployeeDetails() {
        System.out.println(EmployeeConstants.FIRST_NAME);
        String name = getUserInput(EmployeeConstants.
                NAME_PATTERN, "name eg: karthick");
        try {
            List<Employee> searchEmployee = employeeController.
                    findEmployeeDetails(name);
            if (!searchEmployee.isEmpty()) {
                for (Employee employee : searchEmployee) {
                    System.out.println(employee);
                }
            } else {
                logger.warn("no records");
                System.out.println(EmployeeConstants.RECORD_EMPTY_MESSAGE);
            }
        } catch (EmsException emsException) {
            logger.error("empty records...!!");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Delete the employee details based on the employee id.
     */
    public void deleteEmployeeDetails() {
        System.out.println(EmployeeConstants.ID + "to delete");
        int id = Integer.parseInt(getUserInput(EmployeeConstants.
                EMPLOYEE_ID_PATTERN, "id eg: 1 or 12"));
        try {
            if (!employeeController.deleteEmployeeDetails(id)) {
                logger.warn("record not found..!! ");
                System.out.println(EmployeeConstants.ERROR_404);
            } else {
                System.out.println(EmployeeConstants.
                        SUCCESSFULL_MESSAGE + "deleted");
            }
        } catch (EmsException e) {
            logger.error("empty record..!!");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update the employee Details based on the employee id.
     */
    public void updateEmployeeDetails() {
        EmployeeDTO employeeDTO = null;
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
        System.out.println(EmployeeConstants.DATE_OF_JOINING);
        LocalDate dateOfJoining = getDate();
        System.out.println(EmployeeConstants.DOB);
        LocalDate dateOfBirth = getDateOfBirth(dateOfJoining);
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
                        logger.warn("invalid data");
                        System.out.println(EmployeeConstants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(EmployeeConstants.SELECT_OPTION_ERROR
                        +EmployeeConstants.GENDER_OPTION);
            }
        } while (!(choose > 0 && choose < 4 ));
        System.out.println(EmployeeConstants.EMAIL);
        String email = getEmail(EmployeeConstants.EMAIL_PATTERN, "email" +
                " eg: karthick17@gmail.com");
        System.out.println(EmployeeConstants.PHONE_NUMBER);
        long phoneNumber = Long.parseLong(getPhoneNumber(EmployeeConstants.
                PHONE_NUMBER_PATTERN,"phone number eg:7898765678"));
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
        employeeDTO = new EmployeeDTO(firstName, lastName,
                dateOfBirth, salary, gender, email, phoneNumber,
                dateOfJoining, listAddressDTO);
        try {
            if (!employeeController.updateEmployeeDetails(employeeDTO, id)) {
                System.out.println(EmployeeConstants.
                        SUCCESSFULL_MESSAGE + "updated");
            } else {
                System.out.println(EmployeeConstants.NOT_UPDATED_MESSAGE);
            }
        } catch (EmsException emsException) {
            logger.error("database not connected..!!");
            System.out.println(emsException.getMessage());
        }
    }
}