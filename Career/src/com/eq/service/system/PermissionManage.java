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
		Permission permission = impl.selectOne(PU.getInt("id"));
		if(null!=PU.getString("name")) {
			
		}
		return "添加成功";
	}
}
