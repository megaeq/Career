package com.eq.service.system;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.util.BaseAction;

@Controller
@RequestMapping("loginManage")
public class LoginManage extends BaseAction
{
	@RequiresGuest
	@RequestMapping(value="hasLogin")
	@ResponseBody
	public String hasLogin(@RequestParam Map<String, Object> params) {
		String hasLogin="";
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			hasLogin = "hasLogin";
		}
		return hasLogin;
	}
}
