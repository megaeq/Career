package com.eq.service.lottory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.util.BaseAction;

@Controller
@RequestMapping("page/lottory/game")
public class GameManage extends BaseAction {
	@RequestMapping("getList")
	@ResponseBody
	public List<Game> getList(Date startDate, Date endDate) {
		GameImpl impl = (GameImpl) getBean("gameImpl");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return impl.selectList(params);
	}
}
