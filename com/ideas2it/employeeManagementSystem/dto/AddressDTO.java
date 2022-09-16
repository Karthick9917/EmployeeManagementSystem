package com.ideas2it.employeeManagementSystem.dto;

/**
 * This class with the attributes house building number, street name,
 * area name, city name, pincode initialize these attributes
 * with the help of constructor
 *
 *@version    1.8.0_281
 *@author     Karthick
 */
public class AddressDTO {

    private int buildingNumber;
    private String street;
    private String area;
    private String city;
    private int pincode;

    public AddressDTO(int buildingNumber, String street, String area,
                      String city, int pincode) {
        this.buildingNumber = buildingNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.pincode = pincode;
    }

    public AddressDTO() {
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String toString() {
        return buildingNumber + ", "+ street + ", " + area + "," +
                " " + city + ", " + pincode;
    }
}

