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
require(["dojo/store/Memory"],
	    function(Memory){
	 
	        var employees = [
	            {name:"Jim", department:"accounting"},
	            {name:"Bill", department:"engineering"},
	            {name:"Mike", department:"sales"},
	            {name:"John", department:"sales"}
	        ];
	        var employeeStore = new Memory({data:employees, idProperty: "name"});
	        console.log(employeeStore);
	        employeeStore.query({department:"sales"}).forEach(function(employee){
	            // this is called for each employee in the sales department
	            console.log(employee.name);
	        });
	     // add a new employee
	        employeeStore.add({name:"George", department:"accounting"});
	        // remove Bill
	        employeeStore.remove("Bill");
	     // retrieve object with the name "Jim"
	        var jim = employeeStore.get("Jim");
	        // show the department property
	        console.log("Jim's department is " + jim.department);
	        // iterate through all the properties of jim:
	        for(var i in jim){
	            console.log(i, "=", jim[i]);
	        }
	        // update his department
	        jim.department = "engineering";
	        // and store the change
	        employeeStore.put(jim);
	        employeeStore.query({department:"sales"}, {
	            // the results should be sorted by department
	            sort:[{attribute:"department", descending: false}],
	            // starting at an offset of 0
	            start: 0,
	            // with a limit of 10 objects
	            count: 10
	        }).map(function(employee){
	            // return just the name, mapping to an array of names
	            return employee.name;
	        }).forEach(function(employeeName){
	            console.log(employeeName);
	        });
	 
	});

</script>
</head>
<body>

</body>
</html>