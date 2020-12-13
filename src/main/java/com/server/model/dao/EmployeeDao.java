package com.server.model.dao;

import com.server.exception.DaoException;
import com.server.model.entity.EmployeeModel;

import java.util.List;
import java.util.Optional;


public interface EmployeeDao
{
	Optional<EmployeeModel> findByLoginAndPassword(String login, String password) throws DaoException;

	List<EmployeeModel> findAllEmployees() throws DaoException;
}
