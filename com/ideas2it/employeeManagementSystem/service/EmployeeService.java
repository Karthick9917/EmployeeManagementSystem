package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.dao.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.service.impl.EmployeeServiceImpl;

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
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDao.createEmployeeDetails(EmployeeMapper.toEmployee(employeeDTO));
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> readEmployeeDetails() throws SQLException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        for (int i = 0; i < employeeList.size(); i++) {
            employeeDTOList.add(EmployeeMapper.toEmployeeDTO(employeeList.get(i)));
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> findEmployeeDetails(String employeeDTO_Name) throws SQLException {
        List<Employee> employeeList = employeeDao.searchEmployee();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<EmployeeDTO> matchedList = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            employeeDTOList.add(EmployeeMapper.toEmployeeDTO(employeeList.get(i)));
        }
        for (int i = 0; i < employeeDTOList.size(); i++) {
            if (employeeDTOList.get(i).getFirstName().substring(0, 3)
                    .equals(employeeDTO_Name.substring(0, 3))) {
                matchedList.add(employeeDTOList.get(i));
            }
        }
        return matchedList;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployeeDetails(int employeeDTO_Id) throws SQLException {
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        int employeeId = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getId() == employeeDTO_Id) {
                employeeId = employeeDTO_Id;
            }
        }
        return employeeDao.deleteEmployeeDetails(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDao.updateEmployeeDetails(EmployeeMapper.toEmployee(employeeDTO));
    }
}
