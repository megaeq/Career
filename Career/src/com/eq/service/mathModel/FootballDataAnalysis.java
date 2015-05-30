/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title FootballDataAnalysis.java
 * @package com.eq.service.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-27 上午8:47:53
 * @version V1.0  
 */
package com.eq.service.mathModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.entity.mathModel.MathModel;
import com.eq.dao.entity.mathModel.MathModelRef;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.dao.impl.mathModel.MathModelImpl;
import com.eq.dao.impl.mathModel.MathModelRefImpl;
import com.eq.util.BaseAction;
import com.eq.util.mathModel.FootballModel;

/**
 * @className FootballDataAnalysis
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-27 上午8:47:53
 */
@Controller
@RequestMapping("footballDataAnalysis")
public class FootballDataAnalysis extends BaseAction
{
	@Autowired
	private MathModelImpl mathModelImpl;
	@Autowired
	private MathModelRefImpl mathModelRefImpl;
	@Autowired
	private GameImpl gameImpl;
	@Autowired
	private FootballModel footballModel;
	@ResponseBody
	@RequestMapping("getResultList")
	public List<MathModel> getResultList(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		List<MathModel> modelList = mathModelImpl.selectList(pps);
		Game game = gameImpl.selectOne(getInt("id"));
		for(MathModel model:modelList) {
			Float result = footballModel.getResult(game, model.getId());
			//间距
			Float interval = (model.getMaxResult()-model.getMinResult())/(400*5);
			pps.clear();
			pps.put("mathModelId", model.getId());
			pps.put("maxResult", result+interval/2);
			pps.put("minResult", result-interval/2);
			pps.put("order", "1");
			List<MathModelRef> modelRefList = mathModelRefImpl.selectList(pps);
			List<Game> gameList = new ArrayList<Game>();
			Float score = 0f;
			for(MathModelRef modelRef:modelRefList) {
				Game g = gameImpl.selectOne(modelRef.getGameId());
				gameList.add(g);
				score+=g.score();
			}
			model.setAverageScore(score/(modelRefList.size()==0?1:modelRefList.size()));
			model.setGameList(gameList);
		}
		for(int i=0;i<modelList.size();i++) {
			boolean hasExchange = false;
			for(int j=1;j<modelList.size()-1;j++) {
				if(modelList.get(j).getAverageScore()>modelList.get(j-1).getAverageScore()) {
					hasExchange = true;
					MathModel model = modelList.get(j);
					modelList.remove(j);
					modelList.add(j-1, model);
				}
			}
			if(!hasExchange) {
				break;
			}
		}
		if(modelList.size()>10) {
			return modelList.subList(0, 9);
		} else {
			return modelList;
		}
		
	}
}
