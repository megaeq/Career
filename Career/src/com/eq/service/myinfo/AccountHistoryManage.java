package com.eq.service.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.myinfo.AccountHistory;
import com.eq.dao.impl.myinfo.AccountHistoryImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/myinfo/accountHistory")
public class AccountHistoryManage extends BaseAction {
	@Autowired
	private AccountHistoryImpl	impl;

	@ResponseBody
	@RequestMapping("add")
	public void add(@RequestParam Map<String, Object> params) {
		this.params = params;
		AccountHistory history = new AccountHistory();
		history.setIncome(getFloat("income"));
		history.setCost(getFloat("cost"));
		history.setCreateTime(DateUtil.getNowTime());
		history.setMemo(getString("memo"));
		history.setUsages(getString("usages"));
		history.setAccountId(getInt("accountId"));
		impl.add(history);
	}

	@ResponseBody
	@RequestMapping("getList")
	public List<AccountHistory> getList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("accountId", getInt("accountId"));
		return impl.selectList(pps);
	}

	@ResponseBody
	@RequestMapping("delete")
	public void delete(int id) {
		impl.delete(id);
	}

	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		this.params = params;
		AccountHistory history = new AccountHistory();
		history.setId(getInt("id"));
		history.setCost(getFloat("cost"));
		history.setIncome(getFloat("income"));
		history.setMemo(getString("memo"));
		history.setUsages(getString("usages"));
		impl.update(history);
	}
}
