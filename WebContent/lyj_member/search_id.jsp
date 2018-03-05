<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String email = (String) request.getAttribute("email");
String name= (String) request.getAttribute("name");
	
	try
	{
%>

<center>
<form method = "post" action = "<%=request.getContextPath()%>/header.do?cmd=main">
<%
		if(email != null)
		{
%>
			<%= name %>님의 이메일 주소는 <b><%=email  %></b> 입니다.<p>
			<input type = "submit" value = "메인으로..">
<%
		}
		else
		{
%>
			이름 또는 전화번호가 틀렸습니다.<p>
			<input type = "button" value = "다시 입력하기" onclick = 
				"javascript:gogo()">
<%
		}
%>
</form>
</center>
<%
		}catch(Exception e) {}
%>

<script>

function gogo() {
	var url = '<%=request.getContextPath()%>';
	document.location.href = url+'/member.do?cmd=idForm';
}


</script>
