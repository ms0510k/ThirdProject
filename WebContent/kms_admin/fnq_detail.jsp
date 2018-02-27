<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1 style="margin-right: 430px;">${vo.fnqtitle }</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_list" style="margin-right: 350px;">자주묻는 질문 목록</a><br><br>
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">글번호</td>
   <td>${vo.fnqnum }</td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">질문 내용</td>
   <td><textarea rows="5" cols="50" readonly="readonly">${vo.fnqcontent }</textarea></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">질문 답변</td>
   <td><textarea rows="5" cols="50" readonly="readonly">${vo.fnqresult }</textarea></td>
</tr>
</table>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_delete&fnqnum=${vo.fnqnum}">삭제</a>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_update&fnqnum=${vo.fnqnum}">수정</a>
</div>
</body>
</html>