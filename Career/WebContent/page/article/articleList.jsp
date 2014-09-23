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
 	        	 console.log(response);
 	        	 for(var i=0;i<response.length;i++) {
 	        		 
 	        	 }
 	             var store = new Memory({ data: response });
 	             console.log(store);
 	             
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
<div id="layout" class="pure-g">
   

    <div class="content pure-u-1 pure-u-md-3-4">
        <div>
            <!-- A wrapper for all the blog posts -->
            <div class="posts">
                <h1 class="content-subhead">Pinned Post</h1>

                <!-- A single blog post -->
                <section class="post">
                    <header class="post-header">
                        <img class="post-avatar" alt="Tilo Mitra&#x27;s avatar" height="48" width="48" src="img/common/tilo-avatar.png">

                        <h2 class="post-title">Introducing Pure</h2>

                        <p class="post-meta">
                            By <a href="#" class="post-author">Tilo Mitra</a> under <a class="post-category post-category-design" href="#">CSS</a> <a class="post-category post-category-pure" href="#">Pure</a>
                        </p>
                    </header>

                    <div class="post-description">
                        <p>
                            Yesterday at CSSConf, we launched Pure – a new CSS library. Phew! Here are the <a href="https://speakerdeck.com/tilomitra/pure-bliss">slides from the presentation</a>. Although it looks pretty minimalist, we’ve been working on Pure for several months. After many iterations, we have released Pure as a set of small, responsive, CSS modules that you can use in every web project.
                        </p>
                    </div>
                </section>
            </div>

            

            <div class="footer">
                <div class="pure-menu pure-menu-horizontal pure-menu-open">
                    <ul>
                        <li><a href="http://purecss.io/">About</a></li>
                        <li><a href="http://twitter.com/yuilibrary/">Twitter</a></li>
                        <li><a href="http://github.com/yahoo/pure/">GitHub</a></li>
                    </ul>
                </div>
            </div>
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
</body>
</html>