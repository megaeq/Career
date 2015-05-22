/**
 * Copyright(C) 2011-2012 BillionHealth Software Technology LTD. All Rights Reserved.
 * @title MathModelManage.java
 * @package com.eq.service.mathModel
 * @compiler JDK1.6
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 上午8:53:48
 * @version V1.0  
 */
package com.eq.service.mathModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.entity.mathModel.MathModel;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.dao.impl.mathModel.MathModelImpl;
import com.eq.service.lottory.GameManage;
import com.eq.util.BaseAction;
import com.eq.util.mathModel.FootballModel;

/**
 * @className MathModelManage
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 上午8:53:48
 */
@Component
@RequestMapping("page/mathModel")
public class MathModelManage extends BaseAction
{
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MathModelImpl mathModelImpl;
	@Autowired
	private GameImpl gameImpl;
	@Autowired
	private FootballModel footballModel;
	@ResponseBody
	@RequestMapping("footballModelDataProcesser")
	public String FootballModelDataProcesser(@RequestParam Map<String, Object> params) {
		this.params = params;
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("type", "1");
		List<MathModel> modelList = mathModelImpl.selectList(pps);
		//FootballModel footballModel = new FootballModel();
		Long i=0l;
		for(MathModel model:modelList) {
			pps.clear();
			pps.put("maxId", model.getMaxId());
			pps.put("hasFinish", "1");
			List<Game> gameList = gameImpl.selectList(pps);
			for(Game game:gameList) {
				footballModel.add(game, model.getId());
			}
			model.setMaxId(gameList.get(gameList.size()-1).getId());
			mathModelImpl.update(model);
			i++;
			if(i%128==0) {
				logger.info("处理第 "+i+" 条数据……");
			}
		}
		return "成功";
	}
}
