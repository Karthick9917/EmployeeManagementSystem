package com.ideas2it.employeeManagementSystem.mapper;

import com.ideas2it.employeeManagementSystem.dto.AddressDTO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.model.Address;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

    /**
     * Convert projectDto object to projectModel Object
     * @param projectDTO - passing the object
     * @return project object
     */
    public static Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        List<Employee> employee = new ArrayList<Employee>();
        project.setId(projectDTO.getId());
        project.setDomain(projectDTO.getDomain());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDue(projectDTO.getProjectDue());
        project.setProjectStart(projectDTO.getProjectStart());
        project.setProjectEnd(projectDTO.getProjectEnd());
        if(null != projectDTO.getEmployeeDTO()) {
            for(EmployeeDTO employeeDTO: projectDTO.getEmployeeDTO()){
                employee.add(toEmployee(employeeDTO));
            }
            project.setEmployee(employee);
        }
        return project;
    }

    /**
     * Convert addressDTO object to addressModel Object
     *
     * @param employeeDTO object
     * @return employee
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        return employee;
    }

    /**
     * Convert projectModel object to projectDto Object
     * @param project - passing the object
     * @return projectDto object
     */
    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        List<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
        projectDTO.setId(project.getId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setDomain(project.getDomain());
        projectDTO.setProjectDue(project.getProjectDue());
        projectDTO.setProjectStart(project.getProjectStart());
        projectDTO.setProjectEnd(project.getProjectEnd());
        if(null != project.getEmployee()) {
            for(Employee employee: project.getEmployee()){
                employeeDTO.add(toEmployeeDTO(employee));
            }
            projectDTO.setEmployeeDTO(employeeDTO);
        }
        return projectDTO;
    }

    /**
     * Convert addressDTO object to addressModel Object
     *
     * @param employee object
     * @return employee
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        return employeeDTO;
    }
}
