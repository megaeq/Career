/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title RolePermissionRel.java
 * @package com.eq.dao.entity.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 上午10:36:06
 * @version V1.0  
 */
package com.eq.dao.entity.system;

import com.eq.dao.entity.BaseEntity;

/**
 * @className RolePermissionRel
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-2 上午10:36:06
 */
public class RolePermissionRel extends BaseEntity
{

	/**
	 * @fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int roleId;
	private int permissionId;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getRoleId()
	{
		return roleId;
	}
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
	public int getPermissionId()
	{
		return permissionId;
	}
	public void setPermissionId(int permissionId)
	{
		this.permissionId = permissionId;
	}

}
