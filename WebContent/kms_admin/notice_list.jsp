<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>



	<div id="header" style="margin-bottom: 100px; ">
	<nav
		class="navbar  navbar-expand-sm  bg-primary  navbar-dark fixed-top">

		<!-- Brand/logo -->
		<a class="navbar-brand" href="#">4JO Exchange</a>

		<!-- Links -->
		<ul class="navbar-nav">
			<li class="nav-item"
				style="padding-right: 100px; padding-left: 100px;"><a
				class="nav-link" href="#">현재시세차트</a></li>
			<li class="nav-item"
				style="padding-right: 100px; padding-left: 100px;"><a
				class="nav-link" href="#">거래하기</a></li>

			<li class="nav-item"
				style="padding-right: 100px; padding-left: 100px;"><a
				class="nav-link" href="#">입금/출금</a></li>

			<!-- Dropdown -->
			<li class="nav-item  dropdown"
				style="padding-right: 220px; padding-left: 50px;"><a
				class="nav-link  dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"> 고객센터 </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="#">자주묻는질문</a> <a class="dropdown-item"
						href="#">1:1문의</a> 
				</div></li>

			<form class="form-inline" action="#">
				<input class="form-control" type="text" placeholder="검색어">
				<button class="btn btn-success" type="submit">찾기</button>
			</form>


			<li class="nav-item"
				style="padding-right: 20px; padding-left: 20px;"><a
				class="nav-link" href="#">로그인</a></li>

			<li class="nav-item"
				style="padding-right: 20px; padding-left: 20px;"><a
				class="nav-link" href="#">회원가입</a></li>

		</ul>
	</nav>
	<div id="left" style="float: left;">
	left
	</div>
	</div>
	<div id="body1" style="margin-top: 10px;" align="center">
	<h1>공지사항</h1><br>
	<div align="left" style="margin-left: 710px;">
<a href="<%=request.getContextPath()%>/admin.do?cmd=notice_insert">공지 등록</a>
</div><br>
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
<br>
<c:choose>
   <c:when test="${startPage>4 }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=notice&pageNum=${startPage-1 }">이전</a>
   </c:when>
   <c:otherwise>
      이전
   </c:otherwise>
</c:choose>
   <c:forEach var="i" begin="${startPage }"  end="${endPage }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="<%=request.getContextPath()%>/admin.do?cmd=notice&pageNum=${i}">
            <span style="color:blue">[${i }]</span>
            </a>
         </c:when>
         <c:otherwise>
            <a href="<%=request.getContextPath()%>/admin.do?cmd=notice&pageNum=${i}">
            <span style="color:gray">[${i }]</span>
            </a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   
   <c:choose>
   <c:when test="${endPage<pageCount }">
      <a href="<%=request.getContextPath() %>/admin.do?cmd=notice&pageNum=${endPage+1 }">다음</a>
   </c:when>
   <c:otherwise>
      다음
   </c:otherwise>
</c:choose>
</div>
<br>
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
	</div>
	<div id="left" style="float: right;">
	right
	</div>
</body>
</html>