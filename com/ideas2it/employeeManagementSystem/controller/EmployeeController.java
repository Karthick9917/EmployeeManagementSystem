package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import com.ideas2it.employeeManagementSystem.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.service.impl.ProjectServiceImpl;

import java.time.LocalDate;
import java.util.List;

/*
 * It is passing the data between the two classes.
 * once the operation is done
 * returning the acknowledgement.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * Passing the pattern and user input, and it returns the true or
     * false once the input is valid.
     *
     * @param pattern - for check the input.
     * @param userInput - for check the input is matches or not
     * @return true for false once the input is valid.
     */
    public boolean userInputValidation(String pattern, String userInput) {
       return employeeService.userInputValidation(pattern,userInput);
    }

    /**
     * Passing the date, and it returns the true or false
     * once the date is valid.
     *
     * @param date - for check the date is valid or not
     * @return true for false once the input is valid.
     */
    public boolean getDate(String date) {
        return employeeService.validateDate(date);
    }

    /**
     * Passing the date of birth and date of joining, and it returns the true or false
     * based on the given date is valid or not
     * @param dateOfJoining - passing the date of joining for validate
     *                        the date of birth.
     * @param dateOfBirth - passing the date of birth input to be validated.
     * @return true for false once the input is valid.
     */
    public boolean getDateOfBirth(LocalDate dateOfJoining, LocalDate dateOfBirth) {
        return employeeService.getDateOfBirth(dateOfJoining, dateOfBirth);
    }

    /**
     * Passing the string and its return the true or false
     * based on the given String is valid or not
     * @param email - passing the given String for valid.
     * @return true for false once the input is valid.
     */
    public boolean validateEmail(String email) {
        return employeeService.validateEmail(email);
    }

    /**
     * Passing the string and its return the true or false
     * based on the given String is valid or not
     * @param phoneNumber    - passing the given String for valid.
     * @return true for false once the input is valid.
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * Passing the string and its return the true or false
     * based on the given String is valid or not
     * @param id    - passing the given String for valid.
     * @return true for false once the input is valid.
     */
    public boolean validateId(String id) {
        return employeeService.validateId(id);
    }

    /**
     * Passing the integer and its return the object
     * based on the given id.
     * @param employeeId - passing the given integer.
     * @return return the object based on the id.
     */
    public EmployeeDTO getEmployeeById (int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * Passing the integer and its return the object
     * based on the given id.
     * @param projectId - passing the given integer.
     * @return return the object based on the id.
     */
    public ProjectDTO getProjectById(int projectId) {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getProjectById(projectId);
    }

    /**
     * passing the employeeDTO and return the acknowledgement.
     *
     * @param employeeDTO - get an employeeDTO object for create operation.
     * @return the acknowledgement.
     * @throws EmsException
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) throws EmsException {
        return employeeService.createEmployeeDetails(employeeDTO);
    }

    /**
     * Asking all employeeDTO.
     *
     * @return list of all employeesDTO.
     * @throws EmsException
     */
    public List<EmployeeDTO> getAllEmployee() throws EmsException {
         return employeeService.getAllEmployee();
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param employeeName - transfer the String value.
     * @return the list of employeeDTO object.
     * @throws EmsException
     */
    public List<EmployeeDTO> getEmployeesByName(String employeeName) throws EmsException {
        return employeeService.getEmployeesByName(employeeName);
    }

    /**
     * passing the id value for delete operation.
     *
     * @param employeeId - transfer the integer value.
     * @throws EmsException
     */
    public void deleteEmployeeDetails(int employeeId) throws EmsException {
        employeeService.deleteEmployeeDetails(employeeId);
    }

    /**
     * passing the employeeDTO and return the acknowledgement.
     *
     * @param employeeDTO - Getting the employeeDTO object.
     * @throws EmsException
     */
    public void updateEmployeeDetails(EmployeeDTO employeeDTO ) throws EmsException {
        employeeService.updateEmployeeDetails(employeeDTO);
    }

    /**
     * pass the employee object for assigning projects for employee.
     * @param employeeDTO - passing a employeeDTO object.
     * @throws EmsException
     */
    public void assignProjectsForEmployee(EmployeeDTO employeeDTO) throws EmsException {
        employeeService.assignProjectsForEmployee(employeeDTO);
    }
}
