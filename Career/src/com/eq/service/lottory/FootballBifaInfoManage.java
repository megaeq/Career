package com.eq.service.lottory;

import java.util.ArrayList;
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

import com.eq.dao.entity.lottory.BasketBallGame;
import com.eq.dao.entity.lottory.FootballBifa;
import com.eq.dao.impl.lottory.FootballBifaDetailImpl;
import com.eq.dao.impl.lottory.FootballBifaImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.ParserUtils;
import com.eq.util.UrlUtil;
@Component
@RequestMapping("page/lottory/football/bifa")
public class FootballBifaInfoManage extends BaseAction
{
	private final String CHARSET = "gb2312";
	@Autowired
	private FootballBifaImpl bifaImpl;
	@Autowired
	private FootballBifaDetailImpl bifaDetailImpl;
	@ResponseBody
	@RequestMapping("getFootBallGameInfo")
	public String getBifaInfo(@RequestParam Map<String, Object> params) {
		this.params = params;
		Date startDate = getDate("startDate");
		Date endDate = getDate("endDate");
		Long starts = startDate.getTime();
		Long ends = endDate.getTime();
		try {
			for(int i=0;starts<=ends;i++) {
				Date date = new Date(starts);
				String url = getProperty("okooozuqiubifa")+DateUtil.getDateStr(date);
				List<FootballBifa> bifaList = getBifaInfo(Parser.createParser(UrlUtil.getContent(url, CHARSET), CHARSET), date);
				Map<String, Object> pps = new HashMap<String, Object>();
				starts+=24l*60*60*1000;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "异常";
		}
		return "成功";
	}
	
	private List<FootballBifa> getBifaInfo(Parser parser,Date date) {
		List<FootballBifa> bifaList = new ArrayList<FootballBifa>();
		try
		{
			NodeFilter filter = new CssSelectorNodeFilter(
					"div[class='clearfix container_wrapper betfa']");
			NodeList nodeList = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<nodeList.size();i++) {
				TagNode n1 = (TagNode) nodeList.elementAt(i);
				parser = Parser.createParser(n1.toHtml(), CHARSET);
				
				NodeFilter filter2 = new CssSelectorNodeFilter(
						"p[class='float_l']");
				NodeList nodeList2 = parser.extractAllNodesThatMatch(filter2);
				TagNode n2 = (TagNode) nodeList2.elementAt(0);
				String[] types = ParserUtils.toPlainText("b", n2.toHtml());
				String code = DateUtil.getDateStr(date)+types[0].substring(2, 5);
				String type = types[1];
				String time = types[2];
				NodeFilter filter3 = new CssSelectorNodeFilter(
						"p[class='pai_p1']");
				NodeList nodeList3 = parser.extractAllNodesThatMatch(filter3);
				TagNode n30 = (TagNode) nodeList3.elementAt(0);
				String homeNo = n30.toPlainTextString().substring(1,n30.toPlainTextString().length());
				TagNode n31 = (TagNode) nodeList3.elementAt(1);
				String guestNo = n31.toPlainTextString().substring(1,n31.toPlainTextString().length());
				NodeFilter filter4 = new CssSelectorNodeFilter(
						"p[class='pai_p2']");
				NodeList nodeList4 = parser.extractAllNodesThatMatch(filter4);
				TagNode n40 = (TagNode) nodeList4.elementAt(0);
				String homeBelong = n40.toPlainTextString().substring(1, n40.toPlainTextString().length());
				TagNode n41 = (TagNode) nodeList4.elementAt(1);
				String guestBelong = n41.toPlainTextString().substring(1, n41.toPlainTextString().length());
				NodeFilter filter5 = new CssSelectorNodeFilter(
						"div[class='titnamebox titname_box']");
				NodeList nodeList5 = parser.extractAllNodesThatMatch(filter5);
				TagNode n5 = (TagNode) nodeList5.elementAt(0);
				String [] spans = ParserUtils.toPlainText("span", n5.toHtml());
				String homeTeam = spans[0];
				String [] ems = ParserUtils.toPlainText("em", n5.toHtml());
				String lettheball = ems[0].substring(1, 2);
				String [] bs = ParserUtils.toPlainText("b", n5.toHtml());
				String guestTeam = bs[0];
				NodeFilter filter6 = new CssSelectorNodeFilter(
						"p[class='float_r']");
				NodeList nodeList6 = parser.extractAllNodesThatMatch(filter6);
				TagNode n6 = (TagNode) nodeList6.elementAt(0);
				parser = Parser.createParser(n6.toHtml(), CHARSET);
				NodeFilter filter7 = new CssSelectorNodeFilter(
						"a");
				NodeList nodeList7 = parser.extractAllNodesThatMatch(filter7);
				TagNode n70 = (TagNode) nodeList7.elementAt(0);
				String europe = n70.getAttribute("href");
				TagNode n71 = (TagNode) nodeList7.elementAt(1);
				String asia = n71.getAttribute("href");
				TagNode n72 = (TagNode) nodeList7.elementAt(2);
				String analysis = n72.getAttribute("href");
				parser = Parser.createParser(n1.toHtml(), CHARSET);
				NodeFilter filter8 = new CssSelectorNodeFilter(
						"table");
				NodeList nodeList8 = parser.extractAllNodesThatMatch(filter8);
				TagNode n8 = (TagNode) nodeList8.elementAt(0);
				parser = Parser.createParser(n8.toHtml(), CHARSET);
				NodeFilter filter9 = new CssSelectorNodeFilter(
						"tr");
				NodeList nodeList9 = parser.extractAllNodesThatMatch(filter9);
				for(int j=1;j<4;j++) {
					TagNode n9 = (TagNode) nodeList9.elementAt(j);
					parser = Parser.createParser(n9.toHtml(), CHARSET);
					NodeFilter filter10 = new CssSelectorNodeFilter(
							"td");
					NodeList nodeList10 = parser.extractAllNodesThatMatch(filter10);
					TagNode n100 = (TagNode) nodeList10.elementAt(0);
					TagNode n101 = (TagNode) nodeList10.elementAt(1);
					TagNode n102 = (TagNode) nodeList10.elementAt(2);
					TagNode n103 = (TagNode) nodeList10.elementAt(3);
					TagNode n104 = (TagNode) nodeList10.elementAt(4);
					TagNode n105 = (TagNode) nodeList10.elementAt(5);
					TagNode n106 = (TagNode) nodeList10.elementAt(6);
					TagNode n107 = (TagNode) nodeList10.elementAt(7);
					TagNode n108 = (TagNode) nodeList10.elementAt(8);
					TagNode n109 = (TagNode) nodeList10.elementAt(9);
					TagNode n110 = (TagNode) nodeList10.elementAt(10);
					TagNode n111 = (TagNode) nodeList10.elementAt(11);
					TagNode n112 = (TagNode) nodeList10.elementAt(12);
					TagNode n113 = (TagNode) nodeList10.elementAt(13);
					if(StringUtils.isBlank(n101.toPlainTextString())) {
						break;
					}
					String s310 = "";
					if("主胜".equals(n100.toPlainTextString())) {
						s310 = "3";
					} else if("平局".equals(n100.toPlainTextString())){
						s310 = "1";
					} else if("主负".equals(n100.toPlainTextString())) {
						s310 = "0";
					}
					String buys = n101.toPlainTextString();
					String buyrate = n102.toPlainTextString();
					String sales = n103.toPlainTextString();
					String salerate = n104.toPlainTextString();
					String total = n105.toPlainTextString();
					String hot = ParserUtils.toPlainText("span", n106.toHtml())[0];
					String market = n107.toPlainTextString();
					String bifa = n108.toPlainTextString();
					String bifaPercent = n109.toPlainTextString().substring(0, n109.toPlainTextString().length());
					String averageRate = n110.toPlainTextString();
					String averagePercent = n111.toPlainTextString().substring(0, n111.toPlainTextString().length());
					String jincaiPercent =  n112.toPlainTextString().substring(0, n112.toPlainTextString().length());
					String simulate = ParserUtils.toPlainText("span", n113.toHtml())[0];
				}
				
						
						
						
				
			}
			
		}
		catch (ParserException e)
		{
			e.printStackTrace();
		}
		return bifaList;
	}
	
	public static void main(String[] args)
	{
		String CHARSET = "gb2312";
		String url = "http://www.okooo.com/jingcai/shuju/betfa/";
		Date date = new Date();
		FootballBifaInfoManage manage = new FootballBifaInfoManage();
		List<FootballBifa> bifaList = manage.getBifaInfo(Parser.createParser(UrlUtil.getContent(url, CHARSET), CHARSET), date);
		for(int i=0;i<bifaList.size();i++) {
			
		}
	}
}
