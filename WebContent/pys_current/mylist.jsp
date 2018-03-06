<%@page import="pys.vo.listVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<%
	listVO btcList = (listVO) request.getAttribute("BTC");
	listVO eList = (listVO) request.getAttribute("ETH");
	listVO xList = (listVO) request.getAttribute("XRP");
	listVO btgList = (listVO) request.getAttribute("BTG");
	listVO qtumList = (listVO) request.getAttribute("QTUM");
%>



<style>
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



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<h3>* 매수평균가, 평가금액, 평가손익, 수익률은 모두 KRW로 환산한 추정값으로 참고용입니다.</h3>


<div id="main" style="width: 1000px;" align="center">
	<h3 id="lastupdate"></h3>
	<table class="g_table" id="tableAsset">

		<thead>
			<tr>
				<th class="center">보유코인</th>
				<th><span class="sorting">보유수량</span></th>
				<th><span class="sorting">매수평균가</span></th>
				<th><span class="sorting">매수금액</span></th>
				<th><span class="sorting">평가금액</span></th>
				<th><span class="sorting">평가손익</span></th>
				<th></th>
			</tr>
		</thead>
		<!-- 로그인 했을때랑 안했을때랑 나누어서 처리하기  1.로그인 했을때-->
		<tbody>
			<tr title="비트코인 (BTC) 정보를 확인하시려면 클릭하세요">
			<div id="my1">
				<td class="click left_l"><img src="img/비트코인.jpg"
					onclick="gogo1('btc')"><a onclick="gogo1('btc')">비트코인</a></td>
				<td><div id="btc_1"></div></td>
				<td><div id="btc_2"></div></td>
				<td><div id="btc_3"></div></td>
				<td><div id="btc_4"></div></td>
				<td><div id="btc_5"></div></td>
				<td><a href="marketprice.do?cmd=buy&coin=BTC" class="btn btn_coin_buy"
					data-type="1" data-coin="BTC"> 주문하기</a></td>
				</div>
			</tr>
			<tr title="이더리움 (ETH) 정보를 확인하시려면 클릭하세요">
			<div id="my2">
				<td><img src="img/이더리움.jpg" onclick="gogo1('eth')"><a
					onclick="gogo1('eth')">이더리움</a></td>
				<td><div id="eth_1"></div></td>
				<td><div id="eth_2"></div></td>
				<td><div id="eth_3"></div></td>
				<td><div id="eth_4"></div></td>
				<td><div id="eth_5"></div></td>
				<td><a href="marketprice.do?cmd=buy&coin=ETH" class="btn btn_coin_buy"
					data-type="1" data-coin="ETH"> 주문하기</a></td>
			</div>
			</tr>

			<tr title="리플 (XRP) 정보를 확인하시려면 클릭하세요">
			<div id="my3">
				<td><img src="img/리플.jpg" onclick="gogo1('xrp')"><a
					onclick="gogo1('xrp')">리플</a></td>
				<td><div id="xrp_1"></div></td>
				<td><div id="xrp_2"></div></td>
				<td><div id="xrp_3"></div></td>
				<td><div id="xrp_4"></div></td>
				<td><div id="xrp_5"></div></td>
				<td><a href="marketprice.do?cmd=buy&coin=XRP" class="btn btn_coin_buy"
					data-type="1" data-coin="BTC"> 주문하기</a></td>
</div>
			</tr>
			<tr title="비트코인 골드 (BTG) 정보를 확인하시려면 클릭하세요">
			
			<div id="my4">
				<td class="click left_l"><img src="img/비트코인캐쉬.jpg"
					onclick="gogo1('btg')"><a onclick="gogo1('btg')">비트코인골드</a></td>
				<td><div id="btg_1"></div></td>
				<td><div id="btg_2"></div></td>
				<td><div id="btg_3"></div></td>
				<td><div id="btg_4"></div></td>
				<td><div id="btg_5"></div></td>
				<td><a href="marketprice.do?cmd=buy&coin=BTG" class="btn btn_coin_buy"
					data-type="1" data-coin="BTG"> 주문하기</a></td>
