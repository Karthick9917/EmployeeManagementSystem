package com.ideas2it.employeeManagementSystem.model;

public class Project {
    private int id;
    private String name;
    private String project_due;
    private String project_start;
    private String project_end;

    public Project (int id, String name, String project_due, String project_start, String project_end) {
        this.id = id;
        this.name = name;
        this.project_due = project_due;
        this.project_start = project_start;
        this.project_end = project_end;
    }

    public Project(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_due() {
        return project_due;
    }

    public void setProject_due(String project_due) {
        this.project_due = project_due;
    }

    public String getProject_start() {
        return project_start;
    }

    public void setProject_start(String project_start) {
        this.project_start = project_start;
    }

    public String getProject_end() {
        return project_end;
    }

    public void setProject_end(String project_end) {
        this.project_end = project_end;
    }
}
