package com.server.controller.strategy.impl;

import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.service.UserService;
import com.server.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class GetAdminNameStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(GetAdminNameStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request)
	{
		UserService service = new UserServiceImpl();
		Map<String, Object> response = new HashMap<>();
		try
		{
			response.put(RequestParameter.ADMIN_NAME, service.getAdminName((String) request.get(RequestParameter.LOGIN)));
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;

	}
}
