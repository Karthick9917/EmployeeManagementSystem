package com.ideas2it.employeeManagementSystem.constants;

/**
 * Declaring all the constants
 * @version	1.8.0_281
 * @author	Karthick
 *
 */
public class EmployeeConstants {

    public static final String EMPLOYEE_MANAGEMENT_MENU = "<<< Main Menu >>>" +
            "\n-----------------" +
            "\n1.Add employee " +
            "\n2.Display employee " +
            "\n3.search employee details by name  " +
            "\n4.Delete employee details by id " +
            "\n5.update " +
            "\n6.exit " +
            "\nSelect your option : ";
    public static final String SELECT_OPTION_ERROR = " please Enter valid option !!! ";
    public static final String INPUT_MISMATCH_EXCEPTION = "Invalid data. " +
            "Please enter valid data.!";
    public static final String HOUSE_NUMBER = "Enter building number : ";
    public static final String STREET = "Enter street name : ";
    public static final String STATE = "Enter state : ";
    public static final String CITY= "Enter city : ";
    public static final String PINCODE = "Enter pincode number : ";
    public static final String FIRST_NAME = "Enter employee first name: ";
    public static final String LAST_NAME = "Enter employee last Name: ";
    public static final String DOB = "Enter employee date of birth (YYYY/MM/DD): ";
    public static final String EMAIL = "Enter employee email id :";
    public static final String GENDER_OPTION = "1.male \n 2.female \n 3.others ";
    public static final String GENDER = "Enter employee's gender: ";
    public static final String SALARY = "Enter employee salary: ";
    public static final String DATE_OF_JOINING = "Enter employee " +
            "DateOfJoining (YYYY/MM/DD): ";
    public static final String ASKING_VALID_INPUT = "Please enter the valid ";
    public static final String ADDRESS = "Enter employee address: ";
    public static final String ANOTHER_ADDRESS ="Do you want to add " +
            "another address (Y/N) ? ";
    public static final String ADDRESS_TYPE = "Enter the employee type";
    public static final String ADDRESS_TYPE_OPTION = "1.Permanent \n 2.Temporary ";
    public static final String ERROR_404 = " Record Not found...!";
    public static final String ID = "Enter employee id ";
    public static final String PHONE_NUMBER = "Enter employee phone number ";
    public static final String SUCCESSFULL_MESSAGE = "Record successfully ";
    public static final String NOT_ADDED_MESSAGE = "Record not added..!!";
    public static final String NOT_DELETED_MESSAGE = "Record not deleted..!!";
    public static final String NOT_UPDATED_MESSAGE = "Record not updated..!!";
    public static final String RECORD_EMPTY_MESSAGE = "Record is empty...!!";
    public static final String NOT_MATCHED_MESSAGE = "Record not matched..!!";
    public static final String NAME_PATTERN = "^[a-zA-Z]{3,}([ ]?" +
            "[a-zA-Z]{0,15}){0,}$";
    public static final String LAST_NAME_PATTERN = "^[a-zA-Z]{1,}([ ]? [a-zA-Z]{0,15}){0,}$";
    public static final String SALARY_PATTERN = "([0-9]{3,}([.][0-9]" +
            "{2}))|([0-9]{3,})";
    public static final String DATE_FORMAT = "yyyy-M-d";
    public static final String PHONE_NUMBER_PATTERN = "[6-9][0-9]{9}";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$";
    public static final String EMPLOYEE_ID_PATTERN = "[1-9]{1,}";
    public static final String PINCODE_PATTERN = "^([0-9]{6})$";
    public static final String ANOTHER_ADDRESS_PATTERN = "[a-zA-Z]{1}";
    public static final String DOOR_NUMBER_PATTERN = "([no|No|NO]?[#]?[A-Za-z]*[-/:]?[0-9]+[-/:]?[0-9])";
    public static final String ADDRESS_PATTERN= "^[a-zA-Z]{2,15}([ ]?[a-zA-Z]{0,15}){0,}$";
    public static final String STREET_PATTERN = "^[a-zA-Z0-9]{2,15}([ ]?[a-zA-Z0-9]{0,15}){0,}$";
    public static final String GREETINGS = "\n*** WELCOME TO EMPLOYEE" +
            " MANAGEMENT SYSTEM ***\n";
}
