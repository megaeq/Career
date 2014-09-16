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
require(["dojo/parser", "dijit/form/DateTextBox"]);
function getGrid() {
	require([
	         "dojo/_base/declare",
	         "dojo/request",
	         "dojo/dom",
	         "dojo/store/Memory",
	         "dgrid/OnDemandGrid",
	         "dgrid/extensions/Pagination"
	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination) {
	         request("dojojson", {
	             handleAs: "json",query:{date:dom.byId("date1").value,name:"二狗子"}
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
	                 columns: {
	                     first: "First Name",
	                     last: "Last Name",
	                     totalG: "Games Played"
	                 },
	                 rowsPerPage:10,
	                 pagingTextBox:true,
	                 pagingLinks:2
	             	
	             }, "grid");
	             grid.on(".dgrid-header .dgrid-cell:click", function(evt){
	            	    var cell = grid.cell(evt);
	            	    console.log(cell);
	            	    // cell.element == the element with the dgrid-cell class
	            	    // cell.column == the column definition object for the column the cell is within
	            	    // cell.row == the same object obtained from grid.row(evt)
	            	});
	             grid.on(".dgrid-row .dgrid-cell:click", function(evt){
	            	 	console.log(evt);
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
    	 console.log("${basePath}");
    	 console.log("1111");
    	 getGrid();
     }
</script>
</head>
<body class="claro" onload="getGrid()">
<%=request.getContextPath() %>
<div id="dateTextBox">
	<label for="date1">Drop down Date box:</label>
	<input type="text" name="date1" id="date1" value="2014-09-29"
    data-dojo-type="dijit/form/DateTextBox" onchange="change()"
    required="true" />
    <br>
</div>
<div id="grid"></div>
</body>
</html>