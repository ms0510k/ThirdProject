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
<h1>문의 목록</h1>
<a href="<%=request.getContextPath() %>/member.do?cmd=comp_insert&email=<%=email%>">1:1문의 하기</a>
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<thead style="background-color:#FF8000 ;">
   <tr>
      <th>글번호</th><th>제목</th><th>날짜</th>
   </tr>
   </thead>
   <c:forEach var="vo" items="${list }">
   <tr>
      <td>${vo.comnum }</td>
      <td><a href="<%=request.getContextPath() %>/member.do?cmd=comp_detail&comnum=${vo.comnum}">${vo.comtitle }</a></td>
      <td>${vo.comdate }</td>
   </tr>
   </c:forEach>
</table>
<div>
<c:choose>
   <c:when test="${startPage>4 }">
      <a href="<%=request.getContextPath() %>/member.do?cmd=comp_list&pageNum=${startPage-1 }">이전</a>
   </c:when>
   <c:otherwise>
      이전
   </c:otherwise>
</c:choose>
   <c:forEach var="i" begin="${startPage }"  end="${endPage }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="<%=request.getContextPath()%>/member.do?cmd=comp_list&pageNum=${i}">
            <span style="color:blue">[${i }]</span>
            </a>
         </c:when>
         <c:otherwise>
            <a href="<%=request.getContextPath()%>/member.do?cmd=comp_list&pageNum=${i}">
            <span style="color:gray">[${i }]</span>
            </a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   
   <c:choose>
   <c:when test="${endPage<pageCount }">
      <a href="<%=request.getContextPath() %>/member.do?cmd=comp_list&pageNum=${endPage+1 }">다음</a>
   </c:when>
   <c:otherwise>
      다음
   </c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>