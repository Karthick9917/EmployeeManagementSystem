package com.ideas2it.employeeManagementSystem.dto;

import static java.lang.String.format;

/**
 * This class with the attributes employee id, name, salary, address
 * initialize these attributes with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class EmployeeDTO {
    private String id;
    private String name;
    private long phoneNumber;
    private int salary;
    private String dateOfJoining;
    private AddressDTO addressDTO;

    public EmployeeDTO(String id, String name, long phoneNumber, int salary, String dateOfJoining, AddressDTO addressDTO) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.addressDTO = addressDTO;
        this.dateOfJoining = dateOfJoining;
    }

    public EmployeeDTO() {
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

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }
    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String toString() {
        String displayEmployeeDetails = format("%-18s %-20s %-13s %-18d %-15s", id, name, phoneNumber, salary, dateOfJoining);
        return displayEmployeeDetails + addressDTO;
    }
}
