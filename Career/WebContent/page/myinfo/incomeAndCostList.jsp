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
require(["dijit/Dialog", "dijit/form/TextBox", "dijit/form/Button"]);
function getGrid() {
	require(["dijit/Tooltip", "dojo/domReady!"], function(Tooltip){
	    // create a new Tooltip and connect it to bar1 and bar4
	    new Tooltip({
	        connectId: ["question"],
	        label: "这里是问题"
	    });
	});
	/* require(["dojo/store/JsonRest"], function(JsonRest){
	   	  var store = new JsonRest({
	   	    target: "getList2"
	   	  });

	   	  var self = this;
	   	  console.log(store.query);

	   	  var results = store.query({foo:'bar'}, {
	   	    start: 2,
	   	    count: 5,
	   	    sort: [
	   	      { attribute: "id", descending: true }
	   	    ]
	   	  }).then(function(data){
	   		  console.log(data);
	   		  console.log(results);
	   		results.total.then(function(total){
	   	      console.log("total results: ", total);
	   	      console.log("go on and use data ", data, " with this ", self);
	   	    });
	   	  });
	   	}); */
	require([
	         "dojo/_base/declare",
	         "dojo/request",
	         "dojo/dom",
	         "dojo/store/Memory",
	         "dgrid/OnDemandGrid",
	         "dgrid/extensions/Pagination",
	         "dijit/form/Button",
	         "dojo/store/JsonRest",
	         "dijit/form/TimeTextBox"
	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,Button,JsonRest) {
		/* var store = new JsonRest({
	   	    target: "getList"
	   	  }); */
	             // Once the response is received, build an in-memory store
	             // with the data
	            // console.log(response);
	            
	              
	             // Create an instance of OnDemandGrid referencing the store
	             /* var grid = new OnDemandGrid({
	                 store: store,
	                 columns: {
	                     first: "First Name",
	                     last: "Last Name",
	                     totalG: "Games Played"
	                 }
	             }, "grid"); */
	             var actionRenderCell = function (object, data,cell) {
	            	 //console.log(object);
	            	// console.log(cell);
	                 var btnDelete = new Button({
	                     rowId : object.id,
	                     label: "删除",
	                     onClick: function () {
	                    	 $.blockUI();
	                         request("<%=basePath%>incomeAndCost/delete",{query:{id:this.rowId}}).then(function() {
	                        	 document.getElementById("list").innerHTML="";
	               		    	 getGrid();
	                			 $.unblockUI();
	                         })
	                     }
	                 }, cell.appendChild(document.createElement("div")));
	                 btnDelete._destroyOnRemove = true;
	                 var btnDelete2 = new Button({
	                     rowId : object.id,
	                     label: "编辑",
	                     onClick: function () {
	                    	 $.blockUI();
	                         request("<%=basePath%>incomeAndCost/delete",{query:{id:this.rowId}}).then(function() {
	                        	 document.getElementById("list").innerHTML="";
	               		    	 getGrid();
	                			 $.unblockUI();
	                         })
	                     }
	                 }, cell.appendChild(document.createElement("div")));
	                 btnDelete2._destroyOnRemove = true;

	                 return [btnDelete,btnDelete2];

	             }
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	            	 className: "dgrid-autoheight",
	                 store: new JsonRest({
	         	   	    target: "<%=basePath%>incomeAndCost/getList"
	       	   	  }),
	                 columns: {id:{label:"id"},income:{label:"收入"},cost:{label:"消费"},date1:{label:"日期"},
	                	 usages:{label:"用途"},memo:{label:"备注"},
	                	 edit2:{label:"操作",renderCell: actionRenderCell}},
	                 rowsPerPage:14,
	                 pagingTextBox:true,
	                 pagingLinks:2
	             	
	             }, "list");
	             grid.on(".dgrid-header .dgrid-cell:click", function(evt){
	            	    var cell = grid.cell(evt);
	            	    //console.log(cell);
	            	    // cell.element == the element with the dgrid-cell class
	            	    // cell.column == the column definition object for the column the cell is within
	            	    // cell.row == the same object obtained from grid.row(evt)
	            	});
	             grid.on(".dgrid-row:contextmenu", function(evt){
	            	    var row = grid.row(evt);
	            	    //console.log(row);
	            	    // row.element == the element with the dgrid-row class
	            	    // row.id == the identity of the item represented by the row
	            	    // row.data == the item represented by the row
	            	});
	             grid.startup();
	        
	        // console.log(startDate);
	         startDate.constraints.datePattern='yyyy-MM-dd';
	         times.constraints.timePattern='yyyy-MM-dd HH:mm:ss';
	     });
}

     function change() {
    	 document.getElementById("list").innerHTML="";
    	 getGrid();
     }
     function addinfo() {
    	 require(["dojo/request","dojo/dom",],function(request,dom) {
    		 request("<%=basePath%>incomeAndCost/add",{query:{income:dom.byId("income").value,
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
    	 addorupdate.hide();
     }
     function openAddDiv() {
    	 addorupdate.show();
    	 
     }
</script>
</head>
<body class="claro" onload="getGrid()">
<div id="dateTextBox">
	<label for="date1">起始时间：</label>
	<input type="text" data-dojo-id="startDate" 
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
    <label for="date1">结束时间：</label>
	<input type="text" id="endDate" 
    data-dojo-type="dijit/form/DateTextBox"
    required="true" />
    <input type="text" name="time" data-dojo-id="times"
    data-dojo-type="dijit/form/TimeTextBox"
    required="true" />
    <button data-dojo-type="dijit/form/Button" onclick="change()">查询</button>
    <button data-dojo-type="dijit/form/Button" onclick="openAddDiv()">新增</button>
    <span id="question" style="margin:0 auto;"><img alt="问题" src="<%=basePath%>image/question.png" height="20px" weight="20px"></span>
</div>
<div id="list"></div>
<div data-dojo-type="dijit/Dialog" data-dojo-id="addorupdate" title="编辑">
    <table class="dijitDialogPaneContentArea">
    	<tr>
    		<td>日期</td>
    		<td><input id = "date" type="text"  dojoType="dijit/form/DateTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>收入</td>
    		<td><input id = "income" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>消费</td>
    		<td><input id = "cost" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>日期</td>
    		<td><div><input type="text" id="addDate" data-dojo-type="dijit.form.ValidationTextBox" required="true"/></div></td>
    	</tr>
    	<tr>
    		<td>用途11111111阿萨</td>
    		<td><input id = "usages" type="text"  dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td>备注</td>
    		<td><input id = "memo" type="text" dojoType="dijit.form.ValidationTextBox" required="true"/> </td>
    	</tr>
    	<tr>
    		<td></td><td><button data-dojo-type="dijit/form/Button" onclick="addinfo()">添加</button>
    		<button data-dojo-type="dijit/form/Button" onclick="divclose()">取消</button></td>
    	</tr>
    </table>
</div>
</body>
</html>