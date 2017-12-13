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
<!-- 블록 정보 조회 입력 창  -->
<h1>
	Hello Fabric BlockInfo!  
</h1>
	<table>
		<tr>
			<td><strong>[Chain Information]</strong></td>
		</tr>
		<tr>
			<td>height : ${chain.height}</td>
		</tr>
		<tr>
			<td>currentBlockHash : ${chain.currentBlockHash}</td>
		</tr>
		<tr>
			<td>previousBlockHash : ${chain.previousBlockHash}</td>
		</tr>
	</table>
	<form  method="post" action="/hello/blockResult">
	<table>
		<tr>
			<td><p>Block Id</p></td>
			<td><input type="text" placeholder="Block" name="Block" id="Block"></td>
		</tr>
		
		<tr>
			<td><br><input type="submit" value="CHECK"><br></td>	
		</tr>
	</table>
	</form>
	
	<form method="post" action="/hello/transactionResult">
	<table>
		<tr>
			<td><p>Transaction Id</p></td>
			<td><input type="text" placeholder="Transaction" name="Transaction" id="Transaction"></td>
		</tr>
		<tr>
			<td><br><input type="submit" value="CHECK"></td>
		</tr>
	</table>
	</form>

</body>
</html>