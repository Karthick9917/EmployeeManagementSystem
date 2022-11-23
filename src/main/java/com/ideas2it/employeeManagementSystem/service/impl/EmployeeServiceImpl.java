package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.dao.EmployeeDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * It is passing the data between two class
 * and check the giving data is valid or not.
 * once the operation is done.
 * it will return the acknowledgment.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager
            .getLogger(EmployeeServiceImpl.class.getName());
    private final EmployeeDao employeeDao;

    private final ProjectService projectService;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, ProjectService projectService) {
        this.employeeDao = employeeDao;
        this.projectService = projectService;
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateDateOfJoining(LocalDate date) throws EmsException {
        return ValidationUtil.dateValid(String.valueOf(date)).isAfter(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateDateOfBirth(LocalDate dateOfJoining,
                                       LocalDate dateOfBirth) throws EmsException {
        return ValidationUtil.dateValid(String.valueOf(dateOfBirth))
                .isAfter(dateOfJoining.minusYears(18));
    }

    /**
     * {@inheritDoc}
     */
    public void validateEmployee(EmployeeDTO employeeDTO) throws EmsException {
        if (validateDateOfJoining(employeeDTO.getDateOfBirth()) && validateDateOfBirth(employeeDTO
                .getDateOfJoining(), employeeDTO.getDateOfBirth())) {
            throw new EmsException(Constants.DATE_MISMATCH);
        }
        if (employeeDao.existsByPhoneNumberAndEmail(employeeDTO
                .getPhoneNumber(), employeeDTO.getEmail())) {
            throw new EmsException(Constants.INVALID_DATA);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Employee getEmployeeById(int employeeId) {
        return employeeDao.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(Constants.ERROR_404));
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> toProjectDTO(Employee employee) {
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (Project project : employee.getProject()) {
            projectDTOS.add(ProjectMapper.toProjectDTO(project));
        }
        return projectDTOS;
    }

    /**
     * {@inheritDoc}
     */
    public List<Project> toProject(EmployeeDTO employeeDTO) {
        List<Project> projects = new ArrayList<>();
        for (ProjectDTO projectDTO : employeeDTO.getProjectDTO()) {
            projects.add(ProjectMapper.toProject(projectDTO));
        }
        return projects;
    }


    /**
     * {@inheritDoc}
     */
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws EmsException {
        Employee employee;
        validateEmployee(employeeDTO);
        employee = employeeDao.save(EmployeeMapper.toEmployee(employeeDTO));
        logger.info("Employee " + employee.getId() + " has been created successfully");
        return EmployeeMapper.toEmployeeDTO(employee);
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> getAllEmployee() throws EmsException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> employeeList = employeeDao.findAll();
        if (!employeeList.isEmpty()) {
            for (Employee employee : employeeList) {
                EmployeeDTO employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
                if (!employee.getProject().isEmpty()) {
                    employeeDTO.setProjectDTO(toProjectDTO(employee));
                }
                employeeDTOList.add(employeeDTO);
            }
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> getEmployeesByName(String employeeName) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> employeeList = employeeDao.findByFirstName(employeeName);
        if (!employeeList.isEmpty()) {
            for (Employee employee : employeeList) {
                EmployeeDTO employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
                if (!employee.getProject().isEmpty()) {
                    employeeDTO.setProjectDTO(toProjectDTO(employee));
                }
                employeeDTOList.add(employeeDTO);
            }
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteEmployee(int id) throws NotFoundException {
        try {
            employeeDao.deleteById(id);
            logger.info("Employee " + id + "has been removed successfully");
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            logger.error(emptyResultDataAccessException.getMessage());
            throw new NotFoundException(Constants.FAILED_TO_DELETE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmsException {
        EmployeeDTO updatedEmployeeDTO;
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        if (!employeeDTO.getProjectDTO().isEmpty()) {
            employee.setProject(toProject(employeeDTO));
        }
        Employee updatedEmployee = employeeDao.save(employee);

        updatedEmployeeDTO = EmployeeMapper.toEmployeeDTO(updatedEmployee);
        if (!updatedEmployee.getProject().isEmpty()) {
            updatedEmployeeDTO.setProjectDTO(toProjectDTO(employee));
        }
        logger.info("Employee " + employeeDTO.getId() + "has been updated successfully");
        return updatedEmployeeDTO;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO assignProjectsForEmployee(int employeeId, List<Integer> ids)
            throws EmsException {
        EmployeeDTO employeeDTO;
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(Constants.ERROR_404));
        for (Integer projectId : ids) {
            employee.getProject().add(projectService
                    .getProjectById(projectId));
        }
        Employee updatedEmployee = employeeDao.save(employee);
        employeeDTO = EmployeeMapper.toEmployeeDTO(updatedEmployee);
        if (!updatedEmployee.getProject().isEmpty()) {
            employeeDTO.setProjectDTO(toProjectDTO(updatedEmployee));
        }
        logger.info("Projects are assigned to employee " + employeeDTO.getId());
        return employeeDTO;
    }
}
