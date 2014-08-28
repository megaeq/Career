package com.test.fetchdata.game;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.eq.dao.entity.Game;
import com.eq.util.FileUtils;
import com.eq.util.Mysql;
import com.eq.util.ParserUtils;

public class GameInfoFetch {

	public static String		CHARSET	= "gb2312"; // 字符集编码
	private Connection			conn	= null;
	private PreparedStatement	stmt	= null;
	private Statement			stmt2	= null;
	private ResultSet			rs		= null;

	public static void main(String[] args) {
		GameInfoFetch gf = new GameInfoFetch();
		gf.todo();
	}

	private void todo() {
		List<String> filelist = FileUtils.refreshFileList("D:\\bill");

		for (String fileName : filelist) {
			String[] dates = fileName.split("\\\\");
			String date = dates[dates.length - 1].split("\\.")[0];
			Date date1 = Date.valueOf(date);
			List<Game> gameList = getGameInfo(Parser.createParser(
					FileUtils.readFileByLines(fileName, CHARSET), CHARSET),
					date);
			insertDB(gameList, date1);
		}
	}

	private List<Game> getGameInfo(Parser parser, String date) {
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
					"tr[class='each_match tr_bg']");
			parser = Parser.createParser(n1.toHtml(), CHARSET);
			NodeList nodeList3 = parser.extractAllNodesThatMatch(filter3);
			nodeList2.add(nodeList3);
			for (int i = 0; i < nodeList2.size(); i++) {
				TagNode n2 = (TagNode) nodeList2.elementAt(i);
				parser = Parser.createParser(n2.toHtml(), CHARSET);
				NodeFilter filter4 = new CssSelectorNodeFilter("td");
				NodeList nodeList4 = parser.extractAllNodesThatMatch(filter4);
				Game game = new Game();
				// 0.获取比赛code
				TagNode td0 = (TagNode) nodeList4.elementAt(0);
				parser = Parser.createParser(td0.toHtml(), CHARSET);
				NodeFilter filter0 = new CssSelectorNodeFilter("span");
				NodeList nodeList0 = parser.extractAllNodesThatMatch(filter0);
				TagNode td01 = (TagNode) nodeList0.elementAt(0);
				game.setCode(date + td01.toPlainTextString());
				// 1.获取比赛类型 td1
				TagNode td1 = (TagNode) nodeList4.elementAt(1);
				System.out.println(td1.toPlainTextString());
				game.setGameType(td1.toPlainTextString());
				// 2.获取比赛时间 td2
				TagNode td2 = (TagNode) nodeList4.elementAt(2);
				Timestamp ts = Timestamp.valueOf("2014-"
						+ td2.toPlainTextString() + ":00");
				game.setTs(ts);
				// 3.获取主队 td4
				TagNode td4 = (TagNode) nodeList4.elementAt(4);
				parser = Parser.createParser(td4.toHtml(), CHARSET);
				NodeFilter filter5 = new CssSelectorNodeFilter("a");
				NodeList nodeList5 = parser.extractAllNodesThatMatch(filter5);

				TagNode td41 = (TagNode) nodeList5.elementAt(0);
				game.setHomeTeam(td41.toPlainTextString());
				// 4.全场比分 td5
				TagNode td5 = (TagNode) nodeList4.elementAt(5);
				String[] scores = ParserUtils.toPlainText("b", td5.toHtml());
				if (scores.length >= 3) {
					if (StringUtils.isNotBlank(scores[0])
							&& StringUtils.isNotBlank(scores[2])) {
						game.setHomeScore(Integer.parseInt(scores[0]));
						game.setGuestScore(Integer.parseInt(scores[2]));
					}
				}
				// 5. 获取客队 td6
				TagNode td6 = (TagNode) nodeList4.elementAt(6);
				parser = Parser.createParser(td6.toHtml(), CHARSET);
				NodeFilter filter7 = new CssSelectorNodeFilter("a");
				NodeList nodeList7 = parser.extractAllNodesThatMatch(filter7);
				TagNode td61 = (TagNode) nodeList7.elementAt(0);
				game.setGuestTeam(td61.toPlainTextString());
				// 6.半场比分
				TagNode td7 = (TagNode) nodeList4.elementAt(7);
				parser = Parser.createParser(td7.toHtml(), CHARSET);
				NodeFilter filter8 = new CssSelectorNodeFilter("span");
				NodeList nodeList8 = parser.extractAllNodesThatMatch(filter8);
				TagNode td71 = (TagNode) nodeList8.elementAt(0);
				String half = td71.toPlainTextString();
				String[] halfs = half.split("-");
				if (halfs.length >= 2) {
					if (StringUtils.isNotBlank(halfs[0])
							&& StringUtils.isNotBlank(halfs[1])) {
						game.setHomeHalfScore(Integer.parseInt(halfs[0]));
						game.setGuestHalfScore(Integer.parseInt(halfs[1]));
					}
				}
				// 7.天气
				TagNode td8 = (TagNode) nodeList4.elementAt(8);
				parser = Parser.createParser(td8.toHtml(), CHARSET);
				NodeFilter filter9 = new CssSelectorNodeFilter("img");
				NodeList nodeList9 = parser.extractAllNodesThatMatch(filter9);
				TagNode td81 = (TagNode) nodeList9.elementAt(0);
				if (td81 != null) {
					game.setWeather(td81.getAttribute("alt"));
				}
				// 8.赔率
				TagNode td9 = (TagNode) nodeList4.elementAt(9);
				parser = Parser.createParser(td9.toHtml(), CHARSET);
				NodeFilter filter10 = new CssSelectorNodeFilter("span");
				NodeList nodeList10 = parser.extractAllNodesThatMatch(filter10);
				TagNode td91 = (TagNode) nodeList10.elementAt(0);
				TagNode td92 = (TagNode) nodeList10.elementAt(1);
				TagNode td93 = (TagNode) nodeList10.elementAt(2);
				if (td91 != null && td92 != null && td93 != null) {
					game.setWinRate(Float.parseFloat(td91.toPlainTextString()));
					game.setDrawRate(Float.parseFloat(td92.toPlainTextString()));
					game.setLoseRate(Float.parseFloat(td93.toPlainTextString()));
				}

				gameList.add(game);
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
		return gameList;
	}

