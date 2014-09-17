<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript">
$(function() {
    $('#Button1').click(function() {
        //阻止页面的用户的活动
        $.blockUI();
    });
    $('#Button2').click(function() {
        //自定义信息内容
        $.blockUI({ message: '<h3><img src="busy.gif" /> Just a moment...</h3>' });
    });
    $('#Button3').click(function() {
        //自定义样式
        $.blockUI({ css: { backgroundColor: '#f00', color: '#fff'} });
    });
    $('#Button4').click(function() {
        //定义弹出的信息为页面的某一个元素
        $.blockUI({ message: $('#domMessage') });
    });
    $('#btnClose').click(function() {
        //关闭弹出层
        $.unblockUI();
    });
    $('#Button5').click(function() {
        //设置淡入，淡出，自动关闭时间
        $.blockUI({ fadeIn: 700, fadeOut: 700, timeout: 2000 });
    });
    //简单的气泡提示
    $.growlUI('提示', '删除成功!');
});
</script>
<title>Insert title here</title>
</head>
<body>
<ol>
        <li>阻止页面的用户的活动,不会自动消失，请刷新： $.blockUI();
            <input id="Button1" type="button" value="测试" />
        </li>
        <li>自定义消息：
            <input id="Button2" type="button" value="测试" />
        </li>
        <li>自定义样式：
            <input id="Button3" type="button" value="测试" />
        </li>
        <li>弹出指定的元素，并关闭弹出层(该层可以为隐藏)：
            <input id="Button4" type="button" value="测试" />
        </li>
        <li>设置淡入，淡出，自动关闭时间：
            <input id="Button5" type="button" value="测试" />
        </li>
    </ol>
    <div id="domMessage" style="text-align: center; width: 200px; height: 50px; border;
        1px solid #9cf; padding: 25px; display: none;">
        <h3>
            Message</h3>
        <input id="btnClose" type="button" value="关闭" />
    </div>
</body>
</html>