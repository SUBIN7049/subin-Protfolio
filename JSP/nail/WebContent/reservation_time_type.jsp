<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.web.jdbc.*" %>

<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>예약페이지</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<link type="text/css" rel="stylesheet" href="css/reservationstyle.css">
<script>
$(document).ready(function(){
	$("#submitbt").click(function(){
		alert('예약되었습니다')
	});
});
</script>
</head>
<body>
<div id="pagename">
	<b>예약페이지</b>
</div>
<!-- 이전 페이지에서 고른 시간 데이터를 input hidden에 넣어서 같이 보내주는데 서블릿에서 가져올 때 꼭 name 두번 다시 확인 필수  -->
<form action="nailServlet" >
	<input type="hidden" name="command" value="NeedTimeData2"/>
	<input type="hidden" name="datelast" value="<%= request.getAttribute("reservation_date") %>"/> 
	<input type="hidden" name="user_id" value="<%= request.getAttribute("user_id") %>"/> 
	
	<div id="timebox">
		<p>예약 시간 선택</p>
		<div id="time">
		<c:forEach var="date" items="<%= request.getAttribute(\"times\") %>">
		<input type="radio" name="timeradio" value="${date}">${date}<br/><br/>
		</c:forEach>
		</div>
	</div>
	<br/><br/>

	<div id="menubox">
		<p>시술 종류</p>
		<div id="menu1">
		네일<br/><br/>
			<c:forEach var="nail" items="<%= request.getAttribute(\"menu_nail\") %>">
			<input type="radio" name="nailradio" value="${nail.menu }">${nail.menu }<br/>
			</c:forEach>
		</div>
	
		<div id="menu2">
		페디<br/><br/>
			<c:forEach var="pedi" items="<%= request.getAttribute(\"menu_pedi\") %>">
			<input type="radio" name="pediradio" value="${pedi.menu }">${pedi.menu }<br/>
			</c:forEach>
		</div>
	
		<div id="menu3">
		옵션	<br/><br/>
			<c:forEach var="option" items="<%= request.getAttribute(\"menu_option\") %>">
			<input type="checkbox" name="optioncheck" value="${option.menu}">${option.menu}<br/>
			</c:forEach>
		</div>
		<br/><br/><br/><br/>
		<input id="submitbt" type="submit" value="예약하기"/>
	</div>

	
</form>

</body>
</html>