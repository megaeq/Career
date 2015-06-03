package com.eq.service.lottory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.BeiTouGame;
import com.eq.dao.entity.lottory.Beitou;
import com.eq.dao.entity.lottory.BeitouTemp;
import com.eq.dao.impl.lottory.BeitouGameImpl;
import com.eq.dao.impl.lottory.BeitouImpl;
import com.eq.dao.impl.lottory.BeitouTempImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParamUtils;
@Component
@RequestMapping("beitou")
public class BeitouManage extends BaseAction{
	@Autowired
	private BeitouImpl beitouImpl;
	@Autowired
	private BeitouGameImpl gameImpl;
	@Autowired
	private BeitouTempImpl tempImpl;
	@ResponseBody
	@RequestMapping("add")
	public void add(@RequestParam Map<String, Object> params) {
		ParamUtils PU = new ParamUtils(params);
		BeitouTemp temp = tempImpl.selectOne(1);
		Integer beitouId = 0;
		Beitou beitou = null;
		if(temp.getProfit()>temp.getBottom()*32) {
			//添加beitou
			beitou = new Beitou();
			beitou.setBottom(temp.getBottom()*2);
			beitou.setLevel(1);
			beitou.setProfit(0f);
			beitou.setCreateTime(DateUtil.getNowTime());
			beitouImpl.add(beitou);
			//更新
			temp.setBeitouId(beitou.getId());
			temp.setBottom(beitou.getBottom());
			temp.setProfit(0f);
			tempImpl.update(temp);
			
		} else if(temp.getProfit()+temp.getBottom()*32<0) {
			//添加beitou
			beitou = new Beitou();
			if(temp.getBottom()==2) {
				beitou.setBottom(2);
			} else {
				beitou.setBottom(temp.getBottom()/2);
			}
			beitou.setLevel(1);
			beitou.setProfit(0f);
			beitou.setCreateTime(DateUtil.getNowTime());
			beitouImpl.add(beitou);
			//更新
			temp.setBeitouId(beitou.getId());
			temp.setBottom(beitou.getBottom());
			temp.setProfit(0f);
			tempImpl.update(temp);
		} else {
			beitou = beitouImpl.selectOne(temp.getBeitouId());
			if(beitou.getLevel()>=5) {
				//添加beitou
				beitou = new Beitou();
				if(temp.getBottom()==2) {
					beitou.setBottom(2);
				} else {
					beitou.setBottom(temp.getBottom()/2);
				}
				beitou.setLevel(1);
				beitou.setProfit(0f);
				beitou.setCreateTime(DateUtil.getNowTime());
				beitouImpl.add(beitou);
				//更新
				temp.setBeitouId(beitou.getId());
				temp.setBottom(beitou.getBottom());
				temp.setProfit(0f);
				tempImpl.update(temp);
			} else {
				beitou.setLevel(beitou.getLevel()+1);
				beitouImpl.update(beitou);
			}
		}
		BeiTouGame game = new BeiTouGame();
		game.setAmount(PU.getInt("amount"));
		game.setBeitouId(beitou.getId());
		game.setBelong(PU.getString("belong"));
		game.setBet(PU.getInt("bet"));
		game.setBottom(beitou.getBottom());
		game.setCreateTime(DateUtil.getNowTime());
		game.setDraw(PU.getFloat("draw"));
		game.setGuestTeam(PU.getString("guestTeam"));
		game.setHomeTeam(PU.getString("homeTeam"));
		game.setLevel(1);
		game.setLose(PU.getFloat("lose"));
		game.setType(PU.getString("type"));
		game.setWin(PU.getFloat("win"));
		gameImpl.add(game);
	} 
}
