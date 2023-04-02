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
	$("#cs").click(function(){
		if(confirm("취소하시겠습니까?")){
			alert("취소되었습니다");
		}else{
			return false;
		};
	});
});
</script>

<title>예약취소</title>
</head>
<body>
<div id="pagename">
	<b>예약취소</b>
</div>

<form action="nailServlet">
<input type="hidden" name="command" value="reservationcancel">



 	<div id="retable">
 		<div id="intable">
		<table id="ttable">
			<tr>
			<th>날짜</th>
			<th>시간</th>
			<th>체크</th>
			</tr>

			<c:forEach var="list2"  items="${reservation_cancel_list}" >
			<tr>
			<td>${list2.possible_date }</td>
			<td>${list2.possible_time }</td>
			<td><input type="checkbox" name="cancel_reservationnumber" value="${list2.date_num} ">
   			</tr>
			</c:forEach>
		</table>
		<br/>
		<input id="cs" type="submit" value="취소하기"/>
		</div>
	</div>
</form>
</body>
</html>