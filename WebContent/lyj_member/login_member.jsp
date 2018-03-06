<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/btn.css">


	<div class="header">
	</div>
	<div id="body1" style="margin-top: 10px;" align="center">
		<h1>로그인</h1>

		<form method="post"
			action="<%=request.getContextPath()%>/member.do?cmd=member_login">
			아 이 디 : <input type="text" name="email" value="${param.email }"><br>
			<br> 비밀번호 : <input type="password" name="pwd"
				value="${param.pwd }"><br>
			<div>
				<small style="color: red;">${errMsg }</small>
			</div>
			<br>
			 <input type="submit" value="로그인" class="button grey"> 
			<a href="<%=request.getContextPath()%>/member.do?cmd=joinForm"	class="button black">회원가입</a> 
			
			<!-- 임시제거 -->
			<%-- <a href="<%=request.getContextPath()%>/member.do?cmd=idForm" class="button purple">ID찾기</a>
			<a href="<%=request.getContextPath()%>/member.do?cmd=pwForm" class="button blue_alt">PW찾기</a> --%>
		</form>
	</div>

