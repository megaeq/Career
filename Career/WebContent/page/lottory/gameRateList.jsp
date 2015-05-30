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
<link rel="stylesheet" href="<%=basePath%>js/dojojs/dijit/themes/claro/claro.css">
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 .field-code {
 	
 }
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
 <script type="text/javascript">
require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button"]);
 function getBaseInfo() {
	 var game = $.ajaxResp('get',"<%=basePath%>game/getGameInfo?id="+$.getUrlParam('id'),false);
	 console.log(game.id);
 }
 function getGrid() {
 	require([
 	         "dojo/_base/declare",
 	         "dojo/request",
 	         "dojo/dom",
 	         "dojo/store/Memory",
 	         "dgrid/OnDemandGrid",
 	         "dgrid/extensions/Pagination",
 	         "dojo/store/JsonRest",
 	       "dijit/form/Button"
 	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,JsonRest,Button) {
 					var grid = new (declare([OnDemandGrid, Pagination]))({
 	            	store: new JsonRest({
	         	   	    target: "<%=basePath%>game/getList?R1="+$.getUrlParam('Rw')+"&R2="+$.getUrlParam('Rd')+"&R3="+$.getUrlParam('Rl')}),
 	                className: "dgrid-autoheight",
 	                 columns: {gameType:{label:"联赛类型"},
 	                	homeTeam:{label:"主队"},guestTeam:{label:"客队"},winRate:{label:"主赔"},
 	                	drawRate:{label:"平赔"},loseRate:{label:"客赔"},
 	                	homeScore:{label:"主"},guestScore:{label:"客"},time2:{label:"时间"}
 	                	},
 	                 rowsPerPage:14,
 	                 pagingTextBox:true,
 	                 pagingLinks:8
 	             	
 	             }, "list");
 	             grid.startup();
 	         });
 }
 </script>
</head>
<body onload="getBaseInfo();getGrid();">
<jsp:include page="/header.jsp"></jsp:include>
<div class="baseInfo"></div>
<div id="list"></div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>