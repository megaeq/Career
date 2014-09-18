/**
 * 
 */
package com.eq.service.myinfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.myinfo.IncomeAndCostImpl;
import com.eq.util.BaseAction;

/**
 * @author mega
 * 
 */
@Controller
@RequestMapping("page/myinfo")
public class IncomeAndCostManage extends BaseAction {
	@Autowired
	IncomeAndCostImpl impl;
	@ResponseBody
	@RequestMapping("getList")
	public List<IncomeAndCost> getIncomeAndCostList(Date startDate, Date endDate) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return impl.selectList(params);
	}

	@ResponseBody
	@RequestMapping("add")
	public void add(Float income, Float cost, Date addDate, String usages,
			String memo) {
		System.out.println(income + " " + cost + " " + addDate);
		IncomeAndCost ic = new IncomeAndCost();
		ic.setCost(cost);
		Timestamp ts = new Timestamp(addDate.getTime());
		ic.setDate(ts);
		ic.setIncome(income);
		ic.setUsages(usages);
		ic.setMemo(memo);
		impl.add(ic);
	}
	@ResponseBody
	@RequestMapping("delete")
	public void delete(@RequestParam Map<String, Object> params) {
		this.params=params;
		impl.delete(getInt("id"));
	}
	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		this.params=params;
		IncomeAndCost ic = new IncomeAndCost();
		ic.setCost(getFloat("cost"));
		ic.setIncome(getFloat("income"));
		ic.setDate(getTimestamp("addDate"));
		ic.setUsages(getString("usages"));
		ic.setMemo(getString("memo"));
		impl.update(ic);
	}
}
