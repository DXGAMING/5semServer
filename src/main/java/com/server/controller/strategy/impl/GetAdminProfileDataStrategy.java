package com.server.controller.strategy.impl;

import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.entity.AdminModel;
import com.server.model.service.UserService;
import com.server.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class GetAdminProfileDataStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(GetAdminProfileDataStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request) throws ServiceException
	{
		UserService service = new UserServiceImpl();
		Map<String, Object> response = new HashMap<>();
		try
		{
			AdminModel adminModel = service.getAdminProfile((String) request.get(RequestParameter.LOGIN));
			response.put(RequestParameter.EMAIL, adminModel.getEmail());
			response.put(RequestParameter.ADMIN_NAME, adminModel.getName());
			response.put(RequestParameter.DESIGNATION, adminModel.getDesignation());
			response.put(RequestParameter.CONTACT, adminModel.getContact());
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
