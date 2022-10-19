package com.ideas2it.employeeManagementSystem.dao;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;
import com.ideas2it.employeeManagementSystem.dao.impl.Dao;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.view.EmployeeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
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

    private static Logger logger = LogManager.getLogger(EmployeeView.class.getName());

    SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();

    /**
     * Getting the employee's address details and insert into database
     * @param employee - get an employee object for create operation
     * @return the acknowledgment
     * @throws EmsException
     */
    public boolean createEmployeeDetails (Employee employee) throws EmsException {
        int employeeId;
        Session session = null;
        try {
            session = sessionfactory.openSession();
            session.beginTransaction();
            employeeId = (int) session.save(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            session.getTransaction.rollback();
            throw new EmsException(EmployeeConstants.NOT_ADDED_MESSAGE);
        } finally {
            session.close();
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
        List<Employee> employeeList = new ArrayList<Employee>();
        Session session = null;
        try {
            session = sessionfactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Employee");
            employeeList = query.list();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(EmployeeConstants.RECORD_EMPTY_MESSAGE);
        } finally {
            session.close();
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
        Employee employee;
        try {
            session = sessionfactory.openSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            session.remove(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_DELETED_MESSAGE);
        }

    }

    /**
     * getting the all employees details from the database
     *
     * @return the employees list
     * @throws EmsException
     */
    public List<Employee> searchEmployee(String firstName) throws EmsException {
        List<Employee> searchEmployeeList = new ArrayList<Employee>();
        Session session = null;
        try {
            session = sessionfactory.openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            searchEmployeeList.add((Employee) criteria.add(Restrictions.like("firstName", (firstName + "%"))).uniqueResult());
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(EmployeeConstants.RECORD_EMPTY_MESSAGE);
        } finally {
            session.close();
        }
        return searchEmployeeList;
    }

    /**
     * Get an employee object for update operation
     * @param employee - get an employee object for update operation
     * @return the acknowledgement
     * @throws  EmsException
     */
    public boolean updateEmployeeDetails(Employee employee) throws EmsException {
        Session session = null;
        Employee updateEmployee;
        try {
            session = sessionfactory.openSession();
            session.beginTransaction();
            updateEmployee = (Employee) session.merge(employee);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(EmployeeConstants.NOT_UPDATED_MESSAGE);
        } finally {
            session.close();
        }
        return updateEmployee != null;
    }
}

