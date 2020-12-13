package com.server.model.dao;

import com.server.exception.DaoException;
import com.server.model.entity.ClientModel;

import java.util.List;


public interface ClientDao
{
	List<ClientModel> findAllClients() throws DaoException;
}
