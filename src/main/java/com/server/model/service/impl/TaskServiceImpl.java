package com.server.model.service.impl;

import com.server.exception.DaoException;
import com.server.exception.ServiceException;
import com.server.model.dao.ProjectDao;
import com.server.model.dao.TaskDao;
import com.server.model.dao.impl.ProjectDaoImpl;
import com.server.model.dao.impl.TaskDaoImpl;
import com.server.model.service.TaskService;


public class TaskServiceImpl implements TaskService
{
	private TaskDao taskDao = TaskDaoImpl.getInstance();

	@Override
	public void updateTaskProgress(final String progress, final String name) throws ServiceException
	{
		try
		{
			taskDao.updateTaskProgress(progress, name);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
}
