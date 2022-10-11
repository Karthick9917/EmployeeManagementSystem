package com.ideas2it.employeeManagementSystem.mapper;

import com.ideas2it.employeeManagementSystem.dto.AddressDTO;
import com.ideas2it.employeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.employeeManagementSystem.model.Address;
import com.ideas2it.employeeManagementSystem.model.Employee;

import java.util.ArrayList;
import java.util.List;


/*
 * This class convert DTO object to model object and model to DTO
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeMapper {

    /**
     * Convert employeeDTO object to employeeModel Object
     *
     * @param employeeDTO object
     * @return employee
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        List<Address> address = new ArrayList<Address>();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        if(null != employeeDTO.getAddressDTO()) {
            for(AddressDTO addressDTO: employeeDTO.getAddressDTO()){
                address.add(toAddress(addressDTO));
            }
            employee.setAddress(address);
        }
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
        address.setDoorNumber(addressDTO.getDoorNumber());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPincode(addressDTO.getPincode());
        address.setType(addressDTO.getType());
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
        List<AddressDTO> addressDTO = new ArrayList<AddressDTO>();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setDateOfJoining(employee.getDateOfJoining());
        if(null != employee.getAddress()) {
            for(Address address: employee.getAddress()){
                addressDTO.add(toAddressDTO(address));
            }
            employeeDTO.setAddressDTO(addressDTO);
        }
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
        addressDTO.setDoorNumber(address.getDoorNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPincode(address.getPincode());
        addressDTO.setType(address.getType());
        return addressDTO;
    }
}
