package com.server.model.dao;

import com.server.exception.DaoException;


public interface TaskDao
{
	void updateTaskProgress(String taskProgress, String name) throws DaoException;
}
