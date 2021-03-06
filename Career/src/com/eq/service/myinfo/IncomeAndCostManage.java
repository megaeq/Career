/**
 * 
 */
package com.eq.service.myinfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.myinfo.IncomeAndCost;
import com.eq.dao.impl.myinfo.IncomeAndCostImpl;
import com.eq.service.mybatis.PageParameter;
import com.eq.util.BaseAction;
import com.eq.util.ParamUtils;

/**
 * @author mega
 * 
 */
@Controller
@RequestMapping("incomeAndCost")
public class IncomeAndCostManage extends BaseAction {
	@Autowired
	IncomeAndCostImpl impl;
	@ResponseBody
	@RequestMapping("getList")
	public List<IncomeAndCost> getIncomeAndCostList(Date startDate, Date endDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		Cache cache = singletonManager.getCache("authenticationCache");
		if(cache.get("list")!=null) {
			return (List<IncomeAndCost>)cache.get("list").getObjectValue();
		} else {
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			Map<String, Object> pps = impl.selectPageList(params,currentPage,pageSize);
			response.setHeader("Content-Range", rangeStr+pps.get("count"));
			Element elt = new Element("list",pps.get("list"),1l);
			cache.put(elt);
			return (List<IncomeAndCost>)pps.get("list");
		}
		
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
		ParamUtils PU = new ParamUtils(params);
		impl.delete(PU.getInt("id"));
	}
	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		IncomeAndCost ic = new IncomeAndCost();
		ic.setCost(PU.getFloat("cost"));
		ic.setIncome(PU.getFloat("income"));
		ic.setDate(PU.getTimestamp("addDate"));
		ic.setUsages(PU.getString("usages"));
		ic.setMemo(PU.getString("memo"));
		impl.update(ic);
	}
}
