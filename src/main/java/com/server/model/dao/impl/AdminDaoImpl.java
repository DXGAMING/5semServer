package com.server.model.dao.impl;

import com.server.exception.DaoException;
import com.server.model.connection.ConnectionPool;
import com.server.model.dao.AdminDao;
import com.server.model.dao.ProjectDao;
import com.server.model.dao.query.DatabaseQuery;
import com.server.model.entity.AdminModel;
import com.server.model.entity.ProjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class AdminDaoImpl implements AdminDao
{

	private final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);
	private static AdminDao instance;

	private AdminDaoImpl()
	{
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static AdminDao getInstance()
	{
		if (instance == null)
		{
			instance = new AdminDaoImpl();
		}
		return instance;
	}

	@Override
	public Optional<AdminModel> findByLoginAndPassword(final String login, final String password) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.GET_FROM_ADMIN_AUTH_BY_LOGIN_AND_PASS_QUERY))
		{
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			Optional<AdminModel> account = Optional.empty();

			if (resultSet.next())
			{
				account = Optional.of(getAdminFromResult(resultSet));
			}
			return account;
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while finding account by login and password from database", e);
		}
	}

	@Override
	public String findAdminName(final String username) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.GET_ADMIN_NAME_QUERY))
		{
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			String adminName = null;
			while (resultSet.next())
			{
				adminName = resultSet.getString("name");
			}
			return adminName;
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while finding admin name", e);
		}
	}

	@Override
	public AdminModel findAdminProfile(final String username) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.GET_ADMIN_PROFILE_QUERY))
		{
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			return getFullAdminFromResult(resultSet);

		}
		catch (SQLException e)
		{
			throw new DaoException("Error while finding admin profile", e);
		}
	}

	@Override
	public void updateAdminProfile(final String username, final String password, final String name, final String email,
			final String designation, final String contact) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try
		{
			connection.setAutoCommit(false);
			try (PreparedStatement statementAuth = connection.prepareStatement(DatabaseQuery.UPDATE_ADMIN_PASS_QUERY);
				  PreparedStatement statementProfile = connection.prepareStatement(DatabaseQuery.UPDATE_ADMIN_PROFILE_QUERY))
			{
				statementProfile.setString(1, name);
				statementProfile.setString(2, designation);
				statementProfile.setString(3, email);
				statementProfile.setString(4, contact);
				statementProfile.setString(5, username);
				statementProfile.executeUpdate();

				statementAuth.setString(1, password);
				statementAuth.setString(2, username);
				statementAuth.executeUpdate();
				connection.commit();
			}
			catch (SQLException e)
			{
				connection.rollback();
				throw new DaoException("Error while updating admin profile", e);
			}
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while updating admin profile", e);
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

	private AdminModel getAdminFromResult(ResultSet resultSet) throws SQLException
	{
		String login = resultSet.getString("username");
		String password = resultSet.getString("password");

		return new AdminModel(login, password);
	}

	private AdminModel getFullAdminFromResult(ResultSet rs) throws SQLException
	{
		long id = rs.getLong("id");
		String name = rs.getString("name");
		String desig = rs.getString("designation");
		String email = rs.getString("email");
		String contact = rs.getString("contact");
		return new AdminModel(id, name, contact, desig, email);
	}
}
