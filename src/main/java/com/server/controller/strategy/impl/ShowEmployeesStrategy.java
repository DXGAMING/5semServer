package com.server.controller.strategy.impl;

import com.server.controller.strategy.DefaultStrategy;
import com.server.controller.strategy.RequestParameter;
import com.server.exception.ServiceException;
import com.server.model.entity.EmployeeModel;
import com.server.model.service.UserService;
import com.server.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShowEmployeesStrategy implements DefaultStrategy
{
	private final Logger logger = LoggerFactory.getLogger(ShowEmployeesStrategy.class);
	@Override
	public Map<String, Object> perform(Map<String, Object> request) throws ServiceException
	{
		Map<String, Object> response = new HashMap<>();
		UserService userService = new UserServiceImpl();
		try
		{
			List<EmployeeModel> employees = userService.getAllEmployees();
			response.put(RequestParameter.SIZE, employees.size());
			for (int i = 0; i < employees.size(); i++)
			{
				response.put(RequestParameter.EMPLOYEE_EMAIL + i, employees.get(i).getEmail());
				response.put(RequestParameter.EMPLOYEE_NAME + i, employees.get(i).getName());
				response.put(RequestParameter.EMPLOYEE_CONTACT + i, employees.get(i).getContact());
				response.put(RequestParameter.EMPLOYEE_DEPARTMENT + i, employees.get(i).getDepartment());
				response.put(RequestParameter.DESIGNATION + i, employees.get(i).getDesignation());
			}
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage());
		}
		return response;
	}
}
