<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
		content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=2.0,user-scalable=yes" />
<title>首页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
<link href="/favicon.ico" type="image/x-icon" rel=icon>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/career.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jqueryui/jquery-ui.min.css">
<style type="text/css">
.inputimage {
	background: url(${pageContext.request.contextPath}/image/career/career.png) ;
	background-size:100% 100%;
}
</style>
<script type="text/javascript" src="js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui.min.js"></script>
<script type="text/javascript">
$(function() {
	$( "#inputtext" ).bind("keydown.operate",function(e) {
		if(e.keyCode==13) {
			console.log($( "#inputtext" ).val());
		}
	});
	var availableTags = [
	                  	"ActionScript",
	                  	"AppleScript",
	                  	"Asp",
	                  	"BASIC",
	                  	"C",
	                  	"C++",
	                  	"Clojure",
	                  	"COBOL",
	                  	"ColdFusion",
	                  	"Erlang",
	                  	"Fortran",
	                  	"Groovy",
	                  	"Haskell",
	                  	"Java",
	                  	"JavaScript",
	                  	"Lisp",
	                  	"Perl",
	                  	"PHP",
	                  	"Python",
	                  	"Ruby",
	                  	"Scala",
	                  	"Scheme"
	                  ];
	                  $( "#inputtext" ).autocomplete({
	                  	source: availableTags
	                  });
});
				
</script>	
</head>
<body>
	<div>
		<div><image id="changeimage"></div>
	</div>
	<div class="inputimage">
     <div class="inputall">
     	<input id="inputtext" class="inputone" name="inputtext">
     </div>
   </div>
</body>
</html>