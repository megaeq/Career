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
	@RequestMapping("getListByName")
	public List<Game> getListByName(@RequestParam Map<String, Object> params) {
		this.params = params;
		Game game = impl.selectOne(getInt("gameId"));
		Map<String, Object> pps = new HashMap<String, Object>();
		if ("home".equalsIgnoreCase(getString("hg"))) {
			pps.put("teamname", game.getHomeTeam());
		} else if ("guest".equalsIgnoreCase(getString("hg"))) {
			pps.put("teamname", game.getGuestTeam());
		}
		return impl.selectList(pps);
	}

	@ResponseBody
	@RequestMapping("getABList")
	public List<Game> getABList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("aname", getString("aname"));
		pps.put("bname", getString("bname"));
		return impl.selectList(pps);
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
