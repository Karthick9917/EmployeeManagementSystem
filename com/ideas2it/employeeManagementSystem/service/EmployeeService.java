package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.EmployeeDao.impl.EmployeeDao;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.mapper.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

/*
 * Getting employee details from the controller and
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();

    /**
     * Collect the employee from the controller and pass to the Dao
     *
     * @param employeeDTO - Getting the employee object
     * @return to acknowledge the view class
     */
    public boolean createEmployeeDetails(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        return employeeDao.createEmployeeDetails(employee);
    }

    /**
     * Display employee details
     *
     * @return list of all employees.
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
     * Collect the employee name and pass to the DAO
     *
     * @param employeeDTO_Name - transfer the String value to EmployeeDao
     * @return the object to controller
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
     * Collect the employee id and pass to the DAO
     *
     * @param employeeDTO_Id - collect the employee id value
     * @return Give the acknowledgment.
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
     * Collect the employee details and pass to the DAO
     *
     * @param employeeDTO - transfer the object to EmployeeDao
     * @return Give the acknowledgement
     */
    public boolean updateEmployeeDetails(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEmployee(employeeDTO);
        return employeeDao.updateEmployeeDetails(employee);
    }
}
