package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
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

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Passing the object for add operation and return the same object.
     *
     * @param projectDTO - get an projectDto object for create operation
     * @return the acknowledgement
     * @throws EmsException - throws a String message.
     */
    @PostMapping()
    public ProjectDTO addProject(@RequestBody ProjectDTO projectDTO) throws EmsException {
        return projectService.addProject(projectDTO);
    }

    /**
     * Asking all projectDto.
     *
     * @return list of all projectDto.
     * @throws EmsException - throws a String message.
     */
    @GetMapping("getAll")
    public List<ProjectDTO> getAllProject() throws EmsException {
        return projectService.getAllProject();
    }

    /**
     * passing the value and return the list of objects.
     *
     * @param projectName - transfer the String value.
     * @return the list of projectDto object.
     * @throws NotFoundException - throws a String message
     */
    @GetMapping("search/{projectName}")
    public List<ProjectDTO> getProjectsByName(@PathVariable("projectName") String projectName)
            throws NotFoundException {
        return projectService.getProjectsByName(projectName);
    }

    /**
     * passing the value for delete operation.
     *
     * @param id - transfer the integer value for delete operation .
     * @throws NotFoundException - throws a String message.
     */
    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable("id") int id) throws NotFoundException {
        projectService.deleteProject(id);
    }

    /**
     * passing the projectDto for update operation.
     *
     * @param projectDTO - Getting the projectDto object.
     * @return - the object
     * @throws EmsException - throws a String message.
     */
    @PutMapping("update")
    public ProjectDTO updateProject(ProjectDTO projectDTO)
            throws EmsException {
        return projectService.updateProject(projectDTO);
    }

    /**
     * pass the project object for assigning employees to project.
     *
     * @param projectId - Get a id for assign.
     * @param ids       - employees id for assign.
     * @return the same object
     * @throws EmsException - throws a String message.
     */

    @PutMapping("assign/{projectId}")
    public ProjectDTO assignEmployeesForProject(@PathVariable int projectId,
                                                @RequestParam List<Integer> ids) throws NotFoundException {
        return projectService.assignEmployeesForProject(projectId, ids);
    }
}
