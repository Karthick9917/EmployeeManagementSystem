package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
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
 * it will return the acknowledgment.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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
        if (employeeDao.existsByEmail(email)) {
            throw new EmsException((email + " email is already exists"));
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean validatePhoneNumber(Long phoneNumber) {
        if (employeeDao.existsByPhoneNumber(phoneNumber)) {
            throw new EmsException(phoneNumber + " phone number is already exists");
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO getEmployeeById(int employeeId) {
        Employee employee = employeeDao.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new NotFoundException("Record not found...!!");
        }
        EmployeeDTO employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
        if(!employee.getProject().isEmpty()) {
            for(Project project: employee.getProject()) {
                employeeDTO.getProjectDTO().add(ProjectMapper
                        .toProjectDTO(project));
            }
        }
        return employeeDTO;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = null;
        if (validatePhoneNumber(employeeDTO.getPhoneNumber()) &
                (validateEmail(employeeDTO.getEmail()))) {
            employee = employeeDao.save(EmployeeMapper.
                    toEmployee(employeeDTO));
        } else {
            throw new EmsException("Fail to add");
        }
        return EmployeeMapper.toEmployeeDTO(employee);
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.findAll();
        if (employeeList.isEmpty()) {
            throw new NotFoundException("Record not found...!!");
        }
        EmployeeDTO employeeDTO;
        for (Employee employee : employeeList) {
            employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
            if(!employee.getProject().isEmpty()) {
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
    public List<EmployeeDTO> getEmployeesByName(String employeeName) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.findByFirstName(employeeName);
        if (employeeList.isEmpty()) {
            throw new NotFoundException("Record not found...!!");
        }
        EmployeeDTO employeeDTO;
        for (Employee employee : employeeList) {
            employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
            if(!employee.getProject().isEmpty()) {
                for(Project project: employee.getProject()) {
                    employeeDTO.getProjectDTO().add(ProjectMapper.toProjectDTO(project));
                }
            }
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public String deleteEmployeeDetails(int id) {
        if(!employeeDao.existsById(id)) {
            throw new NotFoundException("Record not found..!!");
        }
        employeeDao.deleteById(id);
        return "Deleted";
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDTO) {
        if (employeeDao.existsById(employeeDTO.getId())){
            throw new NotFoundException("Record not found...!!");
        }
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        if(!employeeDTO.getProjectDTO().isEmpty()) {
            for(ProjectDTO projectDTO: employeeDTO.getProjectDTO()){
                employee.getProject().add(ProjectMapper.toProject(projectDTO));
            }
        }
        Employee updatedEmployee = employeeDao.save(employee);

        EmployeeDTO updatedEmployeeDTO = EmployeeMapper.toEmployeeDTO(updatedEmployee);
        if(!updatedEmployee.getProject().isEmpty()) {
            for(Project project: updatedEmployee.getProject()){
                updatedEmployeeDTO.getProjectDTO().add(ProjectMapper.toProjectDTO(project));
            }
        }
        return updatedEmployeeDTO;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO assignProjectsForEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        if (!employeeDTO.getProjectDTO().isEmpty()) {
            for (ProjectDTO projectDTO : employeeDTO.getProjectDTO()) {
                employee.getProject().add(ProjectMapper.toProject(projectDTO));
            }
        }
        Employee updatedEmployee = employeeDao.save(employee);

        EmployeeDTO updatedEmployeeDTO = EmployeeMapper.toEmployeeDTO(updatedEmployee);
        if(!updatedEmployee.getProject().isEmpty()) {
            for(Project project: updatedEmployee.getProject()){
                updatedEmployeeDTO.getProjectDTO().add(ProjectMapper.toProjectDTO(project));
            }
        }
        return updatedEmployeeDTO;
    }
}
