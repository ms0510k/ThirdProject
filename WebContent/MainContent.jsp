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
	String id = (String)session.getAttribute("id");
	//보여질 페이지를 파라미터로 얻어오기
	String spage = (String)request.getAttribute("page");
	 if(spage == null){
		spage="first.jsp";
	}
%>

<div id="wrap">
	<div id="header" >
		<jsp:include page="header.jsp"/>
	</div>
	
	<div id="content">
		<jsp:include page="<%=spage %>"/>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
		
	</div>


</div>
</body>
</html>