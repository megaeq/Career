package com.eq.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.system.User;
import com.eq.dao.impl.system.UserImpl;
import com.eq.service.shiro.PasswordHelper;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParamUtils;
@Controller
@RequestMapping("user")
public class UserManage extends BaseAction
{
	@Autowired
	private UserImpl impl; 
	@RequiresGuest
	@RequestMapping(value="add")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("name", PU.getString("username"));
		List<User> userList = impl.selectList(pps);
		if(userList.isEmpty()) {
			User user = new User();
			user.setCreateTime(DateUtil.getNowTime());
			user.setLock(0);
			user.setName(PU.getString("username"));
			user.setPwd(PU.getString("password"));
			new PasswordHelper().encryptPassword(user);
			System.out.println(user.getSalt());
			System.out.println(user.getPwd());
			impl.add(user);
			return "用户添加成功";
		} else {
			return "该用户已存在";
		}
		
	}
}
