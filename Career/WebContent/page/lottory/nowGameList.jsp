<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>足球现场</title>
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
 	         "dojo/store/JsonRest",
 	       "dijit/form/Button"
 	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,JsonRest,Button) {
 	             var grid = new (declare([OnDemandGrid, Pagination]))({
 	            	store: new JsonRest({
	         	   	    target: "game/getList?isNow=now"}),
 	                className: "dgrid-autoheight",
 	                 columns: {code:{label:"编号"},gameType:{label:"联赛类型"},
 	                	homeTeam:{label:"主队"},guestTeam:{label:"客队"},winRate:{label:"主赔"},
 	                	drawRate:{label:"平赔"},loseRate:{label:"客赔"},time2:{label:"时间"},
 	                	edit:{label:"操作",renderCell:function(object,data,cell) {
 	                		var btn1 = new Button({
 			                     rowId : object.id,
 			                     label: "数据分析",
 			                     onClick: function () {
 			                    	location.href="<%=basePath%>page/lottory/footballDataAnanlysisResultList.jsp?id="+object.id;
 			                     }
 			                 }, cell.appendChild(document.createElement("div")));
 	                	}}
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
<body class="claro" onload="getGrid()">
<jsp:include page="/header.jsp"></jsp:include>
<div id="list"></div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>