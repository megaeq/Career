package com.eq.service.lottory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.BasketBallGame;
import com.eq.dao.impl.lottory.BasketBallGameImpl;
import com.eq.util.BaseAction;
@Component
@RequestMapping("page/lottory/basketball")
public class BasketBallManage extends BaseAction {
	@Autowired
	private BasketBallGameImpl impl;
	@ResponseBody
	@RequestMapping("getList")
	public List<BasketBallGame> getList(@RequestParam Map<String, Object> params) {
		this.params  = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("startDate", getDate("startDate"));
		pps.put("endDate", getDate("endDate"));
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<BasketBallGame>)pps2.get("list");
	}
}
