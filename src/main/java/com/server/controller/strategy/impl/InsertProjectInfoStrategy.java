package com.server.controller.strategy.impl;

import com.server.controller.AttributeKey;
import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.service.ProjectService;
import com.server.model.service.impl.ProjectServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



public class InsertProjectInfoStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(InsertProjectInfoStrategy.class);
	@Override
	public Map<String, Object> perform(Map<String, Object> request)
	{
		ProjectService projectService = new ProjectServiceImpl();
		Map<String, Object> response = new HashMap<>();
		try
		{
			projectService.addProject((String) request.get(RequestParameter.PROJECT_NAME), (LocalDate) request.get(RequestParameter.PROJECT_START_DATE),
					(LocalDate) request.get(RequestParameter.PROJECT_END_DATE), (String) request.get(RequestParameter.CLIENT_NAME));
			response.put(AttributeKey.SUCCESSFUL_CHANGE, true);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;

	}
}
