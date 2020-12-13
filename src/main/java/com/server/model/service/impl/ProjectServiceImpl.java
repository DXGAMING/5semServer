package com.server.model.service.impl;

import com.server.exception.DaoException;
import com.server.exception.ServiceException;
import com.server.model.dao.ProjectDao;
import com.server.model.dao.impl.ProjectDaoImpl;
import com.server.model.entity.ProjectModel;
import com.server.model.service.ProjectService;

import java.time.LocalDate;
import java.util.List;


public class ProjectServiceImpl implements ProjectService
{
	private ProjectDao projectDao = ProjectDaoImpl.getInstance();

	@Override
	public List<ProjectModel> getAllProjects() throws ServiceException
	{
		try
		{
			return projectDao.findAllProjects();
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void addProject(final String projectName, final LocalDate startDate, final LocalDate endDate, final String clientName) throws ServiceException
	{

		try
		{
			projectDao.addProject(projectName, startDate, endDate, clientName);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
}
