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
})(jQuery);
 