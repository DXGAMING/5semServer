package com.server.controller.strategy.impl;

import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.entity.ClientModel;
import com.server.model.service.ClientService;
import com.server.model.service.impl.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShowClientsStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(ShowClientsStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request) throws ServiceException
	{
		Map<String, Object> response = new HashMap<>();
		ClientService clientService = new ClientServiceImpl();
		try
		{
			List<ClientModel> clients = clientService.getAllClients();
			response.put(RequestParameter.SIZE, clients.size());
			for (int i = 0; i < clients.size(); i++)
			{
				response.put(RequestParameter.LOGIN + i, clients.get(i).getId());
				response.put(RequestParameter.CLIENT_NAME + i, clients.get(i).getName());
				response.put(RequestParameter.CLIENT_CONTACT + i, clients.get(i).getContactPerson());
				response.put(RequestParameter.CLIENT_PHONE + i, clients.get(i).getPhone());
				response.put(RequestParameter.CLIENT_ADDRESS + i, clients.get(i).getAddress());
			}
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
