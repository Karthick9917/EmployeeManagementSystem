package com.ideas2it.employeeManagementSystem.model;

import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

/**
 * This class with the attributes employee id, first name, last name,
 * date of birth, salary, gender, email,phone number, address
 * initialize these attributes with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double salary;
    private String gender;
    private String email;
    private long phoneNumber;
    private LocalDate dateOfJoining;
    private List<Address> address;

    public Employee(int id, String firstName, String lastName,
                    LocalDate dateOfBirth, double salary, String gender,
                    String email, long phoneNumber, LocalDate dateOfJoining,
                    List<Address> address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.address = address;
    }
    public Employee (){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String toString() {
        String displayEmployeeDetails = format("%-18s %-20s %-10s %-13s %-9d %-13s %-19s %-13s %-10s", id, firstName, lastName, dateOfBirth, salary, gender, email, phoneNumber, dateOfJoining);
        return displayEmployeeDetails + address;
    }
}