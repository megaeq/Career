package com.test.fetchdata.team;

import java.io.File;
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

import com.eq.dao.entity.lottory.Country;
import com.eq.dao.entity.lottory.Team;
import com.eq.util.FileUtils;
import com.eq.util.Mysql;

public class TeamInfoFetch {
	public static String		CHARSET		= "gb2312";				// �ַ����
	private Connection			conn		= null;
	private PreparedStatement	stmt		= null;
	private static List<String>	filelist	= new ArrayList<String>();

	public static void main(String[] args) {
		TeamInfoFetch tf = new TeamInfoFetch();
		tf.todo();
	}

	public void todo() {
		refreshFileList("D:\\球队");
		for (String fileName : filelist) {
			String[] belongs = fileName.split("\\\\");
			String belong = belongs[belongs.length - 1].split("\\.")[0];
			String country = belongs[belongs.length - 2];
			Country cty = new Country();
			cty.setCountry(country);
			cty.setLeagueMatches(belong);
			System.out.println(belong + " " + country);
			List<Team> teamList = getTeamInfo(Parser.createParser(
					FileUtils.readFileByLines(fileName, CHARSET), CHARSET),
					belong);
			insertDB(teamList);
			insertCountry(cty);
		}

	}

	public static void refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				refreshFileList(files[i].getAbsolutePath());
			} else {
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				System.out.println("---" + strFileName);
				filelist.add(files[i].getAbsolutePath());
			}
		}
	}

	private List<Team> getTeamInfo(Parser parser, String belong) {
		List<Team> teamList = new ArrayList<Team>();
		try {
			NodeFilter filter = new CssSelectorNodeFilter(
					"div[id='showcontentbycodi']");
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			TagNode n1 = (TagNode) nodeList.elementAt(0);

			parser = Parser.createParser(n1.toHtml(), CHARSET);
			NodeFilter filter2 = new CssSelectorNodeFilter("tr");
			NodeList nodeList2 = parser.extractAllNodesThatMatch(filter2);

			for (int i = 2; i < nodeList2.size(); i++) {
				TagNode n2 = (TagNode) nodeList2.elementAt(i);
				parser = Parser.createParser(n2.toHtml(), CHARSET);
				NodeFilter filter3 = new CssSelectorNodeFilter("td");
				NodeList nodeList3 = parser.extractAllNodesThatMatch(filter3);

				TagNode n3 = (TagNode) nodeList3.elementAt(0);
				TagNode n4 = (TagNode) nodeList3.elementAt(1);
				String orderNo = n3.toPlainTextString();
				String chn = n4.toPlainTextString();
				System.out.println(orderNo + " " + chn);
				Team team = new Team();
				team.setChn(chn);
				team.setBelong(belong);
				team.setOrderNo(Integer.parseInt(orderNo));
				teamList.add(team);
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return teamList;
	}

	private void insertDB(List<Team> teamList) {
		try {
			initMysql();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO team_info(chn,belong,orderno) VALUES(?,?,?)";
			stmt = conn.prepareStatement(sql);

			for (Team t : teamList) {
				stmt.setString(1, t.getChn());
				stmt.setString(2, t.getBelong());
				stmt.setInt(3, t.getOrderNo());
				stmt.addBatch();
			}

			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertCountry(Country cty) {
		try {
			initMysql();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO country(country,league_matches) VALUES(?,?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, cty.getCountry());
			stmt.setString(2, cty.getLeagueMatches());
			stmt.addBatch();

			stmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
