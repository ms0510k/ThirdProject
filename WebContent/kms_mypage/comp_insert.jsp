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
<h1>문의 작성</h1>
<form method="post" action="<%=request.getContextPath() %>/member.do?cmd=comp_insertOk">
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">아이디(이메일)</td>
   <td><input type="text" name="email" readonly="readonly" value=<%=email %>></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">제목</td>
   <td><input type="text" name="comtitle" ></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">내용</td>
   <td><textarea rows="5" cols="50" name="comcontent"></textarea></td>
</tr>
</table><br>
	<input type="submit" value="문의 등록">
</form>
</div>
</body>
</html>