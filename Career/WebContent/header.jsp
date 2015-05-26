<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport"
		content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=2.0,user-scalable=yes" />
<title>Insert title here</title>
<style type="text/css">
	.header {
		background:#EDEDED;
		width:100%;
		text-align:right;
	    padding-top:10px;
	    padding-bottom:20px;
	    font-size:12px;
	    z-index:99999;
	}
	.name {
		
	}
	#nav { display:block;list-style:none;}
	#nav .jquery_out {float:left;line-height:32px;display:block; border-right:1px solid #2C00DB; text-align:center; color:#2C00DB;font:18px/32px "微软雅黑"; }
	#nav .jquery_out .smile {padding-left:1em;}
	#nav .jquery_inner {margin-left:16px;}
	#nav .jquery {margin-right:1px;padding:0 2em;}
	#nav .mainlevel { float:left;width:100px;border:1px solid #EDEDED;/*IE6 only*/}
	#nav .mainlevel:hover { background:#ffffff;}
	#nav .mainlevel a {color:#000000; text-decoration:none; line-height:32px; display:block; padding:0 20px; width:60px;}
	#nav .mainlevel a:hover {color:#2C00DB; text-decoration:none;}
	#nav .mainlevel ul {display:none; position:absolute;}
	#nav .mainlevel li { background:#ffffff;list-style:none;width:100px;border:1px solid #EDEDED;/*IE6 only*/}
	#nav .mainlevel li:hover { background:#EDEDED;}
</style>
<script src="<%=basePath %>js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		jQuery.navlevel2 = function(level1,dytime) {
			
			  $(level1).mouseenter(function(){
				  varthis = $(this);
				  delytime=setTimeout(function(){
					varthis.find('ul').slideDown();
				},dytime);
				
			  });
			  $(level1).mouseleave(function(){
				 clearTimeout(delytime);
				 $(this).find('ul').slideUp();
			  });
			  
			};
		  $.navlevel2("li.mainlevel",200);
		jQuery.ajax({
	         type: "post",
	         url: "<%=basePath %>loginManage/getBaseInfo",
	         success: function(data,textStatus){
        	 	 var json = JSON.parse(data);
				 $(".name").append(json.name);
             }
		});
	});
	
	function logout() {
		jQuery.ajax({
            type: "post",
            url: "<%=basePath %>loginManage/loginOut",
            success: function(data,textStatus){
		 		window.location.href = "<%=basePath %>login.jsp";
            }
   		});
	}
</script>
</head>
<body>
	<div class="header">
		<ul id="nav">
			<li class="mainlevel"><a href="<%=basePath %>">首页</a>
		    </li>
		    <li class="mainlevel"><a href="<%=basePath %>page/mathModel/mathModelDataProcesser.jsp">数据挖掘</a>
		        <ul>
		        	<li><a href="#">网页特效</a></li>
		        </ul>
		    </li>
		    <li class="mainlevel"><a href="<%=basePath %>page/lottory/nowGameList.jsp">足球现场</a>
		    </li>
		</ul>
		<span class="name"></span>
		<a href="#" onclick="logout();">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
</body>
</html>