package com.eq.dao.entity.system;

import com.eq.dao.entity.BaseEntity;

public class Role extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String role;
	private String username;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

}
