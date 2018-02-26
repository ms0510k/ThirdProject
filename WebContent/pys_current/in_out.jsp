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

.tab-content {
	height: 542px;
	width: 500px;
}

.submit_go {
	color: #ffffff;
	border-color: #E7EFF7;
	background-color: #52799C;
	font-size: 20pt;
	font-family: Tahoma;
	width: 200px;
	height: 50px
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
System.out.println(coin);

%>


<div id="content1">

	<!-- 시장현황 -->
	<div class="left_content" id="content_left">
		<div class="container">


			<!-- 탭나눠주는 부분 -->
			<ul class="tabs">
				<li class="tab-link current" data-tab="tab-1">KRW 충전하기</li>
				<li class="tab-link" data-tab="tab-2">KRW 출금하기</li>
				<li class="tab-link" data-tab="tab-3">입/출금 내역</li>
			</ul>



			<!-- 입금 탭 설정하기 -->
			<div id="tab-1" class="tab-content current">
				<form>
					<table style="border-spacing: 40px;">
						<tr>
							<td>나의 추정자산</td>
							<td><label id="myasset"></label>KRW</td>
						</tr>
						<tr>
							<td>보유금액</td>
							<td><label id="mykrw"></label>KRW</td>
						</tr>
						<tr>
							<td>충전금액</td>
							<td><input type="text" name="input_price">원</td>
						</tr>


						<tr>
							<td colspan="2"><input type="submit" value='충전하기'
								class="submit_go"></td>
						</tr>





					</table>

				</form>



			</div>


			<!-- 출금 탭 설정하기 -->
			<div id="tab-2" class="tab-content">

				<form>
					<table style="border-spacing: 40px;">
						<tr>
							<td>나의 추정자산</td>
							<td><label id="myasset"></label>KRW</td>
						</tr>
						<tr>
							<td>보유금액</td>
							<td><label id="mykrw"></label>KRW</td>
						</tr>
						<tr>
							<td>출금하기</td>
							<td><input type="text" name="output_price">원</td>
						</tr>


						<tr>
							<td colspan="2"><input type="submit" value="출금신청하기"
								class="submit_go"></td>
						</tr>




					</table>

				</form>



			</div>



			<!-- 입출금내역 탭 설정하기 -->
			<div id="tab-3" class="tab-content">

				<select id="tradeList" style="width: 100px; height: 50px;">
					<option value="all">전체</option>
					<option value="input">입금</option>
					<option value="output">출금</option>


				</select> <br>






			</div>


		</div>

	</div>
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
			var btc_now = data['data']['BTC'].closing_price;

			var now_price = document.getElementById("now_price");

			now_price.innerHTML = "현재 가격 : " + numberWithCommas(btc_now) + "원";

			/* 만약 비트코인이라면 1만 단위로 찍어준다 */
			var money = 50000;
			for (var i = 0; i < 11; i++) {
				var labels = document.getElementById("label" + i);
				labels.innerHTML = numberWithCommas(btc_now - money);
				money -= 10000;
			}

		});

	}

	/* 천원단위 콤마 찍어주는 로직 */
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
</script>
