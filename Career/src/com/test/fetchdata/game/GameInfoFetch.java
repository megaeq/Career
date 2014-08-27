package com.test.fetchdata.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.eq.dao.entity.Game;
import com.eq.util.FileUtils;
import com.eq.util.Mysql;

public class GameInfoFetch {

	public static String			CHARSET		= "gb2312";				// 字符集编码
	private Connection				conn		= null;
	private final PreparedStatement	stmt		= null;
	private static List<String>		filelist	= new ArrayList<String>();

	public static void main(String[] args) {
		GameInfoFetch gf = new GameInfoFetch();
		gf.todo();
	}

	private void todo() {
		getGameInfo(Parser.createParser(
				FileUtils.readFileByLines("D:\\彩票\\足球比分直播.htm", CHARSET),
				CHARSET));
	}

	private List<Game> getGameInfo(Parser parser) {
		List<Game> gameList = new ArrayList<Game>();
		try {
			NodeFilter filter = new CssSelectorNodeFilter(
					"div[id='livescore_table']");
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			TagNode n1 = (TagNode) nodeList.elementAt(0);

			parser = Parser.createParser(n1.toHtml(), CHARSET);
			NodeFilter filter2 = new CssSelectorNodeFilter(
					"tr[class='each_match ']");
			NodeList nodeList2 = parser.extractAllNodesThatMatch(filter2);
			NodeFilter filter3 = new CssSelectorNodeFilter(
					"tr[class='each_match ']");
			parser = Parser.createParser(n1.toHtml(), CHARSET);
			NodeList nodeList3 = parser.extractAllNodesThatMatch(filter3);
			nodeList2.add(nodeList3);
			for (int i = 0; i < nodeList2.size(); i++) {
				TagNode n2 = (TagNode) nodeList2.elementAt(i);
				parser = Parser.createParser(n2.toHtml(), CHARSET);
				NodeFilter filter4 = new CssSelectorNodeFilter("td");
				NodeList nodeList4 = parser.extractAllNodesThatMatch(filter4);
				Game game = new Game();
				// 1.获取比赛类型 td1
				TagNode td1 = (TagNode) nodeList4.elementAt(1);
				System.out.println(td1.toPlainTextString());
				// 2.获取比赛时间 td2
				TagNode td2 = (TagNode) nodeList4.elementAt(2);
				SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
				try {
					Timestamp ts = new Timestamp(sf.parse(
							"2014-" + td2.toPlainTextString()).getTime());
					game.setTs(ts);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 3.获取主队

			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
		return gameList;
	}

	public void initMysql() throws Exception {
		if (conn == null || conn.isClosed()) {
			conn = Mysql.getMysqlConnection();
		}
		/*
		 * if(stmt2 == null || stmt2.isClosed()) { stmt2 =
		 * conn.createStatement(); }
		 */
	}

}
