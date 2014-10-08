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
<link rel="stylesheet" href="<%=basePath%>css/blog.css">
<LINK href="<%=basePath%>favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/coin-slider.css">
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>js/coin-slider.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/blog.js"></script>
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
 	         request("getList", {
 	             handleAs: "json",query:{userId:1}
 	         }).then(function (response) {
 	        	 for(var i=0;i<response.length;i++) {
 	        		dom.byId("articlesection").innerHTML+=getArticleHeadDiv(response[i]);
 	        	 }
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
    		  request("chip/chipin",{query:{money:dom.byId("money").value,
    			  chip:chip,accountId:$.getUrlParam('accountId'),
    			  accountId:$.getUrlParam('accountId')}}).then(function() {
    				  require(["dojo/request","dojo/dom"],function(request,dom) {
    		    		  request("chip/addincome",{query:{income:dom.byId("money").value,
    		    			  accountId:$.getUrlParam('accountId'),memo:chip}}).then(function() {
    		    				  $.unblockUI();
    		    			  });
    		    	  });
    			  });
    	  });
    	  
      }
      function updateInfo() {
        	 require(["dojo/request","dojo/dom",],function(request,dom) {
        		 request("game/update",{query:{id:dom.byId("id").value,suggest:dom.byId("suggest").value,
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
<body class="claro" onload="$initmenu(3);getGrid()">
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
<div id="layout" class="pure-g">
   

    <div class="content pure-u-1 pure-u-md-3-4">
        <div id="articlesection">
            <!-- A wrapper for all the blog posts -->
        </div>
    </div>
</div>
<div id="update" style="text-align: center; width: 200px; height: 180px; border;
    1px solid #9cf; padding: 25px; display: none;">
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