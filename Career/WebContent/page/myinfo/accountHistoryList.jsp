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
  <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
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
	         "dijit/form/Button",
	         'dojo/dom-style' 
	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,Button,domStyle) {
	         request("accountHistory/getList", {
	             handleAs: "json",query:{accountId:$.getUrlParam('accountId')}
	         }).then(function (response) {
	             var store = new Memory({ data: response });
	              
	             var actionRenderCell = function (object, data,cell) {
	                 var btnDelete = new Button({
	                     rowId : object.id,
	                     label: "删除",
	                     onClick: function () {
	                    	 $.blockUI();
	                         request("accountHistory/delete",{query:{id:this.rowId}}).then(function() {
	                        	 document.getElementById("list").innerHTML="";
	               		    	 getGrid();
	                			 $.unblockUI();
	                         })
	                     }
	                 }, cell.appendChild(document.createElement("div")));
	                 var btnDelete2 = new Button({
	                     rowId : object.id,
	                     label: "编辑",
	                     onClick: function() {
	                    	 $("#id").val(object.id);
	                    	 $("#income").val(object.income);
	                    	 $("#cost").val(object.cost);
	                    	 $("#usages").val(object.usages);
	                    	 $("#memo").val(object.memo);
	                    	 $("#addButton").hide();
	                       	 $("#updateButton").show();
	                       	 $.blockUI({ message: $('#add') });
	                     },
	                 }, cell.appendChild(document.createElement("div")));
	             }
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 columns: {time:{label:"创建时间"},income:{label:"收入"},cost:{label:"支出"},
	                	 usages:{label:"用途"},memo:{label:"备注"},
	                	 edit:{label:"操作",renderCell: actionRenderCell}},
	                 rowsPerPage:14,
	                 pagingTextBox:true,
	                 pagingLinks:2
	             	
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
    		 request("accountHistory/add",{query:{income:dom.byId("income").value,
    			 cost:dom.byId("cost").value,usages:dom.byId("usages").value,
    			 accountId:$.getUrlParam('accountId'),memo:dom.byId("memo").value,
    			 date:dom.byId("date").value}
    			 }).then(function() {
   				 document.getElementById("list").innerHTML="";
   		    	 getGrid();
    			 $.unblockUI();
    			 clearclean();
    		 });
    	 });
    	 
     }
     function clearclean() {
    	 $("#id").val("");
    	 $("#income").val("");
    	 $("#cost").val("");
    	 $("#usages").val("");
    	 $("#memo").val("");
     }
     function updateInfo() {
       	 require(["dojo/request","dojo/dom",],function(request,dom) {
       		 request("accountHistory/update",{query:{id:dom.byId("id").value,
       			 income:dom.byId("income").value,memo:dom.byId("memo").value,
    			 cost:dom.byId("cost").value,usages:dom.byId("usages").value,
    			 date:dom.byId("date").value}
       			 }).then(function() {
      				 document.getElementById("list").innerHTML="";
      		    	 getGrid();
       			 $.unblockUI();
      		    	clearclean();
       		 });
       	 });
        }
     
     function divclose() {
    	 $.unblockUI();
    	 clearclean();
     }
     function openAddDiv() {
    	 $("#addButton").show();
    	 $("#updateButton").hide();
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
    <button data-dojo-type="dijit/form/Button">查询</button>
    <button data-dojo-type="dijit/form/Button" onclick="openAddDiv()">新增</button>
</div>
<div id="list"></div>
<div id="add" style="text-align: center; width: 200px; height: 150px; border;
    1px solid #9cf; padding: 25px; display: none;">
    <table>
    	<tr>
    		<td>日期<div style="width:100px;"></div></td>
    		<td>
    		<input id = "date" type="text" /> </td>
    	</tr>
    	<tr>
    		<td>收入<div style="width:100px;"></div></td>
    		<td><input id = "id" type="text" style="display:none" />
    		<input id = "income" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>支出</td>
    		<td><input id = "cost" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>用途</td>
    		<td><input id = "usages" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>备注</td>
    		<td><div><input id="memo" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/></div></td>
    	</tr>
    	<tr>
    		<td></td>
    		<td><button id="addButton" class="greyButton" onclick="addinfo()">添加</button>
    		<button id="updateButton" class="greyButton" onclick="updateInfo()">更新</button>
    		<button  class="greyButton" onclick="divclose()">取消</button></td>
    	</tr>
    </table>
</div>
</body>
</html>