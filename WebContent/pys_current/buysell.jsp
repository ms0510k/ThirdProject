<%@page import="pys.vo.exVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
#content1 {
	width: 854px;
	padding: 10px;
	top: 10px;
	left: 50%;
	overflow: hidden;
}

#content_left {
	width: 500px;
	float: left;
	padding: 10px;
}

#content_right {
	width: 300px;
	float: left;
	padding: 10px;
	margin-left: 10px;
	background-color: #FAFAD2;
	height: 570px;
}

#rTable {
	border: 1px solid #444444;
	border-collapse: collapse;
	width: 200px;
	height: 400px;
}

#rTable td, th {
	border: 1px solid #444444;
	width: 100px;
}

#rTable td {
	color: #FF8000;
}

.container {
	width: 500px;
	margin: 0 auto;
}

ul.tabs {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

ul.tabs li {
	background: none;
	color: #222;
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li.current {
	background: #ededed;
	color: #222;
}

.tab-content {
	display: none;
	background: #ededed;
	padding: 15px;
}



.tab-content.current {
	display: inherit;
}
</style>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>

<%
	String coin = (String) request.getAttribute("coin");
	int coin_amount = 0;
	String type = (String) request.getAttribute("type");
	int kor = 0;

	ArrayList<exVO> eList = (ArrayList<exVO>) request.getAttribute("eList");
	for (int i = 0; i < eList.size(); i++) {
		if (eList.get(i).getExcoin().equals("krw")) {
			kor = eList.get(i).getExmoney();
		}
		if (eList.get(i).getExcoin().equals(coin)) {
			coin_amount = eList.get(i).getExmoney();
		}
	}

	if (coin == null) {
		coin = "BTC";
	} else {
		coin = coin.toUpperCase();
	}

	String coin_name = "";
	switch (coin) {
		case "BTC" :
			coin_name = "비트코인";
			break;
		case "ETH" :
			coin_name = "이더리움";
			break;
		case "XRP" :
			coin_name = "리플";
			break;
		case "BTG" :
			coin_name = "비트코인골드";
			break;
		case "QTUM" :
			coin_name = "퀀텀";
			break;
		default :
			break;
	}

	int memnum = (Integer) request.getAttribute("memnum");

	System.out.println("넘어오는지체크 코인 : " + coin_name + ", 번호 : " + memnum + ", 수량 : " + coin_amount);
%>



<div id="content1">

	<!-- 시장현황 -->
	<div class="left_content" id="content_left">
		<div class="container">


			<!-- 탭나눠주는 부분 -->
			<ul class="tabs">
				<%
					if (type.equals("buy")) {
				%>
				<li class="tab-link current" data-tab="tab-1">매수하기</li>
				<li class="tab-link" data-tab="tab-2">매도하기</li>
				<li class="tab-link" data-tab="tab-3">거래내역</li>
				<%
					} else {
				%>

				<li class="tab-link" data-tab="tab-1">매수하기</li>
				<li class="tab-link current" data-tab="tab-2">매도하기</li>
				<li class="tab-link" data-tab="tab-3">거래내역</li>
				<%
					}
				%>
			</ul>



			<!-- 매수하기 탭 설정하기 -->
			<div id="tab-1" class="tab-content current"
				style="background-color: #E8F5FF;">
				<form method="post" action="<%=request.getContextPath() %>/buysell.do?cmd=buy" name="fr" onsubmit="return check()">
						<input type="hidden" name="val1" id="val1">
						<input type="hidden" name="val2" id="val2">
						<input type="hidden" name="val3" id="val3">
						<input type="hidden" name="coin" id="coin" value="<%=coin %>">
						<input type="hidden" name="memnum" id="memnum" value="<%=memnum %>">
					<table style="border-spacing: 40px;">
						<tr>
							<td>주문유형</td>
							<td><input type="text" value="지정가 주문" readonly="readonly"></td>
						</tr>
						<tr>
							<td>주문수량</td>
							<td><input type="text" placeholder="매수 수량을 입력하세요"
								id="buy_max" name="buy_max" > <input type="button" value="최대"
								onclick="order_buy()"></td>
						</tr>
						
						<tr>
							<td>주문가격</td>
							<td><input type="text" id="buy_input_price" name="buy_input_price"></td>
						</tr>

						<tr>
							<td>주문금액</td>
							<td><div id="buy_order_price" name="buy_order_price"></div>
								<small style="color: gray">KRW</small></td>
						</tr>
						<tr>
							<td>수수료 (약 1%)</td>
							<!-- 수수료 small 안에 넘길때 받아온 값으로 설정하기 -->
							<td><div id="buy_order_commission" name="buy_order_commission"></div>
								<small style="color: gray"><%=coin_name%></small></td>
						</tr>
						<tr>
							<td>총 매수량 (약)</td>
							<!-- 매수량 small 안에 넘길때 받아온 값으로 설정하기 -->
							<td><div id="buy_order_amount" name="buy_order_amount"></div>
								<small style="color: gray"><%=coin_name%></small></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="지정가 매수"
								style="width: 350px; height: 50px; background-color: #96A5FF;"></td>
						</tr>





					</table>

				</form>



			</div>


			<!-- 매도하기 탭 설정하기 -->
			<div id="tab-2" class="tab-content"
				style="background-color: #FFE3EE;">

				
				<form method="post" action="<%=request.getContextPath() %>/buysell.do?cmd=sell" name="fr" onsubmit="return check1()">
						<input type="hidden" name="val_1" id="val_1">
						<input type="hidden" name="val_2" id="val_2">
						<input type="hidden" name="coin" id="coin" value="<%=coin %>">
						<input type="hidden" name="memnum" id="memnum" value="<%=memnum %>">
				
					<table style="border-spacing: 40px;">
						<tr>
							<td>주문유형</td>
							<td><input type="text" value="지정가 주문" readonly="readonly"></td>
						</tr>
						<tr>
							<td>주문수량</td>
							<td><input type="text" placeholder="매도 수량을 입력하세요"
								id="sell_max" name="sell_max"> <input type="button" value="최대"
								onclick="order_sell()"></td>
						</tr>
						<tr>
							<td>주문가격</td>
							<td><input type="text" id="sell_input_price" name="sell_input_price"></td>
						</tr>


						<tr>
							<td>수수료 (약 1%)</td>
							<td><div id="sell_order_commission"></div>
								<small style="color: gray">KRW</small></td>
						</tr>
						<tr>
							<td>총 매도금액 (약)</td>
							<td><div id="sell_order_amount"></div>
								<small style="color: gray">KRW</small></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="지정가 매도"
								style="width: 350px; height: 50px; background-color: #FF7A85;"></td>
						</tr>





					</table>

				</form>



			</div>



		<!-- 거래내역 탭 설정하기 -->
			<div id="tab-3" class="tab-content"
				style="background-color: #FFE3EE;">

				
				



					


			</div>







		</div>
	</div>
	<!--// 시장현황 -->




	<!-- 시장현황 -->
	<div class="right_content" id="content_right">
		<h2 style="color: #FF8000;">
			시장현황(<%=coin_name%>)
		</h2>
		<h3 id="now_price"></h3>
		<br>
		<table id="rTable">



			<tr>
				<th>1<%=coin%> 당 가격
				</th>

			</tr>
			<tr>
				<td><label id="label0" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label1" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label2" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label3" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label4" onclick="gogo(this)"></label></td>

			</tr>
			<tr style="background-color: #FFEB5A">
				<td><label id="label5" onclick="gogo(this)"></label></td>

			</tr>

			<tr>
				<td><label id="label6" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label7" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label8" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label9" onclick="gogo(this)"></label></td>

			</tr>
			<tr>
				<td><label id="label10" onclick="gogo(this)"></label></td>

			</tr>
		</table>
	</div>
	<!--// 시장현황 -->
</div>



<script type="text/javascript">
	$(document).ready(function() {

		$('ul.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');

			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#" + tab_id).addClass('current');
		});

	});

	/* 금액이 들어있는 라벨이 클릭이 되면 해당 금액을 주문 가격으로 넣는다 */
	function gogo(id) {

		var textMove = document.getElementById("buy_input_price");
		var textMove1 = document.getElementById("sell_input_price");
		textMove.value = id.outerText;
		textMove1.value = id.outerText;

	}

	window.onload = callFunction(); 

	 function callFunction(){
		 proc1();//표 전용
		 proc2();//text전용
	 
	 }
	
	
	 //오른쪽 표 전용 함수 1초마다 갱신시켜준다.
	function proc1() {
		try {
			showData(); //표에보이게하기
		} catch (e) {

		} finally {
			setTimeout("proc1()", 1000); //1초후 재시작
		}
	}

	 
	 //왼쪽 텍스트 갱신전용 0.1초마다 텍스트를 보면서 갱신시켜준다.
	function proc2() {
		try {
			showData_left(); //표에보이게하기
			showData_left1(); 
		} catch (e) {

		} finally {
			setTimeout("proc2()", 100); //0.1초후 재시작
		}
	}
	 
	 
	/* api에 있는 금액을 가져온다 */
	function showData() {
		/* 세션값으로 무엇으로 넘겨서 들어왔는지 봐서 해당 코인으로 현재창을 띄워준다 */

		$.get('https://api.bithumb.com/public/ticker/ALL', function(data) {
			/* 코인 관련 실시간 업데이트 부분 */
			
			
			var coin = '<%=coin%>';	
			var btc_now = data['data'][coin].closing_price;

			var now_price = document.getElementById("now_price");

			now_price.innerHTML = "현재 가격 : " + numberWithCommas(btc_now) + "원";

			/* 만약 비트코인이라면 1만 단위로 찍어준다 */
			var money = 50000;
			if (btc_now > 1000000) {
				for (var i = 0; i < 11; i++) {
					var labels = document.getElementById("label" + i);
					labels.innerHTML = numberWithCommas(btc_now - money);
					money -= 10000;
				}
			} else if (btc_now > 10000) {
				money = 5000;
				for (var i = 0; i < 11; i++) {
					var labels = document.getElementById("label" + i);
					labels.innerHTML = numberWithCommas(btc_now - money);
					money -= 1000;
				}
			} else {
				money = 500;
				for (var i = 0; i < 11; i++) {
					var labels = document.getElementById("label" + i);
					labels.innerHTML = numberWithCommas(btc_now - money);
					money -= 100;
				}
			}

		});

	}

	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	function showData_left() {
		var buy1 = document.getElementById("buy_max");//수량
		var buy2 = document.getElementById("buy_input_price");//개당가격
			var buy3 = document.getElementById("buy_order_price");//주문금액
			var buy4 = document.getElementById("buy_order_commission");//수수료
			var buy5 = document.getElementById("buy_order_amount");//총매수량

		if (buy1.value.length != 0 && buy2.value.length != 0) {
			var buysum = buy1.value * removeComma(buy2.value);
			buy3.innerHTML = buysum.toFixed(0);
			buy4.innerHTML = (buy1.value * 0.01).toFixed(5);
			buy5.innerHTML = (buy1.value - (buy1.value * 0.01)).toFixed(5);
		}

		 if(buy1.value.length == 0 || buy2.value.length == 0){
			buy3.innerHTML = 0;
			buy4.innerHTML = 0;
			buy5.innerHTML = 0;
		}
		 
		
	}

	function showData_left1() {
		var sell1 = document.getElementById("sell_max");//수량
		var sell2 = document.getElementById("sell_input_price");//개당가격
			var sell4 = document.getElementById("sell_order_commission");//수수료
			var sell5 = document.getElementById("sell_order_amount");//총매도액
		
		
		if (sell1.value.length != 0 && sell2.value.length != 0) {

			var sellsum = sell1.value * removeComma(sell2.value);
			sell4.innerHTML = (sellsum * 0.01).toFixed(0);
			sell5.innerHTML = (sellsum - (sellsum * 0.01)).toFixed(0);
		}
		
		 if(sell1.value.length == 0 || sell2.value.length == 0){
				sell4.innerHTML = 0;
				sell5.innerHTML = 0;
			}
	}

	function order_buy() {
		//채워야 되는위치
		var order1 = document.getElementById("buy_max");

		//가격 가져오기
		var amount = document.getElementById("buy_input_price");
		if (amount.value.length == 0) {
			alert("먼저 가격을 입력해 주세요");
			amount.focus();
		} else {
			var cal =
<%=kor%>
	/ removeComma(amount.value);
			cal = cal.toFixed(5);
			order1.value = cal;

		}
	}

	function order_sell() {
		//채워야 되는위치
		var order1 = document.getElementById("sell_max");

		//가격 가져오기
		var amount = document.getElementById("sell_input_price");
		if (amount.value.length == 0) {
			alert("먼저 가격을 입력해 주세요");
			amount.focus();
		} else {
			order1.value =
<%=coin_amount%>
	;

		}
	}

	/* 천원단위 콤마 찍어주는 로직 */
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	//콤마 지워주는 로직
	function removeComma(n) {
		var txtNumber = '' + n;
		return txtNumber.replace(/(,)/g, "");
	}
	
	
	
	
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	//매수하기 체크하기 값을 비교하면서 정상이면 ok 비정상적 거래면 no 보내기
	function check() {
		
		var order1 = document.getElementById("buy_max");
		if (order1.value.length == 0) {
			alert("먼저 수량을 입력해 주세요");
			order1.focus();
			return false;
		}
		//가격 가져오기
		var amount = document.getElementById("buy_input_price");
		if (amount.value.length == 0) {
			alert("먼저 가격을 입력해 주세요");
			amount.focus();
			return false;
		}
		
			var kor = <%=kor%>;
			var price = document.getElementById("buy_order_price").innerHTML;
			
			
		  if(price>kor) {

		    alert("현재 소지하고 계신 krw를 초과합니다.");
			alert("현재 소유하고 계신 krw은 "+kor+"원 입니다.");


		    return false;

		  }

		  else if(kor == 0) {

		    alert("현재 소유하고 있는 krw 가 없습니다. 충전해 주세요.");

		    return false;

		  }else {
			  //div 영영의 값은 httpservlet 으로 못넘기므로 히든태그에 담아서 넘겨준다.
			  amount.value =  removeComma(amount.value);
			  document.getElementById("val1").value = document.getElementById("buy_order_price").innerHTML;
			  document.getElementById("val2").value = document.getElementById("buy_order_commission").innerHTML;
			  document.getElementById("val3").value = document.getElementById("buy_order_amount").innerHTML;
		  alert("매수예약을 신청하였습니다.");
		  return true;}

		}
	
	
	
	//매도 체크부분
	function check1() {
		
		var order1 = document.getElementById("sell_max");
		if (order1.value.length == 0) {
			alert("먼저 수량을 입력해 주세요");
			order1.focus();
			return false;
		}
		//가격 가져오기
		var amount = document.getElementById("sell_input_price");
		if (amount.value.length == 0) {
			alert("먼저 가격을 입력해 주세요");
			amount.focus();
			return false;
		}
		
		
		var coin_amount = <%=coin_amount%>;
		var price = document.getElementById("sell_max").value;
		
		
	  if(price>coin_amount) {

	    alert("현재 소지하고 계신 수량를 초과합니다.");
		alert("현재 소유하고 계신 수량은 "+coin_amount+"개 입니다.");


	    return false;

	  }

	  else if(coin_amount == 0) {

	    alert("현재 소유하고 있는 코인이 없습니다. 먼저 코인을 구매해 주세요.");

	    return false;

	  }else {
	  alert("매도예약을 신청하였습니다.");
	  //div 영영의 값은 httpservlet 으로 못넘기므로 히든태그에 담아서 넘겨준다.
	  amount.value =  removeComma(amount.value);
	  document.getElementById("val_1").value = document.getElementById("sell_order_commission").innerHTML;
	  document.getElementById("val_2").value = document.getElementById("sell_order_amount").innerHTML;
	  return true;}

	}
	
	
	
	
	
	
	
	
	
	
	
	
</script>
