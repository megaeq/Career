<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>   
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>  
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h:form>
		<h:outputText value="姓名"></h:outputText>
		<h:outputText value="#{jsftest.name}"></h:outputText>
		<h:outputText value="年龄"></h:outputText>
		<h:outputText value="#{jsftest.age}"></h:outputText>
	</h:form>
	
</body>
</html>