package com.ideas2it.employeeManagementSystem.dao;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.dao.impl.Dao;
import com.ideas2it.employeeManagementSystem.model.Address;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.util.CustomConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Getting employee
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class EmployeeDao implements Dao {

    Connection connection = CustomConnection.getCustomConnection().getConnection();

    /**
     * Getting the employee's address details and insert into database
     * @param employee - get an employee object for create operation
     * @return the acknowledgment
     * @throws SQLException, EmsException
     */
    public boolean createEmployeeDetails (Employee employee)throws SQLException, EmsException{
        int employeeId = 0;
        int employeeAdded;
        PreparedStatement preparedStatement = connection.prepareStatement("insert " +
                "into employee (first_name, last_name, date_of_birth, " +
                "salary, gender, email, phone_number, date_of_joining)" +
                " values (?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
        preparedStatement.setDouble(4, employee.getSalary());
        preparedStatement.setString(5, employee.getGender());
        preparedStatement.setString(6, employee.getEmail());
        preparedStatement.setLong(7, employee.getPhoneNumber());
        preparedStatement.setDate(8, Date.valueOf(employee.getDateOfJoining()));
        employeeAdded = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.executeQuery("select " +
                "employee_id from employee where email = " +
                "'" + employee.getEmail() + "'");
        while (resultSet.next()) {
            employeeId = resultSet.getInt("employee_id");
        }
        boolean isAdded = addAddress(employee, employeeId);
        preparedStatement.close();
        if (employeeAdded < 1 && isAdded != true) {
            throw new EmsException(EmployeeConstants.NOT_ADDED_MESSAGE);
        }
        return isAdded;
    }

    /**
     * Getting the employee's address details and insert into database
     * @param employee - get an employee object for create operation
     * @param employeeId - receive an int value
     * @return the acknowledgment
     * @throws SQLException, EmsException
     */
    public boolean addAddress(Employee employee, int employeeId) throws SQLException, EmsException {
        PreparedStatement insertStatement = null;
        int addAddress = 0;
        for (Address address : employee.getAddress()) {
            insertStatement = connection.prepareStatement("insert" +
                    " into address (employee_id, door_no, street, city," +
                    " state, pincode, type) values (?,?,?,?,?,?,?)");
            insertStatement.setInt(1, employeeId);
            insertStatement.setInt(2, address.getDoorNumber());
            insertStatement.setString(3, address.getStreet());
            insertStatement.setString(4, address.getCity());
            insertStatement.setString(5, address.getState());
            insertStatement.setInt(6, address.getPincode());
            insertStatement.setString(7, address.getType());
            addAddress = insertStatement.executeUpdate();
        }
        insertStatement.close();
        return (addAddress != 0);
    }

    /**
     * Getting all the employees from database
     * @return the list of all employee
     * @throws SQLException, EmsException
     */
    public List readEmployeeDetails() throws SQLException, EmsException {
        List<Employee> employeeList = new ArrayList<Employee>();
        Statement statement = connection.createStatement();
        ResultSet resultSetEmployee = statement.executeQuery("select" +
                " employee_id, first_name, last_name," +
                " date_of_birth, salary, gender, email," +
                " phone_number, date_of_joining from employee");
        while (resultSetEmployee.next()) {
            Employee employee = new Employee();
            int employeeId = resultSetEmployee.getInt("employee_id");
            employee.setId(employeeId);
            employee.setFirstName(resultSetEmployee.getString("first" +
                    "_name"));
            employee.setLastName((resultSetEmployee.getString("last" +
                    "_name")));
            employee.setDateOfBirth(LocalDate.parse(resultSetEmployee.getString("date" +
                    "_of_birth")));
            employee.setSalary(resultSetEmployee.getInt("salary"));
            employee.setGender(resultSetEmployee.getString("gender"));
            employee.setEmail(resultSetEmployee.getString("email"));
            employee.setPhoneNumber(resultSetEmployee.getLong("phone" +
                    "_number"));
            employee.setDateOfJoining(resultSetEmployee.getDate("date" +
                    "_of_joining").toLocalDate());
            List<Address> addressList = readEmployeeAddress(employeeId);
            employee.setAddress(addressList);
            employeeList.add(employee);
        }
        statement.close();
        if (employeeList.isEmpty()) {
            throw new EmsException(EmployeeConstants.RECORD_EMPTY_MESSAGE);
        }
        return employeeList;
    }

    /**
     * Getting all the employee's address from the database
     * @param employeeId - receive an int value
     * @return the list of employee's address
     * @throws SQLException, EmsException
     */
    public List readEmployeeAddress(int employeeId) throws SQLException, EmsException {
        Statement statement = connection.createStatement();
        ResultSet resultSetAddress = statement.executeQuery("select" +
                " * from address where employee_id = " + employeeId);
        List<Address> addressList = new ArrayList<>();
        while (resultSetAddress.next()) {
            Address address = new Address();
            address.setDoorNumber(resultSetAddress.getInt("door_no"));
            address.setStreet(resultSetAddress.getString("street"));
            address.setCity(resultSetAddress.getString("city"));
            address.setState(resultSetAddress.getString("state"));
            address.setPincode(resultSetAddress.getInt("pincode"));
            address.setType(resultSetAddress.getString("type"));
            addressList.add(address);
        }
        statement.close();
        return addressList;
    }

    /**
     * delete the employee from database by employee id
     * @param employeeId - receive an int value
     * @return the acknowledgement
     * @throws SQLException, EmsException
     */
    public boolean deleteEmployeeDetails(int employeeId) throws SQLException, EmsException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from employee where employee_id = ?");
        preparedStatement.setInt(1, employeeId);
        int isDeleted = preparedStatement.executeUpdate();
        preparedStatement.close();
        if ((isDeleted == 0)) {
            throw new EmsException(EmployeeConstants.NOT_DELETED_MESSAGE);
        }
        return (isDeleted != 0);
    }

    /**
     * getting the all employees details from the database
     *
     * @return the employees list
     * @throws SQLException, EmsException
     */
    public List searchEmployee() throws SQLException, EmsException {
        return readEmployeeDetails();
    }

    /**
     * Get an employee object for update operation
     * @param employee - get an employee object for update operation
     * @return the acknowledgement
     * @throws SQLException, EmsException
     */
    public boolean updateEmployeeDetails(Employee employee) throws SQLException, EmsException {
        PreparedStatement preparedStatement = connection.prepareStatement("" +
                "update employee set first_name = ?, last_name = ?," +
                " date_of_birth = ?, salary = ?, gender = ?, email = ?," +
                " phone_number = ?, date_of_joining = ? where employee_id = ?");
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setDate(3, Date.valueOf(employee.getDateOfBirth()));
        preparedStatement.setDouble(4, employee.getSalary());
        preparedStatement.setString(5, employee.getGender());
        preparedStatement.setString(6, employee.getEmail());
        preparedStatement.setLong(7, employee.getPhoneNumber());
        preparedStatement.setDate(8, Date.valueOf(employee.getDateOfJoining()));
        preparedStatement.setInt(9, employee.getId());
        preparedStatement.executeUpdate();
        boolean isUpdated = updateAddress(employee);
        preparedStatement.close();
        if (!isUpdated) {
            throw new EmsException(EmployeeConstants.NOT_UPDATED_MESSAGE);
        }
        return isUpdated;
    }

    /**
     * update the employee's address to the database
     * @param employee - get an employee object for address update operation
     * @return the acknowledgement
     * @throws SQLException, EmsException
     */
    public boolean updateAddress(Employee employee) throws SQLException, EmsException {
        PreparedStatement insertStatement = null;
        for (Address address : employee.getAddress()) {
            insertStatement = connection.prepareStatement(
                    "update address set door_no = ?, street = ?, city = ?," +
                            " state = ?, pincode = ?, type = ? where employee_id = ?");
            insertStatement.setInt(1, address.getDoorNumber());
            insertStatement.setString(2, address.getStreet());
            insertStatement.setString(3, address.getCity());
            insertStatement.setString(4, address.getState());
            insertStatement.setInt(5, address.getPincode());
            insertStatement.setString(6, address.getType());
            insertStatement.setInt(7, employee.getId());
            insertStatement.addBatch();
        }
        int updated = insertStatement.executeBatch().length;
        insertStatement.close();
        return (updated !=0);
    }
}

