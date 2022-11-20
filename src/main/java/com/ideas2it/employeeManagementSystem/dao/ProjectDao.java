package com.ideas2it.employeeManagementSystem.dao;

import com.ideas2it.employeeManagementSystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer> {

    List<Project> findByProjectName(String name);

}
