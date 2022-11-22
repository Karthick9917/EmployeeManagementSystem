package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * It is passing the data between the two classes.
 * once the operation is done
 * returning the acknowledgement.
 *
 * @version	1.8.0_281
 * @author	Karthick
 */

@RestController
@RequestMapping("api/v1/ems/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Passing the object for add operation and return the same object.
     *
     * @param employeeDTO - passing the
     * @return - the same object
     * @throws EmsException - throws a String message.
     */
    @PostMapping()
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO)
            throws EmsException {
        return employeeService.addEmployee(employeeDTO);
    }

    /**
     * Asking all employeeDTO.
     *
     * @return - list of all employeesDTO.
     * @throws EmsException - throws a String message
     */
    @GetMapping("getAll")
    public List<EmployeeDTO> getAllEmployee() throws EmsException {
        return employeeService.getAllEmployee();
    }

    /**
     * passing the value and return the list of objects.
     *
     * @param employeeName - transfer the String value.
     * @return the list of employeeDTO object.
     * @throws NotFoundException - throws a String message
     */

    @GetMapping("search/{firstName}")
    public List<EmployeeDTO> getEmployeesByName(@PathVariable("firstName") String employeeName)
            throws NotFoundException {
        return employeeService.getEmployeesByName(employeeName);
    }

    /**
     * passing the value for delete operation.
     *
     * @param id - transfer the integer value.
     * @throws NotFoundException - throws a String message
     */
    @DeleteMapping("{id}")
    public void deleteEmployeeDetails(@PathVariable("id") int id)
            throws NotFoundException {
        employeeService.deleteEmployee(id);
    }

    /**
     * passing the employeeDTO for update operation.
     *
     * @param employeeDTO - Getting the employeeDTO object.
     * @return - the object.
     * @throws EmsException - throws a String message
     */
    @PutMapping("update")
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO)
            throws EmsException {
        return employeeService.updateEmployee(employeeDTO);
    }

    /**
     * pass the employee object for assigning projects to employee.
     *
     * @param employeeId get a id for assign.
     * @param ids        - project id for assign.
     * @return - the same object
     * @throws EmsException - throws a String message.
     */
    @PutMapping("assign/{employeeId}")
    public EmployeeDTO assignProjectsForEmployee(@PathVariable int employeeId
            , @RequestParam List<Integer> ids) throws EmsException {
        return employeeService.assignProjectsForEmployee(employeeId, ids);
    }
}
