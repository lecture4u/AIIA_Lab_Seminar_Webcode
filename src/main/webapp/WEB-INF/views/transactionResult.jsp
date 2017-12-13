<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td><strong>[Transactions Information]</strong></td>
		</tr>
		<tr>
			<td>type : ${transaction.type}</td>			
		</tr>	
		<tr>
			<td>chaincodeID : ${transaction.chaincodeID}</td>
		</tr>
		<tr>
			<td>txid : ${transaction.txid}</td>
		</tr>
		<tr>
			<td>payload : ${transaction.payload}</td>
		</tr>		
	</table>
</body>
</html>