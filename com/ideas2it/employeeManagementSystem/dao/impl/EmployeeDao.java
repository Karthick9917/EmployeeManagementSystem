package com.ideas2it.employeeManagementSystem.dao.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.dao.Dao;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

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

    private static Logger logger = LogManager.getLogger(EmployeeDao.class.getName());

    private ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    /**
     * Getting the employee's details and insert into database
     * @param employee - get an employee object for create operation
     * @return the acknowledgment
     * @throws EmsException
     */
    public boolean createEmployeeDetails (Employee employee) throws EmsException {
        int employeeId;
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            employeeId = (int) session.save(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.NOT_ADDED_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        logger.info("Employee " + employeeId + "has been created successfully");
        return employeeId > 0;
    }

    /**
     * Getting all the employees from database
     * @return the list of all employee
     * @throws EmsException
     */
    public List<Employee> readEmployeeDetails() throws EmsException {
        Session session = null;
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            Query query = session.createQuery("from Employee");
            employeeList = query.list();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.RECORD_EMPTY_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return employeeList;
    }

    /**
     * delete the employee from database by employee id
     * @param employeeId - receive an int value
     * @return the acknowledgement
     * @throws EmsException
     */
    public void deleteEmployeeDetails(int employeeId) throws EmsException {
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            session.remove(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.NOT_DELETED_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * getting the all employees details from the database.
     * @return the employees list
     * @throws EmsException
     */
    public List<Employee> searchEmployee(String firstName) throws EmsException {
        List<Employee> searchEmployeeList = new ArrayList<Employee>();
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            searchEmployeeList = (List<Employee>) criteria.add(Restrictions.like("firstName", (firstName + "%"))).list();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.RECORD_EMPTY_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return searchEmployeeList;
    }

    /**
     * Get an employee object for update operation
     * @param employee - get an employee object for update operation
     * @throws  EmsException
     */
    public void updateEmployeeDetails(Employee employee) throws EmsException {
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.NOT_UPDATED_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}

