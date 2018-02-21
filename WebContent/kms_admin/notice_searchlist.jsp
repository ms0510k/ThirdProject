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
<h1>공지사항</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=notice_insert">공지 등록</a>
<table border="1" width="500">
   <tr>
      <th>글번호</th><th>제목</th><th>조회수</th><th>날짜</th>
   </tr>
   <c:forEach var="vo" items="${list }">
   <tr>
      <td>${vo.notnum }</td>
      <td><a href="<%=request.getContextPath() %>/admin.do?cmd=detail&notnum=${vo.notnum}">${vo.nottitle }</a></td>
      <td>${vo.nothit }</td>
      <td>${vo.notdate }</td>
   </tr>
   </c:forEach>
</table>
<div>

<c:choose>
   <c:when test="${startPage>4 }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=search&pageNum=${startPage-1 }&search=<%=request.getParameter("search")%>&word=<%=request.getParameter("word")%>">이전</a>
   </c:when>
   <c:otherwise>
      이전
   </c:otherwise>
</c:choose>
   <c:forEach var="i" begin="${startPage }"  end="${endPage }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="<%=request.getContextPath()%>/admin.do?cmd=search&pageNum=${i}&search=<%=request.getParameter("search")%>&word=<%=request.getParameter("word")%>">
            <span style="color:blue">[${i }]</span>
            </a>
         </c:when>
         <c:otherwise>
            <a href="<%=request.getContextPath()%>/admin.do?cmd=search&pageNum=${i}&search=<%=request.getParameter("search")%>&word=<%=request.getParameter("word")%>">
            <span style="color:gray">[${i }]</span>
            </a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   
   <c:choose>
   <c:when test="${endPage<pageCount }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=search&pageNum=${endPage+1 }&search=<%=request.getParameter("search")%>&word=<%=request.getParameter("word")%>">다음</a>
   </c:when>
   <c:otherwise>
      다음
   </c:otherwise>
</c:choose>
</div>
<div>
<form method="post" action="<%=request.getContextPath() %>/admin.do?cmd=search">
   <select name="search" id="search" size="1">
   <option value="nottitle">제목</option>
   <option value="notcontent">내용</option>
   </select>
<input type="text" name="word">
<input type="submit" value="검색">
</form>
</div>
</body>
</html>