	private void insertDB(List<Game> gameList, Date date) {
		try {
			initMysql();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO game (home_team,guest_team,home_score,guest_score,home_half_score,guest_half_score,win_rate,draw_rate,lose_rate,let_the_ball,weather,time,home_team_id,guest_team_id,code,game_type)";
			sql += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);

			for (Game g : gameList) {
				stmt.setString(1, g.getHomeTeam());
				stmt.setString(2, g.getGuestTeam());
				if (g.getHomeScore() == null) {
					stmt.setInt(3, -1);
				} else {
					stmt.setInt(3, g.getHomeScore());
				}
				if (g.getGuestScore() == null) {
					stmt.setInt(4, -1);
				} else {
					stmt.setInt(4, g.getGuestScore());
				}
				if (g.getHomeHalfScore() == null) {
					stmt.setInt(5, -1);
				} else {
					stmt.setInt(5, g.getHomeHalfScore());
				}
				if (g.getGuestHalfScore() == null) {
					stmt.setInt(6, -1);
				} else {
					stmt.setInt(6, g.getGuestHalfScore());
				}
				if (g.getWinRate() == null) {
					stmt.setFloat(7, 0);
				} else {
					stmt.setFloat(7, g.getWinRate());
				}
				if (g.getDrawRate() == null) {
					stmt.setFloat(8, 0);
				} else {
					stmt.setFloat(8, g.getDrawRate());
				}
				if (g.getLoseRate() == null) {
					stmt.setFloat(9, 0);
				} else {
					stmt.setFloat(9, g.getLoseRate());
				}
				stmt.setInt(10, 0);
				stmt.setString(11, g.getWeather());
				stmt.setTimestamp(12, g.getTs());
				Long homeTeamId = getTeamId(g.getHomeTeam());
				Long guestTeamId = getTeamId(g.getGuestTeam());
				if (homeTeamId != null) {
					stmt.setLong(13, homeTeamId);
				} else {
					stmt.setLong(13, 1l);
				}
				if (guestTeamId != null) {
					stmt.setLong(14, getTeamId(g.getGuestTeam()));
				} else {
					stmt.setLong(14, 1l);
				}
				stmt.setString(15, g.getCode());
				stmt.setString(16, g.getGameType());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
			String sql1 = "INSERT INTO game_complete(date) VALUES(?)";
			stmt = conn.prepareStatement(sql1);
			stmt.setDate(1, date);
			stmt.addBatch();
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Long getTeamId(String name) {
		Long id = 1l;
		try {
			initMysql();
			String sql = "SELECT * FROM team_info WHERE chn='" + name + "'";
			rs = stmt2.executeQuery(sql);
			if (rs.next()) {
				id = rs.getLong("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public void initMysql() throws Exception {
		if (conn == null || conn.isClosed()) {
			conn = Mysql.getMysqlConnection();
		}

		if (stmt2 == null || stmt2.isClosed()) {
			stmt2 = conn.createStatement();
		}

	}

}
