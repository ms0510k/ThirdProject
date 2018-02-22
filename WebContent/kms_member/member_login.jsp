<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="header">
<jsp:include page="../header.jsp"/>
</div>
<div id="body1" style="margin-top: 10px;" align="center">
<h1>로그인</h1>
<form method="post" action="<%=request.getContextPath() %>/member.do?cmd=member_login">
	아이디:<input type="text" name="email" value="${param.email }"><br>
	비밀번호:<input type="password" name="pwd" value="${param.pwd }"><br>
	<div>${errMsg }</div>
	<input type="submit" value="로그인">
</form>
</div>
</body>
</html>