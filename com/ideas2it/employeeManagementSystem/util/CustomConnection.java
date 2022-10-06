package com.ideas2it.employeeManagementSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This makes the connection between the database and our application.
 * From this connection we can manipulate the data in database.
 *
 * @version 1.8.0_281
 * @author	Karthick
 */
public class CustomConnection {

    private static CustomConnection customConnection;
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/employee_management_system";
    private static final String user = "root";
    private static final String pass = "K@rthick5665";

    private CustomConnection() {
    }

    /**
     * Create an object for custom connection.
     * @return - the custom Connection object.
     */
    public static CustomConnection getCustomConnection() {
        if (customConnection == null) {
            customConnection = new CustomConnection();
        }
        return customConnection;
    }

    /**
     * This makes the connection between the database and java application.
     * @return - the connection
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return connection;
    }
}