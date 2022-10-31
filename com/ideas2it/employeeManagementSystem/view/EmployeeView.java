package com.ideas2it.employeeManagementSystem.view;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.controller.EmployeeController;
import com.ideas2it.employeeManagementSystem.dto.AddressDTO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
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
    public void manageEmployee() {
        int option = 0;

        do {
            try {
                System.out.println(Constants.EMPLOYEE_MANAGEMENT_MENU);
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
                        this.assignProjectsForEmployee();
                        break;

                    case 7:
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
        } while (option != 7);
    }

    /**
     * Getting an employee address details from user
     *
     * @return address object
     */
    private AddressDTO addAddress() {
        AddressDTO addressDTO = new AddressDTO();
        System.out.print(Constants.HOUSE_NUMBER);
        String buildingNumber = getUserInput(Constants.
                DOOR_NUMBER_PATTERN,"door number..!!");
        addressDTO.setDoorNumber(buildingNumber);
        System.out.println(Constants.STREET);
        String street = getUserInput(Constants.
                STREET_PATTERN, "street name" +
                " eg: karaneeswarar koil 1st street");
        addressDTO.setStreet(street);
        System.out.println(Constants.CITY);
        String city = getUserInput(Constants.
                ADDRESS_PATTERN, "city eg: chennai");
        addressDTO.setCity(city);
        System.out.println(Constants.STATE);
        String state = getUserInput(Constants.
                ADDRESS_PATTERN,"state eg: tamil nadu");
        addressDTO.setState(state);
        System.out.println(Constants.PINCODE);
        int pincode = Integer.parseInt(getUserInput(Constants.
                PINCODE_PATTERN, "pincode eg:600004"));
        addressDTO.setPincode(pincode);
        System.out.println(Constants.ADDRESS_TYPE);
        String type = "";
        int choose = 0;
        do{
            try {
                System.out.println(Constants.ADDRESS_TYPE_OPTION);
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
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.SELECT_OPTION_ERROR);
            }
        } while (!(choose > 0 && choose < 3 ));
        addressDTO.setType(type);
        return addressDTO;
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
                System.out.println(Constants.
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
                System.out.println(Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else if (!employeeController.userInputValidation
                    (pattern, email)) {
                System.out.println(Constants.
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
                System.out.println(Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else if(!employeeController.userInputValidation(pattern, phoneNumber)) {
                System.out.println(Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return phoneNumber;
    }

    /**
     * Getting the input from the user and passing for the input is valid or not.
     * @param pattern - for check the given input is matches or not.
     * @param errorMessage - for Displaying the message
     *                       once the given input is invalid.
     * @return the string value once the given input is valid.
     */
    public String getId(String pattern, String errorMessage) {
        boolean isValid = false;
        String id;
        do{
            scanner = new Scanner(System.in);
            id = scanner.nextLine();
            if (!employeeController.userInputValidation(pattern, id)) {
                System.out.println(Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else if(!employeeController.validateId(id)){
                System.out.println(Constants.ERROR_404 + "\n" + Constants.
                        ASKING_VALID_INPUT + errorMessage);
            } else {
                isValid = true;
            }
        } while (!isValid);
        return id;
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
                    System.out.println(Constants.
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
        EmployeeDTO employeeDTO = new EmployeeDTO();
        System.out.println(Constants.FIRST_NAME);
        String firstName = getUserInput(Constants.NAME_PATTERN,
                "first name (eg:karthick)");
        employeeDTO.setFirstName(firstName);
        System.out.println(Constants.LAST_NAME);
        String lastName = getUserInput(Constants.
                LAST_NAME_PATTERN, "last name (eg:b or baskar)");
        employeeDTO.setLastName(lastName);
        System.out.println(Constants.SALARY);
        Double salary = Double.parseDouble(getUserInput(Constants.
                SALARY_PATTERN, "salary (eg: 34000.45)"));
        employeeDTO.setSalary(salary);
        System.out.println(Constants.DATE_OF_JOINING);
        LocalDate dateOfJoining = getDate();
        employeeDTO.setDateOfJoining(dateOfJoining);
        System.out.println(Constants.DOB);
        LocalDate dateOfBirth = getDateOfBirth(dateOfJoining);
        employeeDTO.setDateOfBirth(dateOfBirth);
        System.out.println(Constants.GENDER);
        String gender = "";
        int choose = 0;
        do{
            try {
                System.out.println(Constants.GENDER_OPTION);
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
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.SELECT_OPTION_ERROR);
            }
        } while (!(choose > 0 && choose < 4 ));
        employeeDTO.setGender(gender);
        System.out.println(Constants.EMAIL);
        String email = getEmail(Constants.EMAIL_PATTERN, "email" +
                " eg: karthick17@gmail.com");
        System.out.println(Constants.PHONE_NUMBER);
        employeeDTO.setEmail(email);
        long phoneNumber = Long.parseLong(getPhoneNumber(Constants.
                PHONE_NUMBER_PATTERN,"phone number eg:7898765678"));
        employeeDTO.setPhoneNumber(phoneNumber);
        System.out.println(Constants.ROLE);
        String role = "";
        int select = 0;
        do{
            try {
                System.out.println(Constants.ROLE_OPTION);
                scanner = new Scanner(System.in);
                select = scanner.nextInt();
                switch (select) {
                    case 1:
                        role = "trainer";
                        break;
                    case 2:
                        role = "trainee";
                        break;
                    default:
                        logger.warn("invalid data");
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.SELECT_OPTION_ERROR);
            }
        } while (!(select > 0 && select < 3 ));
        employeeDTO.setRole(role);
        System.out.println(Constants.ADDRESS);
        AddressDTO addressDTO = addAddress();
        listAddressDTO.add(addressDTO);
        System.out.println(Constants.ADD_ANOTHER_ADDRESS);
        String anotherAddress = getUserInput(Constants.
                ANOTHER_ADDRESS_PATTERN, "input eg:y or n ");
        if (anotherAddress.equalsIgnoreCase("Y")) {
            AddressDTO tempAddressDTO = addAddress();
            listAddressDTO.add(tempAddressDTO);
        }
        employeeDTO.setAddressDTO(listAddressDTO);
        try {
            if (employeeController.createEmployeeDetails(employeeDTO)) {
                System.out.println(Constants.
                        SUCCESSFUL_MESSAGE + "created ");
            } else {
                System.out.println(Constants.FAILED_TO_ADD);
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
                    getAllEmployee();
            if (employeesList.isEmpty()) {
                logger.warn("No records in database");
                System.out.println(Constants.ERROR_404);
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
        System.out.println(Constants.FIRST_NAME);
        String name = getUserInput(Constants.
                NAME_PATTERN, "name eg: karthick");
        try {
            List<EmployeeDTO> searchEmployee = employeeController.
                    getEmployeesByName(name);
            if (!searchEmployee.isEmpty()) {
                for (EmployeeDTO employeeDTO : searchEmployee) {
                    System.out.println(employeeDTO);
                }
            } else {
                logger.warn("no records");
                System.out.println(Constants.ERROR_404);
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
        System.out.println(Constants.EMPLOYEE_ID + "to delete");
        int id =  Integer.parseInt(getId(Constants.
                ID_PATTERN, "id eg: 1 or 12"));
        try {
            employeeController.deleteEmployeeDetails(id);
            logger.info("Employee " + id + "has been removed successfully");
            System.out.println(Constants.
                    SUCCESSFUL_MESSAGE + "deleted");
        } catch (EmsException e) {
            logger.error("empty record..!!");
            System.out.println(Constants.ERROR_404);
        }
    }

    /**
     * Update the employee Details based on the employee id.
     */
    public void updateEmployeeDetails() {
        System.out.println(Constants.EMPLOYEE_ID + "to update ");
        int id = Integer.parseInt(getId(Constants.
                ID_PATTERN, "id eg: 1 or 12"));
        EmployeeDTO employeeDTO = employeeController.getEmployeeById(id);
        employeeDTO.setId(id);
        int option = 0;
        do {
            try {
                System.out.println(Constants.UPDATE_EMPLOYEE_MENU);
                scanner = new Scanner(System.in);
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println(Constants.FIRST_NAME);
                        employeeDTO.setFirstName(getUserInput(Constants
                                .NAME_PATTERN,"first name (eg:karthick)"));
                        break;
                    case 2:
                        System.out.println(Constants.LAST_NAME);
                        employeeDTO.setLastName(getUserInput(Constants
                                .LAST_NAME_PATTERN, "last name (eg:b or baskar)"));
                        break;
                    case 3:
                        System.out.println(Constants.DATE_OF_JOINING);
                        employeeDTO.setDateOfJoining(getDate());
                        break;
                    case 4:
                        System.out.println(Constants.DOB);
                        LocalDate dateOfBirth = getDateOfBirth(employeeDTO.getDateOfJoining());
                        employeeDTO.setDateOfBirth(dateOfBirth);
                        break;
                    case 5:
                        System.out.println(Constants.SALARY);
                        employeeDTO.setSalary(Double
                                .parseDouble(getUserInput(Constants
                                .SALARY_PATTERN, "salary (eg: 34000.45)")));
                        break;
                    case 6:
                        employeeDTO.setGender(getGender());
                        break;
                    case 7:
                        System.out.println(Constants.EMAIL);
                        employeeDTO.setEmail(getEmail(Constants
                                .EMAIL_PATTERN, "email eg: karthick17@gmail.com"));
                        break;
                    case 8:
                        System.out.println(Constants.PHONE_NUMBER);
                        employeeDTO.setPhoneNumber(Long
                                .parseLong(getPhoneNumber(Constants
                                        .PHONE_NUMBER_PATTERN,"phone number" +
                                        " eg:7898765678")));
                        break;
                    case 9:
                        employeeDTO.setRole(getRole());
                        break;
                    case 10:
                        employeeDTO.setAddressDTO(updateAddress(employeeDTO));
                        break;
                    case 11:
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
        } while (option != 11);
        try {
            employeeController.updateEmployeeDetails(employeeDTO);
                logger.info("Employee " + id + "has been updated successfully");
                System.out.println(Constants.
                        SUCCESSFUL_MESSAGE + "updated");
        } catch (EmsException emsException) {
            logger.error("database not connected..!!");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * Getting the updated address object and
     * set to the concerned employee address type.
     * @param employeeDTO - passing the object for update the address.
     * @return the list of address object
     */
    public List<AddressDTO> updateAddress(EmployeeDTO employeeDTO) {
        List<AddressDTO> addressDTOList = new ArrayList<AddressDTO>();
        boolean isUpdateAnotherAddress;
        do {
            isUpdateAnotherAddress = false;
            System.out.println(Constants.UPDATE_ADDRESS_TYPE);
            String type = "";
            int choose = 0;
            do {
                try {
                    System.out.println(Constants.ADDRESS_TYPE_OPTION);
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
                            System.out.println(Constants.
                                    SELECT_OPTION_ERROR);
                    }
                } catch (InputMismatchException inputMismatchException) {
                    logger.error("input mismatch");
                    System.out.println(Constants.SELECT_OPTION_ERROR);
                }
            } while (!(choose > 0 && choose < 3));
            Boolean checkAddress = false;
            for (AddressDTO addressDto : employeeDTO.getAddressDTO()) {
                if (addressDto.getType().equals(type)) {
                    addressDTOList.add(getAddress(addressDto));
                    checkAddress = true;
                }
            }
            if (!checkAddress) {
                System.out.println(type + " address is not there for update...!!");
                System.out.println("Do you want to add " + type + " address (Y/N) ?");
                String confirmation = getUserInput(Constants.
                        ANOTHER_ADDRESS_PATTERN, "input eg: y or n");
                if (confirmation.equalsIgnoreCase("Y")) {
                    addressDTOList.add(addAddress());
                }
            }
            System.out.println("Do you want to continue to Update the address (Y/N) ?");
            String confirmation = getUserInput(Constants.
                    ANOTHER_ADDRESS_PATTERN, "input eg: y or n");
            if (confirmation.equalsIgnoreCase("Y")) {
                isUpdateAnotherAddress = true;
            }
        } while (isUpdateAnotherAddress);
        return addressDTOList;
    }

    /**
     * pass the project object for assigning project to employee.
     */
    private void assignProjectsForEmployee() {
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        System.out.println(Constants.EMPLOYEE_ID + "to assign projects");
        int id =  Integer.parseInt(getId(Constants.
                ID_PATTERN, "id eg: 1 or 12"));
        EmployeeDTO employeeDTO = employeeController.getEmployeeById(id);
        if (employeeDTO.getProjectDTO() != null) {
            projectDTOList.addAll(employeeDTO.getProjectDTO());
        }
        System.out.println("How Many projects to Assign for this employee");
        int projectCount = Integer.parseInt(getUserInput(Constants
                .ID_PATTERN,"count of project"));
        for (int count = 0; count < projectCount; count++) {
            projectDTOList.add(getProject());
        }
        employeeDTO.setProjectDTO(projectDTOList);
        try {
            employeeController.assignProjectsForEmployee(employeeDTO);
            logger.info("Project " + id + "has been updated successfully");
            System.out.println(Constants
                    .SUCCESSFUL_MESSAGE + "updated");
        } catch (EmsException emsException) {
            logger.error("database not connected..!!");
            System.out.println(emsException.getMessage());
        }
    }

    /**
     * To get a project dto object based on project id.
     * @return projectDTO object
     */
    public ProjectDTO getProject() {
        ProjectDTO projectDTO = null;
        System.out.println(Constants.PROJECT_ID + " to Assign");
        int projectId = Integer.parseInt(getUserInput(Constants
                .ID_PATTERN,"project id"));
        try {
            projectDTO = employeeController.getProjectById(projectId);
            if (projectDTO == null) {
                System.out.println(Constants.ERROR_404);
                getProject();
            }
        } catch (EmsException e) {
            logger.error("empty record..!!");
            System.out.println(Constants.ERROR_404);
        }
        return projectDTO;
    }

    /**
     * Get the gender of the employee from the user.
     * @return the string value of the role.
     */
    public String getGender() {
        System.out.println(Constants.GENDER);
        String gender = "";
        int choose = 0;
        do{
            try {
                System.out.println(Constants.GENDER_OPTION);
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
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.SELECT_OPTION_ERROR);
            }
        } while (!(choose > 0 && choose < 4 ));
        return gender;
    }

    /**
     * Get the role of the employee from the user.
     *
     * @return the string value of the role.
     */
    public String getRole() {
        System.out.println(Constants.ROLE);
        String role = "";
        int select = 0;
        do{
            try {
                System.out.println(Constants.ROLE_OPTION);
                scanner = new Scanner(System.in);
                select = scanner.nextInt();
                switch (select) {
                    case 1:
                        role = "trainer";
                        break;
                    case 2:
                        role = "trainee";
                        break;
                    default:
                        logger.warn("invalid data");
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.SELECT_OPTION_ERROR);
            }
        } while (!(select > 0 && select < 3 ));
        return role;
    }

    /**
     * Get the address of the employee from the user for update
     * @param addressDTO - passing the object for update.
     * @return the updated object
     */
    private AddressDTO getAddress(AddressDTO addressDTO) {
        System.out.print(Constants.HOUSE_NUMBER);
        String buildingNumber = getUserInput(Constants.
                DOOR_NUMBER_PATTERN,"door number..!!");
        addressDTO.setDoorNumber(buildingNumber);
        System.out.println(Constants.STREET);
        String street = getUserInput(Constants.
                STREET_PATTERN, "street name" +
                " eg: karaneeswarar koil 1st street");
        addressDTO.setStreet(street);
        System.out.println(Constants.CITY);
        String city = getUserInput(Constants.
                ADDRESS_PATTERN, "city eg: chennai");
        addressDTO.setCity(city);
        System.out.println(Constants.STATE);
        String state = getUserInput(Constants.
                ADDRESS_PATTERN,"state eg: tamil nadu");
        addressDTO.setState(state);
        System.out.println(Constants.PINCODE);
        int pincode = Integer.parseInt(getUserInput(Constants.
                PINCODE_PATTERN, "pincode eg:600004"));
        addressDTO.setPincode(pincode);
        System.out.println(Constants.ADDRESS_TYPE);
        String type = "";
        int choose = 0;
        do{
            try {
                System.out.println(Constants.ADDRESS_TYPE_OPTION);
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
                        System.out.println(Constants.
                                SELECT_OPTION_ERROR);
                }
            } catch (InputMismatchException inputMismatchException) {
                logger.error("input mismatch");
                System.out.println(Constants.SELECT_OPTION_ERROR);
            }
        } while (!(choose > 0 && choose < 3 ));
        addressDTO.setType(type);
        return addressDTO;
    }
}