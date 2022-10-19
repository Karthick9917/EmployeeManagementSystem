package com.ideas2it.employeeManagementSystem.model;

/**
 * Address class with the attributes house building number, street name,
 * area name, city name, pincode initialize these attributes
 * with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class Address {

    private int id;
    private String doorNumber;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String type;

    public Address(int id, String doorNumber, String street,
                      String city, String state, int pinCode, String type) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address() {
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

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return doorNumber + " "+ street + " "+ city + " "
                + state + " " + pinCode + " - " + type + " address" + "\n" +
                "                        ";
    }
}

