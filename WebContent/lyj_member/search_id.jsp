<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디찾기</title>
</head>
<% request.setCharacterEncoding("euc-kr"); %>

<%

	String name = request.getParameter("name");
	String jumin1 = request.getParameter("jumin1");
	String jumin2 = request.getParameter("jumin2");
	
	LogonDBBean manager = LogonDBBean.getInstance();
	LogonDataBean c = manager.searchId(name, jumin1, jumin2); 
	
	try
	{
%>

<body >
<center>
<form method = "post" action = "main.jsp">
<%
		if(c != null)
		{
%>
			<%= name %>님에 아이디는 <b><%= c.getId() %></b> 입니다.<p>
			<input type = "submit" value = "메인으로..">
<%
		}
		else
		{
%>
			이름 또는 주민등록번호가 틀렸습니다.<p>
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