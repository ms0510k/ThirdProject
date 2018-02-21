<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${vo.nottitle }</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=notice">공지목록</a>
<table border="1" width="600">
<tr>
   <td>공지번호</td>
   <td>${vo.notnum }</td>
</tr>
<tr>
   <td>공지내용</td>
   <td><textarea rows="5" cols="50" readonly="readonly">${vo.notcontent }</textarea></td>
</tr>
<tr>
   <td>조회수</td>
   <td>${vo.nothit }</td>
</tr>
<tr>
   <td>작성일</td>
   <td>${vo.notdate }</td>
</tr>
</table>
<a href="<%=request.getContextPath()%>/admin.do?cmd=delete&notnum=${vo.notnum}">삭제</a>
<a href="<%=request.getContextPath()%>/admin.do?cmd=update&notnum=${vo.notnum}">수정</a>
</body>
</html>