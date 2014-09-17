<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../js/dojojs/dijit/themes/claro/claro.css">
<style type="text/css"> 
 @import "../../js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "../../js/dojojs/dojo/resources/dojo.css"; 
 </style> 
 <script type="text/javascript" src="../../js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
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
	         request("getList", {
	             handleAs: "json",query:{startDate:dom.byId("startDate").value,endDate:dom.byId("endDate").value}
	         }).then(function (response) {
	             // Once the response is received, build an in-memory store
	             // with the data
	             console.log(response);
	             var store = new Memory({ data: response });
	              
	             // Create an instance of OnDemandGrid referencing the store
	             /* var grid = new OnDemandGrid({
	                 store: store,
	                 columns: {
	                     first: "First Name",
	                     last: "Last Name",
	                     totalG: "Games Played"
	                 }
	             }, "grid"); */
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: store,
	                 columns: {id:{label:"id"},income:{label:"收入"},cost:{label:"消费"},date1:{label:"日期"},usage:{label:"用途"},memo:{label:"备注"}},
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
    
</div>
<div id="list"></div>
</body>
</html>