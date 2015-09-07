/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title PermissionManage.java
 * @package com.eq.service.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午10:51:49
 * @version V1.0  
 */
package com.eq.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.system.Permission;
import com.eq.dao.impl.system.PermissionImpl;
import com.eq.util.BaseAction;
import com.eq.util.ParamUtils;

/**
 * @className PermissionManage
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午10:51:49
 */
@Controller
@RequestMapping("permission")
public class PermissionManage extends BaseAction
{
	@Autowired
	private PermissionImpl impl;
	@RequestMapping("add")
	@ResponseBody
	private List<Permission> getList(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("name", PU.getString("name"));
		pps.put("available", PU.getString("available"));
		pps.put("roleId", PU.getString("role"));
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<Permission>)pps2.get("list");
		
	}
	@RequestMapping("add")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		if(null!=PU.getString("name")) {
			pps.put("name", PU.getString("name"));
			List<Permission> list = impl.selectList(pps);
			if(!list.isEmpty()) {
				return "权限名称已存在";
			}
		}
		if(null!=PU.getString("permission")) {
			pps.clear();
			pps.put("permission", PU.getString("permission"));
			List<Permission> list = impl.selectList(pps);
			if(!list.isEmpty()) {
				return "权限已存在";
			}
		}
		Permission permission = new Permission();
		permission.setAvailable(1);
		permission.setPermission(PU.getString("permission"));
		permission.setName(PU.getString("name"));
		impl.add(permission);
		return "添加成功";
	}
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		if(null!=PU.getString("name")) {
			pps.put("name", PU.getString("name"));
			List<Permission> list = impl.selectList(pps);
			if(!list.isEmpty()) {
				return "权限名称已存在";
			}
		}
		if(null!=PU.getString("permission")) {
			pps.clear();
			pps.put("permission", PU.getString("permission"));
			List<Permission> list = impl.selectList(pps);
			if(!list.isEmpty()) {
				return "权限已存在";
			}
		}
		Permission permission = impl.selectOne(PU.getInt("id"));
		permission.setPermission(PU.getString("permission"));
		permission.setName(PU.getString("name"));
		permission.setAvailable(PU.getInt("available"));
		return "添加成功";
	}
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		impl.delete(PU.getInt("id"));
		return "删除成功";
	}
}
