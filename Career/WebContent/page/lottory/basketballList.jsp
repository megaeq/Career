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
 .dgrid-column-set-4{
 	width:5%;
 	text-align:center;
 } 
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>js/coin-slider.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
 <script type="text/javascript">
 require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button"]);
 var array = new Array;
 function getGrid() {
 	require(["dojo/ready",
 	         "dojo/_base/declare",
 	         "dojo/request",
 	        "dojo/dom",
 	         "dojo/store/Memory",
 	         "dgrid/OnDemandGrid",
 	         "dgrid/extensions/Pagination",
 	        "dijit/form/Button",
 	       "dojo/store/JsonRest",
 	      'dgrid/ColumnSet'
 	     ], function (ready,declare,request,dom, Memory, OnDemandGrid, Pagination,Button,JsonRest,ColumnSet) {
 		ready(function(){
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
	            	var on4 = new Button({
                     rowId : object.id,
                     label: "编辑",
                     onClick: function () {
                    	// console.log(array.length);
                    	$("#id").val(object.id);
                    	dom.byId("code").innerHTML=object.code;
                    	dom.byId("home").innerHTML=object.homeTeam;
                    	dom.byId("guest").innerHTML=object.guestTeam;
                    	$("#suggest").val(object.suggest);
                    	 $.blockUI({ message: $('#update') });
                     }
                 }, cell.appendChild(document.createElement("div")));
	            	var btn1 = new Button({
                     rowId : object.id,
                     label: "往绩",
                     onClick: function () {
                    	location.href="<%=basePath%>page/lottory/gameHistoryList.jsp?gameId="+object.id;
                     }
                 }, cell.appendChild(document.createElement("div")));
	             }
	             var grid = new (declare([OnDemandGrid, Pagination,ColumnSet]))({
	                 store: new JsonRest({
	         	   	    target: "<%=basePath%>basketball/getList?startDate="+dom.byId("startDate").value+"&endDate="+dom.byId("endDate").value}),
	                className: "dgrid-autoheight",
                	columnSets:[[[ {field:"code",label:"编号"}],
                	              [{field:"type",label:"联赛类型"}]],
                	             [[{field:"homeTeam",label:"主队"}],
                	              [{field:"guestTeam",label:"客队"}]],
                	             [[{field:"winRate",label:"主胜"}],
                 	              [{field:"loseRate",label:"主负"}]],
                	             [[{field:"pw",label:"pw"}],
                 	              [ {field:"pl",label:"pl"}]],
                	             [
         	                       [{field:"letTheBall",label:"让球数"}]
         	                      ],
                	              [[{field:"winRateLB",label:"让球胜"}],
                	                [{field:"loseRateLB",label:"让球负"}]],
                	               [[{field:"pwlb",label:"pwlb"}],
                	                [{field:"pllb",label:"pllb"}]],
                	               [[
                	                 {field:"bigScore",label:"大球"}
                	                 ]],
                	                [[{field:"winRateBS",label:"大球胜"}],
                	                  [{field:"loseRateBS",label:"大球负"}]],
                	                   [[{field:"pwbs",label:"pwbs"}],
                	                    [{field:"plbs",label:"plbs"}]],
                	                 [[{field:"time2",label:"时间"}]]
                	            ],
	                 rowsPerPage:14,
	                 pagingTextBox:true,
	                 pagingLinks:4
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
      function chipin() {
    	  $.blockUI();
    	 
    	  //console.log($("#multi").val()+" "+$("#money").val());
    	  var chip = "";
    	  for(var i=0;i<array.length;i++) {
    		chip+=array[i]+";";  
    	  }
    	  require(["dojo/request","dojo/dom"],function(request,dom) {
    		  request("<%=basePath%>chip/chipin",{query:{money:dom.byId("money").value,
    			  chip:chip,accountId:$.getUrlParam('accountId'),
    			  accountId:$.getUrlParam('accountId')}}).then(function() {
    				  require(["dojo/request","dojo/dom"],function(request,dom) {
    		    		  request("chip/addincome",{query:{income:dom.byId("money").value,
    		    			  accountId:$.getUrlParam('accountId'),memo:chip}}).then(function() {
    		    				  array=[];
    		    				  dom.byId("tbody").innerHTML="";
    		    				  $.unblockUI();
    		    			  });
    		    	  });
    			  });
    	  });
    	  
      }
      function updateInfo() {
        	 require(["dojo/request","dojo/dom",],function(request,dom) {
        		 request("<%=basePath%>game/update",{query:{id:dom.byId("id").value,suggest:dom.byId("suggest").value,
        			 }
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
      function clearclean() {
     	 $("#id").val("");
     	 $("#suggest").val("");
      }
 </script>
</head>
<body class="claro" onload="$initmenu(1);getGrid();">
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
    <button data-dojo-type="dijit/form/Button" onclick="change()">查询</button>
</div>
<div id="list"></div>
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
<div data-dojo-type="dijit/Dialog" data-dojo-id="detail" title="详情" style="display: none;">
    <table>
    	<tr>
    		<td>代号<div style="width:100px;"></div></td>
    		<td><input id = "id" type="0" style="display:none" />
    		<div id="code"></div>
    	</tr>
    	<tr>
    		<td>主队</td>
    		<td><div id="home"></div> </td>
    	</tr>
    	<tr>
    		<td>客队</td>
    		<td><div id="guest"></div> </td>
    	</tr>
    	<tr>
    		<td>建议</td>
    		<td><textarea class="t_area" id="suggest"></textarea>
    		</td>
    	</tr>
    	
    	<tr>
    		<td></td>
    		<td>
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