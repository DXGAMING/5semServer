package com.server.model.dao.impl;

import com.server.exception.DaoException;
import com.server.model.connection.ConnectionPool;
import com.server.model.dao.TaskDao;
import com.server.model.dao.query.DatabaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TaskDaoImpl implements TaskDao
{
	private final Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);
	private static TaskDao instance;

	private TaskDaoImpl()
	{
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static TaskDao getInstance()
	{
		if (instance == null)
		{
			instance = new TaskDaoImpl();
		}
		return instance;
	}

	@Override
	public void updateTaskProgress(final String taskProgress, final String name) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try
		{
			connection.setAutoCommit(false);
			try (PreparedStatement statement = connection.prepareStatement(DatabaseQuery.UPDATE_TASK_PROGRESS_QUERY))
			{
				statement.setString(1, taskProgress);
				statement.setString(2, name);
				statement.executeUpdate();
				connection.commit();
			}
			catch (SQLException e)
			{
				connection.rollback();
				throw new DaoException("Error while updating task progress", e);
			}
		}
		catch (SQLException | DaoException e)
		{
			throw new DaoException("Error while updating task progress", e);
		}
		finally
		{
			try
			{
				connection.setAutoCommit(true);
			}
			catch (SQLException e)
			{
				logger.error("connection error", e);
			}
			connectionPool.releaseConnection(connection);
		}
	}
}
