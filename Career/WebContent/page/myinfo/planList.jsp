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
<LINK href="<%=basePath%>favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/coin-slider.css">
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>js/coin-slider.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
<script type="text/javascript">
require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button","dijit/Dialog",
         "dijit/form/Select"]);
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
	         request("plan/getList", {
	             handleAs: "json"
	         }).then(function (response) {
	             var store = new Memory({ data: response });
	             var actionRenderCell = function (object, data,cell) {
	                 var btn1 = new Button({
	                     rowId : object.id,
	                     label: "删除",
	                     onClick: function () {
	                    	 processing.show();
	                         request("plan/delete",{query:{id:this.rowId}}).then(function() {
	                        	 document.getElementById("list").innerHTML="";
	               		    	 getGrid();
	                			 processing.hide();
	                         })
	                     }
	                 }, cell.appendChild(document.createElement("div")));
	                 var btn2 = new Button({
	                     rowId : object.id,
	                     label: "编辑",
	                     onClick: function() {
	                    	 $("#id").val(object.id);
	                    	 $("#name").val(object.name);
	                    	 $("#memo").val(object.memo);
	                    	 level.value=object.level;
	                    	 $("#complete").val(object.complete);
	                    	 $("#addButton").hide();
	                       	 $("#updateButton").show();
	                       	 types.value=object.type;
	                       	myDialog.show();
	                     },
	                 }, cell.appendChild(document.createElement("div")));
	                
	                 
	             }
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 columns: {id:{label:"id"},name:{label:"名称"},levels:{label:"级别",renderCell:function(object, data,cell) {
	                	 var div = document.createElement("div");
                		 if(1==object.level) {
                			 div.innerHTML = "一级";
                		 } else if(2==object.level){
                			 div.innerHTML ="二级";
                		 } else if(3==object.level) {
                			 div.innerHTML ="三级";
                		 }
            		    return div;
	                 }},
	                	 memo:{label:"备注"},type:{label:"类型"},
	                	 complete:{label:"完成度",renderCell:function(object, data,cell) {
	                		 var div = document.createElement("div");
	                		 div.innerHTML = object.complete+"%";
	                		 return div;
	                	 }},
	                	 edit:{label:"操作",renderCell: actionRenderCell}},
	                 rowsPerPage:10,
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
    		 request("plan/add",{query:{name:dom.byId("name").value,memo:dom.byId("memo").value,
    			 level:level.value,complete:dom.byId("complete").value,
    			 type:types.value
    			 }
    			 }).then(function() {
   				 document.getElementById("list").innerHTML="";
   		    	 getGrid();
   		    	myDialog.hide();
    			 clearclean();
    		 });
    	 });
    	 
     }
     function clearclean() {
    	 $("#id").val("");
    	 $("#name").val("");
    	 $("#memo").val("");
    	 level.value=1;
    	 $("#complete").val("");
     }
     function updateInfo() {
       	 require(["dojo/request","dojo/dom",],function(request,dom) {
       		 request("plan/update",{query:{id:dom.byId("id").value,name:dom.byId("name").value,
       			 memo:dom.byId("memo").value,level:level.value,
       			 complete:dom.byId("complete").value,type:types.value}
       			 }).then(function() {
      				 document.getElementById("list").innerHTML="";
      		    	 getGrid();
      		    	myDialog.hide();
      		    	clearclean();
       		 });
       	 });
        }
     
     function divclose() {
    	 myDialog.hide();
    	 clearclean();
     }
     function openAddDiv() {
    	 $("#addButton").show();
    	 $("#updateButton").hide();
    	 myDialog.show();
     }
     function refresh() {
    	
    	 require(["dojo/request","dojo/dom",],function(request,dom) {
    		 processing.show();
    		 request("plan/refresh").then(function() {
   				 document.getElementById("list").innerHTML="";
   		    	 getGrid();
   		    	
    		 });
    		 processing.hide();
    	 });
     }
</script>
</head>
<body class="claro" onload="$initmenu(2);getGrid()">
<div class="main" id="menu">
	  </div>
	  <div class="content">
    <div class="content_resize">
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
    <button data-dojo-type="dijit/form/Button" onclick="refresh()">刷新</button>
</div>
<div id="list"></div>
<div data-dojo-type="dijit/Dialog" data-dojo-id="processing" style="display:none">处理中...</div>
<div data-dojo-type="dijit/Dialog" data-dojo-id="myDialog" title="编辑" style="display:none">
    <table>
    	<tr>
    		<td>名称<div style="width:100px;"></div></td>
    		<td><input id = "id" type="text" style="display:none" />
    		<input id = "name" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>备注</td>
    		<td><input id = "memo" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>级别</td>
    		<td><select  data-dojo-id="level" data-dojo-type="dijit/form/Select">
    			<option value="1" selected="true">一级</option>
    			<option value="2">二级</option>
    			<option value="3">三级</option>
    		</select></td>
    	</tr>
    	<tr>
    		<td>类型</td>
    		<td><select  data-dojo-id="types" data-dojo-type="dijit/form/Select">
    			<option value="生涯" selected="true">生涯</option>
    			<option value="彩票">彩票</option>
    		</select></td>
    	</tr>
    	<tr>
    		<td>完成度</td>
    		<td><input id = "complete" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> %</td>
    	</tr>
    	
    	<tr>
    		<td></td>
    		<td><button id="addButton" class="greyButton" onclick="addinfo()">添加</button>
    		<button id="updateButton" class="greyButton" onclick="updateInfo()">更新</button>
    		<button  class="greyButton" onclick="divclose()">取消</button></td>
    	</tr>
    </table>
</div>
      <div class="clr"></div>
    </div>
  </div>
	</div>

</body>
</html>