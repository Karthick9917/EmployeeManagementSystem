package com.ideas2it.employeeManagementSystem.model;

import static java.lang.String.format;

/**
 * Employee class with the attributes employee id, name, salary, address
 * initialize these attributes with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class Employee {
    private String id;
    private String name;
    private int salary;
    private String dateOfJoining;
    private Address address;

    public Employee(String id, String name, int salary, String dateOfJoining, Address address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.dateOfJoining = dateOfJoining;
    }

    public Employee () {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        String displayEmployeeDetails = format("%-18s %-20s %-18d %-15s", id, name, salary, dateOfJoining);
        return displayEmployeeDetails + address;
    }
}