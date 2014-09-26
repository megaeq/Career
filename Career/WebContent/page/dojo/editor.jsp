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
<script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
require(["dojo/parser","dijit/_editor/plugins/LinkDialog","dijit/_editor/plugins/TextColor","dojo/parser",
          "dojo/domReady!"]);
require(["dijit/Editor", "dijit/_editor/plugins/AlwaysShowToolbar", "dojo/dom", "dojo/query",
         "dijit/_editor/plugins/ViewSource", "dijit/_editor/plugins/FullScreen",
         "dijit/_editor/plugins/FontChoice"],
		function(Editor, AlwaysShowToolbar, dom, query){
		        var myEditor = new Editor({
		            height: '',
		            extraPlugins: [AlwaysShowToolbar,'viewsource','|','delete','|','createLink','unlink',
		                           'insertImage','|',
		                           "foreColor",'hiliteColor','|','fontName', 'fontSize', 'formatBlock','|','fullScreen'],
		        }, dom.byId('programmatic2'));
		        console.log(myEditor);
		        myEditor.startup();
		       // query('#create2').orphan();
		dom.byId("getcontent").onclick=function() {
			console.log(myEditor.value);
			dom.byId("content").innerHTML=myEditor.value;
		}
		});

		

</script>
</head>
<body class="claro">
<input id="getcontent" type="button" value="获取">
<div id="programmatic2">This div will become an auto-expanding editor.</div>
<div id="content"></div>
</body>
</html>