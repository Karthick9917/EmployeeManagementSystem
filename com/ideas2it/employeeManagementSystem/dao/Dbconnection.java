package com.ideas2it.employeeManagementSystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    private static final String url = "jdbc:mysql:// localhost:3306/employee_management_system";
    private static final String user = "root";
    private static final String pass = "K@rthick5665";
    private static Connection connection = null;

    private Dbconnection(){
    }
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException sqlException ){
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

