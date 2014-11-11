package com.eq.service.lottory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eq.dao.entity.lottory.BasketBallGame;
import com.eq.dao.entity.lottory.Game;
import com.eq.dao.impl.lottory.BasketBallGameImpl;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParserUtils;
import com.eq.util.UrlUtil;
@Component
@RequestMapping("page/lottory")
public class BasketBallGameInfoManage extends BaseAction{
	private final String CHARSET = "gb2312";
	@Autowired
	private BasketBallGameImpl impl;
	@ResponseBody
	@RequestMapping("getBasketBallBallGameInfo")
	public String getGameInfo(@RequestParam Map<String, Object> params){
		this.params = params;
		Date startDate = getDate("startDate");
		Date endDate = getDate("endDate");
		Long starts = startDate.getTime();
		Long ends = endDate.getTime();
		try {
			for(int i=0;starts<=ends;i++) {
				Date date = new Date(starts);
				String url = getProperty("okooolancaihunhe")+DateUtil.getDateStr(date);
				List<BasketBallGame> gameList = getGameInfo(Parser.createParser(UrlUtil.getContent(url, CHARSET), CHARSET), date);
				Map<String, Object> pps = new HashMap<String, Object>();
				for(BasketBallGame game:gameList) {
					pps.clear();
					pps.put("code", game.getCode());
					if(impl.selectList(pps).isEmpty()) {
						impl.add(game);
					} else {
						impl.update(game);
					}
				}
				starts+=24l*60*60*1000;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "异常";
		}
		return "成功";
	}
	
	private List<BasketBallGame> getGameInfo(Parser parser,Date date) {
		List<BasketBallGame> gameList = new ArrayList<BasketBallGame>();
		try {
			int code1=0;
			int code2=0;
			NodeFilter filter = new CssSelectorNodeFilter(
					"table[class='jcmaintable one']");
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			TagNode n1 = (TagNode) nodeList.elementAt(0);

			parser = Parser.createParser(n1.toHtml(), CHARSET);
			NodeFilter filter2 = new CssSelectorNodeFilter(
					"tr[class='alltrObj trClassbg endMacthObj overbg']");
			NodeList nodeList2 = parser.extractAllNodesThatMatch(filter2);
			NodeFilter filter3 = new CssSelectorNodeFilter(
					"tr[class='alltrObj endMacthObj overbg']");
			parser = Parser.createParser(n1.toHtml(), CHARSET);
			NodeList nodeList3 = parser.extractAllNodesThatMatch(filter3);
			for(BasketBallGame game:getGameList(nodeList2, parser, date)) {
				gameList.add(game);
			}
			for(BasketBallGame game:getGameList(nodeList3, parser, date)) {
				gameList.add(game);
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return gameList;
	}
	
	private List<BasketBallGame> getGameList(NodeList nodeList,Parser parser,Date date) {
		List<BasketBallGame> gameList = new ArrayList<BasketBallGame>();
		try {
			int code1=0;
			for(int i=0;i<nodeList.size();i++) {
				TagNode n2 = (TagNode) nodeList.elementAt(i);
				System.out.println(n2.toPlainTextString());
				parser = Parser.createParser(n2.toHtml(), CHARSET);
				NodeFilter filter4 = new CssSelectorNodeFilter("td");
				NodeList nodeList4 = parser.extractAllNodesThatMatch(filter4);
				BasketBallGame game = new BasketBallGame();
				//0.获取比赛code td0
				TagNode td0 = (TagNode) nodeList4.elementAt(0);
				parser = Parser.createParser(td0.toHtml(), CHARSET);
				System.out.println(td0);
				NodeFilter filter0 = new CssSelectorNodeFilter("span");
				NodeList nodeList0 = parser.extractAllNodesThatMatch(filter0);
				TagNode td01 = (TagNode) nodeList0.elementAt(0);
				game.setCode(DateUtil.getDateStr(date) + td01.toPlainTextString());
				Integer.parseInt(td01.toPlainTextString());
				if(Integer.parseInt(td01.toPlainTextString())>code1) {
					code1 = Integer.parseInt(td01.toPlainTextString());
					// 1.获取比赛类型 td1
					parser = Parser.createParser(td0.toHtml(), CHARSET);
					NodeFilter filter1 = new CssSelectorNodeFilter("a");
					NodeList nodeList1 = parser.extractAllNodesThatMatch(filter1);
					TagNode td02 = (TagNode) nodeList1.elementAt(0);
					game.setType(td02.toPlainTextString());
					// 2.获取比赛时间 td2
					TagNode td1 = (TagNode) nodeList4.elementAt(1);
					String time= td1.getAttribute("title").split("：")[1];
					Timestamp ts = Timestamp.valueOf(time);
					game.setTime(ts);
					//3.获取球队及比分td3
					TagNode td2 = (TagNode) nodeList4.elementAt(2);
					parser = Parser.createParser(td2.toHtml(), CHARSET);
					NodeFilter filter5 = new CssSelectorNodeFilter("a");
					NodeList nodeList5 = parser.extractAllNodesThatMatch(filter5);
					TagNode td21 = (TagNode) nodeList5.elementAt(0);
					game.setGuestTeam(td21.getAttribute("title"));
					TagNode td22 = (TagNode) nodeList5.elementAt(1);
					game.setHomeTeam(td22.getAttribute("title"));
					String scores = ParserUtils.toPlainText("b", td2.toHtml())[0];
					if(scores.split("-").length==2) {
						game.setGuestScore(Integer.parseInt(scores.split("-")[0]));
						game.setHomeScore(Integer.parseInt(scores.split("-")[1]));
					}
					//3.获取赔率等信息 td4
					TagNode td4 = (TagNode) nodeList4.elementAt(4);
					parser = Parser.createParser(td4.toHtml(), CHARSET);
					NodeFilter filter6 = new CssSelectorNodeFilter("a");
					NodeList nodeList6 = parser.extractAllNodesThatMatch(filter6);
					TagNode td41 = (TagNode) nodeList6.elementAt(0);
					game.setLoseRate(Float.parseFloat(td41.toPlainTextString()));
					TagNode td42 = (TagNode) nodeList6.elementAt(1);
					game.setWinRate(Float.parseFloat(td42.toPlainTextString()));
					TagNode td43 = (TagNode) nodeList6.elementAt(2);
					game.setLoseRateLB(Float.parseFloat(td43.toPlainTextString()));
					TagNode td44 = (TagNode) nodeList6.elementAt(3);
					game.setWinRateLB(Float.parseFloat(td44.toPlainTextString()));
					TagNode td45 = (TagNode) nodeList6.elementAt(4);
					game.setLoseRateBS(Float.parseFloat(td45.toPlainTextString()));
					TagNode td46 = (TagNode) nodeList6.elementAt(5);
					game.setWinRateBS(Float.parseFloat(td46.toPlainTextString()));
					parser = Parser.createParser(td4.toHtml(), CHARSET);
					NodeFilter filter7 = new CssSelectorNodeFilter("span");
					NodeList nodeList7 = parser.extractAllNodesThatMatch(filter7);
					TagNode td71 = (TagNode) nodeList7.elementAt(0);
					game.setLetTheBall(Float.parseFloat(td71.toPlainTextString()));
					TagNode td72 = (TagNode) nodeList7.elementAt(1);
					game.setBigScore(Float.parseFloat(td72.toPlainTextString()));
					//概率
					game.setPl(1d/(1+game.getLoseRate()/game.getWinRate()));
					game.setPw(game.getLoseRate()*game.getPl()/game.getWinRate());
					game.setPllb(1d/(1+game.getLoseRateLB()/game.getWinRateLB()));
					game.setPwlb(game.getLoseRateLB()*game.getPllb()/game.getWinRateLB());
					game.setPlbs(1d/(1+game.getLoseRateBS()/game.getWinRateBS()));
					game.setPwbs(game.getLoseRateBS()*game.getPlbs()/game.getWinRateBS());
					gameList.add(game);
				} else{
					break; 
				}
				
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return gameList;
	}
}
