<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css"> 
 @import "../../js/dojojs/dojox/grid/resources/tundraGrid.css"; 
 @import "../../js/dojojs/dojo/resources/dojo.css"; 
 </style> 
<script type="text/javascript" src="../../js/dojojs/dojo/dojo.js" data-dojo-config="parseOnLoad: true,  async: true,isdebug:true"></script>
<script type="text/javascript">
require([
         "dojo/request",
         "dojo/store/Memory",
         "dgrid/OnDemandGrid"
     ], function (request, Memory, OnDemandGrid) {
         request("dojojson", {
             handleAs: "json"
         }).then(function (response) {
             // Once the response is received, build an in-memory store
             // with the data
             console.log(response);
             var store = new Memory({ data: response });
              
             // Create an instance of OnDemandGrid referencing the store
             var grid = new OnDemandGrid({
                 store: store,
                 columns: {
                     first: "First Name",
                     last: "Last Name",
                     totalG: "Games Played"
                 }
             }, "grid");
          
             grid.startup();
         });
     });
</script>
</head>
<body>
<div id="grid"></div>
</body>
</html>