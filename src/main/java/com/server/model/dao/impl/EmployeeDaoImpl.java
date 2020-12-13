package com.server.model.dao.impl;

import com.server.exception.DaoException;
import com.server.model.connection.ConnectionPool;
import com.server.model.dao.AdminDao;
import com.server.model.dao.EmployeeDao;
import com.server.model.dao.query.DatabaseQuery;
import com.server.model.entity.AdminModel;
import com.server.model.entity.ClientModel;
import com.server.model.entity.EmployeeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmployeeDaoImpl implements EmployeeDao
{
	private final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	private static EmployeeDao instance;

	private EmployeeDaoImpl()
	{
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static EmployeeDao getInstance()
	{
		if (instance == null)
		{
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	@Override
	public Optional<EmployeeModel> findByLoginAndPassword(final String login, final String password) throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.GET_FROM_EMPLOYEE_AUTH_BY_LOGIN_AND_PASS_QUERY))
		{
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			Optional<EmployeeModel> account = Optional.empty();

			if (resultSet.next())
			{
				account = Optional.of(getEmployeeFromResult(resultSet));
			}
			return account;
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while finding account by login and password from database", e);
		}
	}

	@Override
	public List<EmployeeModel> findAllEmployees() throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SHOW_EMPLOYEES_QUERY))
		{
			List<EmployeeModel> employees = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				EmployeeModel employee = getFullEmployeeFromResult(resultSet);
				employees.add(employee);
			}
			return employees;
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while get all employees from database", e);
		}
	}

	private EmployeeModel getEmployeeFromResult(ResultSet resultSet) throws SQLException
	{
		String login = resultSet.getString("username");
		String password = resultSet.getString("password");

		return new EmployeeModel(login, password);
	}

	private EmployeeModel getFullEmployeeFromResult(ResultSet rs) throws SQLException
	{
		long empID = rs.getLong("id");
		String employeeName = rs.getString("name");
		String empDesig = rs.getString("designation");
		String empDep = rs.getString("department");
		String empPhone = rs.getString("contact");
		String empMail = rs.getString("email");

		return new EmployeeModel(empID, employeeName, empDep, empDesig, empPhone, empMail);
	}
}
