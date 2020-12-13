package com.server.controller.strategy.impl;

import com.server.controller.AttributeKey;
import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.service.TaskService;
import com.server.model.service.impl.TaskServiceImpl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class UpdateTaskProgressStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(UpdateTaskProgressStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request) throws ServiceException
	{
		TaskService service = new TaskServiceImpl();
		Map<String, Object> response = new HashMap<>();
		try
		{
			service.updateTaskProgress((String) request.get(RequestParameter.TASK_PROGRESS), (String) request.get(RequestParameter.TASK_NAME));
			response.put(AttributeKey.SUCCESSFUL_CHANGE, true);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
