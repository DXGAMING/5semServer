package com.server.model.entity;

public class AdminModel implements Entity
{
	private long id;
	private String name;
	private String contact;
	private String designation;
	private String email;
	private String login;
	private String password;

	public AdminModel(long id, String name, String contact, String designation, String email, String login, String password)
	{
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.designation = designation;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public AdminModel(long id, String name, String contact, String designation, String email)
	{
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.designation = designation;
		this.email = email;
	}

	public AdminModel(String login, String password)
	{
		this.login = login;
		this.password = password;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
