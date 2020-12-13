package com.server.model.dao;

import com.server.exception.DaoException;
import com.server.model.entity.ProjectModel;

import java.time.LocalDate;
import java.util.List;


public interface ProjectDao
{
	List<ProjectModel> findAllProjects() throws DaoException;

	void addProject(String projectName, LocalDate startDate, LocalDate endDate, String clientName) throws DaoException;
}
