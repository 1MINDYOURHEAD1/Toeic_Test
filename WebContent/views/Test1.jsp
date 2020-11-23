<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/myTagFilesLib.tld" prefix="onlyDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 페이지</title>
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
	<p><h2>토익VocaTest 첫번째 페이지</h2>
	<c:if test="${sessionScope.id != null}">
	<onlyDate:onlyDate>
		<c:set var="date" value="${date_list}"/>
	</onlyDate:onlyDate>
	<c:set var="nowTime">
		<fmt:formatDate value="${date}" pattern="yyyy년MM월dd일/HH시mm분ss초"/> 
	</c:set>
	<h4 style="color:#72C778">
		<c:out value='${sessionScope.id}'/>님 시험진행중입니다.
	         시험시작 시간:<c:out value="${nowTime}"/></h4><hr>
		<form id='test_form1' action='${pageContext.request.contextPath}/toeic?action=test2' method='POST'>						
			<p><b>${toeic_list1.q_id}.</b>
				${toeic_list1.q}
			</p><br>
			A:<input type="radio" name="test1" value="1" required />${toeic_list1.a}<br>
			B:<input type="radio" name="test1" value="2" required />${toeic_list1.b}<br>
			C:<input type="radio" name="test1" value="3" required />${toeic_list1.c}<br>
			D:<input type="radio" name="test1" value="4" required />${toeic_list1.d}<br>
			
			
			<p><b>${toeic_list2.q_id}.</b>
				${toeic_list2.q}
			</p><br>
			A:<input type="radio" name="test2" value="1" required />${toeic_list2.a}<br>
			B:<input type="radio" name="test2" value="2" required />${toeic_list2.b}<br>
			C:<input type="radio" name="test2" value="3" required />${toeic_list2.c}<br>
			D:<input type="radio" name="test2" value="4" required />${toeic_list2.d}<br>
			
			
			<p><b>${toeic_list3.q_id}.</b>
				${toeic_list3.q}
			</p><br>
			A:<input type="radio" name="test3" value="1" required />${toeic_list3.a}<br>
			B:<input type="radio" name="test3" value="2" required />${toeic_list3.b}<br>
			C:<input type="radio" name="test3" value="3" required />${toeic_list3.c}<br>
			D:<input type="radio" name="test3" value="4" required />${toeic_list3.d}<br>
			
			<br><br>
			<input type="button" value="뒤로가기 " onclick="window.history.go(-1);"/>&nbsp;&nbsp;
			<input type="submit" value="다음 페이지로 " />
		</form>
	</c:if>
</body>
</html>