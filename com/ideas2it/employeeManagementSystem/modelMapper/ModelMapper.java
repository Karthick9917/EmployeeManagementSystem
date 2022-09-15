package com.ideas2it.employeeManagementSystem.modelMapper;

import com.ideas2it.employeeManagementSystem.model.Address;
import com.ideas2it.employeeManagementSystem.model.AddressDTO;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.EmployeeDTO;

/*
 * This class convert DTO object to model object and model to DTO
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class ModelMapper {

    /**
     * Convert employeeDTO object to employeeModel Object
     *
     * @param employeeDTO object
     * @return employee
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        employee.setAddress(toAddress(employeeDTO.getAddressDTO()));
        return employee;
    }

    /**
     * Convert addressDTO object to addressModel Object
     *
     * @param addressDTO object
     * @return employee
     */
    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        address.setStreet(addressDTO.getStreet());
        address.setArea(addressDTO.getArea());
        address.setCity(addressDTO.getCity());
        address.setPincode(addressDTO.getPincode());
        return address;
    }

    /**
     * Convert employeeModel object to employeeDTO Object
     *
     * @param employee object
     * @return employeeDTO
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDateOfJoining(employee.getDateOfJoining());
        employeeDTO.setAddressDTO(toAddressDTO(employee.getAddress()));
        return employeeDTO;
    }

    /**
     * Convert employeeModel object to employeeDTO Object
     *
     * @param address object
     * @return addressDTO
     */
    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setBuildingNumber(address.getBuildingNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setArea(address.getArea());
        addressDTO.setCity(address.getCity());
        addressDTO.setPincode(address.getPincode());
        return addressDTO;
    }
}
