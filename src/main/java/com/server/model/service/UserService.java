package com.server.model.service;

import com.server.exception.ServiceException;
import com.server.model.entity.AdminModel;
import com.server.model.entity.EmployeeModel;

import java.util.List;


public interface UserService
{
	boolean authenticateAdmin(String login, String password) throws ServiceException;

	boolean authenticateEmployee(String login, String password) throws ServiceException;

	String getAdminName(String username) throws ServiceException;

	List<EmployeeModel> getAllEmployees() throws ServiceException;

	AdminModel getAdminProfile(String username) throws ServiceException;

	void updateAdminProfile(String username, String password, String name, String email, String designation, String contact) throws ServiceException;
}
