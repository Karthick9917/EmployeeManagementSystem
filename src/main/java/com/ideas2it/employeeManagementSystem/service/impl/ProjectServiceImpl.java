package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.dao.ProjectDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LogManager
            .getLogger(ProjectServiceImpl.class.getName());
    private final ProjectDao projectDAO;

    @Autowired
    ApplicationContext context;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDAO) {
        this.projectDAO = projectDAO;
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateDate(LocalDate date) throws EmsException {
        return ValidationUtil.dateValid(String.valueOf(date)).isBefore(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    public Project getProjectById(int id) {
        return projectDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.ERROR_404));
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> toEmployeeDTO(Project project) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : project.getEmployee()) {
            employeeDTOS.add(EmployeeMapper.toEmployeeDTO(employee));
        }
        return employeeDTOS;
    }

    /**
     * {@inheritDoc}
     */
    public List<Employee> toEmployee(ProjectDTO projectDTO) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDTO employeeDTO : projectDTO.getEmployeeDTO()) {
            employees.add(EmployeeMapper.toEmployee(employeeDTO));
        }
        return employees;
    }

    /**
     * {@inheritDoc}
     */
    public void validateProject(ProjectDTO projectDTO) throws EmsException {
        if (validateDate(projectDTO.getProjectStart())
                && validateDate(projectDTO.getProjectEnd())
                && validateDate(projectDTO.getProjectDue())) {
            throw new EmsException(Constants.DATE_MISMATCH);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO addProject(ProjectDTO projectDTO) throws EmsException {
        Project project;
        try {
            validateProject(projectDTO);
            project = projectDAO.save(ProjectMapper.toProject(projectDTO));
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.FAILED_TO_ADD);
        }
        logger.info("Project " + project.getId() + "has been created successfully");
        return ProjectMapper.toProjectDTO(project);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getAllProject() throws EmsException {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        try {
            for (Project project : projectDAO.findAll()) {
                ProjectDTO projectDTO = ProjectMapper.toProjectDTO(project);
                if (!project.getEmployee().isEmpty()) {
                    projectDTO.setEmployeeDTO(toEmployeeDTO(project));
                }
                projectDTOList.add(projectDTO);
            }
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.FAILED_TO_FETCH);
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getProjectsByName(String projectName)
            throws NotFoundException {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        try {
            for (Project project : projectDAO.findByProjectName(projectName)) {
                ProjectDTO projectDTO = ProjectMapper.toProjectDTO(project);
                if (!project.getEmployee().isEmpty()) {
                    projectDTO.setEmployeeDTO(toEmployeeDTO(project));
                }
                projectDTOList.add(projectDTO);
            }
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new NotFoundException(Constants.ERROR_404);
        }
        return projectDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteProject(int id) throws NotFoundException {
        try {
            projectDAO.deleteById(id);
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new NotFoundException(Constants.FAILED_TO_DELETE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO updateProject(ProjectDTO projectDTO) throws EmsException {
        ProjectDTO updatedProjectDTO;
        try {
            Project project = ProjectMapper.toProject(projectDTO);
            if (!projectDTO.getEmployeeDTO().isEmpty()) {
                project.setEmployee(toEmployee(projectDTO));
            }
            Project updatedProject = projectDAO.save(project);

            updatedProjectDTO = ProjectMapper.toProjectDTO(updatedProject);
            if (!updatedProject.getEmployee().isEmpty()) {
                updatedProjectDTO.setEmployeeDTO(toEmployeeDTO(updatedProject));
            }
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.FAILED_TO_UPDATE);
        }
        return updatedProjectDTO;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO assignEmployeesForProject(int projectId, List<Integer> ids)
            throws EmsException {
        EmployeeService employeeService = (EmployeeServiceImpl)context
                .getBean("employeeService");
        ProjectDTO projectDTO;
        try {
            Project project = projectDAO.findById(projectId)
                    .orElseThrow(() -> new NotFoundException(Constants.ERROR_404));
            for (Integer employeeId : ids) {
                project.getEmployee().add(employeeService
                        .getEmployeeById(employeeId));
            }
            Project updatedProject = projectDAO.save(project);

            projectDTO = ProjectMapper.toProjectDTO(updatedProject);
            if (!updatedProject.getEmployee().isEmpty()) {
                projectDTO.setEmployeeDTO(toEmployeeDTO(updatedProject));
            }
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.FAILED_TO_ASSIGN);
        }
        return projectDTO;
    }
}

