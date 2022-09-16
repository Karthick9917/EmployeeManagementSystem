package com.ideas2it.employeeManagementSystem.model;

import static java.lang.String.format;

/**
 * This class with the attributes employee id, name, salary, address
 * initialize these attributes with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class Employee {
    private String id;
    private String name;
    private long phoneNumber;
    private int salary;
    private String dateOfJoining;
    private Address address;

    public Employee(String id, String name, long phoneNumber, int salary, String dateOfJoining, Address address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        String displayEmployeeDetails = format("%-18s %-20s %-13s %-18d %-15s", id, name, phoneNumber, salary, dateOfJoining);
        return displayEmployeeDetails + address;
    }
}