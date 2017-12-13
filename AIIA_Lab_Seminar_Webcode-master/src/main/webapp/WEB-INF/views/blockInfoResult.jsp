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
<!-- 블록 정보 조회 결과 창  -->
<h1>
	Block Info Result! 
</h1>
	<table>
		<tr>
			<td><strong>[Block Information]</strong></td>
		</tr>
		<tr>
			<td>type : ${block.type}</td>			
		</tr>	
		<tr>
			<td>chaincode ID : ${block.chaincodeID}</td>
		</tr>
		<tr>
			<td>txid : ${block.txid}</td>
		</tr>
		<tr>
			<td>payload : ${block.payload}</td>
		</tr>
		<tr>
			<td>stateHash : ${block.stateHash}</td>
		</tr>
		<tr>
			<td>previousBlockHash : ${block.previousBlockHash}</td>
		</tr>
		<tr>
			<td>Transaction Timestamp seconds : ${block.txSeconds}</td>
		</tr>
		<tr>
			<td>Transaction Timestamp nanos : ${block.txNanos}</td>
		</tr>
		<tr>
			<td>localLedger CommitTimestamp seconds : ${block.LgSeconds}</td>
		</tr>
		<tr>
			<td>localLedger CommitTimestamp nanos : ${block.LgNanos}</td>
		</tr>				
			
	</table>

</body>
</html>