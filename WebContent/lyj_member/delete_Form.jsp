<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
</head>
<div class="header">
<jsp:include page="../header.jsp"/>

<body onload = "begin()">
<form name = "myform" action = "deletePro.jsp" method = "post"
	onSubmit = "return checkIt()">
<table cellspacing = "1" cellpadding = "1" width = "260"
	border = "1" align = "center">
<tr height = "30">
	<td colspan = "2" align = "middle" >
		<font size = "+1"><b>회원 탈퇴</b></font>
	</td>
</tr>
<tr>
	<td width = "110"  align = "center">
		비밀번호
	</td>
	<td width = "150" align = "center">
		<input type = "password" name = "passwd" size = "15" maxlength = "12">
	</td>
</tr>
<tr height = "30">
	<td colspan = "2" align = "middle" >
		<input type = "submit" value = "회원탈퇴">
		<input type = "button" value = "취  소"
			onclick = "javascript:window.location='main.jsp'">
	</td>
</tr>
</table>
</form>
</body>
</html>