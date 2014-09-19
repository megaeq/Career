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
	         request("account/getList", {
	             handleAs: "json"
	         }).then(function (response) {
	             // Once the response is received, build an in-memory store
	             // with the data
	            // console.log(response);
	             var store = new Memory({ data: response });
	              
	             var actionRenderCell = function (object, data,cell) {
	            	 //console.log(object);
	            	 console.log(cell);
	                 var btnDelete = new Button({
	                     rowId : object.id,
	                     label: "删除",
	                     onClick: function () {
	                    	 $.blockUI();
	                         request("delete",{query:{id:this.rowId}}).then(function() {
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
	                    	 domStyle.set(dom.byId("addButton"), 'display', 'none');
	                    	 
	                       	 $.blockUI({ message: $('#add') });
	                     },
	                 }, cell.appendChild(document.createElement("div")));
	             }
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 columns: {name:{label:"名称"},belong:{label:"所属机构"},balance:{label:"余额"},
	                	 time:{label:"创建时间"},type:{label:"账户类型",renderCell:function(object, data,cell) {
	                		 if(0==object.isReal) {
	                			 return "虚拟";
	                		 } else {
	                			 return "现实";
	                		 }
	                	 }},
	                	 edit:{label:"操作",renderCell: actionRenderCell}},
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
    	 
    	 require(["dojo/request","dojo/dom",],function(request,dom) {
    		 request("account/add",{query:{name:dom.byId("name").value,
    			 pwd:dom.byId("pwd").value,belong:dom.byId("belong").value,
    			 isReal:dom.byId("isReal").value}
    			 }).then(function() {
   				 document.getElementById("list").innerHTML="";
   		    	 getGrid();
    			 $.unblockUI();
    		 });
    	 });
    	 
     }
     function updateInfo() {
       	 require(["dojo/request","dojo/dom",],function(request,dom) {
       		 request("account/update",{query:{id:dom.byId("id").value,name:dom.byId("name").value,
       			 pwd:dom.byId("pwd").value,belong:dom.byId("belong").value,
       			 isReal:dom.byId("isReal").value,balance:dom.byId("balance")}
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
    	 $("#addButton").show();
    	 $("#updateButton").hide();
    	 $.blockUI({ message: $('#add') });
     }
     function openEditDiv() {
    	 $("#addButton").hide();
       	 $("#updateButton").show();
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
<div id="add" style="text-align: center; width: 20px; height: 150px; border;
    1px solid #9cf; padding: 25px; display: none;">
    <table>
    	<tr>
    		<td width="50px">名称</td>
    		<td><input id = "id" type="text" style="display:none" />
    		<input id = "name" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td width="50px">密码</td>
    		<td><input id = "pwd" type="password" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td width="50px">所属机构</td>
    		<td><input id = "belong" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td width="50px">余额</td>
    		<td><div><input id="balance" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/></div></td>
    	</tr>
    	<tr>
    		<td width="50px">账户类型</td>
    		<td><input id = "isReal" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	
    	<tr>
    		<td><button id="addButton" onclick="addinfo()">添加</button>
    		<button id="updateButton" data-dojo-type="dijit/form/Button" onclick="updateInfo()">更新</button></td>
    		<td><button data-dojo-type="dijit/form/Button" onclick="divclose()">取消</button></td>
    	</tr>
    </table>
</div>
</body>
</html>