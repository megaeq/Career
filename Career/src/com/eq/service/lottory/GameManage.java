package com.eq.service.lottory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.util.BaseAction;

@Controller
@RequestMapping("page/lottory/game")
public class GameManage extends BaseAction {
	@Autowired
	private GameImpl	impl;

	@RequestMapping("getList")
	@ResponseBody
	public List<Game> getList(Date startDate, Date endDate) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return impl.selectList(params);
	}

	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		this.params = params;
		Game game = new Game();
		game.setId(getInt("id"));
		game.setSuggest(getString("suggest"));
		impl.update(game);
	}
}
