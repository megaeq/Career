function getArticleHeadDiv(article) {
	var div="";
	div+="<div class='posts'><h1 class='content-subhead'>"+article.type+"</h1>"+
	"<section class='post'><header class='post-header'>"+
	"<img class='post-avatar' alt='Tilo Mitra&#x27;s avatar' height='48' width='48' src='"+
	"http://localhost:8080/Career/image/01.png'><h2 class='post-title'>"+article.title+
	"</h2><p class='post-meta'>由<a href='#' class='post-author'>"+article.userName+"</a>编辑于 "+article.time;
	console.log(article.tagList);
	console.log(article.tagList.length);
	for(var i=0;i<article.tagList.length;i++) {
		div+="<a class='post-category post-category-"+parseInt(72*Math.random())+"' href='#'>"+
		article.tagList[i]+"</a>";
	}
	div+="</p></header><div class='post-description'><p>"+article.intro+
	"</p></div></section></div>";
	return div;
}