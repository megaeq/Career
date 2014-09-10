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
<script type="text/javascript" src="../../js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true"></script>
<script type="text/javascript">
require(["dojo/data/ItemFileWriteStore","dojox/grid/DataGrid"],function(){
    /*set up data store*/
    var data = {
      identifier: 'id',
      items: []
    };
    var data_list = [
      { col1: "normal", col2: false, col3: 'But are not followed by two hexadecimal', col4: 29.91},
      { col1: "important", col2: false, col3: 'Because a % sign always indicates', col4: 9.33},
      { col1: "important", col2: false, col3: 'Signs can be selectively', col4: 19.34}
    ];
    var rows = 60;
    for(var i=0, l=data_list.length; i<rows; i++){
      data.items.push(dojo.mixin({ id: i+1 }, data_list[i%l]));
    }
    var store = new dojo.data.ItemFileWriteStore({data: data});

    /*set up layout*/
    var layout = [[
      {'name': 'Column 1', 'field': 'id', 'width': '100px'},
      {'name': 'Column 2', 'field': 'col2', 'width': '100px'},
      {'name': 'Column 3', 'field': 'col3', 'width': '200px'},
              {'name': 'Column 4', 'field': 'col4', 'width': '150px'}
    ]];

    /*create a new grid:*/
    var grid = new dojox.grid.DataGrid({
        id: 'grid',
        store: store,
        structure: layout,
        rowSelector: '20px'},
      document.createElement('div'));

    /*append the new grid to the div*/
    dojo.byId("gridDiv").appendChild(grid.domNode);
	alert("hello");
    /*Call startup() to render the grid*/
    grid.startup();
});


</script>
</head>
<body class="tundra">
<div id="gridDiv"></div>
</body>
</html>