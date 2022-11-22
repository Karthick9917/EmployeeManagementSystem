package com.ideas2it.employeeManagementSystem.service;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.Exception.NotFoundException;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface to declare the abstract method
 * to EmployeeService
 *
 * @author Karthick
 * @version 1.8.0_281
 */

public interface EmployeeService {

    /**
     * Return true or false based on the date is valid or not.
     *
     * @param date - passing the date input to be validated.
     * @return true or false based on the given date.
     * @throws EmsException - throws a String message
     */
    boolean validateDateOfJoining(LocalDate date) throws EmsException;

    /**
     * Return true or false based on the date of birth is valid or not.
     *
     * @param dateOfJoining - for the given date birth is valid or not.
     * @param dateOfBirth   - for the given date is valid or not.
     * @return true or false based on the given date of birth.
     * @throws EmsException - throws a String message.
     */
    boolean validateDateOfBirth(LocalDate dateOfJoining, LocalDate dateOfBirth)
            throws EmsException;

    /**
     * validate the object based on the given values is valid or not.
     *
     * @param employeeDTO - passing the object for validate.
     * @throws EmsException - throws a String message.
     */
    void validateEmployee(EmployeeDTO employeeDTO) throws EmsException;

    /**
     * Find out the particular object based on the given id.
     *
     * @param employeeId - passing the integer.
     * @return the object based on the given id.
     * @throws NotFoundException - throws a String message.
     */
    Employee getEmployeeById(int employeeId) throws NotFoundException;

    /**
     * converting the model object to dto object.
     *
     * @param employeeDTO - passing the DTO object for converting
     *                    the DTO object to model object.
     * @return the list of Model object.
     */
    List<Project> toProject(EmployeeDTO employeeDTO);

    /**
     * converting the model object to dto object.
     *
     * @param employee - passing the model object for converting
     *                 the model object to dto object.
     * @return the list of DTO object.
     */
    List<ProjectDTO> toProjectDTO(Employee employee);

    /**
     * passing the employeeDTO object to EmployeeDAO.
     *
     * @param employeeDTO - get an employeeDTO object for create operation.
     * @return The object once the operation is done.
     * @throws EmsException - throws a String message.
     */
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws EmsException;

    /**
     * Passing all employeeDTO from the EmployeeDAO
     *
     * @return list of all the employeeDTO
     * @throws EmsException - throws a String message.
     */
    List<EmployeeDTO> getAllEmployee() throws EmsException;

    /**
     * it will return the employeeDTO based on employee name from the employeeDAO.
     *
     * @param employeeName - receive a String value
     * @return the list of employeeDTO.
     * @throws NotFoundException - throws a String message.
     */
    List<EmployeeDTO> getEmployeesByName(String employeeName) throws NotFoundException;

    /**
     * pass the employee id for delete operation.
     *
     * @param employeeDTO_Id - receive a integer value.
     * @throws NotFoundException - throws a String message.
     */
    void deleteEmployee(int employeeDTO_Id) throws NotFoundException;

    /**
     * pass the employee object to EmployeeDAO for update operation.
     *
     * @param employeeDTO - passing a employeeDTO object.
     * @return the object once the operation is done.
     * @throws EmsException - throws a String message.
     */
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmsException;

    /**
     * pass the employee object for assigning projects.
     *
     * @param employeeId - passing an id for object.
     * @param ids        - passing the ids for assign the object.
     * @return the object once the operation is done.
     * @throws EmsException - throws a String message.
     */
    EmployeeDTO assignProjectsForEmployee(int employeeId, List<Integer> ids)
            throws EmsException;
}