package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Getting employeeDTO
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeService implements EmployeeServiceImpl {

    private EmployeeDao employeeDao = new EmployeeDao();

    /**
     * Return true or false based on the user Input is valid or not.
     * @param pattern - check the user input based on the formatter.
     * @param userInput - user input to be validated.
     * @return true or false based on the user input.
     */
    public boolean userInputValidation(String pattern, String userInput)
            throws EmsException {
        return ValidationUtil.isInputValid(pattern, userInput);
    }

    /**
     * Return true or false based on the date is valid or not.
     * @param date - passing the date input to be validated.
     * @return true or false based on the given date.
     * @throws EmsException
     */
    public boolean getDate(String date) throws EmsException {
        return ValidationUtil.dateValid(date).isAfter(LocalDate.now());
    }

    /**
     * Return true or false based on the date of birth is valid or not.
     * @param dateOfJoining - for the given date birth is valid or not.
     *
     * @param dateOfBirth - for the given date is valid or not.
     * @return true or false based on the given date of birth.
     * @throws EmsException
     */
    public boolean getDateOfBirth(LocalDate dateOfJoining,
                                  LocalDate dateOfBirth) throws EmsException {
        return dateOfBirth.isAfter(dateOfJoining.minusYears(18));
    }

    /**
     * Return true or false based on the given string is valid or not.
     * @param email - passing the string for validate.
     * @return true or false based on the given email.
     * @throws EmsException
     */
    public boolean validateEmail(String email) throws EmsException {
        return readEmployeeDetails().stream().anyMatch(employeeDTO -> employeeDTO.getEmail().equals(email));
    }

    /**
     * Return true or false based on the given string is valid or not.
     * @param phoneNumber - passing the string for validate.
     * @return true or false based on the given phone number.
     * @throws EmsException
     */
    public boolean validatePhoneNumber(String phoneNumber) throws EmsException{
        return readEmployeeDetails().stream()
                .anyMatch(employeeDTO -> String.valueOf(employeeDTO
                        .getPhoneNumber()).equals(phoneNumber));
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
    public List<Employee> findEmployeeDetails(String employeeName)
            throws EmsException {
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
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO)
            throws EmsException {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        return employeeDao.updateEmployeeDetails(employee);
    }
}
