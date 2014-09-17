/**
 * 
 */
package com.eq.service.myinfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@ResponseBody
	@RequestMapping("getList")
	public List<IncomeAndCost> getIncomeAndCostList(Date startDate, Date endDate) {
		IncomeAndCostImpl impl = (IncomeAndCostImpl) getBean("incomeAndCostImpl");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return impl.selectList(params);
	}

	@ResponseBody
	@RequestMapping("add")
	public void add(Float income, Float cost, Date addDate, String usages,
			String memo) {
		IncomeAndCostImpl impl = (IncomeAndCostImpl) getBean("incomeAndCostImpl");
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
}
