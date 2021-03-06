package com.eq.service.myinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.myinfo.Account;
import com.eq.dao.entity.myinfo.AccountHistory;
import com.eq.dao.entity.system.Select;
import com.eq.dao.impl.myinfo.AccountHistoryImpl;
import com.eq.dao.impl.myinfo.AccountImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParamUtils;

@Controller
@RequestMapping("accountHistory")
public class AccountHistoryManage extends BaseAction {
	@Autowired
	private AccountHistoryImpl	impl;
	@Autowired
	private AccountImpl			accountImpl;

	@Transactional
	@ResponseBody
	@RequestMapping("add")
	public void add(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		AccountHistory history = new AccountHistory();
		history.setIncome(PU.getFloat("income"));
		history.setCost(PU.getFloat("cost"));
		if (PU.getTimestamp("date") != null) {
			history.setCreateTime(PU.getTimestamp("date"));
		} else {
			history.setCreateTime(DateUtil.getNowTime());
		}

		history.setMemo(PU.getString("memo"));
		history.setUsages(PU.getString("usages"));
		history.setAccountId(PU.getInt("accountId"));
		impl.add(history);
		// 修改账户余额
		Account account1 = accountImpl.selectOne(PU.getInt("accountId"));
		account1.setBalance(account1.getBalance() + PU.getFloat("income")
				- PU.getFloat("cost"));
		accountImpl.updateWithoutPwd(account1);
		// 修改源账户记录
		AccountHistory history2 = new AccountHistory();
		history2.setAccountId(account1.getDestinationId());
		history2.setCost(PU.getFloat("income"));
		history2.setIncome(PU.getFloat("cost"));
		if (PU.getTimestamp("date") != null) {
			history2.setCreateTime(PU.getTimestamp("date"));
		} else {
			history2.setCreateTime(DateUtil.getNowTime());
		}
		history2.setMemo(PU.getString("memo"));
		history2.setUsages(PU.getString("usages"));
		impl.add(history2);
		// 修改源账户余额
		Account account2 = accountImpl.selectOne(account1.getDestinationId());
		account2.setBalance(account2.getBalance() - PU.getFloat("income")
				+ PU.getFloat("cost"));
		accountImpl.updateWithoutPwd(account2);
		//
		if (StringUtils.isNotBlank(PU.getString("destinationId"))) {
			AccountHistory history3 = new AccountHistory();
			history3.setIncome(PU.getFloat("cost"));
			history3.setCost(PU.getFloat("income"));
			if (PU.getTimestamp("date") != null) {
				history3.setCreateTime(PU.getTimestamp("date"));
			} else {
				history3.setCreateTime(DateUtil.getNowTime());
			}

			history3.setMemo(PU.getString("memo"));
			history3.setUsages(PU.getString("usages"));
			history3.setAccountId(PU.getInt("destinationId"));
			impl.add(history3);
			// 修改账户余额
			Account account3 = accountImpl.selectOne(PU.getInt("destinationId"));
			account3.setBalance(account3.getBalance() + PU.getFloat("cost")
					- PU.getFloat("income"));
			accountImpl.updateWithoutPwd(account3);
			// 修改源账户记录
			AccountHistory history4 = new AccountHistory();
			history4.setAccountId(account3.getDestinationId());
			history4.setCost(PU.getFloat("cost"));
			history4.setIncome(PU.getFloat("income"));
			if (PU.getTimestamp("date") != null) {
				history4.setCreateTime(PU.getTimestamp("date"));
			} else {
				history4.setCreateTime(DateUtil.getNowTime());
			}
			history4.setMemo(PU.getString("memo"));
			history4.setUsages(PU.getString("usages"));
			impl.add(history4);
			// 修改源账户余额
			Account account4 = accountImpl.selectOne(account3
					.getDestinationId());
			account4.setBalance(account4.getBalance() + PU.getFloat("income")
					- PU.getFloat("cost"));
			accountImpl.updateWithoutPwd(account4);
		}

	}

	@ResponseBody
	@RequestMapping("getList")
	public List<AccountHistory> getList(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("accountId", PU.getInt("accountId"));
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<AccountHistory>)pps.get("list");
	}

	@ResponseBody
	@RequestMapping("getAccountList")
	public List<Select> getAccountList(int accountId) {
		Account account = accountImpl.selectOne(accountId);
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("destinationId", account.getDestinationId());
		List<Account> accountList = accountImpl.selectList(pps);
		List<Account> accountList2 = new ArrayList<Account>();
		for (Account acc : accountList) {
			if (acc.getId() != account.getId()) {
				accountList2.add(acc);
			}
		}
		List<Select> selectList = new ArrayList<Select>();
		for (Account acc : accountList2) {
			Select select = new Select();
			select.setId(acc.getId() + "");
			select.setLabel(acc.getName());
			selectList.add(select);
		}
		Select select = new Select();
		select.setId("");
		select.setLabel("不限");
		select.setSelected(true);
		selectList.add(select);
		return selectList;
	}

	@ResponseBody
	@RequestMapping("delete")
	public void delete(int id) {
		impl.delete(id);
	}

	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		AccountHistory history = impl.selectOne(PU.getInt("id"));
		float change = (history.getCost() - PU.getFloat("cost"))
				- (history.getIncome() - PU.getFloat("income"));
		// history.setCost(getFloat("cost"));
		// history.setIncome(getFloat("income"));
		history.setMemo(PU.getString("memo"));
		history.setUsages(PU.getString("usages"));
		history.setCreateTime(PU.getTimestamp("date"));
		impl.update(history);
		// 修改账户余额
		/*
		 * Account account = accountImpl.selectOne(history.getAccountId());
		 * account.setBalance(account.getBalance() + change);
		 * accountImpl.updateWithoutPwd(account);
		 */
	}
	@ResponseBody
	@RequestMapping("sum")
	public Map<String, Object> sum(int accountId) {
		 return impl.sum(accountId);
	}
}
