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
})(jQuery);
 