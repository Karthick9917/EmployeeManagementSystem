package com.ideas2it.employeeManagementSystem.controller;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.service.EmployeeService;
import com.ideas2it.employeeManagementSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    private EmployeeService employeeService;
    private ProjectService projectService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    /**
     * Passing the pattern and user input, and it returns the true or
     * false once the input is valid.
     *
     * @param pattern - for check the input.
     * @param userInput - for check the input is matches or not
     * @return true for false once the input is valid.
     */
    public boolean userInputValidation(String pattern, String userInput) {
        return employeeService.userInputValidation(pattern,userInput);
    }

    /**
     * Passing the date, and it returns the true or false
     * once the date is valid.
     *
     * @param date - for check the date is valid or not
     * @return true for false once the input is valid.
     */
    public boolean getDate(String date) {
        return employeeService.validateDate(date);
    }

    /**
     * Passing the date of birth and date of joining, and it returns the true or false
     * based on the given date is valid or not
     * @param dateOfJoining - passing the date of joining for validate
     *                        the date of birth.
     * @param dateOfBirth - passing the date of birth input to be validated.
     * @return true for false once the input is valid.
     */
    public boolean getDateOfBirth(LocalDate dateOfJoining, LocalDate dateOfBirth) {
        return employeeService.getDateOfBirth(dateOfJoining, dateOfBirth);
    }

    /**
     * Passing the string and its return the true or false
     * based on the given String is valid or not
     * @param email - passing the given String for valid.
     * @return true for false once the input is valid.
     */
    public boolean validateEmail(String email) {
        return employeeService.validateEmail(email);
    }

    /**
     * Passing the string and its return the true or false
     * based on the given String is valid or not
     * @param phoneNumber    - passing the given String for valid.
     * @return true for false once the input is valid.
     */
    public boolean validatePhoneNumber(Long phoneNumber) {
        return employeeService.validatePhoneNumber(phoneNumber);
    }

    /**
     * Passing the integer and its return the object
     * based on the given id.
     * @param employeeId - passing the given integer.
     * @return return the object based on the id.
     */
    public EmployeeDTO getEmployeeById (int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * Passing the integer and its return the object
     * based on the given id.
     * @param projectId - passing the given integer.
     * @return return the object based on the id.
     */
    public ProjectDTO getProjectById(int projectId) {
        return projectService.getProjectById(projectId);
    }

    /**
     * passing the employeeDTO and return the same object.
     *
     * @param - employeeDTO - get an employeeDTO object for create operation.
     * @return - the object
     * @throws - EmsException
     */
    @PostMapping()
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO)
            throws EmsException {
        return employeeService.addEmployee(employeeDTO);
    }

    /**
     * Asking all employeeDTO.
     *
     * @return list of all employeesDTO.
     * @throws NotFoundException
     */
    @GetMapping("getAll")
    public List<EmployeeDTO> getAllEmployee() throws NotFoundException {
        return employeeService.getAllEmployee();
    }

    /**
     * passing the value and return the list of objects.
     *
     * @param employeeName - transfer the String value.
     * @return the list of employeeDTO object.
     * @throws NotFoundException
     */

    @GetMapping("search/{firstName}")
    public List<EmployeeDTO> getEmployeesByName (@PathVariable("firstName") String employeeName)
            throws NotFoundException {
        return employeeService.getEmployeesByName(employeeName);
    }

    /**
     * passing the id value for delete operation.
     *
     * @param id - transfer the integer value.
     * @return - the acknowledgment.
     * @throws NotFoundException
     */
    @DeleteMapping("{id}")
    public String deleteEmployeeDetails(@PathVariable("id") int id)
            throws NotFoundException {
        return employeeService.deleteEmployeeDetails(id);
    }

    /**
     * passing the employeeDTO and return the same object.
     *
     * @param employeeDTO - Getting the employeeDTO object.
     * @return - the object.
     * @throws NotFoundException
     */
    @PutMapping("update")
    public EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDTO )
            throws NotFoundException {
        return employeeService.updateEmployeeDetails(employeeDTO);
    }

     /**
     * pass the employee object for assigning projects to employee.
     * @param employeeId get a employee object
     * @param ids - project id for assigning
     * @return EmployeeDTO object
     * @throws NotFoundException
     */
    @PutMapping("assign/{employeeId}")
    public EmployeeDTO assignProjectsForEmployee(@PathVariable int employeeId
            , @RequestParam List<Integer> ids) throws NotFoundException {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        for (Integer projectId: ids) {
            ProjectDTO projectDTO = projectService.getProjectById(projectId);
            employeeDTO.getProjectDTO().add(projectDTO);
        }
        return employeeService.assignProjectsForEmployee(employeeDTO);
    }
}
