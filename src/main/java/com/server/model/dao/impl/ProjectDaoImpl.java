package com.server.model.dao.impl;

import com.server.exception.DaoException;
import com.server.model.connection.ConnectionPool;
import com.server.model.dao.ProjectDao;
import com.server.model.dao.query.DatabaseQuery;
import com.server.model.entity.ProjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ProjectDaoImpl implements ProjectDao
{
	private final Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);
	private static ProjectDao instance;

	private ProjectDaoImpl()
	{
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static ProjectDao getInstance()
	{
		if (instance == null)
		{
			instance = new ProjectDaoImpl();
		}
		return instance;
	}

	@Override
	public List<ProjectModel> findAllProjects() throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SHOW_PROJECTS_QUERY))
		{
			List<ProjectModel> projects = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				ProjectModel project = getProjectFromResult(resultSet);
				projects.add(project);
			}
			return projects;
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while get all cars from database", e);
		}
	}

	@Override
	public void addProject(final String projectName, final LocalDate startDate, final LocalDate endDate, final String clientName) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try
		{
			connection.setAutoCommit(false);
			try (PreparedStatement statement = connection.prepareStatement(DatabaseQuery.INSERT_PROJECT_QUERY)
				  )
			{
				statement.setString(1, projectName);
				statement.setDate(2, Date.valueOf(startDate));
				statement.setDate(3, Date.valueOf(endDate));
				statement.setString(4, clientName);
				statement.executeUpdate();

				connection.commit();
			}
			catch (SQLException e)
			{
				connection.rollback();
				throw new DaoException("Error while adding project", e);
			}
		}
		catch (SQLException | DaoException e)
		{
			throw new DaoException("Error while adding project", e);
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

	private ProjectModel getProjectFromResult(ResultSet resultSet) throws SQLException
	{
		String projectName = resultSet.getString("project_name");
		String startDate = resultSet.getString("start_date");
		String endDate = resultSet.getString("end_date");

		return new ProjectModel(projectName, startDate, endDate);
	}
}
