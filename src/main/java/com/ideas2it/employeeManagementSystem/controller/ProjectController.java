package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import com.ideas2it.employeeManagementSystem.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.service.impl.ProjectServiceImpl;

import java.util.List;

/*
 * It is passing the data between the two classes.
 * once the operation is done
 * returning the acknowledgement.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class ProjectController {

    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * Passing the pattern and user input, and it returns the true or
     * false once the input is valid.
     *
     * @param pattern - for check the input.
     * @param userInput - for check the input is matches or not
     * @return true for false once the input is valid.
     */
    public boolean userInputValidation(String pattern, String userInput) {
        return projectService.userInputValidation(pattern,userInput);
    }

    /**
     * Passing the date, and it returns the true or false
     * once the date is valid.
     *
     * @param date - for check the date is valid or not
     * @return true for false once the input is valid.
     */
    public boolean getDate(String date) {
        return projectService.validateDate(date);
    }


    /**
     * Passing the string and its return the true or false
     * based on the given String is valid or not
     * @param id    - passing the given String for valid.
     * @return true for false once the input is valid.
     */
    public boolean validateId(int id) {
        return projectService.validateId(id);
    }

    /**
     * Passing the integer and its return the object
     * based on the given id.
     * @param id - passing the given integer.
     * @return return the object based on the id.
     */
    public ProjectDTO getProjectById(int id) {
        return projectService.getProjectById(id);
    }

    /**
     * Passing the integer and its return the object
     * based on the given id.
     * @param employeeId - passing the given integer.
     * @return return the object based on the id.
     */
    public EmployeeDTO getEmployeeById(int employeeId) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * passing the projectDto and return the acknowledgement.
     * @param projectDTO - get an projectDto object for create operation
     * @return the acknowledgement
     * @throws EmsException
     */
    public boolean addProject(ProjectDTO projectDTO) throws EmsException {
        return projectService.addProject(projectDTO);
    }

    /**
     * Asking all projectDto.
     * @return list of all projectDto.
     * @throws EmsException
     */
    public List<ProjectDTO> getAllProject() throws EmsException {
        return projectService.getAllProject();
    }

    /**
     * passing the projectDto for update operation.
     * @param projectDTO - passing the projectDto object.
     * @throws EmsException
     */
    public void updateProject(ProjectDTO projectDTO) throws EmsException {
        projectService.updateProject(projectDTO);
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param projectName - transfer the String value.
     * @return the list of projectDto object.
     * @throws EmsException
     */
    public List<ProjectDTO> getProjectsByName(String projectName) throws EmsException {
        return projectService.getProjectsByName(projectName);
    }

    /**
     * passing the value for delete operation.
     *
     * @param id - transfer the integer value.
     * @throws EmsException
     */
    public void deleteProject(int id) throws EmsException {
        projectService.deleteProject(id);
    }

    /**
     * pass the project object for assigning employees for project.
     * @param projectDTO - passing a projectDTO object.
     * @throws EmsException
     */
    public void assignEmployeesForProject(ProjectDTO projectDTO) throws EmsException {
        projectService.assignEmployeesForProject(projectDTO);
    }
}
