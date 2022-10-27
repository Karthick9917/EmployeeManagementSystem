package com.ideas2it.employeeManagementSystem.util;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    private static SessionFactory sessionfactory;

    private ConnectionUtil() {
    }

    /**
     * Create an object for custom connection.
     * @return - the custom Connection object.
     */
    public static ConnectionUtil getConnectionUtil() {
        if (connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }

    /**
     * This makes the connection between the database and java application.
     * @return - the connection
     */
    public Session getConnection() throws EmsException {
        if (sessionfactory == null) {
            try {
                sessionfactory = new Configuration().configure().buildSessionFactory();
                logger.info("Database connected successfully");
            } catch (HibernateException hibernateException) {
                logger.fatal("Database not connected");
                throw new EmsException("Connection failed..!!");
            }
        }
        return sessionfactory.openSession();
    }
}