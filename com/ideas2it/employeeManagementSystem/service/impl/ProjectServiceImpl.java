package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dao.impl.ProjectDAO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Getting projectDTO.
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class ProjectServiceImpl  implements ProjectService {

    private ProjectDAO projectDAO  = new ProjectDAO();
    private EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * {@inheritDoc}
     */
    public boolean userInputValidation(String pattern, String userInput) {
        return ValidationUtil.isInputValid(pattern, userInput);
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateDate(String date) throws EmsException {
        return ValidationUtil.dateValid(date).isBefore(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateId(String id) {
        return getAllProject().stream()
                .anyMatch(projectDTO -> String.valueOf(projectDTO
                        .getId()).equals(id));
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO getProjectById(int id) {
        List<ProjectDTO> projectDTOS = getAllProject();
        ProjectDTO projectDTO = null;
        for (ProjectDTO project : projectDTOS) {
            if (project.getId() == id) {
                projectDTO = project;
                break;
            }
        }
        return projectDTO;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO getEmployeeById(int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    /**
     * {@inheritDoc}
     */
    public boolean addProject(ProjectDTO projectDTO) throws EmsException {
        return projectDAO.addProject(ProjectMapper.toProject(projectDTO));
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getAllProject() throws EmsException {
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        List<Project> projectList = projectDAO.getAllProject();
        for (Project project : projectList) {
            projectDTOList.add(ProjectMapper
                    .toProjectDTO(project));
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void updateProject(ProjectDTO projectDTO) throws EmsException{
        projectDAO.updateProject(ProjectMapper.toProject(projectDTO));

    }

    /**
     * {@inheritDoc}
     */
    public void deleteProject(int id) throws EmsException {
        projectDAO.deleteProject(id);
    }

    /**
     * {@inheritDoc}
     */
    public void assignEmployeesForProject(ProjectDTO projectDTO) throws EmsException {
         projectDAO.updateProject(ProjectMapper.toProject(projectDTO));
    }
}
