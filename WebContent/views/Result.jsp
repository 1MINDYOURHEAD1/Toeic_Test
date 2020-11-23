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
<title>결과화면</title>
<style>
	td {
		padding: 2px;
		border: 1px solid black;
	}
	tr {
		font-weight: bolder;
	}
	h5 {
		float: left;
	}
	form{
		display: table;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
	}
	#div1{
		text-align:center;
	}
	#div2{
		float: left;
	}
	#div3{
		float: left;
	}
 	#t1 {
		margin-left: 100px;
	}
	#t2{
		margin-left: 144px;
	}
	#t3{
		margin-left: 240px;
	} 
	#sub1{
		margin-left: 210px;
		margin-top: 30px;
	}
</style>
</head>
<body>
	<div id="div1">
		<myTags:Header/>
		<p><h2>VocaTest 결과 페이지</h2>
		<onlyDate:onlyDate>
			<c:set var="date" value="${date_list}"/>
		</onlyDate:onlyDate>
		<c:set var="nowTime">
			<fmt:formatDate value="${date}" pattern="yyyy년MM월dd일/HH시mm분ss초"/> 
		</c:set>
		수고 하셨습니다. 시험 결과입니다.
		<h4 style="color:#72C778">시험종료 시간:<c:out value="${nowTime}"/></h4>
		<hr>
	</div>
	
	<form id="result_form" action="${pageContext.request.contextPath}/toeic?action=End" method='POST'>
		<div id="div2">
		<h3 style="margin-left:68px; color:#48498E;">${sessionScope.id} 님 이 선택하신 답</h3>
		<c:forEach var="choose" items="${choose_list}" varStatus="status">
			<table id="t1">
				<tr>
					<td style= 'width:50px; height:21px;'>${status.count} 번 </td>
					<td style= 'width:30px; height:21px;'><c:out value="${choose}"></c:out></td>
				</tr>
			</table>
		</c:forEach>
		</div>
		<div id="div2">
			<h3 style="margin-left:168px; color:#E440D8; ">정 답</h3>
			<c:forEach var="answer" items="${answer_list}" varStatus="status">
				<table id="t2">
					<tr>
						<td style= 'width:50px; height:21px;'>${status.count} 번 </td>
						<td style= 'width:30px; height:21px;'><c:out value="${answer.answer}"></c:out></td>
					</tr>
				</table>
			</c:forEach>
		</div>
	
		<div id="div3">
		<h4 style="margin-left:170px; color:#EF3353">${sessionScope.id}님의 점수는  ${myscore}/30 점 입니다.</h4>
			<h3 style="margin-left:200px;">${sessionScope.id}님의 시험기록</h3>
			<c:forEach var="scorelist" items="${score_list}" varStatus="status">
				<table id="t3">
					<tr>
						<td style= 'width:50px; height:17px;'>${scorelist.count} 회 </td>
						<td style= 'width:50px; height:17px;'>${scorelist.score} 점</td>
					</tr>
				</table>
			</c:forEach>
			<input id="sub1" type="submit" value="종료하기" />
		</div>
	</form>
</body>
</html>