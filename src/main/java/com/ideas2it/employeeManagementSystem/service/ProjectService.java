package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;

import java.util.List;

/**
 * This interface to declare the abstract method
 * to EmployeeService
 *
 * @version 1.8.0_281
 * @author	Karthick
 */
public interface ProjectService {

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
     * Find out the particular object based on the given id.
     * @param id - passing the integer.
     * @return the object based on the given id.
     */
    ProjectDTO getProjectById(int id);

    /**
     * passing the projectDTO object to database.
     *
     * @param projectDTO - passing the projectDTO object for create operation.
     * @return The object.
     */
    ProjectDTO addProject(ProjectDTO projectDTO);

    /**
     * Passing the all projectDTO from the projectDAO
     *
     * @return list of all the ProjectDTO
     */
    List<ProjectDTO> getAllProject();

    /**
     * pass the project object to ProjectDAO for update operation.
     *
     * @param projectDTO - passing a projectDTO object.
     * @return the same object.
     */
    ProjectDTO updateProject(ProjectDTO projectDTO);

    /**
     * pass the project id for delete operation.
     *
     * @param id - receive a integer value.
     * @return the acknowledgement once the operation is done
     */
    String deleteProject(int id);

    /**
     * pass the project object to ProjectDAO for assigning employees for project.
     * @param projectDTO - passing a projectDTO object.
     * @return - the object
     */
    ProjectDTO assignEmployeesForProject(ProjectDTO projectDTO);

    /**
     * It will return the projectDTO object based on project name.
     * @param projectName- receive a String value
     * @return the list of objects.
     */
    List<ProjectDTO> getProjectsByName(String projectName);
}
