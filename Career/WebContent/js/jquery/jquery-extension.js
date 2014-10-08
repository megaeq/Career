 (function ($) {
	 //获取url参数
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
    //添加下拉框
    $.addSelect=function(div,id,selectList) {
    	var s = "<select id='"+id+"'>";
    	for(var i=0;i<selectList.length;i++) {
    		s+="<option value='"+selectList[i].id+"'";
    		if(selectList[i].selected) {
    			s+=" selected='true'";
    		}
    		s+=">"+selectList[i].label+"</option>";
    	}
    	s+="</select>";
    	$("#"+div)[0].innerHTML=s;
    }
    //<small>Page 1 of 3</small> <span>1</span><a href="#">2</a><a href="#">3</a> <a href="#">&raquo;</a>
    $pages = function(div,count,pagenow) {
    	var s = "";
    	s+="<small>第"+pagenow+"/"+count+"页";
    	for(var i=0;i<count;i++) {
    	}
    }
    $initmenu = function(orderno) {
    	var basePath="http://localhost:8080/Career/";
    	var array = new Array();
    	var s1="";
    	var s2="";
    	var s3="";
    	array.push("<a href='"+basePath+"index.jsp'><span>首页</span></a>",
		"<a href='"+basePath+"page/myinfo/accountList.jsp'><span>我的账户</span></a>",
		"<a href='"+basePath+"page/myinfo/planList.jsp'><span>计划</span></a>",
		"<a href='"+basePath+"page/article/articleList.jsp'><span>文章</span></a>");
		for(var i=0;i<array.length;i++) {
			if(i==orderno) {
				s2+="<li class='active'>"+array[i]+"</li>";
			} else {
				s2+="<li>"+array[i]+"</li>";
			}
		}
		s1="<div class='header_resize'><div class='menu_nav'><ul>";
		s3="</ul></div><div class='clr'></div></div>";
		var s=s1+s2+s3;
		$("#menu")[0].innerHTML=s;
		 $('.menu_nav ul, .menu_nav ul li a, .content .mainbar h2, .content .sidebar h2, .content .mainbar .article, .content .sidebar .gadget, .post_content a.rm, .content p.pages span, .content p.pages a').css({"border-radius":"6px", "-moz-border-radius":"6px", "-webkit-border-radius":"6px"});
    	Cufon.replace('h1, h2, h3, h4, h5, h6, .post_content a.rm, .menu_nav ul li a', { hover: true });
    }
    
})(jQuery);
 