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
		String id = (String) session.getAttribute("id");
		//보여질 페이지를 파라미터로 얻어오기
		String spage = (String) request.getAttribute("page");
		if (spage == null) {
			spage = "first.jsp";
		}
		String msg=request.getParameter("msg");
		
		if("delok".equals(msg)){
			%>
			<script type="text/javascript">
			alert("회원 탈퇴가 정상처리 되었습니다.");
			</script>
			<%
		}else if("meminok".equals(msg)){
	%>
			<script type="text/javascript">
			alert("회원 가입이 정상처리 되었습니다.");
			</script>
	<%
		}
	%>

	<div id="wrap">
		<div id="header">
			<jsp:include page="header.jsp" />
		</div>

		<div id="content">
			<jsp:include page="<%=spage%>" />
		</div>
		<div id="footer">
			<jsp:include page="footer.jsp" />

		</div>

	</div>
</body>

<script type="text/javascript">




window.onload = proc1(); 


//오른쪽 표 전용 함수 1초마다 갱신시켜준다.
function proc1() {
	try {
		showData(); //표에보이게하기
	} catch (e) {

	} finally {
		setTimeout("proc1()", 2000); //1초후 재시작
	}
}


//메인 창을 키고 미체결 상태의 거래를 현재 가격가 비교하면서 일치하면 거래로 바꿔주기
function showData(){
	
	
	$.get('https://api.bithumb.com/public/ticker/ALL', function(data) {
		var btc_now = data['data']['BTC'].closing_price;
		var eth_now = data['data']['ETH'].closing_price;
		var xrp_now = data['data']['XRP'].closing_price;
		var bch_now = data['data']['BTG'].closing_price;
		var qtum_now = data['data']['QTUM'].closing_price;
	 	var xhr=new XMLHttpRequest();
	 	//일단 임시로 이렇게 가긴함
		xhr.open('get',"http://192.168.0.31:8081/ThirdProject/buysell.do?cmd=tcheck&BTC="+btc_now+"&ETH="+eth_now+"&XRP="+xrp_now+"&BCH="+bch_now+"&QTUM="+qtum_now,true);
	
	 	xhr.send(null);

	});
	
	
	
	
	
}



</script>


</html>