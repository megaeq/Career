/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title ResourceManage.java
 * @package com.eq.service.system
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午10:52:07
 * @version V1.0  
 */
package com.eq.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.system.Resource;
import com.eq.dao.impl.system.ResourceImpl;
import com.eq.util.BaseAction;
import com.eq.util.ParamUtils;

/**
 * @className ResourceManage
 * @description TODO
 * @author Mega.Yan
 * @date 2015-9-6 上午10:52:07
 */
public class ResourceManage extends BaseAction
{
	private ResourceImpl impl;
	@RequestMapping("add")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		if(null!=PU.getString("value")) {
			pps.put("value", PU.getString("value"));
			List<Resource> list = impl.selectList(pps);
			if(!list.isEmpty()) {
				return "资源已存在";
			}
		}
		 
		Resource resource = new Resource();
		resource.setName(PU.getString("name"));
		resource.setPermission(PU.getString("permission"));
		resource.setValue(PU.getString("value"));
		impl.add(resource);
		return "添加成功";
	}
	@RequestMapping("update")
	@ResponseBody
	public String update(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		if(null!=PU.getString("value")) {
			pps.put("value", PU.getString("value"));
			List<Resource> list = impl.selectList(pps);
			if(!list.isEmpty()) {
				return "资源已存在";
			}
		}
		Resource resource = impl.selectOne(PU.getInt("id"));
		resource.setName(PU.getString("name"));
		resource.setPermission(PU.getString("permission"));
		resource.setValue(PU.getString("value"));
		impl.update(resource);
		return "保存成功";
	}
	@RequestMapping("update")
	@ResponseBody
	public String delete(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		impl.delete(PU.getInt("id"));
		return "删除成功";
	}
}
