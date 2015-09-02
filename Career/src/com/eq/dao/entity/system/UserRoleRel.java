/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title UserRoleRel.java
 * @package com.eq.dao.entity.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 上午9:26:09
 * @version V1.0  
 */
package com.eq.dao.entity.system;

import com.eq.dao.entity.BaseEntity;

/**
 * @className UserRoleRel
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 上午9:26:09
 */
public class UserRoleRel extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int roleId;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public int getRoleId()
	{
		return roleId;
	}
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

}
