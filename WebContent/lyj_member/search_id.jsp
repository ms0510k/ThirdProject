<%@page import="lyj.bean.LogonDataBean"%>
<%@page import="lyj.bean.LogonDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디찾기</title>
</head>
<% request.setCharacterEncoding("utf-8"); %>

<%
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	
	try
	{
%>

<body >
<center>
<form method = "post" action = "login_member.jsp">
<%
		if(name != null)
		{
%>
			<%= name %>님에 아이디는 <b><%=  %></b> 입니다.<p>
			<input type = "submit" value = "메인으로..">
<%
		}
		else
		{
%>
			이름 또는 전화번호가 틀렸습니다.<p>
			<input type = "button" value = "다시 입력하기" onclick = 
				"javascript:window.location='search_idForm.jsp'">
<%
		}
%>
</form>
</center>
</body>
<%
		}catch(Exception e) {}
%>
</html>