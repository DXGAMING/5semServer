package com.server.model.entity;

import java.util.Date;


public class TaskModel implements Entity
{
	private long id;
	private String name;
	private String time;
	private Date taskStartDate;
	private Date taskEndDate;
	private String progress;
	private String dependency;
	private long assigned;

	public TaskModel(long id, String name, String time, Date taskStartDate, Date taskEndDate, String progress, String dependency, long assigned)
	{
		this.id = id;
		this.name = name;
		this.time = time;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.progress = progress;
		this.dependency = dependency;
		this.assigned = assigned;
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

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public Date getTaskStartDate()
	{
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate)
	{
		this.taskStartDate = taskStartDate;
	}

	public Date getTaskEndDate()
	{
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate)
	{
		this.taskEndDate = taskEndDate;
	}

	public String getProgress()
	{
		return progress;
	}

	public void setProgress(String progress)
	{
		this.progress = progress;
	}

	public String getDependency()
	{
		return dependency;
	}

	public void setDependency(String dependency)
	{
		this.dependency = dependency;
	}

	public long getAssigned()
	{
		return assigned;
	}

	public void setAssigned(long assigned)
	{
		this.assigned = assigned;
	}
}
