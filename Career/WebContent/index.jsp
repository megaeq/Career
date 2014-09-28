<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/coin-slider.css">
<title>Insert title here</title>
</head>
<body>
	<div class="main">
		 <div class="header_resize">
	      <div class="menu_nav">
	        <ul>
	          <li class="active"><a href="index.html"><span>Home Page</span></a></li>
	          <li><a href="#"><span>Support</span></a></li>
	          <li><a href="#"><span>About Us</span></a></li>
	          <li><a href="#"><span>Blog</span></a></li>
	          <li><a href="#"><span>Contact Us</span></a></li>
	        </ul>
	      </div>
	      <div class="logo">
	        <h1><a href="#"><span>Brightpulse</span> <small>Company Slogan Here</small></a></h1>
	      </div>
	      <div class="clr"></div>
	    </div>
	  </div>
	</div>
</body>
</html>