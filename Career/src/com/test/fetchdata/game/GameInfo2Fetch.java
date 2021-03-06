package com.test.fetchdata.game;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.eq.dao.entity.lottory.Game;
import com.eq.util.FileUtils;
import com.eq.util.Mysql;
import com.eq.util.ParserUtils;

public class GameInfo2Fetch {
	public static String		CHARSET	= "gb2312"; // 字符集编码
	private Connection			conn	= null;
	private PreparedStatement	stmt	= null;
	private Statement			stmt2	= null;
	private Statement			stmt3	= null;
	private ResultSet			rs		= null;
	private ResultSet			rs2		= null;

	public static void main(String[] args) {
		GameInfo2Fetch gf = new GameInfo2Fetch();
		// gf.todo();
	}

	private void todo() {
		// List<String> filelist = FileUtils.refreshFileList("D:\\bill");
		try {
			initMysql();
			String sql = "select id,dirpath,date from game_complete where flag='0'";
			rs = stmt2.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getRow());
				String fileName = rs.getString("dirpath");
				Date date = rs.getDate("date");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
				List<Game> gameList = getGameInfo(Parser.createParser(
						FileUtils.readFileByLines(fileName, CHARSET), CHARSET),
						df.format(date), df2.format(date));
				insertDB(gameList, rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * for (String fileName : filelist) { String[] dates =
		 * fileName.split("\\\\"); String date = dates[dates.length -
		 * 1].split("\\.")[0]; Date date1 = Date.valueOf(date);
		 * 
		 * List<Game> gameList = getGameInfo(Parser.createParser(
		 * FileUtils.readFileByLines(fileName, CHARSET), CHARSET), date);
		 * insertDB(gameList, date1);
		 * 
		 * insertFile(fileName, date1); }
		 */
	}

	private List<Game> getGameInfo(Parser parser, String year, String date) {
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
				Timestamp ts = Timestamp.valueOf(year + td2.toPlainTextString()
						+ ":00");
				game.setTime(ts);
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

	private void insertFile(String filename, Date date) {
		try {
			initMysql();
			conn.setAutoCommit(false);
			String sql = "insert into game_complete(date,dirpath,flag) values(?,?,?)";

			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, date);
			stmt.setString(2, filename);
			stmt.setString(3, "0");
			stmt.addBatch();
			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertDB(List<Game> gameList, int id) {
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO game (hometeam,guestteam,homescore,guestscore,homehalfscore,guesthalfscore,winrate,drawrate,loserate,lettheball,weather,time,hometeamid,guestteamid,code,gametype)";
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
				stmt.setTimestamp(12, g.getTime());
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
			String sql1 = "update game_complete set flag='1' where id=?";
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, id);
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
			rs2 = stmt3.executeQuery(sql);
			if (rs2.next()) {
				id = rs2.getLong("id");
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
		if (stmt3 == null || stmt3.isClosed()) {
			stmt3 = conn.createStatement();
		}

	}
}
