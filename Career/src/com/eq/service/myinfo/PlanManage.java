package com.eq.service.myinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.myinfo.Plan;
import com.eq.dao.entity.myinfo.PlanHistory;
import com.eq.dao.impl.myinfo.PlanHistoryImpl;
import com.eq.dao.impl.myinfo.PlanImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/myinfo/plan")
public class PlanManage extends BaseAction {
	@Autowired
	private PlanImpl	impl;
	@Autowired
	private PlanHistoryImpl historyImpl;

	@ResponseBody
	@RequestMapping("getList")
	public List<Plan> getList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("userId",getUser().getId());
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<Plan>)pps2.get("list");
	}

	@ResponseBody
	@RequestMapping("add")
	public String add(@RequestParam Map<String, Object> params) {
		this.params = params;
		Plan plan = new Plan();
		plan.setComplete(0);
		if (getString("time") != null) {
			plan.setCreateTime(DateUtil.getTimestamp(getString("time")));
		} else {
			plan.setCreateTime(DateUtil.getNowTime());
		}
		plan.setIsDel(0);
		plan.setLevel(getInt("level"));
		plan.setMemo(getString("memo"));
		plan.setName(getString("name"));
		plan.setType(getString("type"));
		plan.setComplete(getInt("complete"));
		plan.setUserId(getUser().getId());
		if (1 == impl.add(plan)) {
			PlanHistory planHistory = new PlanHistory();
			planHistory.setCreateTime(DateUtil.getNowTime());
			planHistory.setMemo(getString("memo"));
			planHistory.setPlanId(plan.getId());
			planHistory.setType("新增");
			if(historyImpl.add(planHistory)==1) {
				return "success";
			} else {
				return "failure";
			}
			
		} else {
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping("delete")
	public String delete(@RequestParam Map<String, Object> params) {
		this.params = params;
		Plan plan = impl.selectOne(getInt("id"));
		plan.setIsDel(1);
		if (impl.update(plan) == 1) {
			PlanHistory planHistory = new PlanHistory();
			planHistory.setCreateTime(DateUtil.getNowTime());
			planHistory.setMemo("完成度"+plan.getComplete()+"%,删除");
			planHistory.setPlanId(plan.getId());
			planHistory.setType("删除");
			historyImpl.add(planHistory);
			return "success";
		} else {
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping("update")
	public String update(@RequestParam Map<String, Object> params) {
		this.params = params;
		
		Plan plan = impl.selectOne(getInt("id"));
		int level = plan.getLevel();
		plan.setComplete(getInt("complete"));
		plan.setLevel(getInt("level"));
		plan.setMemo(getString("memo"));
		plan.setName(getString("name"));
		plan.setType(getString("type"));
		if (impl.update(plan) == 1) {
			PlanHistory planHistory = new PlanHistory();
			planHistory.setCreateTime(DateUtil.getNowTime());
			planHistory.setPlanId(plan.getId());
			String operation = "";
			if(level>getInt("level")) {
				operation = "升级";
			} else if(level==getInt("level")){
				operation = "平级";
			} else {
				operation = "降级";
			}
			planHistory.setType(operation);
			planHistory.setMemo("完成度"+getInt("complete")+"%");
			if(historyImpl.add(planHistory)==1) {
				return "success";
			} else {
				return "failure";
			}
			
		} else {
			return "failure";
		}
	}
	@ResponseBody
	@RequestMapping("refresh")
	public String refresh() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", getUser().getId());
		List<Plan> planList = impl.selectList(params);
		for(int i=0;i<planList.size();i++) {
			Plan plan = planList.get(i);
			Long endTime = DateUtil.getNowTime().getTime();
			Long startTime = plan.getCreateTime().getTime();
			Long days = (endTime-startTime)/(1000*60*60*24);
			if(days>=10&&plan.getLevel()>1) {
				plan.setLevel(plan.getLevel()-1);
				impl.update(plan);
				PlanHistory planHistory = new PlanHistory();
				planHistory.setCreateTime(DateUtil.getNowTime());
				planHistory.setMemo("自动升级，完成度："+plan.getComplete()+"%");
				planHistory.setPlanId(plan.getId());
				planHistory.setType("自动升级");
				historyImpl.add(planHistory);
			}
		}
		return "success";
	}

}
