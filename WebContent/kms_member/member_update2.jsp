<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
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
<h1>회원정보 수정</h1>
<form method="post" action="<%=request.getContextPath() %>/member.do?cmd=member_updateOk" onsubmit="return fsubmit();">
<table border="1" width="500" class="t" style="border-collapse: collapse; text-align: center; line-height: 1.5;">
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">이름</td>
   <td><input type="text" name="name" value=${vo.name } readonly="readonly"></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">아이디(이메일)</td>
   <td><input type="text" name="email" value=${vo.email } readonly="readonly" >
   </td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">비밀번호</td>
   <td><input id="fpwd" type="password" name="pwd" value=${vo.pwd }><br>
   	<span id="pwcheck1" style="font-size: 12px; color: red"></span>
   </td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">비밀번호 확인</td>
   <td><input id="lpwd" type="password" name="pwd" value=${vo.pwd } onfocusout="checkpasswd()"><br>
   	<span id="pwcheck2" style="font-size: 12px; color: red"></span>
   </td>
   
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">연락처</td> 
   <td><input value=${vo.phone } id="phone"  type="text" name="phone" placeholder="숫자만 입력해주세요" onkeypress="return fn_press(event, 'numbers');" onkeydown="fn_press_han(this);"></td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">은행명</td>
   <td><input type="text" name="bank" value=${vo.bank }>
   </td>
</tr>
<tr>
   <td style="background-color:#FF8000 ; font-weight: bold;">계좌번호(숫자만)</td>
   <td><input value=${vo.account } id="account" type="text" name="account" placeholder="숫자만 입력해주세요"  onkeypress="return fn_press(event, 'numbers');" onkeydown="fn_press_han(this);"></td>
</tr>
</table><br>
	<input type="submit" value="수정 하기">
</form>
</div>
</body>
</html>