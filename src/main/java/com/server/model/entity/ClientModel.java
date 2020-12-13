package com.server.model.entity;

public class ClientModel implements Entity
{
	private long id;
	private String name;
	private String contactPerson;
	private String phone;
	private String address;

	public ClientModel(long id, String name, String contactPerson, String phone, String address)
	{
		this.id = id;
		this.name = name;
		this.contactPerson = contactPerson;
		this.phone = phone;
		this.address = address;
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

	public String getContactPerson()
	{
		return contactPerson;
	}

	public void setContactPerson(String contactPerson)
	{
		this.contactPerson = contactPerson;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}
