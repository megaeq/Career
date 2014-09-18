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
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
 <script type="text/javascript">
 require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button"]);
 var array = new Array;
 function getGrid() {
 	require([
 	         "dojo/_base/declare",
 	         "dojo/request",
 	        "dojo/dom",
 	         "dojo/store/Memory",
 	         "dgrid/OnDemandGrid",
 	         "dgrid/extensions/Pagination",
 	        "dijit/form/Button"
 	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,Button) {
 	         request("game/getList", {
 	             handleAs: "json",query:{startDate:dom.byId("startDate").value,
 	            	 endDate:dom.byId("endDate").value}
 	         }).then(function (response) {
 	             var store = new Memory({ data: response });
 	             var chipin = function(object, data, cell) {
 	            	var on3 = new Button({
	                     rowId : object.id,
	                     label: "3",
	                     onClick: function () {
	                    	// console.log(array.length);
	                    	 if(array.length<4) {
	                    		 dom.byId("tbody").innerHTML+="<tr><td>"+object.code+"</td>"+
		                    	 "<td style='color:#e81010;'>"+object.homeTeam+"</td><td>平</td>"+
		                    	 "<td>"+object.guestTeam+"</td>";
		                    	 array.push(object.id+",3");
	                    	 }
	                     }
	                 }, cell.appendChild(document.createElement("div")));
 	            	var on1 = new Button({
	                     rowId : object.id,
	                     label: "1",
	                     onClick: function () {
	                    	// console.log(array.length);
	                    	 if(array.length<4) {
	                    		 dom.byId("tbody").innerHTML+="<tr><td>"+object.code+"</td>"+
		                    	 "<td>"+object.homeTeam+"</td><td style='color:#e81010;'>平</td>"+
		                    	 "<td>"+object.guestTeam+"</td>";
	                    		 array.push(object.id+",1");
	                    	 }
	                     }
	                 }, cell.appendChild(document.createElement("div")));
 	            	var on0 = new Button({
	                     rowId : object.id,
	                     label: "0",
	                     onClick: function () {
	                    	// console.log(array.length);
	                    	 if(array.length<4) {
	                    		 dom.byId("tbody").innerHTML+="<tr><td>"+object.code+"</td>"+
		                    	 "<td>"+object.homeTeam+"</td><td>平</td>"+
		                    	 "<td style='color:#e81010;'>"+object.guestTeam+"</td>";
	                    		 array.push(object.id+",0");
	                    	 }
	                    	
	                     }
	                 }, cell.appendChild(document.createElement("div")));
 	             }
 	             var grid = new (declare([OnDemandGrid, Pagination]))({
 	                 store: store,
 	                 columns: {id:{label:"id"},code:{lable:"编号"},gameType:{label:"联赛类型"},
 	                	homeTeam:{label:"主队"},guestTeam:{label:"客队"},winRate:{label:"主胜"},
 	                	drawRate:{label:"平局"},loseRate:{label:"主负"},time2:{label:"时间"},
 	                	weather:{label:"天气"},edit:{label:"操作",renderCell:chipin}},
 	                 rowsPerPage:14,
 	                 pagingTextBox:true,
 	                 pagingLinks:2
 	             	
 	             }, "list");
 	             grid.on(".dgrid-header .dgrid-cell:click", function(evt){
 	            	    var cell = grid.cell(evt);
 	            	    console.log(cell);
 	            	    // cell.element == the element with the dgrid-cell class
 	            	    // cell.column == the column definition object for the column the cell is within
 	            	    // cell.row == the same object obtained from grid.row(evt)
 	            	});
 	             grid.on(".dgrid-row:contextmenu", function(evt){
 	            	    var row = grid.row(evt);
 	            	    console.log(row);
 	            	    // row.element == the element with the dgrid-row class
 	            	    // row.id == the identity of the item represented by the row
 	            	    // row.data == the item represented by the row
 	            	});
 	             grid.startup();
 	         });
 	     });
 }

      function change() {
     	 document.getElementById("list").innerHTML="";
     	 getGrid();
      }
      function addinfo() {
     	 require(["dojo/request","dojo/dom"],function(request,dom) {
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
      function chipin() {
    	  $.blockUI();
    	 
    	  //console.log($("#multi").val()+" "+$("#money").val());
    	  var chip = "";
    	  for(var i=0;i<array.length;i++) {
    		chip+=array[i]+";";  
    	  }
    	  require(["dojo/request","dojo/dom"],function(request,dom) {
    		  request("chip/chipin",{query:{money:dom.byId("money").value,
    			  chip:chip}}).then(function() {
    				  $.unblockUI();
    			  });
    	  });
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
<br>
<div id="chipList">
<table id="chiptable" class="pure-table pure-table-bordered">
<thead>
<tr>
	<th width="100px">编号</th><th width="100px">主队</th>
	<th width="100px">平</th><th width="100px">客队</th>
</tr>
</thead>
<tbody id="tbody"></tbody>
</table>
<input type="text" id="money" placeHolder="投注额">
<button data-dojo-type="dijit/form/Button" onclick="chipin()">投注</button>
</div>
</body>
</html>