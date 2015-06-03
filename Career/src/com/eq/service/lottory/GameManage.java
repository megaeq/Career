package com.eq.service.lottory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParamUtils;

@Controller
@RequestMapping("game")
public class GameManage extends BaseAction {
	Logger logger = Logger.getLogger(GameManage.class);
	@Autowired
	private GameImpl	impl;

	@RequestMapping("getList")
	@ResponseBody
	public List<Game> getList(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("startDate", PU.getDate("startDate"));
		pps.put("endDate", PU.getDate("endDate"));
		if("now".equals(PU.getString("isNow"))) {
			pps.put("startDate", DateUtil.getNowTime());
		}
		pps.put("Rw", PU.getFloat("Rw"));
		pps.put("Rd", PU.getFloat("Rd"));
		pps.put("Rl", PU.getFloat("Rl"));
		pps.put("R1", PU.getFloat("R1"));
		pps.put("R2", PU.getFloat("R2"));
		pps.put("R3", PU.getFloat("R3"));
		Map<String, Object> pps2 = impl.selectPageList(pps, currentPage, pageSize);
		response.setHeader("Content-Range", rangeStr+pps2.get("count"));
		return (List<Game>)pps2.get("list");
	}
	

	@ResponseBody
	@RequestMapping("getListByName")
	public List<Game> getListByName(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Game game = impl.selectOne(PU.getInt("gameId"));
		
		
		Map<String, Object> pps = new HashMap<String, Object>();
		if ("home".equalsIgnoreCase(PU.getString("hg"))) {
			pps.put("teamname", game.getHomeTeam());
		} else if ("guest".equalsIgnoreCase(PU.getString("hg"))) {
			pps.put("teamname", game.getGuestTeam());
		}
		return impl.selectList(pps);
	}

	@ResponseBody
	@RequestMapping("getABList")
	public List<Game> getABList(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Game game = impl.selectOne(PU.getInt("gameId2"));
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("aname", game.getHomeTeam());
		pps.put("bname", game.getGuestTeam());
		return impl.selectList(pps);
	}

	@ResponseBody
	@RequestMapping("update")
	public void update(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Game game = new Game();
		game.setId(PU.getInt("id"));
		game.setSuggest(PU.getString("suggest"));
		impl.update(game);
	}
	@ResponseBody
	@RequestMapping("getGameInfo")
	public Game getGameInfo(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		return impl.selectOne(PU.getInt("id"));
	}
}
