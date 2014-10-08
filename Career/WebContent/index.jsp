<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="<%=basePath%>favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/coin-slider.css">
<title>Insert title here</title>

<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>js/coin-slider.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-extension.js"></script>
</head>
<body onload="$initmenu(0)">
	<div class="main" id="menu">
	  </div>
	  <div class="content">
    <div class="content_resize">
      <div class="clr"></div>
    </div>
  </div>
	</div>
</body>
</html>