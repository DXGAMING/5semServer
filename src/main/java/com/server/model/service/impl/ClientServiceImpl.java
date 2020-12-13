package com.server.model.service.impl;

import com.server.exception.DaoException;
import com.server.exception.ServiceException;
import com.server.model.dao.ClientDao;
import com.server.model.dao.impl.ClientDaoImpl;
import com.server.model.entity.ClientModel;
import com.server.model.service.ClientService;

import java.util.List;


public class ClientServiceImpl implements ClientService
{
	private ClientDao clientDao = ClientDaoImpl.getInstance();

	@Override
	public List<ClientModel> getAllClients() throws ServiceException
	{
		try {
			return clientDao.findAllClients();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
