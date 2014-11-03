package com.eq.service.myinfo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.myinfo.Account;
import com.eq.dao.entity.system.User;
import com.eq.dao.impl.myinfo.AccountImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/myinfo/account")
public class AccountManage extends BaseAction {
	@Autowired
	private AccountImpl	impl;

	@ResponseBody
	@RequestMapping("getList")
	public List<Account> getList(@RequestParam Map<String, Object> params) {
		this.params = params;
		getUser();
		Map<String, Object> pps = new HashMap<String, Object>();
		if(getUser()!=null) {
			
		}
		pps.put("userId", getUser().getId());
		return impl.selectList(pps);
		
	}

	@RequestMapping("add")
	@ResponseBody
	public void add(@RequestParam Map<String, Object> params) {
		this.params = params;
		Account account = new Account();
		account.setBalance(getFloat("balance"));
		account.setBelong(getString("belong"));
		account.setCreateTime(DateUtil.getNowTime());
		account.setIsReal(getInt("isReal"));
		account.setName(getString("name"));
		account.setPwd(getString("pwd"));
		account.setUserId(getUser().getId());
		impl.add(account);
	}

	@RequestMapping("delete")
	@ResponseBody
	public void delete(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("id", getInt("id"));
		params2.put("pwd", getString("pwd"));
		impl.delete(params2);
	}

	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		this.params = params;
		Account account = new Account();
		account.setBalance(getFloat("balance"));
		account.setBelong(getString("belong"));
		account.setCreateTime(DateUtil.getNowTime());
		account.setId(getInt("id"));
		account.setIsReal(getInt("isReal"));
		account.setName(getString("name"));
		account.setPwd(getString("pwd"));
		impl.update(account);
	}
}
