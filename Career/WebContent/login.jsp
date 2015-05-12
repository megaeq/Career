<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
		content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=2.0,user-scalable=yes" />
<link rel="shortcut icon" type="image/ico" href="/images/favicon.ico" />
<title>Login</title>
<link href="css/login/styles.css" type="text/css" media="screen" rel="stylesheet" />
<link href="js/jquery/jquery-ui-1.8.16.custom.css" rel="stylesheet"/>
<script src="js/jquery/jquery-1.11.1.min.js"></script>
<script src="js/jquery/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
        	$.ajax({
	             type: "post",
	             url: "loginManage/hasLogin",
	             success: function(data,textStatus){
						 if(data=="hasLogin") {
							 window.location.href = "index.jsp";
						 }
                     }
        	});
            $(".logininput").blur(function() {
                if ($(this).val() == "") {
                    $(this).css("border-color", "red");
                                    }
                else
                    $(this).css("border-color", "#D9D6C4");
            });
        });
        function submit1() {
             var k = 0;
             var ajaxhtml = "";
             $(".logininput").each(function(i, obj) {
                 if ($(obj).val().trim() == "") {
                     k++;
                     $(this).css("border-color", "red");
                     $(this).focus();
                     return false;
                 }
             });
             if (k != 0) return;
             ajaxhtml = $("#loginbtn").html();
             $("#loginbtn").html("Loading....  <img src='image/loading.gif' alt='Announcement' /> ");
             $("#loginbtn").attr("disabled", "disabled");
             if(jQuery("#rememberMe1").is(":checked")) {
     			jQuery("#rememberMe").val(true);
     		} else {
     			jQuery("#rememberMe").val(false);
     		}
			$("#form").submit();
         }
        
    </script>
</head>
<body id="login">
	<div id="wrappertop">
    </div>
    <div id="wrapper">
    	<div id="content">
    		<div id="header">
    		<h1>
                    <a href="">
                       <!--·Ɩogo <img src="logo.png"   height="50"  width="100"  alt="logo">--></a></h1>	
    		</div>
    		<div id="darkbanner" class="banner320">
                <h2>系统登录</h2>
            </div>
            <div id="darkbannerwrap">
            </div>
            <form id="form" method="post">
            	 <fieldset class="form">
                <p>
                    <label class="loginlabel" for="username">
                        用户名:</label>
                    <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all" name="username"
                        id="username" type="text" value="" />
                </p>
                <p>
                    <label class="loginlabel" for="password">
                        密码:</label>
                    <span>
                        <input class="logininput ui-keyboard-input ui-widget-content ui-corner-all"   name="password" id="password" type="password" />
                    </span>
                </p>
                <button id="loginbtn" type="button" class="positive" onclick="submit1();">
                    <img src="image/key.png" alt="Announcement" />登录</button>
                <ul id="forgottenpassword">
                    <li class="boldtext">|</li>
                    <li>
                        <input type="checkbox" name="rememberMe1" id="rememberMe1"><label for="rememberMe1">记住</label>
                        <input type="text" name="rememberMe" id="rememberMe" style="display:none;"></li>
                </ul>
            </fieldset>
            </form>
    	</div>
    </div>
</body>
</html>