package com.eq.service.lottory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import com.eq.dao.entity.lottory.Game;
import com.eq.dao.impl.lottory.GameImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParserUtils;
import com.eq.util.UrlUtil;

@Component
@RequestMapping("page/lottory")
public class GameInfoManage extends BaseAction
{
	private final String CHARSET = "gb2312";
	@Autowired
	private GameImpl impl;
	@ResponseBody
	@RequestMapping("getFootBallGameInfo")
	public String getGameInfo(@RequestParam Map<String, Object> params)
	{
		this.params = params;
		Date startDate = getDate("startDate");
		Date endDate = getDate("endDate");
		Long starts = startDate.getTime();
		Long ends = endDate.getTime();
		try
		{
			for (int i = 0; starts <= ends; i++)
			{
				Date date = new Date(starts);
				String url = getProperty("okooozucaijingcai") + "?date=" + DateUtil.getDateStr(date);
				List<Game> gameList = getGameInfo(UrlUtil.getContent(url, CHARSET), date);
				Map<String, Object> pps = new HashMap<String, Object>();
				for (Game game : gameList)
				{
					pps.clear();
					pps.put("code", game.getCode());
					if(impl.selectList(pps).isEmpty())
					{
						impl.add(game);
					}
					else
					{
						impl.update(game);
					}
				}
				starts += 24l * 60 * 60 * 1000;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "异常";
		}
		return "成功";
	}

	private List<Game> getGameInfo(String html, Date date)
	{
		List<Game> gameList = new ArrayList<Game>();
		NodeList nodeList = ParserUtils.getNodeList(html, "div[id='livescore_table']");
		TagNode n1 = (TagNode) nodeList.elementAt(0);

		NodeList nodeList2 = ParserUtils.getNodeList(n1.toHtml(), "tr[class='each_match ']");
		NodeList nodeList3 = ParserUtils.getNodeList(n1.toHtml(), "tr[class='each_match tr_bg']");
		nodeList2.add(nodeList3);
		for (int i = 0; i < nodeList2.size(); i++)
		{
			TagNode n2 = (TagNode) nodeList2.elementAt(i);
			NodeList nodeList4 = ParserUtils.getNodeList(n2.toHtml(), "td");
			Game game = new Game();
			// 0.获取比赛code
			TagNode td0 = (TagNode) nodeList4.elementAt(0);
			NodeList nodeList0 = ParserUtils.getNodeList(td0.toHtml(), "span");
			TagNode td01 = (TagNode) nodeList0.elementAt(0);
			game.setCode(DateUtil.getDateStr(date) + td01.toPlainTextString());
			// 1.获取比赛类型 td1
			TagNode td1 = (TagNode) nodeList4.elementAt(1);
			System.out.println(td1.toPlainTextString());
			game.setGameType(td1.toPlainTextString());
			// 2.获取比赛时间 td2
			TagNode td2 = (TagNode) nodeList4.elementAt(2);
			Timestamp ts = Timestamp.valueOf(DateUtil.getCalendar(date).get(Calendar.YEAR) + "-" + td2.toPlainTextString() + ":00");
			game.setTime(ts);
			// 3.获取主队 td4
			TagNode td4 = (TagNode) nodeList4.elementAt(4);
			NodeList nodeList5 = ParserUtils.getNodeList(td4.toHtml(), "a");

			TagNode td41 = (TagNode) nodeList5.elementAt(0);
			game.setHomeTeam(td41.toPlainTextString());
			// 4.全场比分 td5
			TagNode td5 = (TagNode) nodeList4.elementAt(5);
			String[] scores = ParserUtils.toPlainText("b", td5.toHtml());
			if(scores.length >= 3)
			{
				if(StringUtils.isNotBlank(scores[0]) && StringUtils.isNotBlank(scores[2]))
				{
					game.setHomeScore(Integer.parseInt(scores[0]));
					game.setGuestScore(Integer.parseInt(scores[2]));
				}
			}
			// 5. 获取客队 td6
			TagNode td6 = (TagNode) nodeList4.elementAt(6);
			NodeList nodeList7 = ParserUtils.getNodeList(td6.toHtml(), "a");
			TagNode td61 = (TagNode) nodeList7.elementAt(0);
			game.setGuestTeam(td61.toPlainTextString());
			// 6.半场比分
			TagNode td7 = (TagNode) nodeList4.elementAt(7);
			NodeList nodeList8 = ParserUtils.getNodeList(td7.toHtml(), "span");
			TagNode td71 = (TagNode) nodeList8.elementAt(0);
			String half = td71.toPlainTextString();
			String[] halfs = half.split("-");
			if(halfs.length >= 2)
			{
				if(StringUtils.isNotBlank(halfs[0]) && StringUtils.isNotBlank(halfs[1]))
				{
					game.setHomeHalfScore(Integer.parseInt(halfs[0]));
					game.setGuestHalfScore(Integer.parseInt(halfs[1]));
				}
			}
			// 7.天气
			TagNode td8 = (TagNode) nodeList4.elementAt(8);
			NodeList nodeList9 = ParserUtils.getNodeList(td8.toHtml(), "img");
			TagNode td81 = (TagNode) nodeList9.elementAt(0);
			if(td81 != null)
			{
				game.setWeather(td81.getAttribute("alt"));
			}
			// 8.赔率
			TagNode td9 = (TagNode) nodeList4.elementAt(9);
			NodeList nodeList10 = ParserUtils.getNodeList(td9.toHtml(), "span");
			TagNode td91 = (TagNode) nodeList10.elementAt(0);
			TagNode td92 = (TagNode) nodeList10.elementAt(1);
			TagNode td93 = (TagNode) nodeList10.elementAt(2);
			if(td91 != null && td92 != null && td93 != null)
			{
				game.setWinRate(Float.parseFloat(td91.toPlainTextString()));
				game.setDrawRate(Float.parseFloat(td92.toPlainTextString()));
				game.setLoseRate(Float.parseFloat(td93.toPlainTextString()));
			}
			if(game.getWinRate() != null && game.getDrawRate() != null && game.getLoseRate() != null)
			{
				game.setPw(1 / (1 + game.getWinRate() / game.getDrawRate() + game.getWinRate() / game.getLoseRate()));
				game.setPd(game.getPw() * game.getWinRate() / game.getDrawRate());
				game.setPl(game.getPw() * game.getWinRate() / game.getLoseRate());
			}
			if(game.getHomeScore() != null && game.getHomeScore() > 0 && game.getGuestScore() != null && game.getWinRate() != null && game.getDrawRate() != null && game.getLoseRate() != null)
			{
				game.setHasFinish("1");
			}
			else
			{
				game.setHasFinish("0");
			}
			gameList.add(game);
		}
		return gameList;
	}
}
