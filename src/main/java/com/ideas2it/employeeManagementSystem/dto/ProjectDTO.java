package com.ideas2it.employeeManagementSystem.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class with the attributes projectDTO id, project name, domain,
 * project due, project start, project end, employeeDTO
 * initialize these attributes with the help of constructor
 *
 * @author Karthick
 * @version 1.8.0_281
 */
public class ProjectDTO {
    private int id;
    private String projectName;
    private String domain;
    private LocalDate projectDue;
    private LocalDate projectStart;
    private LocalDate projectEnd;
    private List<EmployeeDTO> employeeDTO = new ArrayList<>();

    public ProjectDTO(int id, String projectName, String domain,
                      LocalDate projectDue, LocalDate projectStart,
                      LocalDate projectEnd, List<EmployeeDTO> employeeDTO) {
        this.id = id;
        this.projectName = projectName;
        this.domain = domain;
        this.projectDue = projectDue;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.employeeDTO = employeeDTO;
    }

    public ProjectDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public LocalDate getProjectDue() {
        return projectDue;
    }

    public void setProjectDue(LocalDate projectDue) {
        this.projectDue = projectDue;
    }

    public LocalDate getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(LocalDate projectStart) {
        this.projectStart = projectStart;
    }

    public LocalDate getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(LocalDate projectEnd) {
        this.projectEnd = projectEnd;
    }

    public List<EmployeeDTO> getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(List<EmployeeDTO> employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public String toString() {
        String displayProject = "\nProject Id            :  " + id +
                "\nProject Name          :  " + projectName +
                "\nDomain                :  " + domain +
                "\nProject due           :  " + projectDue +
                "\nProject Start         :  " + projectStart +
                "\nProject end           :  " + projectEnd +
                "\nEmployee              :";
        StringBuilder stringBuilder = new StringBuilder();
        if (!employeeDTO.isEmpty()) {
            employeeDTO.forEach(e -> stringBuilder.append(e.getId())
                    .append(" - ")
                    .append(e.getFirstName())
                    .append("\n                         "));
        } else {
            stringBuilder.append("Employee not assign");
        }
        return displayProject + "  " + stringBuilder;
    }
}
