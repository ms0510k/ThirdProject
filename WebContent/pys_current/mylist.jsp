<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>









<html>
<head>

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



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>







<div id="main" style="width: 1000px;">
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
			<tr data-coin="btc" title="비트코인 (BTC) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/비트코인.jpg"
					onclick="gogo1('btc')"><a onclick="gogo1('btc')">비트코인</a></td>
				<td><strong id="btc_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="btc_change"></strong>
				<td><strong id="btc_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td></td>
					<td></td>
					
					<td><input type="button" value="주문"></td>
					
			</tr>
			<tr data-coin="eth" title="이더리움 (ETH) 정보를 확인하시려면 클릭하세요">
				<td><img src="img/이더리움.jpg" onclick="gogo1('eth')"><a
					onclick="gogo1('eth')">이더리움</a></td>
				<td><strong id="eth_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="eth_change"></strong>
				<td><strong id="eth_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td></td>
					
					<td></td>
						<td><input type="button" value="주문"></td>
			</tr>

			<tr data-coin="xrp" title="리플 (XRP) 정보를 확인하시려면 클릭하세요">
				<td><img src="img/리플.jpg" onclick="gogo1('xrp')"><a
					onclick="gogo1('xrp')">리플</a></td>
				<td><strong id="xrp_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="xrp_change"></strong>
				<td><strong id="xrp_amount" class="sort_change"
					data-sorting="0"></strong></td>

				<td></td>
					
					<td></td>
						<td><input type="button" value="주문"></td>
			</tr>
			<tr data-coin="bch" title="비트코인 골드 (BTG) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/비트코인캐쉬.jpg"
					onclick="gogo1('bch')"><a onclick="gogo1('bch')">비트코인골드</a></td>
				<td><strong id="bch_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="bch_change"></strong>
				<td><strong id="bch_amount" class="sort_change"
					data-sorting="0"></strong></td>

				<td></td>
					
					<td></td>
						<td><input type="button" value="주문"></td>
			</tr>

			<tr data-coin="qtum" title="퀀텀 (QTUM) 정보를 확인하시려면 클릭하세요">
				<td class="click left_l"><img src="img/퀀텀.jpg"
					onclick="gogo1('qtum')"><a onclick="gogo1('qtum')">퀀텀</a></td>
				<td><strong id="qtum_now" class="sort_total" data-sorting="0"></strong></td>
				<td><strong id="qtum_change"></strong>
				<td><strong id="qtum_amount" class="sort_change"
					data-sorting="0"></strong></td>


				<td></td>
					
					<td></td>
						<td><input type="button" value="주문"></td>
			</tr>




		

		</tbody>
		

	</table>

</div>







</body>
</html>