package com.eq.dao.entity.system;

import com.eq.dao.entity.BaseEntity;

public class Resource extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String value;
	private String permission;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public String getPermission()
	{
		return permission;
	}
	public void setPermission(String permission)
	{
		this.permission = permission;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	

}
