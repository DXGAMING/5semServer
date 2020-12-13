package com.server.model.service.impl;

import com.server.exception.DaoException;
import com.server.exception.ServiceException;
import com.server.model.dao.AdminDao;
import com.server.model.dao.EmployeeDao;
import com.server.model.dao.impl.AdminDaoImpl;
import com.server.model.dao.impl.EmployeeDaoImpl;
import com.server.model.entity.AdminModel;
import com.server.model.entity.EmployeeModel;
import com.server.model.service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService
{
	private AdminDao adminDao = AdminDaoImpl.getInstance();
	private EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();

	@Override
	public boolean authenticateAdmin(final String login, final String password) throws ServiceException
	{
		try
		{
			return adminDao.findByLoginAndPassword(login, password).isPresent();
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public boolean authenticateEmployee(final String login, final String password) throws ServiceException
	{
		try
		{
			return employeeDao.findByLoginAndPassword(login, password).isPresent();
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String getAdminName(final String username) throws ServiceException
	{
		try
		{
			return adminDao.findAdminName(username);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<EmployeeModel> getAllEmployees() throws ServiceException
	{
		try {
			return employeeDao.findAllEmployees();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public AdminModel getAdminProfile(final String username) throws ServiceException
	{
		try
		{
			return adminDao.findAdminProfile(username);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateAdminProfile(final String username, final String password,final String name, final String email,
			final String designation, final String contact) throws ServiceException
	{
		try
		{
			adminDao.updateAdminProfile(username,password,name, email, designation, contact);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e.getMessage());
		}
	}
}
