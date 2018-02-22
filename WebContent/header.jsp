<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<link rel="stylesheet"
	href="css/header.css">
<script src="//code.jquery.com/jquery.min.js"></script>

<!-- 코드작업부분 -->
<script>
$(function(){
  $(".zeta-menu li").hover(function(){
    $('ul:first',this).show();
  }, function(){
    $('ul:first',this).hide();
  });
  $(".zeta-menu>li:has(ul)>a").each( function() {
    $(this).html( $(this).html()+' &or;' );
  });
  $(".zeta-menu ul li:has(ul)")
    .find("a:first")
    .append("<p style='float:right;margin:-3px'>&#9656;</p>");
});

function gogo() {
	alert("아");
}
</script>

<!-- 몸통부분 -->
<body>
<div class='zeta-menu-bar' style="margin-bottom: 50px; ">
  <ul class="zeta-menu">

  <!-- 로고부분 -->
    <li><a href="#" style=" font-weight: bold; padding-left: 70px; padding-right: 100px;" >4JO Exchange</a></li>

  <!-- 현재시세차트부분 -->  
    <li><a href="#" style="padding-left: 50px; padding-right: 70px;">현재시세차트</a>
    	<ul>
        <li><a href="#">비트코인</a></li>
        <li><a href="#">이더리움</a></li>
        <li><a href="#">리플</a></li>
        <li><a href="#">비트코인골드</a></li>
        <li><a href="#">퀀텀</a></li>
        <li><a href="#">민수코인</a></li>
         <li><a href="#">민승코인</a></li>
     	 </ul>
    </li>

  	<!-- 거래하기부분 -->  
     <li><a href="#" style="padding-left: 50px; padding-right: 70px;">거래하기</a></li> 

    <!-- 마이페이지 부분 -->
    <li><a href="javascript:gogo()" style="padding-left: 50px; padding-right: 70px;">마이페이지</a>
      <ul>
        <li><a href="#">2-A 메뉴</a></li>
        <li><a href="#">2-B 메뉴</a></li>
      </ul>
    </li>

    <!-- 고객센터부분 -->
    <li><a href="#" style="padding-left: 50px; padding-right: 70px;">고객센터</a>
      <ul>
        <li><a href="#">자주묻는질문</a></li>
        <li><a href="#">1:1문의</a></li>
      </ul>
    </li> 

    <!-- 로그인부분 -->
    <li><a href="#" style="padding-left: 200px; padding-right: 50px;">로그인</a></li> 

    <!-- 회원가입부분 -->
    <li><a href="#" style="padding-left: 50px; padding-right: 50px;">회원가입</a></li> 

    <!-- 만약 로그인이 된 상태라면 이용할 로그아웃 부분 로그인이 되 있으면 로그인 부분 회원가입부분이랑 분기로 처리 -->
    <!-- <li><a href="#">로그아웃</a></li>  -->

  </ul>
</div>

<div id="body1" style="margin-top: 10px;" align="center">
	<h1>body</h1>
	</div>

</body>
</html>