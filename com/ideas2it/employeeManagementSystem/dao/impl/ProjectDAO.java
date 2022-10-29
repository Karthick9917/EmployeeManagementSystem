package com.ideas2it.employeeManagementSystem.dao.impl;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.model.Employee;
import com.ideas2it.employeeManagementSystem.model.Project;
import com.ideas2it.employeeManagementSystem.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private static Logger logger = LogManager.getLogger(ProjectDAO.class.getName());

    private ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    /**
     * Getting the project details and insert into database
     * @param project - get a project object for create operation
     * @return the acknowledgment
     * @throws EmsException
     */
    public boolean addProject(Project project) throws EmsException {
        int projectId;
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            projectId = (int) session.save(project);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.NOT_ADDED_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        logger.info("Project " + projectId + "has been created successfully");
        return projectId > 0;
    }

    /**
     * Getting all the projects from database.
     * @return the list of all the project.
     * @throws EmsException
     */
    public List<Project> getAllProject() throws EmsException {
        Session session = null;
        List<Project> projectList = new ArrayList<Project>();
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            Query query = session.createQuery("from Project");
            projectList = query.list();
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.RECORD_EMPTY_MESSAGE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return projectList;
    }

    /**
     * To update project on the database.
     * @param project - get a project object for update operation
     * @throws  EmsException
     */
    public void updateProject(Project project) throws EmsException {
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            session.update(project);
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

    /**
     * delete the project from database by project id
     * @param id - receive an int value
     * @return the acknowledgement
     * @throws EmsException
     */
    public void deleteProject(int id) throws EmsException {
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            Project project = session.get(Project.class, id);
            session.remove(project);
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
}
