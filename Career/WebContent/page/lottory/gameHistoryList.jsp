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
<link rel="stylesheet" href="<%=basePath%>js/dojojs/dijit/themes/claro/claro.css">
<link rel="stylesheet" href="<%=basePath%>css/my.css">
<link rel="stylesheet" href="<%=basePath%>css/pure.css">
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 .winstyle{
 	color:#f70d0d;
 	font-weight:900;
 	font-size:15px;
 }
 .drawstyle{
 	color:#f2c308;
 	font-weight:900;
 	font-size:15px;
 }
 .losestyle{
 	color:#457fd6;
 	font-weight:900;
 	font-size:15px;
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
	         request("<%=basePath%>game/getListByName", {
	             handleAs: "json",query:{hg:"home",gameId:$.getUrlParam('gameId')}
	         }).then(function (response) {
	             var store = new Memory({ data: response });
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 className: "dgrid-autoheight",
	                 columns: {gameType:{label:"比赛类型"},time2:{label:"时间"},
	                	 
	                	 teamInfo:{label:"主队-客队",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.homeTeam+" VS "+object.guestTeam;
	                		 return div; 
	                	 }},
	                	 score:{label:"全场比分",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.homeScore+" - "+object.guestScore;
	                		 return div;
	                	 }},
	                	 winrate:{label:"主",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.winRate;
	                		 div.style="width:20px;";
	                		 if(object.homeScore>object.guestScore) {
	                			 div.setAttribute("class","winstyle");
	                		 }
	                		 return div;
	                		 }},
	                	 drawrate:{label:"平",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=""+object.drawRate;
	                		 if(object.homeScore==object.guestScore) {
	                			 div.setAttribute("class","drawstyle");
	                		 }
	                		 return div;
	                		 }},
	                	 loserate:{label:"负",width:"10px",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.drawRate;
	                		 if(object.homeScore<object.guestScore) {
	                			 div.setAttribute("class","losestyle");
	                		 }
	                		 return div;
	                		 }}
	                	 },
	                 rowsPerPage:14,
	                 pagingTextBox:true,
	                 pagingLinks:2
	             	
	             }, "homelist");
	             grid.startup();
	         });
	         request("<%=basePath%>game/getListByName", {
	             handleAs: "json",query:{hg:"guest",gameId:$.getUrlParam('gameId')}
	         }).then(function (response) {
	             var store = new Memory({ data: response });
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 className: "dgrid-autoheight",
	                 columns: {gameType:{label:"比赛类型"},time2:{label:"时间"},
	                	 
	                	 teamInfo:{label:"主队-客队",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.homeTeam+" VS "+object.guestTeam;
	                		 return div; 
	                	 }},
	                	 score:{label:"全场比分",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.homeScore+" - "+object.guestScore;
	                		 return div;
	                	 }},
	                	 winrate:{label:"主",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.winRate;
	                		 div.style="width:20px;";
	                		 if(object.homeScore>object.guestScore) {
	                			 div.setAttribute("class","winstyle");
	                		 }
	                		 return div;
	                		 }},
	                	 drawrate:{label:"平",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=""+object.drawRate;
	                		 if(object.homeScore==object.guestScore) {
	                			 div.setAttribute("class","drawstyle");
	                		 }
	                		 return div;
	                		 }},
	                	 loserate:{label:"负",width:"10px",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 console.log(div);
	                		 div.innerHTML=object.drawRate;
	                		 if(object.homeScore<object.guestScore) {
	                			 div.setAttribute("class","losestyle");
	                		 }
	                		 return div;
	                		 }}
	                	 },
	                 rowsPerPage:14,
	                 pagingTextBox:true,
	                 pagingLinks:2
	             	
	             }, "guestlist");
	             grid.startup();
	         });
	         request("<%=basePath%>game/getABList", {
	             handleAs: "json",query:{gameId:$.getUrlParam('gameId')}
	         }).then(function (response) {
	             var store = new Memory({ data: response });
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 className: "dgrid-autoheight",
	                 columns: {gameType:{label:"比赛类型"},time2:{label:"时间"},
	                	 
	                	 teamInfo:{label:"主队-客队",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.homeTeam+" VS "+object.guestTeam;
	                		 return div; 
	                	 }},
	                	 score:{label:"全场比分",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.homeScore+" - "+object.guestScore;
	                		 return div;
	                	 }},
	                	 winrate:{label:"主",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=object.winRate;
	                		 div.style="width:20px;";
	                		 if(object.homeScore>object.guestScore) {
	                			 div.setAttribute("class","winstyle");
	                		 }
	                		 return div;
	                		 }},
	                	 drawrate:{label:"平",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 div.innerHTML=""+object.drawRate;
	                		 if(object.homeScore==object.guestScore) {
	                			 div.setAttribute("class","drawstyle");
	                		 }
	                		 return div;
	                		 }},
	                	 loserate:{label:"负",width:"10px",renderCell:function(object,data,cell){
	                		 var div = document.createElement("div");
	                		 console.log(div);
	                		 div.innerHTML=object.drawRate;
	                		 if(object.homeScore<object.guestScore) {
	                			 div.setAttribute("class","losestyle");
	                		 }
	                		 return div;
	                		 }}
	                	 },
	                 rowsPerPage:14,
	                 pagingTextBox:true,
	                 pagingLinks:2
	             	
	             }, "ablist");
	             grid.startup();
	         });
	     });
}
</script>
</head>
<body class="claro" onload="getGrid()">
<div> 主队战绩</div>
<div id="homelist"></div>
<div>客队战绩</div>
<div id="guestlist"></div>
<div>双方往绩</div>
<div id="ablist"></div>
</body>
</html>