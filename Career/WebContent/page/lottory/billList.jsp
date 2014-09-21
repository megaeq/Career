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
	         request("bill/getList", {
	             handleAs: "json",query:{accountId:$.getUrlParam('accountId')}
	         }).then(function (response) {
	             var store = new Memory({ data: response });
	              
	             var actionRenderCell = function (object, data,cell) {
	            	 if(0==object.flag) {
	            		 var btn1 = new Button({
		                     rowId : object.id,
		                     label: "结算",
		                     onClick: function () {
		                    	 $.blockUI();
		                         request("bill/clearing",{query:{billId:object.id}}).then(function() {
		                        	 document.getElementById("list").innerHTML="";
		               		    	 getGrid();
		                			 $.unblockUI();
		                         })
		                     }
		                 }, cell.appendChild(document.createElement("div")));
	            	 }
	             }
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 columns: {id:{label:"id"},betAmount:{label:"投注量（元）"},sp:{label:"sp"},
	                	 income:{label:"收入"},flag:{label:"是否完结",renderCell:function(object, data,cell) {
	                		 var div = document.createElement("div");
	                		 if(0==object.flag) {
	                			 div.innerHTML = "未完结";
	                		 } else if(1==object.flag){
	                			 div.innerHTML ="完结";
	                		 }
                		    return div;
	                	 }},cluster:{label:"串"},time2:{label:"时间"},
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
    		 request("account/add",{query:{name:dom.byId("name").value,balance:dom.byId("balance").value,
    			 pwd:dom.byId("pwd").value,belong:dom.byId("belong").value,
    			 isReal:dom.byId("isReal").value}
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
    	 $("#name").val("");
    	 $("#pwd").val("");
    	 $("#belong").val("");
    	 $("#balance").val("");
    	 $("#isReal").val("");
     }
     function updateInfo() {
       	 require(["dojo/request","dojo/dom",],function(request,dom) {
       		 request("account/update",{query:{id:dom.byId("id").value,name:dom.byId("name").value,
       			 pwd:dom.byId("pwd").value,belong:dom.byId("belong").value,
       			 isReal:dom.byId("isReal").value,balance:dom.byId("balance").value}
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
    		<td>名称<div style="width:100px;"></div></td>
    		<td><input id = "id" type="text" style="display:none" />
    		<input id = "name" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>密码</td>
    		<td><input id = "pwd" type="password" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>所属机构</td>
    		<td><input id = "belong" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>余额</td>
    		<td><div><input id="balance" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/></div></td>
    	</tr>
    	<tr>
    		<td>账户类型</td>
    		<td><input id = "isReal" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
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