package com.server.model.dao.impl;

import com.server.exception.DaoException;
import com.server.model.connection.ConnectionPool;
import com.server.model.dao.AdminDao;
import com.server.model.dao.ClientDao;
import com.server.model.dao.query.DatabaseQuery;
import com.server.model.entity.ClientModel;
import com.server.model.entity.ProjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDaoImpl implements ClientDao
{
	private final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);
	private static ClientDao instance;

	private ClientDaoImpl()
	{
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static ClientDao getInstance()
	{
		if (instance == null)
		{
			instance = new ClientDaoImpl();
		}
		return instance;
	}

	@Override
	public List<ClientModel> findAllClients() throws DaoException
	{
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try (Connection connection = connectionPool.getConnection();
			  PreparedStatement statement = connection.prepareStatement(DatabaseQuery.SHOW_CLIENTS_QUERY))
		{
			List<ClientModel> clients = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				ClientModel client = getClientFromResult(resultSet);
				clients.add(client);
			}
			return clients;
		}
		catch (SQLException e)
		{
			throw new DaoException("Error while get all cars from database", e);
		}
	}

	private ClientModel getClientFromResult(final ResultSet resultSet) throws SQLException
	{
		long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String contact = resultSet.getString("contact_person");
		String phone = resultSet.getString("phone");
		String address = resultSet.getString("address");

		return new ClientModel(id, name, contact, phone, address);
	}
}
