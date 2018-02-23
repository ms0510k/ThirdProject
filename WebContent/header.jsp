<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css">
<script src="//code.jquery.com/jquery.min.js"></script>

<!-- 코드작업부분 -->
<script>
	$(function() {
		$(".zeta-menu li").hover(function() {
			$('ul:first', this).show();
		}, function() {
			$('ul:first', this).hide();
		});
		$(".zeta-menu>li:has(ul)>a").each(function() {
			$(this).html($(this).html() + ' &or;');
		});
		$(".zeta-menu ul li:has(ul)").find("a:first").append(
				"<p style='float:right;margin:-3px'>&#9656;</p>");
	});

	function gogo() {
		alert("로그인이 필요합니다.");
		location.href="/ThirdProject/kms_member/member_login.jsp";
	}
</script>

<!-- 몸통부분 관리자 부분과 일반사용자 부분 분리할 예정 -->
<body>
	<%
		String email = (String) session.getAttribute("email");
	%>


	<!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
	<!-- 일반 사용자 부분입니다 -->

	<%
		if (!("admin".equals(email))) {
	%>

	<div class='zeta-menu-bar' style="margin-bottom: 50px;">
		<ul class="zeta-menu">

			<!-- 로고부분 -->
			<li><a href="<%=request.getContextPath()%>/header.do?cmd=main"
				style="font-weight: bold; padding-left: 70px; padding-right: 100px;">4JO
					Exchange</a></li>

			<!-- 현재시세차트부분 -->
			<li><a href="<%=request.getContextPath()%>/header.do?cmd=chart" style="padding-left: 50px; padding-right: 70px;">현재시세차트</a>
				<ul>
					<li><a href="#">비트코인</a></li>
					<li><a href="#">이더리움</a></li>
					<li><a href="#">리플</a></li>
					<li><a href="#">비트코인골드</a></li>
					<li><a href="#">퀀텀</a></li>
					<li><a href="#">민수코인</a></li>
					<li><a href="#">프링코인</a></li>
				</ul></li>
			<%
				if (email != null) {
			%>
			<!-- 거래하기부분 -->
			<li><a href="<%=request.getContextPath()%>/header.do?cmd=trade" style="padding-left: 50px; padding-right: 70px;">거래하기</a></li>

			<!-- 마이페이지 부분 -->
			<li><a href="<%=request.getContextPath()%>/kms_mypage/mypage_index.jsp"
				style="padding-left: 50px; padding-right: 70px;">마이페이지</a>
				<ul>
					<li><a href="#">2-A 메뉴</a></li>
					<li><a href="#">2-B 메뉴</a></li>
				</ul></li>
			<%
				} else {
			%>
			<!-- 거래하기부분 -->
			<li><a href="javascript:gogo()" style="padding-left: 50px; padding-right: 70px;">거래하기</a></li>

			<!-- 마이페이지 부분 -->
			<li><a href="javascript:gogo()"
				style="padding-left: 50px; padding-right: 70px;">마이페이지</a>
				<ul>
					<li><a href="javascript:gogo()">2-A 메뉴</a></li>
					<li><a href="javascript:gogo()">2-B 메뉴</a></li>
				</ul></li>
			<%
				}
			%>
			<!-- 고객센터부분 -->
			<li><a href="#" style="padding-left: 50px; padding-right: 70px;">고객센터</a>
				<ul>
					<li><a href="#">자주묻는질문</a></li>
					<li><a href="#">1:1문의</a></li>
				</ul></li>
			<%
				if (email != null) {
			%>
			<li><a
				href="<%=request.getContextPath()%>/member.do?cmd=member_logout"
				style="padding-left: 200px; padding-right: 50px;">로그아웃</a></li>
			<li><a href="#" style="padding-left: 50px; padding-right: 50px;"><%=email%></a></li>
			
			<%
				} else {
			%>
			<!-- 로그인부분 -->
			<li><a
				href="<%=request.getContextPath()%>/kms_member/member_login.jsp"
				style="padding-left: 200px; padding-right: 50px;">로그인</a></li>
			<!-- 회원가입부분 -->
			<li><a
				href="<%=request.getContextPath()%>/kms_member/member_insert.jsp"
				style="padding-left: 50px; padding-right: 50px;">회원가입</a></li>
			<%
				}
			%>
			<!-- 만약 로그인이 된 상태라면 이용할 로그아웃 부분 로그인이 되 있으면 로그인 부분 회원가입부분이랑 분기로 처리 -->
			<!-- <li><a href="#">로그아웃</a></li>  -->
		</ul>
	</div>

	<!-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
	<!-- 관리자 부분입니다 -->
	<%
		} else {
	%>

	<div class='zeta-menu-bar' style="margin-bottom: 50px;">
		<ul class="zeta-menu">

			<!-- 로고부분 -->
			<li><a href="#"
				style="font-weight: bold; padding-left: 70px; padding-right: 100px;">4JO
					Exchange</a></li>

			<!-- 공지사항부분 -->
			<li><a href="<%=request.getContextPath()%>/admin.do?cmd=notice" style="padding-left: 50px; padding-right: 70px;">공지사항</a>

			</li>

			<!-- 문의답변부분 -->
			<li><a href="<%=request.getContextPath()%>/admin.do?cmd=comp" style="padding-left: 50px; padding-right: 70px;">문의답변</a>

			</li>
			<!-- 출금신청대기자부분 -->
			<li><a href="#" style="padding-left: 50px; padding-right: 70px;">출금
					신청 대기자</a></li>
			<!-- 탈퇴신청대기자부분 -->
			<li><a href="#" style="padding-left: 50px; padding-right: 70px;">탈퇴신청대기자</a>

			</li>
			<!-- 정산부분 -->
			<li><a href="#" style="padding-left: 50px; padding-right: 70px;">정산</a>

			</li>


				<!-- 정산부분 -->
			<li><a href="#" style="padding-left: 50px; padding-right: 70px;">관리자님 환영합니다1</a>

			</li>
			<li><a href="<%=request.getContextPath()%>/member.do?cmd=member_logout"
				style="padding-left: 50px; padding-right: 70px;">로그아웃</a></li>
			
		</ul>
	</div>


	<%
		}
	%>
	
	
	
	
	
	
</body>
</html>