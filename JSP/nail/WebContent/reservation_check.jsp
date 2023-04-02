<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.web.jdbc.*" %>


<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/reservationstyle.css">
<title>예약확인페이지</title>

</head>
<body>
	<div id="pagename">
		<b>예약확인</b>
	</div>
	<form action = "nailServlet">
		<input type="hidden" name="command" value="reservationcheck"/>
		
		<div id = "phone">
		<input type="text" name="re_user_id"/>
		<input type="submit" value="확인"/>
		</div>
		
	</form>
</body>
</html>