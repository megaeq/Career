package com.eq.service.system;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.system.User;
import com.eq.util.BaseAction;

@Controller
@RequestMapping("loginManage")
public class LoginManage extends BaseAction
{
	@RequiresGuest
	@RequestMapping(value = "hasLogin")
	@ResponseBody
	public String hasLogin(@RequestParam Map<String, Object> params)
	{
		String hasLogin = "";
		Subject subject = getSubject();
		if(subject.isAuthenticated())
		{
			hasLogin = "hasLogin";
		}
		return hasLogin;
	}
	@RequestMapping(value = "loginOut")
	@ResponseBody
	public String loginOut(@RequestParam Map<String, Object> params)
	{
		Subject subject = getSubject();
		subject.logout();
		return "success";
	}
	@RequestMapping(value = "getBaseInfo")
	@ResponseBody
	public String getBaseInfo(@RequestParam Map<String, Object> params) {
		JSONObject json = new JSONObject();
		User user = getUser();
		json.put("name", user.getName());
		json.put("imagePath", user.getImagePath());
		return json.toString();
	} 
}
