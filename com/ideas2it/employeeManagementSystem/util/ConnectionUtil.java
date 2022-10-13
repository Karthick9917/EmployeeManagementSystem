package com.ideas2it.employeeManagementSystem.util;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class ConnectionUtil {

    private static Logger logger = LogManager.getLogger(ConnectionUtil.class.getName());
    private static ConnectionUtil connectionUtil;
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/employee_management_system";
    private static final String user = "root";
    private static final String pass = "K@rthick5665";

    private ConnectionUtil() {
    }

    /**
     * Create an object for custom connection.
     * @return - the custom Connection object.
     */
    public static ConnectionUtil getCustomConnection() {
        if (connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }

    /**
     * This makes the connection between the database and java application.
     * @return - the connection
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, pass);
                logger.info("Database connected sucessfully");
            } catch (SQLException sqlException) {
                logger.fatal("Database not connected");
                throw new EmsException("Connection failed..!!");
            }
        }
        return connection;
    }
}