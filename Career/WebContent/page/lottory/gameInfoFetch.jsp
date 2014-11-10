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
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
 <script type="text/javascript">
 require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button"]);
 function getGame() {
 	require([
 	         "dojo/request",
 	         "dojo/dom",
 	        "dijit/Dialog"
 	     ], function (request,dom,Dialog) {
 		$.blockUI();
 		request("getFootBallGameInfo",{query:{startDate:dom.byId("startDate").value,
			 endDate:dom.byId("endDate").value}
			 }).then(function(response) {
					 $.unblockUI();
				 	myDialog = new Dialog({
				        title: "结果",
				        content: response,
				        style: "width: 300px"
				    });
				 	myDialog.show();
		 		});
 	         });
 }
 
 function getBasketBallGame() {
	 	require([
	 	         "dojo/request",
	 	         "dojo/dom",
	 	        "dijit/Dialog"
	 	     ], function (request,dom,Dialog) {
	 		$.blockUI();
	 		request("getBasketBallBallGameInfo",{query:{startDate:dom.byId("startDate").value,
				 endDate:dom.byId("endDate").value}
				 }).then(function(response) {
						 $.unblockUI();
					 	myDialog = new Dialog({
					        title: "结果",
					        content: response,
					        style: "width: 300px"
					    });
					 	myDialog.show();
			 		});
	 	         });
	 }
 </script>
</head>
<body class="claro">
<div id="dateTextBox">
	<label for="date1">起始时间：</label>
	<input type="text" id="startDate" 
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
    <label for="date1">结束时间：</label>
	<input type="text" id="endDate" 
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
    <button data-dojo-type="dijit/form/Button" onclick="getGame()">足球</button>
    <button data-dojo-type="dijit/form/Button" onclick="getBasketBallGame()">篮球</button>
</div>

</body>
</html>