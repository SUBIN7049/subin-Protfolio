<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.web.jdbc.*" %>

<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>예약페이지</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<link type="text/css" rel="stylesheet" href="css/reservationstyle.css">


<title>예약확인페이지</title>
</head>
<body>
	<div id="pagename">
		<b>예약확인</b>
	</div>
	
<form action="nailServlet">
<input type="hidden" name="command" value="reservationcheck2"/>
<input type="hidden" name="re_user_id" value="${reservationlist.get(0).getUser_id()}"/>
 	<div id ="retable">
 		<div id="intable">
		<table >
			<tr>
			<th>번호</th>
			<th>예약날짜</th>
			<th>예약시간</th>
			<th>시술내용</th>
			</tr>
	
			<c:forEach var="list"  items="${reservationlist}" varStatus="status">
			<tr>
			<td>${list.user_id }</td>
			<td>${list.possible_date }</td>
			<td>${list.possible_time }</td>
			<td>${list.service_name }</td>
  			</tr>
			</c:forEach>
		</table>
		<br/>
		<input id="submitbt" type="submit" value="예약취소"/>
		<br/>
		
		</div>		
		<a href ="http://localhost:1111/nail/nailServlet"/>돌아가기
	</div>
	
</form>	
	
</body>
</html>