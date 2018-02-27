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
<div class="header">
<jsp:include page="../header.jsp"/>
</div>
<div id="body1" style="margin-top: 10px;" align="center">
<h1>승인 대기 목록</h1>
<table border="1" width="500">
   <tr>
      <th>승인대기번호</th><th>아이디(이메일)</th><th>상태</th><th>날짜</th><th>승인</th>
   </tr>
   <c:forEach var="vo" items="${list }">
   <tr>
      <td>${vo.connum }</td>
      <td>${vo.email }</td>
      <td>${vo.confirm }</td>
      <td>${vo.condate }</td>
      <td><a href="<%=request.getContextPath() %>/admin.do?cmd=conf_ok&connum=${vo.connum}">승인</a></td>
   </tr>
   </c:forEach>
</table>
<div>
<c:choose>
   <c:when test="${startPage>4 }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=conf_list&pageNum=${startPage-1 }">이전</a>
   </c:when>
   <c:otherwise>
      이전
   </c:otherwise>
</c:choose>
   <c:forEach var="i" begin="${startPage }"  end="${endPage }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="<%=request.getContextPath()%>/admin.do?cmd=conf_list&pageNum=${i}">
            <span style="color:blue">[${i }]</span>
            </a>
         </c:when>
         <c:otherwise>
            <a href="<%=request.getContextPath()%>/admin.do?cmd=conf_list&pageNum=${i}">
            <span style="color:gray">[${i }]</span>
            </a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   
   <c:choose>
   <c:when test="${endPage<pageCount }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=conf_list&pageNum=${endPage+1 }">다음</a>
   </c:when>
   <c:otherwise>
      다음
   </c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>