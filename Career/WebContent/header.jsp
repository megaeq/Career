<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport"
		content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=2.0,user-scalable=yes" />
<title>Insert title here</title>
<style type="text/css">
	.header {
		width:90%;
		text-align:right;
		padding-left: 5%;
	    padding-right: 5%;
	    padding-top:2%;
	    padding-bottom:2%;
	    font-size:12px;
	}
	.name {
		
	}
</style>
<script src="js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery.ajax({
	         type: "post",
	         url: "loginManage/getBaseInfo",
	         success: function(data,textStatus){
        	 	 var json = JSON.parse(data);
				 $(".name").append(json.name);
             }
		});
	});
	
	function logout() {
		jQuery.ajax({
            type: "post",
            url: "loginManage/loginOut",
            success: function(data,textStatus){
		 		window.location.href = "login.jsp";
            }
   		});
	}
</script>
</head>
<body>
	<div class="header">
		<span class="name"></span>
		<a href="#" onclick="logout();">退出</a>
	</div>
</body>
</html>