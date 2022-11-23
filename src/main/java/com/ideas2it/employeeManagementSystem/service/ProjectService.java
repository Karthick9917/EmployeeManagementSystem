package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface to declare the abstract method
 * to EmployeeService
 *
 * @version 1.8.0_281
 * @author Karthick
 */
public interface ProjectService {

    /**
     * Return true or false based on the date is valid or not.
     *
     * @param date - passing the date input to be validated.
     * @return true or false based on the given date.
     * @throws EmsException
     */
    boolean validateDate(LocalDate date) throws EmsException;

    /**
     * Find out the particular object based on the given id.
     *
     * @param id - passing the integer.
     * @return the object based on the given id.
     * @throws EmsException - throws a String message
     */
    Project getProjectById(int id) throws EmsException;

    /**
     * Converting the model object to dto object.
     *
     * @param project - passing the model object for converting
     *                the model object to dto object.
     * @return - the list of DTO object.
     */
    List<EmployeeDTO> toEmployeeDTO(Project project);

    /**
     * Converting the model object to dto object.
     *
     * @param projectDTO - passing the DTO object for converting
     *                   the DTO object to model object.
     * @return - the list of Model object.
     */
    List<Employee> toEmployee(ProjectDTO projectDTO);

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
     * @return list of all the ProjectDTO.
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
     * @throws NotFoundException - throws a String message.
     */
    void deleteProject(int id) throws NotFoundException;

    /**
     * pass the project object to ProjectDAO for assigning employees for project.
     *
     * @param projectId - passing an id for object.
     * @param ids       - passing the ids for assign the object.
     * @return - the object
     */
    ProjectDTO assignEmployeesForProject(int projectId, List<Integer> ids);

    /**
     * It will return the projectDTO object based on project name.
     *
     * @param projectName- receive a String value
     * @return the list of objects.
     */
    List<ProjectDTO> getProjectsByName(String projectName);
}
