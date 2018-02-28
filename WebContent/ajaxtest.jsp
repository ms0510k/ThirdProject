<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

function showData() {
	var a = document.getElementById("ck").value;
	var divTag = document.getElementById("cake");
	divTag.innerHTML = a;
}




function proc() {
	try {
		showData(); //표에보이게하기
		
	} catch (e) {

	} finally {
		setTimeout("proc()", 100); //10초후 재시작
	}
}



</script>



<body onload="proc()">

<input type="text" id="ck">

<div id="cake"></div>
</body>
</html>