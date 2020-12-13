package com.server.model.entity;

public class EmployeeModel implements Entity
{
	private long id;
	private String name;
	private String contact;
	private String designation;
	private String email;
	private String department;
	private String gender;
	private String login;
	private String password;

	public EmployeeModel(long id, String name, String contact, String designation, String email, String department, String gender, String login, String password)
	{
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.designation = designation;
		this.email = email;
		this.department = department;
		this.gender = gender;
		this.login = login;
		this.password = password;
	}

	public EmployeeModel(long id, String name, String contact, String designation, String email, String department)
	{
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.designation = designation;
		this.email = email;
		this.department = department;
	}

	public EmployeeModel(String login, String password)
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

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
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
