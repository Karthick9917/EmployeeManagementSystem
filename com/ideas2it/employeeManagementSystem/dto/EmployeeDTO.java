package com.ideas2it.employeeManagementSystem.dto;


import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

/**
 * This class with the attributes employeeDTO id, first name, last name,
 * date of birth, salary, gender, email,phone number, addressDTO
 * initialize these attributes with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private double salary;
    private String gender;
    private String email;
    private long phoneNumber;
    private LocalDate dateOfJoining;

    private String role;
    private List<AddressDTO> addressDTO;

    public EmployeeDTO(String firstName, String lastName,
                       LocalDate dateOfBirth, double salary, String gender,
                       String email, long phoneNumber, LocalDate dateOfJoining, String role,
                       List<AddressDTO> addressDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.role = role;
        this.addressDTO = addressDTO;
    }
    public EmployeeDTO() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AddressDTO> getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(List<AddressDTO> addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String toString() {
        String displayEmployeeDetails = "\nEmployee Id           :  " + id +
                "\nFirst Name            :  " + firstName +
                "\nLast Name             :  " + lastName +
                "\nDate Of Birth         :  " + dateOfBirth +
                "\nSalary                :  " + salary +
                "\nGender                :  " + gender +
                "\nEmail                 :  " + email +
                "\nPhoneNumber           :  +91" + phoneNumber +
                "\nDate Of Joining       :  " + dateOfJoining +
                "\nAddress               :";

        return displayEmployeeDetails + "  " + addressDTO.toString().replace(
                "[","").replace("]","").replace(",", "");
    }
}
