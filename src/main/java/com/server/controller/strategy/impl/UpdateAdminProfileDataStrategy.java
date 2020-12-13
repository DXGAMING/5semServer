package com.server.controller.strategy.impl;

import com.server.controller.AttributeKey;
import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.service.UserService;
import com.server.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class UpdateAdminProfileDataStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(UpdateAdminProfileDataStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request)
	{
		UserService service = new UserServiceImpl();
		Map<String, Object> response = new HashMap<>();
		try
		{
			service.updateAdminProfile((String) request.get(RequestParameter.LOGIN), (String) request.get(RequestParameter.PASSWORD),
					(String)request.get(RequestParameter.ADMIN_NAME), (String)request.get(RequestParameter.EMAIL),
					(String)request.get(RequestParameter.DESIGNATION), (String)request.get(RequestParameter.CONTACT));
			response.put(AttributeKey.SUCCESSFUL_CHANGE, true);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
