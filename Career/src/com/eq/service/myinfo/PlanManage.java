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
import com.eq.dao.impl.myinfo.PlanImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/myinfo/plan")
public class PlanManage extends BaseAction {
	@Autowired
	private PlanImpl	impl;

	@ResponseBody
	@RequestMapping("getList")
	public List<Plan> getList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		return impl.selectList(pps);
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
		if (1 == impl.add(plan)) {
			return "success";
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
			return "success";
		} else {
			return "failure";
		}
	}

	@ResponseBody
	@RequestMapping("update")
	public String update(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("id", getInt("id"));
		pps.put("type", getString("type"));
		pps.put("name", getString("name"));
		return "";
	}

}
