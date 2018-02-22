<%@page import="kms.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var xhr=null;
function idcheck(){
	var email=document.getElementsByName("email")[0].value;
	if(id==""){
		var span=document.getElementById("idcheck");
		span.innerHTML="";
		return;
	}
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=callback;
	xhr.open('get','kms_member/member_idcheck.jsp?email='+email,true);
	xhr.send();
}
function callback(){
	alert("test2");
	if(xhr.readyState==4 && xhr.status==200){
		alert("success");
	}else{
		alert("no");
	}
}
</script>
</head>
<body>
<div class="header">
<jsp:include page="../header.jsp"/>
</div>
<div id="body1" style="margin-top: 10px;" align="center">
<h1>회원가입</h1>
<form method="post" action="<%=request.getContextPath() %>/member.do?cmd=member_insert">
	<table border="1" width="600">
<tr>
   <td>이름</td>
   <td><input type="text" name="name"></td>
</tr>
<tr>
   <td>아이디(이메일)</td>
   <td><input type="text" name="email" onkeyup="idcheck()">
   <span id="idcheck" style="font-size: 12px; color: red"></span></td>
</tr>
<tr>
   <td>비밀번호</td>
   <td><input type="password" name="pwd"></td>
</tr>
<tr>
   <td>비밀번호 확인</td>
   <td><input type="password" name="pwd"></td>
</tr>
<tr>
   <td>연락처</td>
   <td><input type="text" name="phone"></td>
</tr>
<tr>
   <td>은행명</td>
   <td><input type="text" name="bank"></td>
</tr>
<tr>
   <td>계좌번호(숫자만)</td>
   <td><input type="text" name="account"></td>
</tr>
</table>
	<input type="submit" value="가입하기">
</form>
</div>
</body>
</html>