package com.server.model.dao;

import com.server.exception.DaoException;
import com.server.model.entity.AdminModel;

import java.util.Optional;


public interface AdminDao
{
	Optional<AdminModel> findByLoginAndPassword(String login, String password) throws DaoException;

	String findAdminName(String username) throws DaoException;

	AdminModel findAdminProfile(String username) throws DaoException;

	void updateAdminProfile(String username, String password,String name, String email, String designation, String contact) throws DaoException;
}