</div>
			</tr>
			<tr title="퀀텀 (QTUM) 정보를 확인하시려면 클릭하세요">
			
				<div id="my5">
			
				<td class="click left_l"><img src="img/퀀텀.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">퀀텀</a></td>
				<td><div id="qtum_1"></div></td>
				<td><div id="qtum_2"></div></td>
				<td><div id="qtum_3"></div></td>
				<td><div id="qtum_4"></div></td>
				<td><div id="qtum_5"></div></td>
				<td><a href="marketprice.do?cmd=buy&coin=QTUM" class="btn btn_coin_buy"
					data-type="1" data-coin="BTC"> 주문하기</a></td>
			</div>
			</tr>





		</tbody>


	</table>

</div>

<script type="text/javascript">



 window.onload = proc(); 

	function proc() {
		try {
			
			
			$.get('https://api.bithumb.com/public/ticker/ALL', function(data) {

				var btc_now = data['data']['BTC'].closing_price;
			
				var eth_now = data['data']['ETH'].closing_price;
				
				var xrp_now = data['data']['XRP'].closing_price;
				
				var btg_now = data['data']['BTG'].closing_price;
			
				var qtum_now = data['data']['QTUM'].closing_price;
			
				
				//비트코인부분
				
				if(<%=btcList.getAmount()%> == 0){
					
					document.getElementById("my1").style.visibility = "hidden";
				}else{
				document.getElementById("btc_1").innerHTML = <%=btcList.getAmount()%>+"BTC";
				document.getElementById("btc_2").innerHTML = numberWithCommas(Math.round((<%=btcList.getTotal()%>/<%=btcList.getAmount()%>)))+"KRW";
				document.getElementById("btc_3").innerHTML = numberWithCommas(Math.round(<%=btcList.getTotal()%>))+"KRW";
				document.getElementById("btc_4").style.fontWeight = 'bold';
				document.getElementById("btc_4").innerHTML = numberWithCommas(Math.round((btc_now*<%=btcList.getAmount()%>)))+"KRW";
				
				
				if(((btc_now*<%=btcList.getAmount()%>)-<%=btcList.getTotal()%>)>0){
				
				document.getElementById("btc_5").style.color = 'red';
				document.getElementById("btc_5").innerHTML = (((btc_now*<%=btcList.getAmount()%>)/<%=btcList.getTotal()%>-1)*100).toFixed(5)+"%";
			}else{
				document.getElementById("btc_5").style.color = 'blue';
				document.getElementById("btc_5").innerHTML = (((btc_now*<%=btcList.getAmount()%>)/<%=btcList.getTotal()%>-1)*100).toFixed(5)+"%";
			}
				}
				
				//이더리움부분
				
				if(<%=eList.getAmount()%> == 0){
					
					document.getElementById("my2").style.visibility = "hidden";
				}else{
				document.getElementById("eth_1").innerHTML = <%=eList.getAmount()%>+"ETH";
				document.getElementById("eth_2").innerHTML = numberWithCommas(Math.round((<%=eList.getTotal()%>/<%=eList.getAmount()%>)))+"KRW";
				document.getElementById("eth_3").innerHTML = numberWithCommas(Math.round(<%=eList.getTotal()%>))+"KRW";
				document.getElementById("eth_4").style.fontWeight = 'bold';
				document.getElementById("eth_4").innerHTML = numberWithCommas(Math.round((eth_now*<%=eList.getAmount()%>)))+"KRW";
				
				if(((eth_now*<%=eList.getAmount()%>)-<%=eList.getTotal()%>)>0){
				
				document.getElementById("eth_5").style.color = 'red';
				document.getElementById("eth_5").innerHTML = (((eth_now*<%=eList.getAmount()%>)/<%=eList.getTotal()%>-1)*100).toFixed(5)+"%";
			}else{
				document.getElementById("eth_5").style.color = 'blue';
				document.getElementById("eth_5").innerHTML = (((eth_now*<%=eList.getAmount()%>)/<%=eList.getTotal()%>-1)*100).toFixed(5)+"%";
			}
				}
				
				//리플부분
				if(<%=xList.getAmount()%> == 0){
					
					document.getElementById("my3").style.visibility = "hidden";
				}else{
				
				document.getElementById("xrp_1").innerHTML = <%=xList.getAmount()%>+"XRP";
				document.getElementById("xrp_2").innerHTML = numberWithCommas(Math.round((<%=xList.getTotal()%>/<%=xList.getAmount()%>)))+"KRW";
				document.getElementById("xrp_3").innerHTML = numberWithCommas(Math.round(<%=xList.getTotal()%>))+"KRW";
				document.getElementById("xrp_4").style.fontWeight = 'bold';
				document.getElementById("xrp_4").innerHTML = numberWithCommas(Math.round((xrp_now*<%=xList.getAmount()%>)))+"KRW";
				
				if(((btc_now*<%=xList.getAmount()%>)-<%=xList.getTotal()%>)>0){
				
				document.getElementById("xrp_5").style.color = 'red';
				document.getElementById("xrp_5").innerHTML = (((xrp_now*<%=xList.getAmount()%>)/<%=xList.getTotal()%>-1)*100).toFixed(5)+"%";
			}else{
				document.getElementById("xrp_5").style.color = 'blue';
				document.getElementById("xrp_5").innerHTML = (((xrp_now*<%=xList.getAmount()%>)/<%=xList.getTotal()%>-1)*100).toFixed(5)+"%";
			}
				}
				
				
				
				//비트코인골드부분
				
if(<%=btgList.getAmount()%> == 0){
					
					document.getElementById("my4").style.visibility = "hidden";
				}else{
				document.getElementById("btg_1").innerHTML = <%=btgList.getAmount()%>+"BTG";
				document.getElementById("btg_2").innerHTML = numberWithCommas(Math.round((<%=btgList.getTotal()%>/<%=btgList.getAmount()%>)))+"KRW";
				document.getElementById("btg_3").innerHTML = numberWithCommas(Math.round(<%=btgList.getTotal()%>))+"KRW";
				document.getElementById("btg_4").style.fontWeight = 'bold';
				document.getElementById("btg_4").innerHTML = numberWithCommas(Math.round((btg_now*<%=btgList.getAmount()%>)))+"KRW";
				
				if(((btc_now*<%=btgList.getAmount()%>)-<%=btgList.getTotal()%>)>0){
				
				document.getElementById("btg_5").style.color = 'red';
				document.getElementById("btg_5").innerHTML = (((btg_now*<%=btgList.getAmount()%>)/<%=btgList.getTotal()%>-1)*100).toFixed(5)+"%";
			}else{
				document.getElementById("btg_5").style.color = 'blue';
				document.getElementById("btg_5").innerHTML = (((btg_now*<%=btgList.getAmount()%>)/<%=btgList.getTotal()%>-1)*100).toFixed(5)+"%";
			}
				}
				//퀀텀부분
				
				if(<%=qtumList.getAmount()%> == 0){
					
					document.getElementById("my5").style.visibility = "hidden";
				}else{
				document.getElementById("qtum_1").innerHTML = <%=qtumList.getAmount()%>+"QTUM";
				document.getElementById("qtum_2").innerHTML = numberWithCommas(Math.round((<%=qtumList.getTotal()%>/<%=qtumList.getAmount()%>)))+"KRW";
				document.getElementById("qtum_3").innerHTML = numberWithCommas(Math.round(<%=qtumList.getTotal()%>))+"KRW";
				document.getElementById("qtum_4").style.fontWeight = 'bold';
				document.getElementById("qtum_4").innerHTML = numberWithCommas(Math.round((qtum_now*<%=qtumList.getAmount()%>)))+"KRW";
				
				if(((btc_now*<%=qtumList.getAmount()%>)-<%=qtumList.getTotal()%>)>0){
				
				document.getElementById("qtum_5").style.color = 'red';
				document.getElementById("qtum_5").innerHTML = (((qtum_now*<%=qtumList.getAmount()%>)/<%=qtumList.getTotal()%>-1)*100).toFixed(5)+"%";
			}else{
				document.getElementById("qtum_5").style.color = 'blue';
				document.getElementById("qtum_5").innerHTML = (((qtum_now*<%=qtumList.getAmount()%>)/<%=qtumList.getTotal()%>-1)*100).toFixed(5)+"%";
			}
				}				

			});
		
		
		} catch (e) {

		} finally {
			setTimeout("proc()", 2000); //10초후 재시작
		}
	}



	
	/* 천원단위 콤마 찍어주는 로직 */
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	
	</script>


