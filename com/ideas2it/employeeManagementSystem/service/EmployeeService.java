package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeeManagementSystem.util.EmployeeUtil;

import java.sql.SQLException;
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
     * {@inheritDoc}
     */
    public boolean userInputValidation(String pattern, String userInput) {
        return EmployeeUtil.isInputValid(pattern, userInput);
    }

    /**
     * {@inheritDoc}
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO)
            throws SQLException, EmsException {
        return employeeDao.createEmployeeDetails(EmployeeMapper.
                toEmployee(employeeDTO));
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> readEmployeeDetails() throws SQLException,
            EmsException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        for (int i = 0; i < employeeList.size(); i++) {
            employeeDTOList.add(EmployeeMapper.
                    toEmployeeDTO(employeeList.get(i)));
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> findEmployeeDetails(String employeeDTO_Name)
            throws SQLException, EmsException {
        List<Employee> employeeList = employeeDao.searchEmployee();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> matchedList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getFirstName().substring(0, 3)
                    .equals(employeeDTO_Name.substring(0, 3))) {
                matchedList.add(employee);
            }
        }
        for (Employee employee : matchedList) {
            employeeDTOList.add(EmployeeMapper.toEmployeeDTO(employee));
        }
        if (employeeDTOList.isEmpty()){
            throw new EmsException(EmployeeConstants.NOT_MATCHED_MESSAGE);
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployeeDetails(int employeeDTO_Id)
            throws SQLException, EmsException {
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        int employeeId = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getId() == employeeDTO_Id) {
                employeeId = employeeList.get(i).getId();
            }
        }
        boolean isEmployeeDeleted = employeeDao.deleteEmployeeDetails(employeeId);
        if (!isEmployeeDeleted) {
            throw new EmsException(EmployeeConstants.ERROR_404);
        }
        return isEmployeeDeleted;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO)
            throws SQLException, EmsException {
        return employeeDao.updateEmployeeDetails(EmployeeMapper.
                toEmployee(employeeDTO));
    }
}
