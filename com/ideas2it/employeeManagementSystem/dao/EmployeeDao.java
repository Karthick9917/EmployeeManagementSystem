package com.ideas2it.employeeManagementSystem.dao;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.dao.impl.Dao;
import com.ideas2it.employeeManagementSystem.model.Address;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.util.ConnectionUtil;
import com.ideas2it.employeeManagementSystem.view.EmployeeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static Logger logger = LogManager.getLogger(EmployeeView.class.getName());
    Connection connection = ConnectionUtil.getCustomConnection().getConnection();

    /**
     * Getting the employee's address details and insert into database
     * @param employee - get an employee object for create operation
     * @return the acknowledgment
     * @throws EmsException
     */
    public boolean createEmployeeDetails (Employee employee) throws EmsException {
        int employeeId = 0;
        int employeeAdded;
        boolean isAdded;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("insert " +
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
            isAdded = addAddress(employee, employeeId);
            preparedStatement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_ADDED_MESSAGE);
        }
        logger.info("Employee " + employeeId + "has been created successfully");
        return (employeeAdded != 0  && isAdded);
    }

    /**
     * Getting the employee's address details and insert into database
     * @param employee - get an employee object for create operation
     * @param employeeId - receive an int value
     * @return the acknowledgment
     * @throws EmsException
     */
    public boolean addAddress(Employee employee, int employeeId) {
        PreparedStatement insertStatement = null;
        int addAddress = 0;
        try {
            for (Address address : employee.getAddress()) {
                insertStatement = connection.prepareStatement("insert" +
                        " into address (employee_id, door_no, street, city," +
                        " state, pincode, type) values (?,?,?,?,?,?,?)");
                insertStatement.setInt(1, employeeId);
                insertStatement.setString(2, address.getDoorNumber());
                insertStatement.setString(3, address.getStreet());
                insertStatement.setString(4, address.getCity());
                insertStatement.setString(5, address.getState());
                insertStatement.setInt(6, address.getPincode());
                insertStatement.setString(7, address.getType());
                addAddress = insertStatement.executeUpdate();
            }
            insertStatement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_ADDED_MESSAGE);
        }
        return (addAddress != 0);
    }

    /**
     * Getting all the employees from database
     * @return the list of all employee
     * @throws EmsException
     */
    public List<Employee> readEmployeeDetails() throws EmsException {
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
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
                List<Address> addressList = employeeAddress(employeeId);
                employee.setAddress(addressList);
                employeeList.add(employee);
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.RECORD_EMPTY_MESSAGE);
        }
        return employeeList;
    }

    /**
     * Getting all the employee's address from the database
     * @param employeeId - receive an int value
     * @return the list of employee's address
     * @throws EmsException
     */
    public List employeeAddress(int employeeId) throws EmsException {
        List<Address> addressList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetAddress = statement.executeQuery("select" +
                    " * from address where employee_id = " + employeeId);
            while (resultSetAddress.next()) {
                Address address = new Address();
                address.setDoorNumber(resultSetAddress.getString("door_no"));
                address.setStreet(resultSetAddress.getString("street"));
                address.setCity(resultSetAddress.getString("city"));
                address.setState(resultSetAddress.getString("state"));
                address.setPincode(resultSetAddress.getInt("pincode"));
                address.setType(resultSetAddress.getString("type"));
                addressList.add(address);
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.RECORD_EMPTY_MESSAGE);
        }
        return addressList;
    }

    /**
     * delete the employee from database by employee id
     * @param employeeId - receive an int value
     * @return the acknowledgement
     * @throws EmsException
     */
    public boolean deleteEmployeeDetails(int employeeId) throws EmsException {
        int deleted;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from employee where employee_id = ?");
            preparedStatement.setInt(1, employeeId);
            deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_DELETED_MESSAGE);
        }
        logger.info("Employee " + employeeId + "has been removed successfully");
        return (deleted != 0);
    }

    /**
     * getting the all employees details from the database
     *
     * @return the employees list
     * @throws EmsException
     */
    public List<Employee> searchEmployee(String firstName) throws EmsException {
        List<Employee> searchEmployeeList = new ArrayList<Employee>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetEmployee = statement.executeQuery("select * " +
                    "from employee where first_name like '" + firstName + "%'" );
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
                List<Address> addressList = employeeAddress(employeeId);
                employee.setAddress(addressList);
                searchEmployeeList.add(employee);
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.RECORD_EMPTY_MESSAGE);
        }
        return searchEmployeeList;
    }

    /**
     * Get an employee object for update operation
     * @param employee - get an employee object for update operation
     * @return the acknowledgement
     * @throws  EmsException
     */
    public boolean updateEmployeeDetails(Employee employee, int employeeId) throws EmsException {
        int employeeUpdated;
        boolean isUpdated;
        try {
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
            preparedStatement.setInt(9, employeeId);
            employeeUpdated = preparedStatement.executeUpdate();
            isUpdated = updateAddress(employee);
            preparedStatement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_UPDATED_MESSAGE);
        }
        logger.info("Employee " + employeeId + "has been updated successfully");
        return (employeeUpdated < 1 && !isUpdated);
    }

    /**
     * update the employee's address to the database
     * @param employee - get an employee object for address update operation
     * @return the acknowledgement
     * @throws EmsException
     */
    public boolean updateAddress(Employee employee) throws EmsException {
        PreparedStatement insertStatement = null;
        int updated;
        try {
            for (Address address : employee.getAddress()) {
                insertStatement = connection.prepareStatement(
                        "update address set door_no = ?, street = ?, city = ?," +
                                " state = ?, pincode = ?, type = ? where employee_id = ?");
                insertStatement.setString(1, address.getDoorNumber());
                insertStatement.setString(2, address.getStreet());
                insertStatement.setString(3, address.getCity());
                insertStatement.setString(4, address.getState());
                insertStatement.setInt(5, address.getPincode());
                insertStatement.setString(6, address.getType());
                insertStatement.setInt(7, employee.getId());
                insertStatement.addBatch();
            }
            updated = insertStatement.executeBatch().length;
            insertStatement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_UPDATED_MESSAGE);
        }
        return (updated != 0);
    }
}

