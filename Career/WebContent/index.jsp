<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
		content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=2.0,user-scalable=yes" />
<title>首页</title>
<link rel="stylesheet" href="<%=basePath%>css/my.css">
<LINK href="<%=basePath%>favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<script type="text/javascript" src="js/jquery/jquery-1.11.1.js"></script>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>