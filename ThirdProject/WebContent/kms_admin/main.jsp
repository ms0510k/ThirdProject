<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>관리자 페이지</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=notice">공지사항</a>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq">문의답변</a>
<a href="">출금 신청 대기자</a>
<a href="">탈퇴 신청 대기자</a>
<a href="">정산</a>
</body>
</html>