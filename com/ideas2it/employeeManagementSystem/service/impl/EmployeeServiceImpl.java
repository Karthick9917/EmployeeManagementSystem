package com.ideas2it.employeeManagementSystem.service.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dao.impl.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Getting employeeDTO
 * once the operation is done.
 * it will return the acknowledgment
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
        List<String> duplicateList = readEmployeeDetails().stream()
                .map(employeeDto -> employeeDto.getEmail()).collect(Collectors.toList());
        return duplicateList.contains(email);
    }

    /**
     * {@inheritDoc}
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        List<Long> duplicateList = readEmployeeDetails().stream()
                .map(employeeDto -> employeeDto.getPhoneNumber()).collect(Collectors.toList());
        return duplicateList.contains(Long.parseLong(phoneNumber));
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateId(String id) {
        return readEmployeeDetails().stream()
                .anyMatch(employeeDTO -> String.valueOf(employeeDTO
                        .getId()).equals(id));
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO getEmployeeById(int employeeId) {
        List<EmployeeDTO> employees = readEmployeeDetails();
        EmployeeDTO employeeDTO = null;
        for (EmployeeDTO employee : employees) {
            if (employee.getId() == employeeId) {
                employeeDTO = employee;
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
        return employeeDao.createEmployeeDetails(EmployeeMapper.
                toEmployee(employeeDTO));
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> readEmployeeDetails() throws EmsException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        for (Employee employee : employeeList) {
            employeeDTOList.add(EmployeeMapper.
                    toEmployeeDTO(employee));
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public List<Employee> findEmployeeDetails(String employeeName) throws EmsException {
        return employeeDao.searchEmployee(employeeName);
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
        employeeDao.updateEmployeeDetails(EmployeeMapper.toEmployee(employeeDTO));
    }
}
