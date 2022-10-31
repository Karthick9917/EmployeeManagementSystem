package com.ideas2it.employeeManagementSystem.mapper;

import com.ideas2it.employeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.employeeManagementSystem.model.Project;

/*
 * This class convert DTO object to model object and model object to DTO object
 *
 * @version	1.8.0_281
 * @author	Karthick
 */
public class ProjectMapper {

    /**
     * Convert projectDto object to projectModel Object
     * @param projectDTO - passing the object
     * @return project object
     */
    public static Project toProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setDomain(projectDTO.getDomain());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectDue(projectDTO.getProjectDue());
        project.setProjectStart(projectDTO.getProjectStart());
        project.setProjectEnd(projectDTO.getProjectEnd());
        return project;
    }

    /**
     * Convert projectModel object to projectDto Object
     * @param project - passing the object
     * @return projectDto object
     */
    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setDomain(project.getDomain());
        projectDTO.setProjectDue(project.getProjectDue());
        projectDTO.setProjectStart(project.getProjectStart());
        projectDTO.setProjectEnd(project.getProjectEnd());
        return projectDTO;
    }
}
