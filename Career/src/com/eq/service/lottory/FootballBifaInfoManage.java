package com.eq.service.lottory;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.util.BaseAction;
@Component
@RequestMapping("page/lottory/football/bifa")
public class FootballBifaInfoManage extends BaseAction
{
	@ResponseBody
	@RequestMapping("getFootBallGameInfo")
	public String getBifaInfo() {
		return "成功";
	}
}
