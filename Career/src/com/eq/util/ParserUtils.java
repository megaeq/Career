package com.eq.util;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ParserUtils
{
	private static String CHARSET = "gb2312";
	public static String[] toPlainText(String tagName,String html)
	{
		String[] ss = html.split("</"+tagName+">");
		String[] ss1 = new String[ss.length-1];
		for(int i=0;i<ss.length-1;i++) {
			String [] ss2 = ss[i].split("<b");
			String s2=ss2[ss2.length-1];
			int index = 0;
			index = s2.indexOf(">");
			if(index==0) {
				if(s2.split(">").length==0) {
					ss1[i] = "";
				} else if(s2.split(">").length==2) {
					ss1[i]=s2.split(">")[1];
				}
			} else if(index==s2.length()-1){
				ss1[i]="";
			} else {
				ss1[i]=s2.split(">")[s2.split(">").length-1];
			}
		}
		return ss1;
	}
	
	public static NodeList getNodeList(String html,String cssFilter) {
		Parser	parser = Parser.createParser(html, CHARSET);
		NodeFilter filter3 = new CssSelectorNodeFilter(cssFilter);
		NodeList nodeList = null;
		try
		{
			nodeList =  parser.extractAllNodesThatMatch(filter3);
		}
		catch (ParserException e)
		{
			e.printStackTrace();
		}
		return nodeList;
	}
	public static void main(String[] args)
	{
		/*String s = "<td class=\"show_score\" val=\"637712\">" + "<a href=\"/soccer/match/637712/\" target=\"_blank\" onClick=\"google_p(['赛事比分直播页', '点击比分']);\">"
				+ "<b class=\"font_red ctrl_homescore\"></b>" + "<b class=\"font_red ctrl_scoresplit\"></b>" + " <b class=\"font_red ctrl_awayscore\"></b>" + "  </a>" + "</td>";*/
		String[] ss = toPlainText("em", "11<em></em>2<em>2</em>11");
		System.out.println(ss[0]+" " +ss[1]);
		//String []ss=s.split(">");
	}
}
