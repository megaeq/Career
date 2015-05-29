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
import com.eq.util.DateUtil;

@Controller
@RequestMapping("page/lottory/game")
public class GameManage extends BaseAction {
	@Autowired
	private GameImpl	impl;

	@RequestMapping("getList")
	@ResponseBody
	public List<Game> getList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("startDate", getDate("startDate"));
		pps.put("endDate", getDate("endDate"));
		if("now".equals(getString("isNow"))) {
			pps.put("startDate", DateUtil.getNowTime());
		}
		pps.put("Rw", getFloat("Rw"));
		pps.put("Rd", getFloat("Rd"));
		pps.put("Rl", getFloat("Rl"));
		pps.put("R1", getFloat("R1"));
		pps.put("R2", getFloat("R2"));
		pps.put("R3", getFloat("R3"));
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<Game>)pps2.get("list");
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
	@ResponseBody
	@RequestMapping("getGameInfo")
	public Game getGameInfo(@RequestParam Map<String, Object> params) {
		this.params = params;
		return impl.selectOne(getInt("id"));
	}
}
