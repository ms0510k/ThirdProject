<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>공지사항</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=notice_insert">공지 등록</a>
<table border="1" width="500">
	<tr>
		<th>글번호</th><th>제목</th><th>조회수</th><th>날짜</th>
	</tr>
</table>
</body>
</html>