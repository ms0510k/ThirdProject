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
<h1 style="margin-right: 430px;">${vo.comtitle }</h1>
<a href="<%=request.getContextPath()%>/member.do?cmd=comp_list&email=${vo.email}" style="margin-right: 400px;">문의 목록</a>
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">문의번호</td>
   <td>${vo.comnum }</td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">문의내용</td>
   <td><textarea rows="5" cols="50" readonly="readonly">${vo.comcontent }</textarea></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">이메일</td>
   <td>${vo.email }</td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">작성일</td>
   <td>${vo.comdate }</td>
</tr>
</table><br>
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">답변내용</td>
   <td><textarea rows="5" cols="50" name="result" readonly="readonly">${vo.comresult }</textarea></td>
</tr>
</table><br>
</div>
</body>
</html>