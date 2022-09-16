package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.dao.impl.EmployeeDao;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.service.impl.EmployeeServiceImpl;

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
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        return employeeDao.createEmployeeDetails(employee);
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> readEmployeeDetails() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            employeeDTOList.add(EmployeeMapper.toEmployeeDTO(employee));
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO findEmployeeDetails(String employeeDTO_Name) {
        List<EmployeeDTO> employeesList = readEmployeeDetails();
        EmployeeDTO foundEmployeeDTO = null;
        for (int i = 0; i < employeesList.size(); i++) {
            EmployeeDTO searchEmployeeDTO = employeesList.get(i);
            if (searchEmployeeDTO.getName().substring(0, 3)
                    .equals(employeeDTO_Name.substring(0, 3))) {
                foundEmployeeDTO = searchEmployeeDTO;
            }
        }
        return foundEmployeeDTO;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployeeDetails(String employeeDTO_Id) {
        List<Employee> employeeList = employeeDao.readEmployeeDetails();
        Employee employee = null;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee searchEmployee = employeeList.get(i);
            if (searchEmployee.getId().equals(employeeDTO_Id)) {
                employee = searchEmployee;
            }
        }
        return employeeDao.deleteEmployeeDetails(employee);
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        return employeeDao.updateEmployeeDetails(employee);
    }
}
