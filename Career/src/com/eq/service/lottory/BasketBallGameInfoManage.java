package com.eq.service.lottory;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.eq.dao.impl.lottory.BasketBallGameImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParamUtils;
import com.eq.util.ParserUtils;
import com.eq.util.UrlUtil;
@Component
@RequestMapping("basketBallGameInfo")
public class BasketBallGameInfoManage extends BaseAction{
	private final String CHARSET = "gb2312";
	@Autowired
	private BasketBallGameImpl impl;
	@ResponseBody
	@RequestMapping("getBasketBallBallGameInfo")
	public String getGameInfo(@RequestParam Map<String, Object> params){
		ParamUtils PU = new ParamUtils(params);
		Date startDate = PU.getDate("startDate");
		Date endDate = PU.getDate("endDate");
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
	/**
	 * 
	 * @title getGameInfo
	 * @description 获取game列表
	 * @param parser
	 * @param date
	 * @return
	 * @return List<BasketBallGame>
	 * @throws
	 */
	private List<BasketBallGame> getGameInfo(Parser parser,Date date) {
		List<BasketBallGame> gameList = new ArrayList<BasketBallGame>();
		try {
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
	/**
	 * 
	 * @title getGameList
	 * @description 获取game列表
	 * @param nodeList
	 * @param parser
	 * @param date
	 * @return
	 * @return List<BasketBallGame>
	 * @throws
	 */
	private List<BasketBallGame> getGameList(NodeList nodeList,Parser parser,Date date) {
		List<BasketBallGame> gameList = new ArrayList<BasketBallGame>();
		try {
			int code1=0;
			for(int i=0;i<nodeList.size();i++) {
				TagNode n2 = (TagNode) nodeList.elementAt(i);
				parser = Parser.createParser(n2.toHtml(), CHARSET);
				NodeFilter filter4 = new CssSelectorNodeFilter("td");
				NodeList nodeList4 = parser.extractAllNodesThatMatch(filter4);
				BasketBallGame game = new BasketBallGame();
				//0.获取比赛code td0
				TagNode td0 = (TagNode) nodeList4.elementAt(0);
				parser = Parser.createParser(td0.toHtml(), CHARSET);
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
					NodeFilter filter6 = new CssSelectorNodeFilter("div");
					NodeList nodeList6 = parser.extractAllNodesThatMatch(filter6);
					//胜负
					TagNode td41 = (TagNode) nodeList6.elementAt(0);
					parser = Parser.createParser(td41.toHtml(), CHARSET);
					NodeFilter filter7 = new CssSelectorNodeFilter("a");
					NodeList nodeList7 = parser.extractAllNodesThatMatch(filter7);
					if(nodeList7.size()==2) {
						TagNode td411 = (TagNode) nodeList7.elementAt(0);
						game.setLoseRate(Float.parseFloat(td411.toPlainTextString()));
						TagNode td412 = (TagNode) nodeList7.elementAt(1);
						game.setWinRate(Float.parseFloat(td412.toPlainTextString()));
					}
					//让球
					TagNode td42 = (TagNode) nodeList6.elementAt(1);
					parser = Parser.createParser(td42.toHtml(), CHARSET);
					NodeFilter filter8 = new CssSelectorNodeFilter("a");
					NodeList nodeList8 = parser.extractAllNodesThatMatch(filter8);
					if(nodeList8.size()==2) {
						TagNode td421 = (TagNode) nodeList8.elementAt(0);
						game.setLoseRateLB(Float.parseFloat(td421.toPlainTextString()));
						TagNode td422 = (TagNode) nodeList8.elementAt(1);
						game.setWinRateLB(Float.parseFloat(td422.toPlainTextString()));
					}
					//大小球
					TagNode td43 = (TagNode) nodeList6.elementAt(2);
					parser = Parser.createParser(td42.toHtml(), CHARSET);
					NodeFilter filter9 = new CssSelectorNodeFilter("a");
					NodeList nodeList9 = parser.extractAllNodesThatMatch(filter9);
					if(nodeList9.size()==2) {
						TagNode td431 = (TagNode) nodeList9.elementAt(0);
						game.setLoseRateBS(Float.parseFloat(td431.toPlainTextString()));
						TagNode td432 = (TagNode) nodeList9.elementAt(1);
						game.setWinRateBS(Float.parseFloat(td432.toPlainTextString()));
					}
					parser = Parser.createParser(td4.toHtml(), CHARSET);
					NodeFilter filter10 = new CssSelectorNodeFilter("span");
					NodeList nodeList10 = parser.extractAllNodesThatMatch(filter10);
					TagNode td71 = (TagNode) nodeList10.elementAt(0);
					if(td71!=null&&!td71.toPlainTextString().contains("&nbsp;")) {
						game.setLetTheBall(Float.parseFloat(td71.toPlainTextString()));
					}
					TagNode td72 = (TagNode) nodeList10.elementAt(1);
					if(td72!=null&&!td72.toPlainTextString().contains("&nbsp;")) {
						game.setBigScore(Float.parseFloat(td72.toPlainTextString()));
					}
					//概率
					if(game.getLoseRate()!=null&&game.getWinRate()!=null&&game.getWinRate().floatValue()!=0&&game.getLoseRate().floatValue()!=0) {
						game.setPl(1d/(1+game.getLoseRate()/game.getWinRate()));
						game.setPw(game.getLoseRate()*game.getPl()/game.getWinRate());
					}
					if(game.getLoseRateLB()!=null&&game.getWinRateLB()!=null&&game.getWinRateLB().floatValue()!=0&&game.getLoseRateLB().floatValue()!=0) {
						game.setPllb(1d/(1+game.getLoseRateLB()/game.getWinRateLB()));
						game.setPwlb(game.getLoseRateLB()*game.getPllb()/game.getWinRateLB());
					}
					if(game.getLoseRateBS()!=null&&game.getWinRateBS()!=null&&game.getWinRateBS().floatValue()!=0&&game.getLoseRateBS()!=0) {
						game.setPlbs(1d/(1+game.getLoseRateBS()/game.getWinRateBS()));
						game.setPwbs(game.getLoseRateBS()*game.getPlbs()/game.getWinRateBS());
					}
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
