<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>아이디 찾기</title>
</head>
<body >
<div class="header">
<jsp:include page="../header.jsp"/>

<form method = "post" action = "search_id.jsp">
<table cellspacing = "1" cellpadding = "1" width = "260" border = "1" align = "center">
<tr height = "30">
      <td width = "110"  align = "center">
       이름
      </td>
      <td width = "150"  align = "center">
            <input type = "text" name = "name"  size = "18" >
      </td>
</tr>
<tr height = "30">
      <td width = "110"  align = "center">
            전화 번호
      </td>
      <td width = "150"  align = "center">
            <input type = "text" name = "phone" size = "18" >
            
      </td>
</tr>
<tr height = "30">
      <td colspan = "2" align = "middle" >
            <input type = "button" value = "뒤로가기" onclick ="javascript:window.location='login_member.jsp'">
            <input type = "submit" value = "찾기">
      </td>
</tr>
</form>
</body>
</html></x>
