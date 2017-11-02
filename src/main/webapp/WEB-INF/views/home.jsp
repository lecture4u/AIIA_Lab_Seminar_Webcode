<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  

<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<!-- Invoke 창  -->
<h1>
	Hello Fabric Invoke!  
</h1>


<form  method="post" action="/insert">
	<table>
		<tr>
			<td><p>From</p></td>
			<td><input type="text" placeholder="From" name="From" id="From"></td>
		</tr>
		
		<tr>
			<td><p>To</p></td>
			<td><input type="text" placeholder="To" name="To" id="To"></td>
		</tr>
		
		<tr>
			<td><p>Amount</p></td>
			<td><input type="text" placeholder="Amount" name="Amount" id="Amount"></td>
		</tr>
		
		<tr>
			<td><br><input type="submit" value="TRADE"></td>	
		</tr>
	</table>
</form>

</body>
</html>
