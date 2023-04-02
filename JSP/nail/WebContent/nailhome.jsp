<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.web.jdbc.*" %>

<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>nail home</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<link type="text/css" rel="stylesheet" href="css/homstyle.css">
 
<script type="text/javascript">
$(document).ready(function(){
	$("#c1").click(function(){
		$("p").css('display','none');
		$(".hh").css('display','block');
	})
	
	$("#c2").click(function(){
		$("p").css('display','none');
		$("#this_month").css('display','block');
	})
	
	$("#c3").click(function(){
		$("p").css('display','none');
		$("#color_gel").css('display','block');
	})
	
	$("#c4").click(function(){
		$("p").css('display','none');
		$("#basic_care").css('display','block');
	})
	
	$("#c5").click(function(){
		$("p").css('display','none');
		$("#design").css('display','block');
	})
	
	$("#c6").click(function(){
		$("p").css('display','none');
		$("#price").css('display','block');
	})
}) 
</script>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<div id="rr">
			nail shop
			</div>
			
			<div id="re">
			<input type="button" value="예약하기"
			onclick="window.location.href='reservation_Date.jsp'; return false;"
			class="reservation-button"/>
		
			<input type="button" value="예약확인 및 취소"
			onclick="window.location.href='reservation_check.jsp'; return false;"
			class="reservation-check-button"/>
			</div>
		</div>
	</div>
		
		
	
	
	
		<div id="categorytext">
			<div class="mh" id="c1">무제한 네일</div>
			<div class="mh" id="c2">이달의 아트</div>
			<div class="mh" id="c3">컬러 젤</div>
			<div class="mh" id="c4">기본 케어</div>
			<div class="mh" id="c5">디자인</div>
			<div class="mh" id="c6">가격</div><br/><br/>
			<img id="mhimg" src="img/leaves-g803ed730d_1920.png">
		</div>
	
		
	<div id="menuExplaintext">
		<div id="menuExplaintext2">
		<p class="hh">무제한<br/><br/>
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_01.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_02.jpg">				
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_03.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_04.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_05.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_06.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_07.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_08.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_09.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_10.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_11.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_12.jpg">
		<img id="hhimg" src="img/KakaoTalk_20221212_154554115_13.jpg">
		</p>
		
		<p id="this_month">12월의 아트 <br/><br/>
		<img id="monthimg" src="img/KakaoTalk_20221212_01.png">
		<img id="monthimg" src="img/KakaoTalk_20221212_02.png">
		
		</p>
		
		<p id="color_gel">기본 컬러 젤 </p>
		
		<p id="basic_care">기본케어</p>
	
		<p id="design">그라데이션 등<br/><br/>
		<img id="designimg" src="img/KakaoTalk_20221212_03.png">
		<img id="designimg" src="img/KakaoTalk_20221212_04.png">
		</p>
		
		<p id="price">	
		<c:forEach var="nail" items="${menu_list}">
		${nail.menu} ------ ${nail.price} <br><br>
		</c:forEach>
		</p>
		<br/>
		</div>
	</div>
</body>
</html>