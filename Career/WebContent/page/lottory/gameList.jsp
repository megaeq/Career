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
 function getGrid() {
 	require([
 	         "dojo/_base/declare",
 	         "dojo/request",
 	         "dojo/dom",
 	         "dojo/store/Memory",
 	         "dgrid/OnDemandGrid",
 	         "dgrid/extensions/Pagination"
 	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination) {
 	         request("game/getList", {
 	             handleAs: "json",query:{startDate:dom.byId("startDate").value,
 	            	 endDate:dom.byId("endDate").value}
 	         }).then(function (response) {
 	             var store = new Memory({ data: response });
 	              
 	             var grid = new (declare([OnDemandGrid, Pagination]))({
 	                 store: store,
 	                className: "dgrid-autoheight",
 	                 columns: {id:{label:"id"},code:{lable:"编号"},gameType:{label:"联赛类型"},
 	                	homeTeam:{label:"主队"},guestTeam:{label:"客队"},winRate:{label:"主胜"},
 	                	drawRate:{label:"平局"},loseRate:{label:"主负"},homeScore:{label:"比分（主）"},
 	                	guestScore:{label:"比分（客）"},time2:{label:"时间"},
 	                	edit:{label:"操作",renderCell:function(object,data,cell) {
 	                		var btn1 = new Button({
 			                     rowId : object.id,
 			                     label: "往绩",
 			                     onClick: function () {
 			                    	location.href="<%=basePath%>page/lottory/gameHistoryList.jsp?gameId="+object.id;
 			                     }
 			                 }, cell.appendChild(document.createElement("div")));
 	                	}}},
 	                 rowsPerPage:14,
 	                 pagingTextBox:true,
 	                 pagingLinks:8
 	             	
 	             }, "list");
 	             grid.startup();
 	         });
 	     });
 }

      function change() {
     	 document.getElementById("list").innerHTML="";
     	 getGrid();
      }
      function addinfo() {
     	 require(["dojo/request","dojo/dom",],function(request,dom) {
     		 request("add",{query:{income:dom.byId("income").value,
     			 cost:dom.byId("cost").value,addDate:dom.byId("addDate").value,
     			 usages:dom.byId("usages").value,memo:dom.byId("memo").value}
     			 }).then(function() {
    				 document.getElementById("list").innerHTML="";
    		    	 getGrid();
     			 $.unblockUI();
     		 });
     	 });
     	 
      }
      function divclose() {
     	 $.unblockUI();
      }
      function openAddDiv() {
     	 $.blockUI({ message: $('#add') });
      }
 </script>
</head>
<body class="claro" onload="getGrid()">
<div id="dateTextBox">
	<label for="date1">起始时间：</label>
	<input type="text" id="startDate" 
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
    <label for="date1">结束时间：</label>
	<input type="text" id="endDate" 
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
    <button data-dojo-type="dijit/form/Button" onclick="change()">查询</button>
    <button data-dojo-type="dijit/form/Button" onclick="openAddDiv()">新增</button>
</div>
<div id="list"></div>
<div id="add" style="text-align: center; width: 200px; height: 200px; border;
    1px solid #9cf; padding: 25px; display: none;">
    <table>
    	<tr>
    		<td><button data-dojo-type="dijit/form/Button" onclick="addinfo()">添加</button></td>
    		<td><button data-dojo-type="dijit/form/Button" onclick="divclose()">取消</button></td>
    	</tr>
    </table>
</div>
</body>
</html>