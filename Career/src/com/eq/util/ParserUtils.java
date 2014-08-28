package com.eq.util;

public class ParserUtils
{
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
	public static void main(String[] args)
	{
		/*String s = "<td class=\"show_score\" val=\"637712\">" + "<a href=\"/soccer/match/637712/\" target=\"_blank\" onClick=\"google_p(['赛事比分直播页', '点击比分']);\">"
				+ "<b class=\"font_red ctrl_homescore\"></b>" + "<b class=\"font_red ctrl_scoresplit\"></b>" + " <b class=\"font_red ctrl_awayscore\"></b>" + "  </a>" + "</td>";*/
		String[] ss = toPlainText("b", "11<b></b>2<b>2</b>11");
		System.out.println(ss[0]+" " +ss[1]);
		//String []ss=s.split(">");
	}
}
