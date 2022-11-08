package com.ideas2it.employeeManagementSystem.dao;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.Constants;
import com.ideas2it.employeeManagementSystem.model.Project;
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
 * It is used for crud operations on the database
 * once the operation is done.
 * it will return the acknowledgment
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class ProjectDao {

    private static Logger logger = LogManager
            .getLogger(ProjectDao.class.getName());

    private ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    /**
     * Getting the project details and insert into database
     * @param project - get a project object for create operation
     * @return the acknowledgment
     * @throws EmsException
     */
    public int addProject(Project project) throws EmsException {
        int projectId;
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            session.beginTransaction();
            projectId = (int) session.save(project);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.FAILED_TO_ADD);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        logger.info("Project " + projectId + "has been created successfully");
        return projectId;
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
            Query query = session.createQuery("from Project");
            projectList = query.list();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.ERROR_404);
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
            throw new EmsException(Constants.FAILED_TO_UPDATE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * delete the project from database by project id
     * @param id - receive an int value
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
            throw new EmsException(Constants.FAILED_TO_DELETE);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    /**
     * getting the all project details from the database.
     * @return the project list
     * @throws EmsException
     */
    public List<Project> getProjectsByName(String projectName) throws EmsException {
        List<Project> searchedProjectList = new ArrayList<Project>();
        Session session = null;
        try {
            session = connectionUtil.getConnection();
            Criteria criteria = session.createCriteria(Project.class);
            searchedProjectList = (List<Project>) criteria.add(Restrictions
                    .like("projectName", (projectName + "%"))).list();
        } catch (HibernateException hibernateException) {
            logger.error(hibernateException.getMessage());
            throw new EmsException(Constants.ERROR_404);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return searchedProjectList;
    }
}
