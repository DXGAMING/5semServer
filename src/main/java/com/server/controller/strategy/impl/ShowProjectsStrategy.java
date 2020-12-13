package com.server.controller.strategy.impl;

import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.entity.ProjectModel;
import com.server.model.service.ProjectService;
import com.server.model.service.impl.ProjectServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ShowProjectsStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(ShowProjectsStrategy.class);

	@Override
	public Map<String, Object> perform(Map<String, Object> request)
	{
		Map<String, Object> response = new HashMap<>();
		ProjectService projectService = new ProjectServiceImpl();
		try
		{
			List<ProjectModel> projects = projectService.getAllProjects();
			response.put(RequestParameter.SIZE, projects.size());
			for (int i = 0; i < projects.size(); i++)
			{
				response.put(RequestParameter.PROJECT_NAME + i, projects.get(i).getProjectName());
				response.put(RequestParameter.PROJECT_START_DATE + i, projects.get(i).getStartDate());
				response.put(RequestParameter.PROJECT_END_DATE + i, projects.get(i).getEndDate());
			}
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
