<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		String email = request.getParameter("email");
%>
<div class="header">
<jsp:include page="../header.jsp"/>
</div>
<div id="body1" style="margin-top: 10px;" align="center">
	<h1>자주묻는 질문 등록</h1>
<form method="post" action="<%=request.getContextPath() %>/admin.do?cmd=fnq_insertOk&email=<%=email%>">
	제목<br>
	<input type="text" name="fnqtitle"><br><br>
	질문<br>
	<textarea rows="5" cols="50" name="fnqcontent"></textarea><br><br>
	답변<br>
	<textarea rows="5" cols="50" name="fnqresult"></textarea><br><br>
	<input type="submit" value="등록">
</form>
</div>
</body>
</html>