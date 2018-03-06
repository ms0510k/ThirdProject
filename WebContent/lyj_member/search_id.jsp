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
	String id = request.getParameter("id");
		
	
%>

<body >
<center>
	<%= name %>님에 아이디는 <b><%=id %></b> 입니다.<p>
</center>
</body>

</html>