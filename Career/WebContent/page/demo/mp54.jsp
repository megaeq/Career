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
 <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<input id="d11" type="text" onClick="WdatePicker()"/>

<input id="d12" type="text"/>
<img onclick="WdatePicker({el:'d12'})" src="<%=basePath%>js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">


</body>
</html>