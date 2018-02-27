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
String coin = (String)request.getAttribute("coin");
String type = (String)request.getAttribute("type");
if(coin == null){
	coin = "BTC";
}else{
	coin = coin.toUpperCase();
}

String coin_name = "";
switch (coin) {
case "BTC":
	coin_name = "비트코인";
	break;
case "ETH":
	coin_name = "이더리움";
	break;
case "XRP":
	coin_name = "리플";
	break;
case "BTG":
	coin_name = "비트코인골드";
	break;
case "QTUM":
	coin_name = "퀀텀";
	break;
default:
	break;
}

int memnum = (Integer)request.getAttribute("memnum");
ArrayList<exVO> eList = (ArrayList<exVO>)request.getAttribute("eList");
System.out.println("넘어오는지체크 코인 : "+coin+", 번호 : "+memnum);

%>



<div id="content1">

	<!-- 시장현황 -->
	<div class="left_content" id="content_left">
		<div class="container">


			<!-- 탭나눠주는 부분 -->
			<ul class="tabs">
			<%
			if(type.equals("buy")){
			%>
				<li class="tab-link current" data-tab="tab-1">매수하기</li>
				<li class="tab-link" data-tab="tab-2">매도하기</li>
				<% }else{%>
				
				<li class="tab-link" data-tab="tab-1">매수하기</li>
				<li class="tab-link current" data-tab="tab-2">매도하기</li>
				
				<%} %>
			</ul>



			<!-- 매수하기 탭 설정하기 -->
			<div id="tab-1" class="tab-content current"
				style="background-color: #E8F5FF;">
				<form>
					<table style="border-spacing: 40px;">
						<tr>
							<td>주문유형</td>
							<td><input type="text" value="지정가 주문" readonly="readonly"></td>
						</tr>
						<tr>
							<td>주문수량</td>
							<td><input type="text" placeholder="매수 수량을 입력하세요"> <input
								type="button" value="최대"></td>
						</tr>
						<tr>
							<td>주문가격</td>
							<td><input type="text" name="input_price"></td>
						</tr>

						<tr>
							<td>주문금액</td>
							<td><label id="order_price">0</label><small
								style="color: gray">KRW</small></td>
						</tr>
						<tr>
							<td>수수료 (약 1%)</td>
							<!-- 수수료 small 안에 넘길때 받아온 값으로 설정하기 -->
							<td><label id="order_commission">0</label><small
								style="color: gray"> 이부분 처리해 주기 </small></td>
						</tr>
						<tr>
							<td>총 매수량 (약)</td>
							<!-- 매수량 small 안에 넘길때 받아온 값으로 설정하기 -->
							<td><label id="order_amount">0</label><small
								style="color: gray"> 이부분 처리해 주기 </small></td>
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

				<form>
					<table style="border-spacing: 40px;">
						<tr>
							<td>주문유형</td>
							<td><input type="text" value="지정가 주문" readonly="readonly"></td>
						</tr>
						<tr>
							<td>주문수량</td>
							<td><input type="text" placeholder="매도 수량을 입력하세요"> <input
								type="button" value="최대"></td>
						</tr>
						<tr>
							<td>주문가격</td>
							<td><input type="text" name="input_price"></td>
						</tr>


						<tr>
							<td>수수료 (약 1%)</td>
							<td><label id="order_commission">0</label><small
								style="color: gray">KRW</small></td>
						</tr>
						<tr>
							<td>총 매도금액 (약)</td>
							<td><label id="order_amount">0</label><small
								style="color: gray">KRW</small></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="지정가 매도"
								style="width: 350px; height: 50px; background-color: #FF7A85;"></td>
						</tr>





					</table>

				</form>



			</div>


		</div>
	</div>
	<!--// 시장현황 -->




	<!-- 시장현황 -->
	<div class="right_content" id="content_right">
		<h2 style="color: #FF8000;">시장현황(<%=coin_name %>)</h2>
		<h3 id="now_price"></h3>
		<br>
		<table id="rTable">



			<tr>
				<th>1<%=coin %> 당 가격</th>

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

		var textMove = document.getElementsByName("input_price");
		textMove[0].value = id.outerText;
		textMove[1].value = id.outerText;

	}

	window.onload = proc1;

	function proc1() {
		try {
			showData(); //표에보이게하기
		} catch (e) {

		} finally {
			setTimeout("proc()", 1000); //10초후 재시작
		}
	}

	/* api에 있는 금액을 가져온다 */
	function showData() {
		/* 세션값으로 무엇으로 넘겨서 들어왔는지 봐서 해당 코인으로 현재창을 띄워준다 */

		$.get('https://api.bithumb.com/public/ticker/ALL', function(data) {
			/* 코인 관련 실시간 업데이트 부분 */
			
			
			
			var btc_now = data['data']['<%=coin%>'].closing_price;

			var now_price = document.getElementById("now_price");

			now_price.innerHTML = "현재 가격 : " + numberWithCommas(btc_now) + "원";

			/* 만약 비트코인이라면 1만 단위로 찍어준다 */
			var money = 50000;
			if(btc_now>1000000){
			for (var i = 0; i < 11; i++) {
				var labels = document.getElementById("label" + i);
				labels.innerHTML = numberWithCommas(btc_now - money);
				money -= 10000;
			}
		}else if(btc_now>10000){
			money = 5000;
			for (var i = 0; i < 11; i++) {
				var labels = document.getElementById("label" + i);
				labels.innerHTML = numberWithCommas(btc_now - money);
				money -= 1000;
			}
		}else{
			money = 500;
			for (var i = 0; i < 11; i++) {
				var labels = document.getElementById("label" + i);
				labels.innerHTML = numberWithCommas(btc_now - money);
				money -= 100;
			}
		}

		});

	}

	/* 천원단위 콤마 찍어주는 로직 */
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
</script>
