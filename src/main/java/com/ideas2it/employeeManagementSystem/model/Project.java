package com.ideas2it.employeeManagementSystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class with the attributes project id, project name, domain,
 * project due, project start, project end, employee
 * initialize these attributes with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class Project {
    private int id;
    private String projectName;
    private String domain;
    private LocalDate projectDue;
    private LocalDate projectStart;
    private LocalDate projectEnd;

    private List<Employee> employee = new ArrayList<>();

    public Project (int id, String projectName, String domain,
                    LocalDate projectDue, LocalDate projectStart,
                    LocalDate projectEnd) {
        this.id = id;
        this.projectName = projectName;
        this.domain = domain;
        this.projectDue = projectDue;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
    }

    public Project(){
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

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
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
        if (employee != null){
            employee.forEach(e -> stringBuilder.append(e.getId() + " - ")
                    .append(e.getFirstName() + "\n                         "));
        } else {
            stringBuilder.append("Employee not assign");
        }
        return displayProject + "  " + stringBuilder;
    }
}
