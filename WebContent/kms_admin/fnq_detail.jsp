<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${vo.fnqtitle }</h1>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_list">자주묻는 질문 목록</a>
<table border="1" width="600">
<tr>
   <td>글번호</td>
   <td>${vo.fnqnum }</td>
</tr>
<tr>
   <td>질문 내용</td>
   <td><textarea rows="5" cols="50" readonly="readonly">${vo.fnqcontent }</textarea></td>
</tr>
<tr>
   <td>질문 답변</td>
   <td><textarea rows="5" cols="50" readonly="readonly">${vo.fnqresult }</textarea></td>
</tr>
</table>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_delete&fnqnum=${vo.fnqnum}">삭제</a>
<a href="<%=request.getContextPath()%>/admin.do?cmd=fnq_update&fnqnum=${vo.fnqnum}">수정</a>
</body>
</html>