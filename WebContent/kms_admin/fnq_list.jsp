<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h1>자주 묻는 질문</h1>
<%
		if ("admin".equals(email)) {
%>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_insert&email=<%=email%>">자주묻는 질문 등록</a><br>
<%} %>
<br>
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<thead style="background-color:#FF8000 ;">
   <tr>
      <th>글번호</th><th>제목</th>
   </tr>
   </thead>
   <c:forEach var="vo" items="${list }">
   <tr>
      <td>${vo.fnqnum }</td>
      <td><a href="<%=request.getContextPath() %>/admin.do?cmd=fnq_detail&fnqnum=${vo.fnqnum}&email=<%=email%>">${vo.fnqtitle }</a></td>
   </tr>
   </c:forEach>
</table>
<div>
<br>
<c:choose>
   <c:when test="${startPage>4 }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=fnq_list&pageNum=${startPage-1 }&email=<%=email%>">이전</a>
   </c:when>
   <c:otherwise>
      이전
   </c:otherwise>
</c:choose>
   <c:forEach var="i" begin="${startPage }"  end="${endPage }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_list&pageNum=${i}&email=<%=email%>">
            <span style="color:blue">[${i }]</span>
            </a>
         </c:when>
         <c:otherwise>
            <a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_list&pageNum=${i}&email=<%=email%>">
            <span style="color:gray">[${i }]</span>
            </a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   
   <c:choose>
   <c:when test="${endPage<pageCount }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=fnq_list&pageNum=${endPage+1 }&email=<%=email%>">다음</a>
   </c:when>
   <c:otherwise>
      다음
   </c:otherwise>
</c:choose>
</div>
<br>
<div>
<form method="post" action="<%=request.getContextPath() %>/admin.do?cmd=fnq_search&email=<%=email%>">
   <select name="search" id="search" size="1">
   <option value="fnqtitle">제목</option>
   <option value="fnqcontent">질문</option>
   <option value="fnqresult">답변</option>
   </select>
<input type="text" name="word">
<input type="submit" value="검색">
</form>
</div>
</div>
</body>
</html>