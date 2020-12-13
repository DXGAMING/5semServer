package com.server.model.entity;

import java.util.Date;


public class ProjectModel implements Entity
{
	private long projectId;
	private String projectName;
	private String startDate;
	private String endDate;
	private String estimatedTime;
	private long clientId;

	public ProjectModel(long projectId, String projectName, String startDate, String endDate, String estimatedTime, long clientId)
	{
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.estimatedTime = estimatedTime;
		this.clientId = clientId;
	}

	public ProjectModel(String projectName, String startDate, String endDate)
	{
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(long projectId)
	{
		this.projectId = projectId;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getEstimatedTime()
	{
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime)
	{
		this.estimatedTime = estimatedTime;
	}

	public long getClientId()
	{
		return clientId;
	}

	public void setClientId(long clientId)
	{
		this.clientId = clientId;
	}
}
