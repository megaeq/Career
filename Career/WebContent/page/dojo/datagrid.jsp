<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <style type="text/css"> 
 @import "../../js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "../../js/dojojs/dojo/resources/dojo.css"; 
 </style> 
<script type="text/javascript" src="../../js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
<script type="text/javascript">
require(["dojo/_base/declare", "dgrid/Grid", "dgrid/Keyboard", "dgrid/Selection", "dojo/domReady!"],
	    function(declare, Grid, Keyboard, Selection){
	        var data = [
	            { first: "Bob", last: "Barker", age: 89 },
	            { first: "Vanna", last: "White", age: 55 },
	            { first: "Pat", last: "Sajak", age: 65 }
	        ];
	 
	        // Create a new constructor by mixing in the components
	        var CustomGrid = declare([ Grid, Keyboard, Selection ]);
	 
	        // Now, create an instance of our custom grid which
	        // have the features we added!
	        var grid = new CustomGrid({
	            columns: {
	                first: "First Name",
	                last: "Last Name",
	                age: "Age"
	            },
	            selectionMode: "single", // for Selection; only select a single row at a time
	            cellNavigation: true // for Keyboard; allow only row-level keyboard navigation
	        }, "grid");
	        grid.renderArray(data);
	        grid.on("dgrid-select", function(event){
	            // Report the item from the selected row to the console.
	            console.log("Row selected: ", event.rows[0].data);
	        });
	        grid.on("dgrid-deselect", function(event){
	            console.log("Row de-selected: ", event.rows[0].data);
	        });
	        
	});


</script>
</head>
<body class="tundra">
<div id="grid"></div>
</body>
</html>