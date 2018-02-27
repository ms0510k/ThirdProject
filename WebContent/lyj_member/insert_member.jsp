<%@page import="kms.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var checkEmail = false;
function fsubmit() {
    // 문제) 아이디, 패스워드, 이름이 채워지지 않은 경우는 submit 차단
    
    if(!document.getElementById("name").value){
    	alert("이름을  입력해주세요")
    	document.getElementById("name").focus();
    	return false; 
    }
    if(!idcheck()) {
        alert("아이디 를 확인해 주세요");
        document.getElementById("idcheck").focus();
        return false;   // submit 진행 차단    
    }
    if(!checkpasswd()) {
        alert("패스워드 를 확인해 주세요");
        document.getElementById("fpwd").focus();
        return false;   // submit 진행 차단    
    }
    if(!document.getElementById("phone").value){
    	alert("휴대전화 번호를 입력해 주세요")
    	document.getElementById("phone").focus();
    	return false; 
    }   
    if(!document.getElementById("account").value){
    	alert("계좌번호를 입력해주세요")
    	document.getElementById("account").focus();
    	return false; 
    }   
}
function  checkpasswd(){
	var pw1 =document.getElementById("fpwd");
	var pw2 =document.getElementById("lpwd");
	var pwcheck1 =document.getElementById("pwcheck1");
	var pwcheck2 =document.getElementById("pwcheck2");
	if(!pw1.value || !pw2.value ){
		return false;	
	}
	if(pw1.value === pw2.value){
		//pwcheck1.innerHTML=""
		pwcheck2.innerHTML=""
		return true;
	}else{ 
		//pwcheck1.innerHTML="비밀번호가 서로 다릅니다. "
		pwcheck2.innerHTML="비밀번호가 서로 다릅니다. "
		return false;
	}	
}

var xhr=null;
// 이메일 형식 확인. 
function idcheck(){
	var email=document.getElementsByName("email")[0].value;
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
	
	var span=document.getElementById("idcheck");
	
	if(regex.test(email) === false){
		span.innerHTML = "잘못된 이메일 형식 입니다. ";
		checkEmail = false;
		return false;
	}else{
		span.innerHTML = "" ;
		checkEmail = true;
		return true;
	}	
}
// 중복 확인 
function Dcheck(value){
	if(checkEmail ){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open('get','../kms_member/member_idcheck.jsp?email='+value,true);
		xhr.send();
	}
}
function callback(){
	var span=document.getElementById("idcheck");
	
	if(xhr.readyState==4 && xhr.status==200){
		var xml=xhr.responseXML;
		var using=xml.getElementsByTagName("using")[0].firstChild.nodeValue;
		if(eval(using)==true){
			span.innerHTML="사용중인 아이디입니다.";
		}else{
			span.innerHTML="사용가능한 아이디입니다.";
		}
	}
}
/* 숫자만 입력받기 */
function fn_press(event, type) {
    if(type == "numbers") {
        if(event.keyCode < 48 || event.keyCode > 57) return false;
        //onKeyDown일 경우 좌, 우, tab, backspace, delete키 허용 정의 필요
    }
}
/* 한글입력 방지 */
function fn_press_han(obj)
{
    //좌우 방향키, 백스페이스, 딜리트, 탭키에 대한 예외
    if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39
    || event.keyCode == 46 ) return;
    //obj.value = obj.value.replace(/[\a-zㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
    obj.value = obj.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
}

</script>
</head>
<body>
<div class="header">
<jsp:include page="../header.jsp"/>
</div>
<div id="body1" style="margin-top: 10px;" align="center">
<h1>회원가입</h1>
<form method="post" action="<%=request.getContextPath() %>/member.do?cmd=insert_member" onsubmit="return fsubmit();">
	<table border="1" width="600">
<tr>
   <td>이름</td>
   <td><input id="name" type="text" name="name"></td>
</tr>
<tr>
   <td>아이디(이메일)</td>
   <td><input type="text" name="email" onkeyup="idcheck()" onfocusout="Dcheck(value)" >
   <span id="idcheck" style="font-size: 12px; color: red"></span>
   </td>
</tr>
<tr>
   <td>비밀번호</td>
   <td><input id="fpwd" type="password" name="pwd">
   	<span id="pwcheck1" style="font-size: 12px; color: red"></span>
   </td>
</tr>
<tr>
   <td>비밀번호 확인</td>
   <td><input id="lpwd" type="password" name="pwd" onfocusout="checkpasswd()">
   	<span id="pwcheck2" style="font-size: 12px; color: red"></span>
   </td>
   
</tr>
<tr>
   <td>연락처</td> 
   <td><input id="phone"  type="text" name="phone" placeholder="숫자만 입력해주세요" onkeypress="return fn_press(event, 'numbers');" onkeydown="fn_press_han(this);"></td>
</tr>
<tr>
   <td>은행명</td>
   <td>
   		<select id="bnak">
				  <option value="KB국민은행">KB국민은행</option>
					<option value="신한은행">신한은행</option>
					<option value="우리은행">우리은행</option>
					<option value="KEB하나은행">KEB하나은행</option>
					<option value="케이뱅크">케이뱅크</option>
					<option value="카카오뱅크">카카오뱅크</option>
		</select>
   </td>
   
</tr>
<tr>
   <td>계좌번호(숫자만)</td>
   <td><input  id="account" type="text" name="account" placeholder="숫자만 입력해주세요"  onkeypress="return fn_press(event, 'numbers');" onkeydown="fn_press_han(this);"></td>
</tr>
</table>
	<input type="submit" value="가입하기">
</form>
</div>
</body>
</html>