<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<h1 align="center">PW 찾기</h1>
<div class="header">

<form method="post"
					action="<%=request.getContextPath()%>/member.do?cmd=pwSearch" name="fr"
					onsubmit="return check()">
<table cellspacing = "1" cellpadding = "1" width = "260" border = "1" align = "center">
<tr height = "30">
	<td width = "110"  align = "center">
		이메일
	</td>
	<td width = "150"  align = "center">
		<input type = "text" name = "id" size = "18">
    </td>
</tr>
<tr height = "30">
	<td width = "110"  align = "center">
		전화 번호
	</td>
	<td width = "150"  align = "center">
		<input type = "text" name = "phone" size = "18" >  
		
	</td>
</tr>
</table>
<div align="center" style="margin-top: 10px;">
<a href="<%=request.getContextPath()%>/member.do?cmd=loginForm"	class="button black">뒤로가기</a> 
            <input type="submit" value="찾기" class="button grey"> 
  </div>
</form>
</div>

<script>

function check() {
	
	if (fr.id.value == "") {

		alert("이메일을 입력해 주세요.");

		fr.id.focus();

		return false;

	}else if(fr.phone.value == ""){
		
		alert("전화번호를 입력해 주세요.");

		fr.phone.focus();

		return false;
		
	}else{
		return true;
	}
	
	
}


</script>



