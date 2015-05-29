<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath%>css/my.css">
<LINK href="<%=basePath%>favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/table.css">
<link rel="stylesheet" href="<%=basePath%>js/dojojs/dijit/themes/claro/claro.css">
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 .field-code {
 	
 }
 .resultTable {
 	width:70%;
 }
 .gameListTable {
 	width:90%;
 }
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
<script type="text/javascript">
 require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button"]);
 function getGrid() {
	 require([
		         "dojo/_base/declare",
		         "dojo/request",
		         "dojo/dom",
		         "dojo/store/Memory",
		         "dgrid/OnDemandGrid",
		         "dgrid/extensions/Pagination",
		         "dijit/form/Button",
		         'dojo/dom-style' 
		     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,Button,domStyle) {
		         request("dataAnalysis/getResultList", {
		             handleAs: "json",query:{id:$.getUrlParam('id')}
		         }).then(function (response) {
		        	 var resultList = response;
		        	 var resultHtml = "";
		        	 for(var i=0;i<resultList.length;i++) {
		        		 var result = resultList[i];
		        		 resultHtml+="<div onclick=\"slide(this,'gameList"+result.id+"')\" slide=\"up\"><table class=\"bordered\" style=\"width:70%;\"><tr>";
		        		 resultHtml+="<td width=\"50%\">"+result.name+"</td>";
		        		 resultHtml+="<td width=\"25%\">"+result.averageScore+"</td>";
		        		 resultHtml+="<td width=\"25%\">"+result.gameList.length+"</td>";
		        		 resultHtml+="</tr></table></div>";
		        		 var gameList = result.gameList;
		        		 resultHtml+="<div id=\"gameList"+result.id+"\" class=\"gameList\" style=\"display:none;\"><table class=\"zebra\" style=\"width:90%;\">";
		        		 resultHtml+="<tr><thread><th>所属</th><th>主队</th><th>客队</th><th>胜赔</th><th>平赔</th><th>负配</th><th>主</th><th>客</th><th>时间</th></thread></tr>";
		        		 for(var j=0;j<gameList.length;j++) {
		        			 resultHtml+="<tr>";
		        			 var game = gameList[j];
		        			 resultHtml+="<td>"+game.gameType+"</td>";
		        			 resultHtml+="<td>"+game.homeTeam+"</td>";
		        			 resultHtml+="<td>"+game.guestTeam+"</td>";
		        			 resultHtml+="<td>"+game.winRate+"</td>";
		        			 resultHtml+="<td>"+game.drawRate+"</td>";
		        			 resultHtml+="<td>"+game.loseRate+"</td>";
		        			 resultHtml+="<td>"+game.homeScore+"</td>";
		        			 resultHtml+="<td>"+game.guestScore+"</td>";
		        			 resultHtml+="<td>"+game.time2+"</td>";
		        			 resultHtml+="</tr>";
		        		 }
		        		 resultHtml+="</table></div>";
		        	 }
		        	 jQuery("#resultList").append(resultHtml);
		             
		         });   
		     });
		    
 }
 
 function slide(obj,divId) {
	 if(obj.getAttribute("slide")=="up") {
		 jQuery("#"+divId).slideDown();
		 obj.setAttribute("slide","down");
		 var gameLists = jQuery(".gameList");
		 for(var i=0;i<gameLists.length;i++) {
			 if(gameLists[i].getAttribute('id')!=divId) {
				 jQuery("#"+gameLists[i].getAttribute('id')).slideUp();
			 }
		 }
	 } else {
		 jQuery("#"+divId).slideUp();
		 obj.setAttribute("slide","up");
	 }
 }

 </script>
</head>
<body onload="getGrid()">
<jsp:include page="/header.jsp"></jsp:include>
<div id="resultList"></div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>