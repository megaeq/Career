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
import com.eq.dao.entity.lottory.FootballBifaDetail;
import com.eq.dao.impl.lottory.FootballBifaDetailImpl;
import com.eq.dao.impl.lottory.FootballBifaImpl;
import com.eq.util.BaseAction;
import com.eq.util.DateUtil;
import com.eq.util.MathUtils;
import com.eq.util.ParamUtils;
import com.eq.util.ParserUtils;
import com.eq.util.UrlUtil;
@Component
@RequestMapping("footballBifaInfo")
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
		ParamUtils PU = new ParamUtils(params);
		Date startDate = PU.getDate("startDate");
		Date endDate = PU.getDate("endDate");
		Long starts = startDate.getTime();
		Long ends = endDate.getTime();
		try {
			for(int i=0;starts<=ends;i++) {
				Date date = new Date(starts);
				String url = getProperty("okooozuqiubifa")+DateUtil.getDateStr(date);
				for(int j=1;;j++) {
					String pageUrl = url+"?PageID="+j;
					List<FootballBifa> bifaList = getBifaInfo(UrlUtil.getContent(pageUrl, CHARSET), date);
					for(FootballBifa bifa:bifaList) {
						Integer bifaId = bifaImpl.add(bifa);
						for(FootballBifaDetail detail:bifa.getDetailList()) {
							detail.setBifaId(bifaId);
							bifaDetailImpl.add(detail);
						}
					}
					if(bifaList.size()==0) {
						break;
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
	
	private List<FootballBifa> getBifaInfo(String html,Date date) {
		List<FootballBifa> bifaList = new ArrayList<FootballBifa>();
			NodeList nodeList = ParserUtils.getNodeList(html, "div[class='clearfix container_wrapper betfa']");
			for(int i=0;i<nodeList.size();i++) {
				TagNode n1 = (TagNode) nodeList.elementAt(i);
				//时间，编号等
				NodeList nodeList2 = ParserUtils.getNodeList(n1.toHtml(),"p[class='float_l'");
				TagNode n2 = (TagNode) nodeList2.elementAt(0);
				String[] types = ParserUtils.toPlainText("b", n2.toHtml());
				String code = DateUtil.getDateStr(date)+types[0].substring(2, 5);
				String type = types[1];
				String time = types[2];
				//排名，所属(主)
				NodeList nodeList3 = ParserUtils.getNodeList(n1.toHtml(),"div[class='pai']");
				TagNode n3 = (TagNode) nodeList3.elementAt(0);
				NodeList nodeList4 = ParserUtils.getNodeList(n3.toHtml(),"p");
				String homeNo = "";
				if(StringUtils.isNotBlank(nodeList4.elementAt(0).toPlainTextString())) {
					homeNo = nodeList4.elementAt(0).toPlainTextString().substring(1,nodeList4.elementAt(0).toPlainTextString().length()-1);
				}
				String homeBelong = "";
				if(StringUtils.isNotBlank(nodeList4.elementAt(1).toPlainTextString())) {
					homeBelong = nodeList4.elementAt(1).toPlainTextString().substring(1,nodeList4.elementAt(1).toPlainTextString().length()-1);
				}
				//排名，所属（客）
				NodeList nodeList5 = ParserUtils.getNodeList(n1.toHtml(),"div[class='pai']");
				TagNode n5 = (TagNode) nodeList5.elementAt(0);
				NodeList nodeList6 = ParserUtils.getNodeList(n5.toHtml(),"p");
				String guestNo = "";
				if(StringUtils.isNotBlank(nodeList6.elementAt(0).toPlainTextString())) {
					guestNo = nodeList6.elementAt(0).toPlainTextString().substring(1,nodeList6.elementAt(0).toPlainTextString().length()-1);
				}
				String guestBelong = "";
				if(StringUtils.isNotBlank(nodeList6.elementAt(1).toPlainTextString())) {
					guestBelong = nodeList6.elementAt(1).toPlainTextString().substring(1,nodeList6.elementAt(1).toPlainTextString().length()-1);
				}
				//主客
				NodeList nodeList7 = ParserUtils.getNodeList(n1.toHtml(),"div[class='titnamebox titname_box']");
				TagNode n7 = (TagNode) nodeList7.elementAt(0);
				String [] spans = ParserUtils.toPlainText("span", n7.toHtml());
				String homeTeam = spans[0];
				String [] ems = ParserUtils.toPlainText("em", n7.toHtml());
				String lettheball = "";
				if(ems.length>=0&&StringUtils.isNotBlank(ems[0])) {
					lettheball = ems[0].substring(1, ems[0].length()-1);
				}
				String [] bs = ParserUtils.toPlainText("b", n7.toHtml());
				String guestTeam = bs[0];
				//链接
				NodeList nodeList8 = ParserUtils.getNodeList(n1.toHtml(),"p[class='float_r']");
				TagNode n8 = (TagNode) nodeList8.elementAt(0);
				NodeList nodeList9 = ParserUtils.getNodeList(n8.toHtml(),"a");
				TagNode n90 = (TagNode) nodeList9.elementAt(0);
				String europe = n90.getAttribute("href");
				TagNode n91 = (TagNode) nodeList9.elementAt(1);
				String asia = n91.getAttribute("href");
				TagNode n92 = (TagNode) nodeList9.elementAt(2);
				String analysis = n92.getAttribute("href");
				//数据
				NodeList nodeList10 = ParserUtils.getNodeList(n1.toHtml(),"table");
				TagNode n10 = (TagNode) nodeList10.elementAt(0);
				NodeList nodeList11 = ParserUtils.getNodeList(n10.toHtml(),"tr");
				FootballBifa bifaEntity = new FootballBifa();
				bifaEntity.setCode(code);
				bifaEntity.setType(type);
				//bifaEntity.setTime(DateUtil.getTimestamp(date.getYear()+time+":00"));
				if(StringUtils.isNotBlank(homeNo)) {
					bifaEntity.setHomeNo(Integer.parseInt(homeNo));
				}
				if(StringUtils.isNotBlank(guestNo)) {
					bifaEntity.setGuestNo(Integer.parseInt(guestNo));
				}
				bifaEntity.setHomeBelong(homeBelong);
				bifaEntity.setGuestBelong(guestBelong);
				bifaEntity.setEurope(europe);
				bifaEntity.setAsia(asia);
				bifaEntity.setAnalyze(analysis);
				bifaEntity.setHomeTeam(homeTeam);
				bifaEntity.setGuestTeam(guestTeam);
				List<FootballBifaDetail> bifaDetailList = new ArrayList<FootballBifaDetail>();
				for(int j=1;j<4;j++) {
					TagNode n11 = (TagNode) nodeList11.elementAt(j);
					NodeList nodeList12 = ParserUtils.getNodeList(n11.toHtml(),"td");
					TagNode n12_0 = (TagNode) nodeList12.elementAt(0);
					TagNode n12_1 = (TagNode) nodeList12.elementAt(1);
					TagNode n12_2 = (TagNode) nodeList12.elementAt(2);
					TagNode n12_3 = (TagNode) nodeList12.elementAt(3);
					TagNode n12_4 = (TagNode) nodeList12.elementAt(4);
					TagNode n12_5 = (TagNode) nodeList12.elementAt(5);
					TagNode n12_6 = (TagNode) nodeList12.elementAt(6);
					TagNode n12_7 = (TagNode) nodeList12.elementAt(7);
					TagNode n12_8 = (TagNode) nodeList12.elementAt(8);
					TagNode n12_9 = (TagNode) nodeList12.elementAt(9);
					TagNode n12_10 = (TagNode) nodeList12.elementAt(10);
					TagNode n12_11 = (TagNode) nodeList12.elementAt(11);
					TagNode n12_12 = (TagNode) nodeList12.elementAt(12);
					TagNode n12_13 = (TagNode) nodeList12.elementAt(13);
					if(StringUtils.isBlank(n12_1.toPlainTextString())) {
						break;
					}
					String s310 = "";
					if("主胜".equals(n12_0.toPlainTextString())) {
						s310 = "3";
					} else if("平局".equals(n12_0.toPlainTextString())){
						s310 = "1";
					} else if("主负".equals(n12_0.toPlainTextString())) {
						s310 = "0";
					}
					String buys = n12_1.toPlainTextString();
					String buyrate = n12_2.toPlainTextString();
					String sales = n12_3.toPlainTextString();
					String salerate = n12_4.toPlainTextString();
					String total = n12_5.toPlainTextString();
					String hot = ParserUtils.toPlainText("span", n12_6.toHtml())[0];
					String market = n12_7.toPlainTextString();
					String bifa = n12_8.toPlainTextString();
					String bifaPercent = n12_9.toPlainTextString().substring(0, n12_9.toPlainTextString().length());
					String averageRate = n12_10.toPlainTextString();
					String averagePercent = n12_11.toPlainTextString().substring(0, n12_11.toPlainTextString().length());
					String jincaiPercent =  n12_12.toPlainTextString().substring(0, n12_12.toPlainTextString().length());
					String simulate = ParserUtils.toPlainText("span", n12_13.toHtml())[0];
					//
					
					if(StringUtils.isNotBlank(lettheball)) {
						bifaEntity.setLetTheBall(Integer.parseInt(lettheball));
					}
					FootballBifaDetail bifaDetail = new FootballBifaDetail();
					bifaDetail.setBuys(MathUtils.getFloat(buys));
					bifaDetail.setBuyRate(MathUtils.getFloat(buyrate));
					bifaDetail.setSales(MathUtils.getFloat(sales));
					bifaDetail.setSaleRate(MathUtils.getFloat(salerate));
					bifaDetail.setTotal(MathUtils.getFloat(total));
					bifaDetail.setHot(MathUtils.getFloat(hot));
					bifaDetail.setMarket(MathUtils.getFloat(market));
					bifaDetail.setBifa(MathUtils.getFloat(bifa));
					bifaDetail.setBifaPercent(MathUtils.getFloat(bifaPercent));
					bifaDetail.setAverageRate(MathUtils.getFloat(averageRate));
					bifaDetail.setAveragePercent(MathUtils.getFloat(averagePercent));
					bifaDetail.setJincaiPercent(MathUtils.getFloat(jincaiPercent));
					bifaDetail.setSimulate(MathUtils.getFloat(simulate));
					bifaDetailList.add(bifaDetail);
				}
				bifaEntity.setDetailList(bifaDetailList);
				bifaList.add(bifaEntity);
			}
			
		return bifaList;
	}
	
	public static void main(String[] args)
	{
		String CHARSET = "gb2312";
		String url = "http://www.okooo.com/jingcai/shuju/betfa/";
		Date date = new Date();
		FootballBifaInfoManage manage = new FootballBifaInfoManage();
		List<FootballBifa> bifaList = manage.getBifaInfo(UrlUtil.getContent(url, CHARSET), date);
		for(int i=0;i<bifaList.size();i++) {
			
		}
	}
}
