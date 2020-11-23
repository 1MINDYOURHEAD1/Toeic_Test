<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네번째 페이지</title>
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
	<p><h2>VocaTest 네번째 페이지</h2>
	<c:if test="${sessionScope.id != null}">
	<h4 style="color:#72C778"><c:out value='${sessionScope.id}'/>님 시험진행중입니다.</h4><hr>
		<form id='test_form4' action='${pageContext.request.contextPath}/toeic?action=test5' method='POST'>						
			<p><b>${toeic_list10.q_id}.</b>
				${toeic_list10.q}
			</p><br>
			A:<input type="radio" name="test10" value="1" required />${toeic_list10.a}<br>
			B:<input type="radio" name="test10" value="2" required />${toeic_list10.b}<br>
			C:<input type="radio" name="test10" value="3" required />${toeic_list10.c}<br>
			D:<input type="radio" name="test10" value="4" required />${toeic_list10.d}<br>
			
			
			<p><b>${toeic_list11.q_id}.</b>
				${toeic_list11.q}
			</p><br>
			A:<input type="radio" name="test11" value="1" required />${toeic_list11.a}<br>
			B:<input type="radio" name="test11" value="2" required />${toeic_list11.b}<br>
			C:<input type="radio" name="test11" value="3" required />${toeic_list11.c}<br>
			D:<input type="radio" name="test11" value="4" required />${toeic_list11.d}<br>
			
			
			<p><b>${toeic_list12.q_id}.</b>
				${toeic_list12.q}
			</p><br>
			A:<input type="radio" name="test12" value="1" required />${toeic_list12.a}<br>
			B:<input type="radio" name="test12" value="2" required />${toeic_list12.b}<br>
			C:<input type="radio" name="test12" value="3" required />${toeic_list12.c}<br>
			D:<input type="radio" name="test12" value="4" required />${toeic_list12.d}<br>
			
			<br><br>
			<input type="button" value="뒤로가기 " onclick="window.history.go(-1);"/>&nbsp;&nbsp;
			<input type="submit" value="다음 페이지로 " />
		</form>
	</c:if>
</body>
</html>