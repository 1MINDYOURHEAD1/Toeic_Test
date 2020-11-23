<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다섯번째 페이지</title>
<style>
	body {
		text-align:center;
	}
	td {
		padding: 5px;
		border: 1px solid black;
	}
</style>
</head>
<body>
	<myTags:Header/>
	<p><h2>VocaTest 다섯번째 페이지</h2>
	<c:if test="${sessionScope.id != null}">
	<h4 style="color:#72C778"><c:out value='${sessionScope.id}'/>님 시험진행중입니다.</h4><hr>
		<form id='test_form5' action='${pageContext.request.contextPath}/toeic?action=result' method='POST'>						
			<p><b>${toeic_list13.q_id}.</b>
				${toeic_list13.q}
			</p><br>
			A:<input type="radio" name="test13" value="1" required />${toeic_list13.a}<br>
			B:<input type="radio" name="test13" value="2" required />${toeic_list13.b}<br>
			C:<input type="radio" name="test13" value="3" required />${toeic_list13.c}<br>
			D:<input type="radio" name="test13" value="4" required />${toeic_list13.d}<br>
			
			
			<p><b>${toeic_list14.q_id}.</b>
				${toeic_list14.q}
			</p><br>
			A:<input type="radio" name="test14" value="1" required />${toeic_list14.a}<br>
			B:<input type="radio" name="test14" value="2" required />${toeic_list14.b}<br>
			C:<input type="radio" name="test14" value="3" required />${toeic_list14.c}<br>
			D:<input type="radio" name="test14" value="4" required />${toeic_list14.d}<br>
			
			
			<p><b>${toeic_list15.q_id}.</b>
				${toeic_list15.q}
			</p><br>
			A:<input type="radio" name="test15" value="1" required />${toeic_list15.a}<br>
			B:<input type="radio" name="test15" value="2" required />${toeic_list15.b}<br>
			C:<input type="radio" name="test15" value="3" required />${toeic_list15.c}<br>
			D:<input type="radio" name="test15" value="4" required />${toeic_list15.d}<br>
			
			<br><br>
			<input type="button" value="뒤로가기 " onclick="window.history.go(-1);"/>&nbsp;&nbsp;
			<input type="submit" value="시험지 제출하기 " />
		</form>
	</c:if>
</body>
</html>