package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.model.Employee;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface to declare the abstract method
 * to EmployeeService
 *
 * @version 1.8.0_281
 * @author	Karthick
 */
public interface EmployeeService {

    /**
     * Return true or false based on the user Input is valid or not.
     * @param pattern - check the user input based on the formatter.
     * @param userInput - user input to be validated.
     * @return true or false based on the user input.
     */
    boolean userInputValidation(String pattern, String userInput);

    /**
     * Return true or false based on the date is valid or not.
     * @param date - passing the date input to be validated.
     * @return true or false based on the given date.
     * @throws EmsException
     */
    boolean validateDate(String date) throws EmsException;

    /**
     * Return true or false based on the date of birth is valid or not.
     * @param dateOfJoining - for the given date birth is valid or not.
     *
     * @param dateOfBirth - for the given date is valid or not.
     * @return true or false based on the given date of birth.
     * @throws EmsException
     */
    boolean getDateOfBirth(LocalDate dateOfJoining,
                                  LocalDate dateOfBirth) throws EmsException;

    /**
     * Return true or false based on the given string is valid or not.
     * @param email - passing the string for validate.
     * @return true or false based on the given email.
     */
    boolean validateEmail(String email);

    /**
     * Return true or false based on the given string is valid or not.
     * @param phoneNumber - passing the string for validate.
     * @return true or false based on the given phone number.
     */
    boolean validatePhoneNumber(String phoneNumber);

    /**
     * Return true or false based on the given string is valid or not.
     * @param id - passing the string for validate.
     * @return true or false based on the given id.
     */
    boolean validateId(String id);

    /**
     * Find out the particular object based on the given id.
     * @param employeeId - passing the integer.
     * @return the object based on the given id.
     */
    EmployeeDTO getEmployeeById(int employeeId);

    /**
     * passing the employeeDTO object to EmployeeDAO.
     *
     * @param employeeDTO - get an employeeDTO object for create operation.
     * @return The acknowledgment.
     * @throws EmsException
     */
    boolean createEmployeeDetails(EmployeeDTO employeeDTO) throws EmsException;

    /**
     * Passing all employeeDTO from the EmployeeDAO
     *
     * @return list of all the employeeDTO
     * @throws EmsException
     *
     */
    List<EmployeeDTO> readEmployeeDetails() throws EmsException;

    /**
     * it will return the employeeDTO based on employee name from the employeeDAO.
     *
     * @param employeeName - receive a String value
     * @return the list of employeeDTO.
     * @throws EmsException
     */
    List<Employee> findEmployeeDetails(String employeeName) throws EmsException;

    /**
     * pass the employee id for delete operation.
     *
     * @param employeeDTO_Id - receive a integer value.
     * @return the acknowledgement once the operation is done
     * @throws EmsException
     */
    void deleteEmployeeDetails(int employeeDTO_Id) throws EmsException;

    /**
     * pass the employee object to EmployeeDAO for update operation.
     *
     * @param employeeDTO - passing a employeeDTO object.
     * @return the acknowledgement once the operation is done.
     * @throws EmsException
     */
    void updateEmployeeDetails(EmployeeDTO employeeDTO) throws EmsException;
}