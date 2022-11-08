package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;

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
public class ProjectServiceImpl  implements ProjectService {

    private ProjectDao projectDAO  = new ProjectDao();

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
    public boolean validateId(int id) {
        return getProjectById(id) != null;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO getProjectById(int id) {
        List<Project> projects = projectDAO.getAllProject();
        ProjectDTO projectDTO = null;
        for (Project project : projects) {
            if (project.getId() == id) {
                projectDTO = ProjectMapper.toProjectDTO(project);
                if(null != project.getEmployee()) {
                    List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
                    for(Employee employee: project.getEmployee()){
                        employeeDTOList.add(EmployeeMapper.toEmployeeDTO(employee));
                    }
                    projectDTO.setEmployeeDTO(employeeDTOList);
                }
                break;
            }
        }
        return projectDTO;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addProject(ProjectDTO projectDTO) throws EmsException {
        int id = projectDAO.addProject(ProjectMapper.toProject(projectDTO));
        return id > 0;
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getAllProject() throws EmsException {
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        List<Project> projectList = projectDAO.getAllProject();
        ProjectDTO projectDTO;
        for (Project project : projectList) {
            projectDTO = ProjectMapper.toProjectDTO(project);
            if(null != project.getEmployee()) {
                List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
                for(Employee employee: project.getEmployee()){
                    employeeDTOList.add(EmployeeMapper.toEmployeeDTO(employee));
                }
                projectDTO.setEmployeeDTO(employeeDTOList);
            }
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void updateProject(ProjectDTO projectDTO) throws EmsException{
        Project project = ProjectMapper.toProject(projectDTO);
        if(null != projectDTO.getEmployeeDTO()) {
            List<Employee> employee = new ArrayList<Employee>();
            for(EmployeeDTO employeeDTO: projectDTO.getEmployeeDTO()){
                employee.add(EmployeeMapper.toEmployee(employeeDTO));
            }
            project.setEmployee(employee);
        }
        projectDAO.updateProject(project);
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
    public List<ProjectDTO> getProjectsByName(String projectName) throws EmsException {
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        List<Project> projectList = projectDAO.getProjectsByName(projectName);
        ProjectDTO projectDTO;
        for (Project project : projectList) {
            projectDTO = ProjectMapper.toProjectDTO(project);
            if(null != project.getEmployee()) {
                List<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
                for(Employee employee: project.getEmployee()){
                    employeeDTO.add(EmployeeMapper.toEmployeeDTO(employee));
                }
                projectDTO.setEmployeeDTO(employeeDTO);
            }
            projectDTOList.add(projectDTO);
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void assignEmployeesForProject(ProjectDTO projectDTO) throws EmsException {
        Project project = ProjectMapper.toProject(projectDTO);
        if(null != projectDTO.getEmployeeDTO()) {
            List<Employee> employee = new ArrayList<Employee>();
            for(EmployeeDTO employeeDTO: projectDTO.getEmployeeDTO()){
                employee.add(EmployeeMapper.toEmployee(employeeDTO));
            }
            project.setEmployee(employee);
        }
        projectDAO.updateProject(project);
    }
}
