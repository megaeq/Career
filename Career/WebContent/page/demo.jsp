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
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 .field-edit {
        width: 200px;
    }
 </style> 
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
 <script type="text/javascript">
 require(["dojo/parser", "dijit/form/DateTextBox","dijit/form/Button"]);
 var array = new Array;
 function getGrid() {
 	require([
 	         "dojo/_base/declare",
 	         "dojo/request",
 	        "dojo/dom",
 	         "dojo/store/Memory",
 	         "dgrid/OnDemandGrid",
 	         "dgrid/extensions/Pagination",
 	        "dijit/form/Button"
 	     ], function (declare,request,dom, Memory, OnDemandGrid, Pagination,Button) {
 	         request("<%=basePath%>game/getList", {
 	             handleAs: "json",query:{startDate:dom.byId("startDate").value,
 	            	 endDate:dom.byId("endDate").value}
 	         }).then(function (response) {
 	             var store = new Memory({ data: response });
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
 	             }
 	             var grid = new (declare([OnDemandGrid, Pagination]))({
 	                 store: store,
 	                className: "dgrid-autoheight",
 	                 columns: {id:{label:"id"},code:{lable:"编号"},
 	                	edit:{label:"操作",renderCell:chipin,width:"200px"}},
 	                 rowsPerPage:14,
 	                 pagingTextBox:true,
 	                 pagingLinks:2
 	             	
 	             }, "list");
 	             grid.on(".dgrid-header .dgrid-cell:click", function(evt){
 	            	    var cell = grid.cell(evt);
 	            	});
 	             grid.on(".dgrid-row:contextmenu", function(evt){
 	            	    var row = grid.row(evt);
 	            	    console.log(row);
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
<body class="claro">

</body>
</html>