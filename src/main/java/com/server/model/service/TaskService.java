package com.server.model.service;

import com.server.exception.ServiceException;


public interface TaskService
{
	void updateTaskProgress(String progress, String name) throws ServiceException;
}
