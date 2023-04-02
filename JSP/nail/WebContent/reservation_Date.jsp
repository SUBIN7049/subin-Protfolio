<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.web.jdbc.*" %>

<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>예약페이지</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<link type="text/css" rel="stylesheet" href="css/reservationstyle.css">
	
</head>
<!-- 이페이지에서 넘긴 날짜 데이터를 다시 서블릿에서 다음페이지 넘어갈 때 request에 attribute로 저장해서 다음 페이로 전송 -->
<body>

<div id="pagename">
	<b>예약페이지</b>
</div>
<form action="nailServlet" >
	<input type="hidden" name="command" value="NeedTimeData"/>
	
	<div id="phone">
		phone : <input type="text" name="user_id" />
	</div><br/>

	<div id="calendar">
		<input type="date" min="2022-12-24" max="2022-12-29" name="reservation_date"/><br/><br/>
	 	<input id="submitbt" type="submit" value="다음"/>
	</div><br/>

	
</form>
</body>
</html>