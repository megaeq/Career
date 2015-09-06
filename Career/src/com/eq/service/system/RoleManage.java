/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title RoleManage.java
 * @package com.eq.service.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午10:51:24
 * @version V1.0  
 */
package com.eq.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.system.Role;
import com.eq.dao.entity.system.RolePermissionRel;
import com.eq.dao.impl.system.RoleImpl;
import com.eq.dao.impl.system.RolePermissionRelImpl;
import com.eq.util.BaseAction;
import com.eq.util.ParamUtils;


/**
 * @className RoleManage
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午10:51:24
 */
@Controller
@RequestMapping("role")
public class RoleManage extends BaseAction
{
	@Autowired
	private RoleImpl roleImpl;
	@Autowired
	private RolePermissionRelImpl rolePermissionImpl;
	@RequestMapping("getRoleList")
	@ResponseBody
	public List<Role> getRoleList(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(PU.getString("nowuser"))) {
			pps.put("userId", getUser().getId());
		}
		pps.put("available", PU.getInt("available"));
		return roleImpl.selectList(pps);
	}
	@RequestMapping("add")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		if(null!=PU.getString("role")) {
			pps.put("role", PU.getString("role"));
			List<Role> list = roleImpl.selectList(pps);
			if(!list.isEmpty()) {
				return "角色已存在";
			}
		}
		if(null!=PU.getString("name")) {
			pps.clear();
			pps.put("name", PU.getString("name"));
			List<Role> list = roleImpl.selectList(pps);
			if(!list.isEmpty()) {
				return "角色名称已存在";
			}
		}
		Role role = new Role();
		role.setAvailable(1);
		role.setRole(PU.getString("role"));
		role.setName(PU.getString("name"));
		roleImpl.add(role);
		return "角色添加成功";
	}
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Role role = roleImpl.selectOne(PU.getInt("id"));
		Map<String, Object> pps = new HashMap<String, Object>();
		if(null!=PU.getInt("available")) {
			role.setAvailable(PU.getInt("available"));
		}
		if(null!=PU.getString("role")) {
			pps.put("role", PU.getString("role"));
			List<Role> list = roleImpl.selectList(pps);
			if(!list.isEmpty()) {
				return "角色已存在";
			}
			role.setRole(PU.getString("role"));
		}
		if(null!=PU.getString("name")) {
			pps.clear();
			pps.put("name", PU.getString("name"));
			List<Role> list = roleImpl.selectList(pps);
			if(!list.isEmpty()) {
				return "角色名称已存在";
			}
			role.setName(PU.getString("name"));
		}
		roleImpl.update(role);
		return "保存成功";
	}
	@RequestMapping("updateRolePermission")
	@ResponseBody
	public String updateRolePermission(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		if("delete".equals(PU.getString("operate"))) {
			Map<String, Object> pps = new HashMap<String, Object>();
			pps.put("roleId", PU.getInt("roleId"));
			pps.put("permissionId", PU.getInt("permissionId"));
			List<RolePermissionRel> list = rolePermissionImpl.selectList(pps);
			rolePermissionImpl.delete(list.get(0).getId());
		} else {
			RolePermissionRel rel = new RolePermissionRel();
			rel.setRoleId(PU.getInt("roleId"));
			rel.setPermissionId(PU.getInt("permissionId"));
			rolePermissionImpl.add(rel);
		}
		return "保存成功";
	}
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		roleImpl.delete(PU.getInt("id"));
		return "删除成功";
	}
	
}
