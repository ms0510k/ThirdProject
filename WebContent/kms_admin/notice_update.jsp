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
<h1>수정하기</h1>
<form method="post" action="<%=request.getContextPath() %>/admin.do?cmd=updateOk&email=<%=email%>">
   공지번호<br>
   <input type="text" name="notnum" readonly="readonly" value="${vo.notnum }"><br><br>
   제목<br>
   <input type="text" name="nottitle" value="${vo.nottitle }"><br><br>
   내용<br>
   <textarea rows="5" cols="50" name="notcontent">${vo.notcontent }</textarea><br><br>
   <input type="submit" value="수정">
</form>
</div>
</body>
</html>