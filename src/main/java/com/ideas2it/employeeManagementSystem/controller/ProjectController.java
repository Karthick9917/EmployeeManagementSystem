package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * It is passing the data between the two classes.
 * once the operation is done
 * returning the acknowledgement.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */

@RestController
@RequestMapping("api/v1/ems/project")
public class ProjectController {

    private ProjectService projectService;
    private EmployeeService employeeService;

    @Autowired
    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

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
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * passing the projectDto and return the acknowledgement.
     * @param projectDTO - get an projectDto object for create operation
     * @return the acknowledgement
     * @throws EmsException
     */
    @PostMapping()
    public ProjectDTO addProject(@RequestBody ProjectDTO projectDTO) throws EmsException {
        return projectService.addProject(projectDTO);
    }

    /**
     * passing the value and return the acknowledgement.
     *
     * @param projectName - transfer the String value.
     * @return the list of projectDto object.
     * @throws NotFoundException
     */
    @GetMapping("search/{projectName}")
    public List<ProjectDTO> getProjectsByName(@PathVariable("projectName") String projectName) throws NotFoundException {
        return projectService.getProjectsByName(projectName);
    }

    /**
     * Asking all projectDto.
     * @return list of all projectDto.
     * @throws NotFoundException
     */
    @GetMapping("getAll")
    public List<ProjectDTO> getAllProject() throws NotFoundException {
        return projectService.getAllProject();
    }

    /**
     * passing the value for delete operation.
     *
     * @param id - transfer the integer value.
     * @return - the acknowledgement
     * @throws NotFoundException
     */
    @DeleteMapping("{id}")
    public String deleteProject(@PathVariable("id") int id) throws NotFoundException {
        return projectService.deleteProject(id);
    }

    /**
     * passing the projectDto for update operation.
     * @param projectDTO - passing the projectDto object.
     * @return - the object
     * @throws NotFoundException
     */
    @PutMapping("update")
    public ProjectDTO updateProject(@RequestBody ProjectDTO projectDTO) throws NotFoundException {
        return projectService.updateProject(projectDTO);
    }

    /**
     * pass the project object for assigning employees to project.
     * @param projectId get a project object
     * @param ids - employees id for assigning
     * @return the object
     * @throws NotFoundException
     */

    @PutMapping("assign/{projectId}")
    public ProjectDTO assignEmployeesForProject(@PathVariable int projectId,
                                                @RequestParam List<Integer> ids) throws NotFoundException {
        ProjectDTO projectDTO = projectService.getProjectById(projectId);
        for (Integer employeeId: ids) {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
            projectDTO.getEmployeeDTO().add(employeeDTO);
        }
        return projectService.assignEmployeesForProject(projectDTO);
    }
}
