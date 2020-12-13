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


public class AuthenticationStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(AuthenticationStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request)
	{
		UserService service = new UserServiceImpl();
		String login = (String) request.get(RequestParameter.LOGIN);
		String password = (String) request.get(RequestParameter.PASSWORD);
		boolean isAdmin = (boolean) request.get(RequestParameter.IS_ADMIN);
		Map<String, Object> response = new HashMap<>();

		try
		{
			if (isAdmin)
			{
				if (service.authenticateAdmin(login, password))
				{
					response.put(AttributeKey.SUCCESSFUL_AUTHENTICATION, true);
				}
				else
				{
					response.put(AttributeKey.SUCCESSFUL_ACTIVATION, false);
				}
			}
			else
			{
				if (service.authenticateEmployee(login, password))
				{
					response.put(AttributeKey.SUCCESSFUL_AUTHENTICATION, true);
				}
				else
				{
					response.put(AttributeKey.SUCCESSFUL_ACTIVATION, false);
				}
			}
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
