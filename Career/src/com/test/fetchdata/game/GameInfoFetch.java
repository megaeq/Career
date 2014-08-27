package com.test.fetchdata.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public static String			CHARSET		= "gb2312";				// �ַ����
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

			for (int i = 0; i < nodeList2.size(); i++) {
				TagNode n2 = (TagNode) nodeList2.elementAt(i);
				System.out.println(n2.toPlainTextString());
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
