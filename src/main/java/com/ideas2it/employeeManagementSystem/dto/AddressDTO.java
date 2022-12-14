package com.ideas2it.employeeManagementSystem.dto;

/**
 * This class with the attributes door number, street,
 * city, state, pincode initialize these attributes
 * with the help of constructor
 *
 * @author Karthick
 * @version 1.8.0_281
 */
public class AddressDTO {
    private int id;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private int pincode;
    private String type;

    public AddressDTO(int id, String buildingNumber, String street,
                      String city, String state, int pincode, String type) {
        this.doorNumber = buildingNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.type = type;
    }

    public AddressDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return doorNumber + " " + street + " " + city + " "
                + state + " " + pincode + " - " + type + " address" + "\n" +
                "                        ";
    }
}
