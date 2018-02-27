<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>



<style>

/*!
 * Copyright (c) 2017 NAVER Corp.
 * billboard.js project is licensed under the MIT license
 * 
 * billboard.js, JavaScript chart library
 * http://naver.github.io/billboard.js/
 * 
 * @version 1.2.0
 */
/*-- Chart --*/
.bb svg {
	font: 10px sans-serif;
	-webkit-tap-highlight-color: transparent;
}

.bb path, .bb line {
	fill: none;
	stroke: #000;
}

.bb text {
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

.bb-legend-item-tile, .bb-xgrid-focus, .bb-ygrid, .bb-event-rect,
	.bb-bars path {
	shape-rendering: crispEdges;
}

.bb-chart-arc path {
	stroke: #fff;
}

.bb-chart-arc text {
	fill: #fff;
	font-size: 13px;
}

/*-- Axis --*/
/*-- Grid --*/
.bb-grid line {
	stroke: #aaa;
}

.bb-grid text {
	fill: #aaa;
}

.bb-xgrid, .bb-ygrid {
	stroke-dasharray: 3 3;
}

/*-- Text on Chart --*/
.bb-text.bb-empty {
	fill: #808080;
	font-size: 2em;
}

/*-- Line --*/
.bb-line {
	stroke-width: 1px;
}

/*-- Point --*/
.bb-circle._expanded_ {
	stroke-width: 1px;
	stroke: white;
}

.bb-selected-circle {
	fill: white;
	stroke-width: 2px;
}

/*-- Bar --*/
.bb-bar {
	stroke-width: 0;
}

.bb-bar._expanded_ {
	fill-opacity: 0.75;
}

/*-- Focus --*/
.bb-target.bb-focused {
	opacity: 1;
}

.bb-target.bb-focused path.bb-line, .bb-target.bb-focused path.bb-step {
	stroke-width: 2px;
}

.bb-target.bb-defocused {
	opacity: 0.3 !important;
}

/*-- Region --*/
.bb-region {
	fill: steelblue;
	fill-opacity: .1;
}

/*-- Brush --*/
.bb-brush .extent {
	fill-opacity: .1;
}

/*-- Select - Drag --*/
/*-- Legend --*/
.bb-legend-item {
	font-size: 12px;
}

.bb-legend-item-hidden {
	opacity: 0.15;
}

.bb-legend-background {
	opacity: 0.75;
	fill: white;
	stroke: lightgray;
	stroke-width: 1;
}

/*-- Title --*/
.bb-title {
	font: 14px sans-serif;
}

/*-- Tooltip --*/
.bb-tooltip-container {
	z-index: 10;
}

.bb-tooltip {
	border-collapse: collapse;
	border-spacing: 0;
	background-color: #fff;
	empty-cells: show;
	opacity: 0.9;
	-webkit-box-shadow: 7px 7px 12px -9px #777777;
	-moz-box-shadow: 7px 7px 12px -9px #777777;
	box-shadow: 7px 7px 12px -9px #777777;
}

.bb-tooltip tr {
	border: 1px solid #CCC;
}

.bb-tooltip th {
	background-color: #aaa;
	font-size: 14px;
	padding: 2px 5px;
	text-align: left;
	color: #FFF;
}

.bb-tooltip td {
	font-size: 13px;
	padding: 3px 6px;
	background-color: #fff;
	border-left: 1px dotted #999;
}

.bb-tooltip td>span, .bb-tooltip td>svg {
	display: inline-block;
	width: 10px;
	height: 10px;
	margin-right: 6px;
}

.bb-tooltip td.value {
	text-align: right;
}

/*-- Area --*/
.bb-area {
	stroke-width: 0;
	opacity: 0.2;
}

/*-- Arc --*/
.bb-chart-arcs-title {
	dominant-baseline: middle;
	font-size: 1.3em;
}

.bb-chart-arcs .bb-chart-arcs-background {
	fill: #e0e0e0;
	stroke: none;
}

.bb-chart-arcs .bb-chart-arcs-gauge-unit {
	fill: #000;
	font-size: 16px;
}

.bb-chart-arcs .bb-chart-arcs-gauge-max {
	fill: #777;
}

.bb-chart-arcs .bb-chart-arcs-gauge-min {
	fill: #777;
}

.bb-chart-arc .bb-gauge-value {
	fill: #000;
}

.g_table_list {
	width: 100%
}

.g_table_list tr th {
	background-color: #f7f7f7;
	text-align: center;
	border: 1px solid #d5d5d5
}

.g_table_list tr td {
	border: 1px solid #d5d5d5;
	text-align: center
}

.g_table_list tr .gray {
	background-color: #e3e5e7
}

.g_table_list tr .org {
	background-color: #fbf1e2
}

.g_table_list tr .fisrt {
	border-left: 1px solid #666876
}

.g_table_list tr .top {
	border-top: 1px solid #666876;
	border-bottom: 1px solid #666876
}

.g_table_list tr .end {
	border-right: 1px solid #666876
}

.g_table_list tr .gend {
	border-bottom: 1px solid #666876
}

.g_table_list tr .left {
	padding-left: 15px;
	text-align: left
}

.g_table_list tr .left02 {
	padding-left: 7px;
	text-align: left
}

.g_table_list tr .right {
	padding-right: 15px;
	text-align: right
}

.g_table_list tr .center {
	padding: 0;
	text-align: center
}

.g_table_list tr .group_end {
	border-bottom: 2px solid #d5d5d5
}

.g_table_list .sell td {
	color: #0e7019
}

.g_table_list .buying td {
	color: #f6742d
}

.g_table {
	width: 100%;
	border-top: 1px solid #111d2c
}

.g_table tr th {
	background-color: #f7f7f7;
	text-align: left;
	padding: 4px 20px;
	border-bottom: 1px solid #cbcbcb;
	line-height: 30px
}

.g_table tr th.right {
	padding-right: 15px;
	text-align: right
}

.g_table tr td {
	padding: 4px 20px;
	border-bottom: 1px solid #cbcbcb;
	line-height: 30px
}

.g_table .left {
	padding-left: 15px;
	text-align: left
}

.g_table .right {
	padding-right: 15px;
	text-align: right
}

.g_table .center {
	padding-left: 0;
	padding-right: 0;
	text-align: center
}

/*# sourceMappingURL=billboard.css.map*/
</style>


<%
		String email = (String) session.getAttribute("email");
	%>



<input type="hidden" id="coin_kind">
<div id="main" style="width: 1000px;">
	<h3 id="lastupdate"></h3>
	<table class="g_table" id="tableAsset">

		<thead>
			<tr>
				<th class="center">코인</th>
				<th><span class="sorting">실시간 시세</span></th>
				<th><span class="sorting">변동률 (%)</span></th>
				<th><span class="sorting">거래금액</span></th>
				<th>구매 / 판매</th>
			</tr>
		</thead>
		
		
		<!-- 로그인 했을때랑 안했을때랑 나누어서 처리하기  1.로그인 했을때-->
		<%if(email != null){ %>
		<tbody>
			<tr data-coin="btc" title="비트코인 (BTC) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/비트코인.jpg"
					onclick="gogo1('btc')"><a onclick="gogo1('btc')">비트코인</a></td>
				<td><strong id="btc_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="btc_change"></strong>
				<td><strong id="btc_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="marketprice.do?cmd=buy&coin=btc" class="btn btn_coin_buy"
					data-type="1" data-coin="BTC"> 구매</a><a href="marketprice.do?cmd=sell&coin=btc"
					class="btn btn_coin_sell" data-type="2" data-coin="BTC"> 판매</a></td>
			</tr>
			<tr data-coin="eth" title="이더리움 (ETH) 정보를 확인하시려면 클릭하세요">
				<td><img src="img/이더리움.jpg" onclick="gogo1('eth')"><a
					onclick="gogo1('eth')">이더리움</a></td>
				<td><strong id="eth_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="eth_change"></strong>
				<td><strong id="eth_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="marketprice.do?cmd=buy&coin=eth" class="btn btn_coin_buy"
					data-type="1" data-coin="ETH"> 구매</a><a href="marketprice.do?cmd=sell&coin=eth"
					class="btn btn_coin_sell" data-type="2" data-coin="ETH"> 판매</a></td>
			</tr>

			<tr data-coin="xrp" title="리플 (XRP) 정보를 확인하시려면 클릭하세요">
				<td><img src="img/리플.jpg" onclick="gogo1('xrp')"><a
					onclick="gogo1('xrp')">리플</a></td>
				<td><strong id="xrp_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="xrp_change"></strong>
				<td><strong id="xrp_amount" class="sort_change"
					data-sorting="0"></strong></td>

				<td><a href="marketprice.do?cmd=buy&coin=xrp" class="btn btn_coin_buy"
					data-type="1" data-coin="XRP"> 구매</a><a href="marketprice.do?cmd=sell&coin=xrp"
					class="btn btn_coin_sell" data-type="2" data-coin="XRP"> 판매</a></td>
			</tr>
			<tr data-coin="bch" title="비트코인 골드 (BTG) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/비트코인캐쉬.jpg"
					onclick="gogo1('bch')"><a onclick="gogo1('bch')">비트코인골드</a></td>
				<td><strong id="bch_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="bch_change"></strong>
				<td><strong id="bch_amount" class="sort_change"
					data-sorting="0"></strong></td>

				<td><a href="marketprice.do?cmd=buy&coin=btg" class="btn btn_coin_buy"
					data-type="1" data-coin="BCH"> 구매</a><a href="marketprice.do?cmd=sell&coin=btg"
					class="btn btn_coin_sell" data-type="2" data-coin="BCH"> 판매</a></td>
			</tr>

			<tr data-coin="qtum" title="퀀텀 (QTUM) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/퀀텀.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">퀀텀</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="marketprice.do?cmd=buy&coin=qtum" class="btn btn_coin_buy"
					data-type="1" data-coin="QTUM"> 구매</a><a href="marketprice.do?cmd=sell&coin=qtum"
					class="btn btn_coin_sell" data-type="2" data-coin="QTUM"> 판매</a></td>
			</tr>




			<tr data-coin="msc" title="민수코인 (msc) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/민수코인.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">민수코인</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:;" class="btn btn_coin_buy"
					data-type="1" data-coin="QTUM"> 구매</a><a href="javascript:;"
					class="btn btn_coin_sell" data-type="2" data-coin="QTUM"> 판매</a></td>
			</tr>


			<tr data-coin="sunc" title="민승코인 (sunc) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/민승코인.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">민승코인</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:;" class="btn btn_coin_buy"
					data-type="1" data-coin="QTUM"> 구매</a><a href="javascript:;"
					class="btn btn_coin_sell" data-type="2" data-coin="QTUM"> 판매</a></td>
			</tr>

		</tbody>
		
		
		
		<!-- 분기처리2 로그인 안했을 때 -->
		<%}else{ %>
		
		
		<tbody>
			<tr data-coin="btc" title="비트코인 (BTC) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/비트코인.jpg"
					onclick="gogo1('btc')"><a onclick="gogo1('btc')">비트코인</a></td>
				<td><strong id="btc_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="btc_change"></strong>
				<td><strong id="btc_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="BTC"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="BTC"> 판매</a></td>
			</tr>
			<tr data-coin="eth" title="이더리움 (ETH) 정보를 확인하시려면 클릭하세요">
				<td><img src="img/이더리움.jpg" onclick="gogo1('eth')"><a
					onclick="gogo1('eth')">이더리움</a></td>
				<td><strong id="eth_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="eth_change"></strong>
				<td><strong id="eth_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="ETH"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="ETH"> 판매</a></td>
			</tr>

			<tr data-coin="xrp" title="리플 (XRP) 정보를 확인하시려면 클릭하세요">
				<td><img src="img/리플.jpg" onclick="gogo1('xrp')"><a
					onclick="gogo1('xrp')">리플</a></td>
				<td><strong id="xrp_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="xrp_change"></strong>
				<td><strong id="xrp_amount" class="sort_change"
					data-sorting="0"></strong></td>

				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="XRP"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="XRP"> 판매</a></td>
			</tr>
			<tr data-coin="bch" title="비트코인 골드 (BTG) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/비트코인캐쉬.jpg"
					onclick="gogo1('bch')"><a onclick="gogo1('bch')">비트코인골드</a></td>
				<td><strong id="bch_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="bch_change"></strong>
				<td><strong id="bch_amount" class="sort_change"
					data-sorting="0"></strong></td>

				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="BCH"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="BCH"> 판매</a></td>
			</tr>

			<tr data-coin="qtum" title="퀀텀 (QTUM) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/퀀텀.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">퀀텀</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="QTUM"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="QTUM"> 판매</a></td>
			</tr>




			<tr data-coin="msc" title="민수코인 (msc) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/민수코인.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">민수코인</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="QTUM"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="QTUM"> 판매</a></td>
			</tr>


			<tr data-coin="sunc" title="민승코인 (sunc) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/민승코인.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">민승코인</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td><a href="javascript:gogo()" class="btn btn_coin_buy"
					data-type="1" data-coin="QTUM"> 구매</a><a href="javascript:gogo()"
					class="btn btn_coin_sell" data-type="2" data-coin="QTUM"> 판매</a></td>
			</tr>

		</tbody>
		
		
		
		
		
		<%} %>
	</table>

</div>




<br>
<br>
<h1 id="titleCoin" style="color: red;"></h1>
<div id="buttonP">
	<select id="timeLine" style="visibility: hidden">
		<option value="minutes/1">==시간대==</option>
		<option value="minutes/1">1분</option>
		<option value="minutes/3">3분</option>
		<option value="minutes/5">5분</option>
		<option value="minutes/10">10분</option>
		<option value="minutes/15">15분</option>
		<option value="minutes/30">30분</option>
		<option value="minutes/60">1시간</option>
		<option value="minutes/240">4시간</option>
		<option value="days">1일</option>
	</select>
</div>
<div id="chart" style="width: 1500px;"></div>






<!-- 바디종료 스크립트 부분 -->
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="js/billboard.js"></script>
<script>

//세션에 아이디 값이 존재하지 않는 경우 로그인 페이지로 보낸다
function gogo() {
	alert("로그인이 필요합니다.");
	location.href="/ThirdProject/kms_member/member_login.jsp";
}



	var minsu = 100000000000;
	
	var minsu1 = {};








	 function callFunction()

	 {
	proc();
	 <%String coin = request.getParameter("coin");
		if (coin != null) {%>
	 gogo1('<%=coin%>');
	 <%}%>
	
	
	 }
	 
	 



 window.onload = callFunction(); 

	function proc() {
		try {
			showData(); //표에보이게하기
			CurrentTime(); // 갱신 시간
		} catch (e) {

		} finally {
			setTimeout("proc()", 1000); //10초후 재시작
		}
	}

	/* 표에 실시간 갱신표 나타내기 */
	function showData() {

		/* https://crix-api-endpoint.upbit.com/v1/crix/candles/minutes/240?code=CRIX.UPBIT.KRW-ADA&count=1 */
		$.get('https://api.bithumb.com/public/ticker/ALL', function(data) {
			/*  var coinone_btc = parseFloat(data['btc']['last']); */

			/* 비트코인 관련 실시간 업데이트 부분 */
			var btc_now = data['data']['BTC'].closing_price;
			var btc_first = data['data']['BTC'].opening_price;
			var btc_amount = data['data']['BTC'].units_traded;
			var btc_change = (btc_now / btc_first) - 1;
			btc_change = btc_change.toFixed(2);
			var btc_total = numberWithCommas(Math.round(btc_amount * btc_now));
			var ii1 = document.getElementById('btc_now');
			var ii2 = document.getElementById('btc_change');
			var ii3 = document.getElementById('btc_amount');
			ii1.innerHTML = numberWithCommas(btc_now) + "원";
			if ((btc_now - btc_first) > 0) {
				ii2.style.color = "red";
				ii2.innerHTML = "+" + numberWithCommas(btc_now - btc_first)
						+ "원(+" + btc_change + "%)";
			} else {
				ii2.style.color = "blue";
				ii2.innerHTML = numberWithCommas(btc_now - btc_first) + "원("
						+ btc_change + "%)";
			}
			ii3.innerHTML = btc_total + "원";

			/* 이더리움 관련 실시간 업데이트 부분 */
			var eth_now = data['data']['ETH'].closing_price;
			var eth_first = data['data']['ETH'].opening_price;
			var eth_amount = data['data']['ETH'].units_traded;
			var eth_change = (eth_now / eth_first) - 1;
			eth_change = eth_change.toFixed(2);
			var eth_total = numberWithCommas(Math.round(eth_amount * eth_now));
			var eth1 = document.getElementById('eth_now');
			var eth2 = document.getElementById('eth_change');
			var eth3 = document.getElementById('eth_amount');
			eth1.innerHTML = numberWithCommas(eth_now) + "원";
			if ((eth_now - eth_first) > 0) {
				eth2.style.color = "red";
				eth2.innerHTML = "+" + numberWithCommas(eth_now - eth_first)
						+ "원(+" + eth_change + ")";
			} else {
				eth2.style.color = "blue";
				eth2.innerHTML = numberWithCommas(eth_now - eth_first) + "원("
						+ eth_change + "%)";
			}
			eth3.innerHTML = eth_total + "원";

			/* 리플 관련 실시간 업데이트 부분 */
			var xrp_now = data['data']['XRP'].closing_price;
			var xrp_first = data['data']['XRP'].opening_price;
			var xrp_amount = data['data']['XRP'].units_traded;
			var xrp_change = (xrp_now / xrp_first) - 1;
			xrp_change = xrp_change.toFixed(2);
			var xrp_total = numberWithCommas(Math.round(xrp_amount * xrp_now));
			var xrp1 = document.getElementById('xrp_now');
			var xrp2 = document.getElementById('xrp_change');
			var xrp3 = document.getElementById('xrp_amount');
			xrp1.innerHTML = numberWithCommas(xrp_now) + "원";
			if ((xrp_now - xrp_first) > 0) {
				xrp2.style.color = "red";
				xrp2.innerHTML = "+" + numberWithCommas(xrp_now - xrp_first)
						+ "원(+" + xrp_change + ")";
			} else {
				xrp2.style.color = "blue";
				xrp2.innerHTML = numberWithCommas(xrp_now - xrp_first) + "원("
						+ xrp_change + "%)";
			}
			xrp3.innerHTML = xrp_total + "원";

			/* 비트코인캐시 관련 실시간 업데이트 부분 */
			var bch_now = data['data']['BCH'].closing_price;
			var bch_first = data['data']['BCH'].opening_price;
			var bch_amount = data['data']['BCH'].units_traded;
			var bch_change = (bch_now / bch_first) - 1;
			bch_change = bch_change.toFixed(2);
			var bch_total = numberWithCommas(Math.round(bch_amount * bch_now));
			var bch1 = document.getElementById('bch_now');
			var bch2 = document.getElementById('bch_change');
			var bch3 = document.getElementById('bch_amount');
			bch1.innerHTML = numberWithCommas(bch_now) + "원";
			if ((bch_now - bch_first) > 0) {
				bch2.style.color = "red";
				bch2.innerHTML = "+" + numberWithCommas(bch_now - bch_first)
						+ "원(+" + bch_change + ")";
			} else {
				bch2.style.color = "blue";
				bch2.innerHTML = numberWithCommas(bch_now - bch_first) + "원("
						+ bch_change + "%)";
			}
			bch3.innerHTML = bch_total + "원";

			/* 퀀텀 관련 실시간 업데이트 부분 */
			var qtum_now = data['data']['QTUM'].closing_price;
			var qtum_first = data['data']['QTUM'].opening_price;
			var qtum_amount = data['data']['QTUM'].units_traded;
			var qtum_change = (qtum_now / qtum_first) - 1;
			qtum_change = qtum_change.toFixed(2);
			var qtum_total = numberWithCommas(Math
					.round(qtum_amount * qtum_now));
			var qtum1 = document.getElementById('qtum_now');
			var qtum2 = document.getElementById('qtum_change');
			var qtum3 = document.getElementById('qtum_amount');
			qtum1.innerHTML = numberWithCommas(qtum_now) + "원";
			if ((qtum_now - qtum_first) > 0) {
				qtum2.style.color = "red";
				qtum2.innerHTML = "+" + numberWithCommas(qtum_now - qtum_first)
						+ "원(+" + qtum_change + ")";
			} else {
				qtum2.style.color = "blue";
				qtum2.innerHTML = numberWithCommas(qtum_now - qtum_first)
						+ "원(" + qtum_change + "%)";
			}
			qtum3.innerHTML = qtum_total + "원";

		});

	}

	//현재시간 계산해서 띄워주는 부분
	function CurrentTime() {
		var d = new Date();
		var options = {
			weekday : "long",
			year : "numeric",
			month : "short",
			day : "numeric",
			hour : "2-digit",
			minute : "2-digit"
		};
		$("#lastupdate").html(d.toLocaleTimeString("en-kor", options));
	}

	/* 천원단위 콤마 찍어주는 로직 */
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	
	
	
	
	
	
	
	
	
	///////////////////////////////
	//////////////////////////
	////////////////////
	//////////////////
	///////////////
	
	
	
	
	
	
	
	function gogo1(coin) {
		document.getElementById("timeLine").style.visibility="visible";
		var timeL = document.getElementById("timeLine").value;

		var loadDt = new Date(); //현재 날짜 및 시간  

		//현재시간 기준 계산

		//시간 계산해서 아래 시간대별로 현재시간 들어갈 부분 넣어주기
		var timeInterval = [];
		//시간 옵션
		var options = {
			hour : "2-digit",
			minute : "2-digit"
		};
		var options_day = {
			day : "numeric",
			hour : "2-digit",
			minute : "2-digit"
		};
		switch (timeL) {
		case "minutes/1":
			var c1 = 60;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 60;
				}
			}
			break;

		case "minutes/3":
			var c1 = 180;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 180;
				}
			}

			break;
		case "minutes/5":
			var c1 = 300;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 300;
				}
			}
			break;
		case "minutes/10":
			var c1 = 600;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 600;
				}
			}
			break;
		case "minutes/15":
			var c1 = 900;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 900;
				}
			}
			break;
		case "minutes/30":
			var c1 = 1800;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 1800;
				}
			}
			break;
		case "minutes/60":
			var c1 = 3600;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * c1)
							.toLocaleTimeString("en-kor", options));
					c1 += 3600;
				}
			}
			break;

		case "minutes/240":
			var c1 = 4;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1000 * 60
							* 60 * c1).toLocaleTimeString("en-kor", options));
					c1 += 4;
				}
			}
			break;
		case "days":
			var c1 = 24;
			for (var i = 0; i < 10; i++) {
				if (i == 0) {
					timeInterval.push(loadDt.toLocaleTimeString("en-kor",
							options_day));
				} else {
					timeInterval.push(new Date(Date.parse(loadDt) - 1 * 1000
							* 60 * 60 * c1).toLocaleTimeString("en-kor",
							options_day));
					c1 += 24;
				}
			}
			break;

		default:
			break;
		}
		timeInterval.reverse();

		//맨 먼저 select 부분 onchange부분을 바꿔준다
		var utcDateString = new Date(new Date().toUTCString()).toISOString();
		var coin_url = "https://crix-api-endpoint.upbit.com/v1/crix/candles/"
				+ timeL;
		var titleCoin = document.getElementById("titleCoin");
		var coin_kind = document.getElementById("coin_kind");

		switch (coin) {
		case 'btc':
			coin_kind.value = "btc";
			titleCoin.innerHTML = "비트코인";
			coin_url += '?code=CRIX.UPBIT.KRW-BTC&count=10&to=' + utcDateString;
			break;
		case 'eth':
			coin_kind.value = "eth";
			titleCoin.innerHTML = "이더리움";
			coin_url += '?code=CRIX.UPBIT.KRW-ETH&count=10&to=' + utcDateString;
			break;
		case 'xrp':
			coin_kind.value = "xrp";
			titleCoin.innerHTML = "리플";
			coin_url += '?code=CRIX.UPBIT.KRW-XRP&count=10&to=' + utcDateString;
			break;
		case 'bch':
			coin_kind.value = "bch";
			titleCoin.innerHTML = "비트코인골드";
			coin_url += '?code=CRIX.UPBIT.KRW-BTG&count=10&to=' + utcDateString;
			break;
		case 'qtum':
			coin_kind.value = "qtum";
			titleCoin.innerHTML = "퀀텀";
			coin_url += '?code=CRIX.UPBIT.KRW-QTUM&count=10&to='
					+ utcDateString;
			break;
		default:
			break;
		}

		var cList = [ "시세" ];
		var eList = [ "MA" ];
		var cMax = 0;
		var cMin = 100000000;

		
		
		$.get(coin_url, function(data) {
			//현제 선택한 시간대별로 시세 금액을 cList에 넣기
			for (var i = 9; i >= 0; i--) {
				var iVal = data[i].tradePrice;
				cList.push(iVal);
				if (cMax < iVal) {
					cMax = iVal;
				}
				if (cMin > iVal) {
					cMin = iVal;
				}
			}

			//cList 에 들어간 현재 금액을 이용해서 이동평균구하기
			for (var i = 1; i < 11; i++) {
				if (i == 1) {
					eList.push(cList[i]);
				} else {
					var sum = 0;
					for (var j = 1; j <= i; j++) {
						sum += cList[j];
					}
					eList.push(sum / i);
				}
			}

			var gogo = [ cList, eList ];
			var chart = bb.generate({
				data : {
					columns : gogo,
					type : "bar",
					types : {
						시세 : "bar",
						MA : "spline"
					},
					groups : [ [ "data1" ] ]
				},
				axis : {
					x : {
						type : "category",
						categories : timeInterval
					},
					y : {
						min : cMin,
						max : cMax
					}
				},
				bindto : "#chart"
			});
		});
	}

	$("#timeLine").on("change", function() {
		var kind = document.getElementById("coin_kind").value;
		gogo1(kind);
	});
</script>


