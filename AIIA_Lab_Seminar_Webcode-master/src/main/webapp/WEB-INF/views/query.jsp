<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Query 입력 창  -->
<h1>
	Hello Fabric Query!  
</h1>
<form  method="post" action="/hello/queryUser">
	<table>
		<tr>
			<td><p>User</p></td>
			<td><input type="text" placeholder="User" name="User" id="User"></td>
		</tr>
		
		<tr>
			<td><br><input type="submit" value="QUERY"></td>	
		</tr>
	</table>
</form>

</body>
</html>