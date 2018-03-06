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
<h1 style="margin-right: 430px;">${vo.nottitle }</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=notice&email=<%=email%>" style="margin-right: 430px;">공지목록</a><br>
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">공지번호</td>
   <td>${vo.notnum }</td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">공지내용</td>
   <td><textarea rows="30" cols="50" readonly="readonly">${vo.notcontent }</textarea></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">조회수</td>
   <td>${vo.nothit }</td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">작성일</td>
   <td>${vo.notdate }</td>
</tr>
</table>
<%
		if ("admin".equals(email)) {
%>
<a href="<%=request.getContextPath()%>/admin.do?cmd=delete&notnum=${vo.notnum}&email=<%=email%>">삭제</a>
<a href="<%=request.getContextPath()%>/admin.do?cmd=update&notnum=${vo.notnum}&email=<%=email%>">수정</a>
<%} %>
</div>
</body>
</html>