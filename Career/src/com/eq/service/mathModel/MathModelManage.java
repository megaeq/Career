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
import com.eq.dao.impl.mathModel.MathModelRefImpl;
import com.eq.service.lottory.GameManage;
import com.eq.util.BaseAction;
import com.eq.util.ParamUtils;
import com.eq.util.mathModel.FootballModel;

/**
 * @className MathModelManage
 * @description TODO
 * @author Mega.Yan
 * @date 2015-5-22 上午8:53:48
 */
@Component
@RequestMapping("mathModel")
public class MathModelManage extends BaseAction
{
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MathModelImpl mathModelImpl;
	@Autowired
	private GameImpl gameImpl;
	@Autowired
	private FootballModel footballModel;
	@Autowired
	private MathModelRefImpl mathModelRefImpl;
	@ResponseBody
	@RequestMapping("footballModelDataProcesser")
	public String FootballModelDataProcesser(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		Map<String, Object> pps = new HashMap<String, Object>();
		pps.put("type", "1");
		List<MathModel> modelList = mathModelImpl.selectList(pps);
		//FootballModel footballModel = new FootballModel();
		Long i=0l;
		for(MathModel model:modelList) {
			for(int j=0;;j++) {
				pps.clear();
				pps.put("maxId", model.getMaxId());
				pps.put("hasFinish", "1");
				pps.put("order", "1");
				List<Game> gameList = (List<Game>)gameImpl.selectPageList(pps, 0, 200).get("list");
				if(gameList.isEmpty()) {
					break;
				}
				for(Game game:gameList) {
					footballModel.add(game, model.getId());
					i++;
					if(i%128==0) {
						logger.info("处理第 "+i+" 条数据……");
					}
				}
				pps.clear();
				pps.put("mathModelId", model.getId());
				Float maxResult = mathModelRefImpl.selectMaxResult(pps);
				Float minResult = mathModelRefImpl.selectMinResult(pps);
				model.setMaxResult(maxResult);
				model.setMinResult(minResult);
				model.setMaxId(gameList.get(gameList.size()-1).getId());
				mathModelImpl.update(model);
				
			}
			
		}
		return "成功";
	}
}
