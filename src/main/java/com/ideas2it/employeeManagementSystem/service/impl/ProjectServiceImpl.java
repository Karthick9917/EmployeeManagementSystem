package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * It is passing the data between two class
 * and check the giving data is valid or not.
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */

@Service
public class ProjectServiceImpl  implements ProjectService {


    private final ProjectDao projectDAO;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDAO) {
        this.projectDAO = projectDAO;
    }

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
    public ProjectDTO getProjectById(int id) {
        Project project = projectDAO.findById(id).orElse(null);
        if (project == null) {
            throw new NotFoundException("Record not found...!!");
        }
        ProjectDTO projectDTO = null;
        projectDTO = ProjectMapper.toProjectDTO(project);
        if(!project.getEmployee().isEmpty()) {
            for(Employee employee: project.getEmployee()){
                projectDTO.getEmployeeDTO().add(EmployeeMapper.toEmployeeDTO(employee));
            }
        }
        return projectDTO;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO addProject(ProjectDTO projectDTO) {
        Project project= projectDAO.save(ProjectMapper.toProject(projectDTO));
        return ProjectMapper.toProjectDTO(project);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getAllProject() {
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        List<Project> projectList = projectDAO.findAll();
        if (projectList.isEmpty()){
            throw new NotFoundException("Record not found..!!");
        }
        ProjectDTO projectDTO;
        for (Project project : projectList) {
            projectDTO = ProjectMapper.toProjectDTO(project);
            if(!project.getEmployee().isEmpty()) {
                for(Employee employee: project.getEmployee()){
                    projectDTO.getEmployeeDTO().add(EmployeeMapper
                            .toEmployeeDTO(employee));
                }
            }
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.toProject(projectDTO);
        if(!projectDTO.getEmployeeDTO().isEmpty()) {
            for(EmployeeDTO employeeDTO: projectDTO.getEmployeeDTO()){
                project.getEmployee().add(EmployeeMapper
                        .toEmployee(employeeDTO));
            }
        }
        Project updatedProject = projectDAO.save(project);

        ProjectDTO updatedProjectDTO = ProjectMapper
                .toProjectDTO(updatedProject);
        if(!updatedProject.getEmployee().isEmpty()) {
            for(Employee employee: updatedProject.getEmployee()){
                updatedProjectDTO.getEmployeeDTO().add(EmployeeMapper
                        .toEmployeeDTO(employee));
            }
        }
        return updatedProjectDTO;
    }

    /**
     * {@inheritDoc}
     */
    public String deleteProject(int id) {
        if (!projectDAO.existsById(id)) {
            throw new NotFoundException("Record not found..!!");
        }
        projectDAO.deleteById(id);
        return "Deleted";
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getProjectsByName(String projectName) {
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        List<Project> projectList = projectDAO.findByProjectName(projectName);
        if(projectList.isEmpty()) {
            throw new NotFoundException("Record not found..!!");
        }
        ProjectDTO projectDTO;
        for (Project project : projectList) {
            projectDTO = ProjectMapper.toProjectDTO(project);
            if(!project.getEmployee().isEmpty()) {
                for(Employee employee: project.getEmployee()){
                    projectDTO.getEmployeeDTO().add(EmployeeMapper.toEmployeeDTO(employee));
                }
            }
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO assignEmployeesForProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.toProject(projectDTO);
        if(!projectDTO.getEmployeeDTO().isEmpty()) {
            for(EmployeeDTO employeeDTO: projectDTO.getEmployeeDTO()){
                project.getEmployee().add(EmployeeMapper
                        .toEmployee(employeeDTO));
            }
        }
        Project updatedProject = projectDAO.save(project);

        ProjectDTO updatedProjectDTO = ProjectMapper
                .toProjectDTO(updatedProject);
        if(!updatedProject.getEmployee().isEmpty()) {
            for(Employee employee: updatedProject.getEmployee()){
                updatedProjectDTO.getEmployeeDTO().add(EmployeeMapper
                        .toEmployeeDTO(employee));
            }
        }
        return updatedProjectDTO;
    }
}

