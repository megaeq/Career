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
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
<script type="text/javascript" src="<%=basePath%>js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>js/coin-slider.min.js"></script>
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
	         'dojo/dom-style' ,
	         "dojo/store/JsonRest"
	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,Button,domStyle,JsonRest) {
	              
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
	            	 var btn1 = new Button({
	                     rowId : object.id,
	                     label: "详情",
	                     onClick: function () {
	                         request("bill/detail",{handleAs: "json",
	                        	 query:{billId:object.id}}).then(function(response) {
	                        	 document.getElementById("detailbody").innerHTML="";
	                        	 for(var i=0;i<response.length;i++) {
	                        		if(response[i].win) {
	                        			document.getElementById("detailbody").innerHTML+=
	                        				 "<tr><td>"+response[i].homeTeam+" - "+response[i].guestTeam+"</td><td>"+
		                        			 response[i].homeScore+" - "+response[i].guestScore+"</td>"+
	                        			"<td style='color:#d62d0f;'>"+response[i].sp+"</td><td style='color:#d62d0f;'>"+response[i].bet+"</td></tr>"
	                        		} else {
	                        			document.getElementById("detailbody").innerHTML+=
	                        				 "<tr><td>"+response[i].homeTeam+" - "+response[i].guestTeam+"</td><td>"+
		                        			 response[i].homeScore+" - "+response[i].guestScore+"</td>"+
		                        			"<td>"+response[i].sp+"</td><td>"+response[i].bet+"</td></tr>"
	                        		}
	                        			 
	                        	 }
	                			 detail.show();
	                         })
	                     }
	                 }, cell.appendChild(document.createElement("div")));
	             }
	             var grid = new (declare([OnDemandGrid, Pagination]))({
	                 store: new JsonRest({
		         	   	    target: "bill/getList?accountId="+$.getUrlParam('accountId')}),
	                 className: "dgrid-autoheight",
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

	         request("bill/sum"
		 			 ,{handleAs: "json",query:{accountId:$.getUrlParam('accountId')}}).then(function(response) {
		 	 			 var sum="";
		 	 			 sum+="<h1>总收入:"+response.income+",总花费:"+response.cost;
		 	 			 sum+=";总盈余："+(response.income-response.cost)+"</h1>";
		 				 dom.byId("sum").innerHTML=sum;
		 			 })  ;
	     });
}

     function change() {
    	 document.getElementById("list").innerHTML="";
    	 getGrid();
     }
     
     function divclose() {
    	 detail.hide();
     }
     function clearAll() {
    	 $.blockUI();
    	 require(["dojo/request","dojo/dom",],function(request,dom) {
       		 request("bill/clearAll",{query:{accountId:$.getUrlParam('accountId')}
       			 }).then(function() {
      				 document.getElementById("list").innerHTML="";
      		    	 getGrid();
      		    	$.unblockUI();
       		 });
       	 });
     }
     function goback() {
    	 window.location="<%=basePath%>page/myinfo/accountList.jsp";
     }
</script>
</head>
<body class="claro" onload="$initmenu(1);getGrid()">
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
    <button data-dojo-type="dijit/form/Button" onclick="clearAll()">结算全部</button>
    <button data-dojo-type="dijit/form/Button" style="float:right;" onclick="goback()">返回
</div>
<div id="list"></div>
<div data-dojo-type="dijit/Dialog" data-dojo-id="detail" title="详情" style="display: none;">
    <table id="detailtable" class="pure-table pure-table-bordered">
    <thead>
		<tr>
			<th width="200px">主队 - 客队</th><th width="50px">比分</th>
			<th width="50px">sp</th><th width="50px">投注</th>
		</tr>
	</thead>
    <tbody id="detailbody"></tbody>
    </table>
</div>
<div id="sum" style="color:red;"></div>
      <div class="clr"></div>
    </div>
  </div>
	</div>

</body>
</html>