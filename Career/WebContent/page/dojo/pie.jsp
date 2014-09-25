<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath%>js/dojojs/dijit/themes/claro/claro.css">
<style type="text/css"> 
 @import "<%=basePath%>js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "<%=basePath%>js/dojojs/dojo/resources/dojo.css"; 
 </style> 
<script type="text/javascript" src="<%=basePath%>js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
<script type="text/javascript">
require(["dojox/charting/Chart", "dojox/charting/axis2d/Default", "dojox/charting/plot2d/Lines", "dojo/ready","dojox/charting/plot2d/Areas",
"dojo/ready","dojox/charting/plot2d/Markers"],
		  function(Chart, Default, Lines, ready,Areas,Markers){
		  ready(function(){
		    var chart1 = new Chart("simplechart", {
		        title: "Production(Quantity)",
		        titlePos: "bottom",
		        titleGap: 25,
		        titleFont: "normal normal normal 15pt Arial",
		        titleFontColor: "orange"
		      });
		    chart1.addPlot("default",{type:Lines,tension: "x",interpolate :true});
		    chart1.addAxis("x");
		    chart1.addAxis("y", {vertical: true});
		    chart1.addSeries("Series 1", [1, 2, 0, 3, 4, 5, 5, 7]);
		    chart1.render();
		  });
		});
require(["dojox/charting/Chart", "dojox/charting/plot2d/Pie", "dojox/charting/action2d/Highlight",
         "dojox/charting/action2d/MoveSlice" , "dojox/charting/action2d/Tooltip",
         "dojox/charting/themes/MiamiNice", "dojox/charting/widget/Legend", "dojo/ready"],
  function(Chart, Pie, Highlight, MoveSlice, Tooltip, MiamiNice, Legend, ready){
  ready(function(){
    var chartTwo = new Chart("chartTwo");
    chartTwo.setTheme(MiamiNice)
     .addPlot("default", {
        type: Pie,
        font: "normal normal 11pt Tahoma",
        fontColor: "black",
        labelOffset: -30,
        radius: 80
    }).addSeries("Series A", [
        {y: 4, text: "Red",   stroke: "black", tooltip: "Red is 50%"},
        {y: 2, text: "Green", stroke: "black", tooltip: "Green is 25%"},
        {y: 1, text: "Blue",  stroke: "black", tooltip: "I am feeling Blue!"},
        {y: 1, text: "Other", stroke: "black", tooltip: "Mighty <strong>strong</strong><br>With two lines!"}
    ]);
    var anim_a = new MoveSlice(chartTwo, "default");
    var anim_b = new Highlight(chartTwo, "default");
    var anim_c = new Tooltip(chartTwo, "default");
    chartTwo.render();
    var legendTwo = new Legend({chart: chartTwo}, "legendTwo");
  });
});
require(["dojox/charting/Chart", "dojox/charting/axis2d/Default", "dojox/charting/plot2d/ClusteredColumns",
         "dojo/fx/easing" , "dojox/charting/themes/Tufte", "dojo/ready"],
  function(Chart, Default, ClusteredColumns, easing, Tufte, ready){
  ready(function(){
    var animChart = new Chart("animChart");
    animChart.setTheme(Tufte).
      addAxis("x", { fixLower: "minor", fixUpper: "minor", natural: true }).
      addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major", includeZero: true }).
      addPlot("default", { type: ClusteredColumns, gap: 10, animate: { duration: 2000, easing: easing.bounceInOut } }).
      addSeries("Series A", [ 2, 1, 0.5, -1, -2 ] ).
      addSeries("Series B", [ -2, -1, -0.5, 1, 2 ] ).
      addSeries("Series C", [ 1, 0.5, -1, -2, -3 ] ).
      addSeries("Series D", [ 0.7, 1.5, -1.2, -1.25, 3 ] ).
      render();
  });
});
require(["dojox/charting/Chart3D", "dojox/charting/plot3d/Bars", "dojox/gfx3d/matrix", "dojo/ready"],
		  function(Chart3D, Bars, m, ready){
		    ready(function(){
		        var chart3d = new Chart3D("chart3d",
		            {
		                lights:   [{direction: {x: 5, y: 5, z: -5}, color: "white"}],
		                ambient:  {color:"white", intensity: 2},
		                specular: "white"
		            },
		            [m.cameraRotateXg(10), m.cameraRotateYg(-10), m.scale(0.8), m.cameraTranslate(-50, -50, 0)]
		        );

		        var bars3d_a = new Bars(500, 500, {gap: 10, material: "yellow"});
		        bars3d_a.setData([1, 2, 3, 2, 1, 2, 3, 4, 5]);
		        chart3d.addPlot(bars3d_a);

		        var bars3d_b = new Bars(500, 500, {gap: 10, material: "red"});
		        bars3d_b.setData([2, 3, 4, 3, 2, 3, 4, 5, 5]);
		        chart3d.addPlot(bars3d_b);

		        var bars3d_c = new Bars(500, 500, {gap: 10, material: "blue"});
		        bars3d_c.setData([3, 4, 5, 4, 3, 4, 5, 5, 5]);
		        chart3d.addPlot(bars3d_c);

		        chart3d.generate().render();
		    });
		});
</script>
</head>
<body class="claro">
<table>
<tr>
	<td>
		<div id="simplechart" style="width: 250px; height: 150px; margin: 5px auto 0px auto;"></div>
	</td>
	<td>
		<div id="markers" style="width: 250px; height: 150px; margin: 5px auto 0px auto;"></div>
	</td>
	<td><div id="chart3d" style="width: 500px; height: 500px; margin: 5px auto 0px auto;"></div></td>
</tr>
<tr>
	<td>
		<div id="chartTwo" style="width: 300px; height: 300px;"></div>
		<!-- <div id="legendTwo"></div> -->
	</td>
	<td>
	<div id="animChart" style="width: 300px; height: 300px;"></div>
	</td>
	
</tr>
</table>


</body>
</html>