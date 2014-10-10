package com.eq.dao.entity.system;

import com.eq.dao.entity.BaseEntity;

public class Permission extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String permission;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPermission()
	{
		return permission;
	}
	public void setPermission(String permission)
	{
		this.permission = permission;
	}

}
