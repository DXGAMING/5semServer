package com.server.model.service;

import com.server.exception.ServiceException;
import com.server.model.entity.ClientModel;

import java.util.List;


public interface ClientService
{
	List<ClientModel> getAllClients() throws ServiceException;
}
