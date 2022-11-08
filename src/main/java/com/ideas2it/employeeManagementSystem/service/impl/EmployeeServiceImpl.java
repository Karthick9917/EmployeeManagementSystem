package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * It is passing the data between two class
 * and check the giving data is valid or not.
 * once the operation is done.
 * it will return the acknowledgment.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();

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
        return ValidationUtil.dateValid(date).isAfter(LocalDate.now());
    }

    /**
     * {@inheritDoc}
     */
    public boolean getDateOfBirth(LocalDate dateOfJoining,
                                  LocalDate dateOfBirth) throws EmsException {
        return dateOfBirth.isAfter(dateOfJoining.minusYears(18));
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateEmail(String email) {
        List<String> duplicateList = getAllEmployee().stream()
                .map(employeeDto -> employeeDto.getEmail()).collect(Collectors.toList());
        return duplicateList.contains(email);
    }

    /**
     * {@inheritDoc}
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        List<Long> duplicateList = getAllEmployee().stream()
                .map(employeeDto -> employeeDto.getPhoneNumber()).collect(Collectors.toList());
        return duplicateList.contains(Long.parseLong(phoneNumber));
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateId(int id) {
        return getEmployeeById(id) != null;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO getEmployeeById(int employeeId) {
        List<Employee> employees = employeeDao.getAllEmployees() ;
        EmployeeDTO employeeDTO = null;
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
                if(null != employee.getProject()) {
                    List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
                    for(Project project: employee.getProject()) {
                        projectDTOList.add(ProjectMapper.toProjectDTO(project));
                    }
                    employeeDTO.setProjectDTO(projectDTOList);
                }
                break;
            }
        }
        return employeeDTO;
    }

    /**
     * {@inheritDoc}
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO)
            throws EmsException {
        int id = employeeDao.createEmployeeDetails(EmployeeMapper.
                toEmployee(employeeDTO));
        return id > 0;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> getAllEmployee() throws EmsException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.getAllEmployees();
        EmployeeDTO employeeDTO;
        for (Employee employee : employeeList) {
            employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
            if(null != employee.getProject()) {
                List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
                for(Project project: employee.getProject()) {
                    projectDTOList.add(ProjectMapper.toProjectDTO(project));
                }
                employeeDTO.setProjectDTO(projectDTOList);
            }
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> getEmployeesByName(String employeeName) throws EmsException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>();
        List<Employee> employeeList = employeeDao.getEmployeesByName(employeeName);
        EmployeeDTO employeeDTO;
        for (Employee employee : employeeList) {
            employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
            if(null != employee.getProject()) {
                for(Project project: employee.getProject()) {
                    projectDTOList.add(ProjectMapper.toProjectDTO(project));
                }
                employeeDTO.setProjectDTO(projectDTOList);
            }
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteEmployeeDetails(int employeeId)
            throws EmsException {
        employeeDao.deleteEmployeeDetails(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateEmployeeDetails(EmployeeDTO employeeDTO)
            throws EmsException {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        if(null != employeeDTO.getProjectDTO()) {
            List<Project> project = new ArrayList<Project>();
            for(ProjectDTO projectDTO: employeeDTO.getProjectDTO()){
                project.add(ProjectMapper.toProject(projectDTO));
            }
            employee.setProject(project);
        }
        employeeDao.updateEmployeeDetails(employee);
    }

    /**
     * {@inheritDoc}
     */
    public void assignProjectsForEmployee(EmployeeDTO employeeDTO)
            throws EmsException {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        if(null != employeeDTO.getProjectDTO()) {
            List<Project> project = new ArrayList<Project>();
            for(ProjectDTO projectDTO: employeeDTO.getProjectDTO()){
                project.add(ProjectMapper.toProject(projectDTO));
            }
            employee.setProject(project);
        }
        employeeDao.updateEmployeeDetails(employee);
    }
}
