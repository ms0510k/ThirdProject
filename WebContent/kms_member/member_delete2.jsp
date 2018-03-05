<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String email=request.getParameter("email"); %>
<div class="header">
<jsp:include page="../header.jsp"/>
</div>
<div id="body1" style="margin-top: 10px;" align="center">
<h1>정말 회원탈퇴 하시겠습니까?</h1><br><br>
<input type="button" value="예" onclick="location.href='<%=request.getContextPath() %>/member.do?cmd=member_deleteOk&email=<%=email%>'">
<input type="button" value="아니오" onclick="location.href='<%=request.getContextPath() %>/head.do?cmd=main'">
</div>
</body>
</html>