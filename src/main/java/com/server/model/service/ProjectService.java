package com.server.model.service;

import com.server.exception.ServiceException;
import com.server.model.entity.ProjectModel;

import java.time.LocalDate;
import java.util.List;


public interface ProjectService
{
	List<ProjectModel> getAllProjects() throws ServiceException;

	void addProject(String projectName, LocalDate startDate, LocalDate endDate, String clientName) throws ServiceException;
}
