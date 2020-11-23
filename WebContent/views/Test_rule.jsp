<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 규정 사항</title>
<style>
	body {
		text-align:center;
	}
	table {
		padding: 5px;
		border: 1px solid black;
	}
	h3 {
		margin-top: 30px;
	}
	#p1 {
		margin-top: 7px;
		margin-bottom: 7px;
		color: #2A2967;
		font-weight: bolder;
		text-decoration: underline;
	}
	#p2 {
		margin-top: 20px;
	}
</style>
</head>
<body>
	<myTags:Header/>
	<h2>모의 토익</h2><hr>
	<c:if test="${id != null}">
	<form id='rule_form' action='${pageContext.request.contextPath}/toeic?action=test1' method='POST'>	
	<p style=text-decoration:underline >
		<c:out value='${sessionScope.id}'/> 님반갑습니다.
	</p>		
		<h3 style=color:#F23F3C >시험 전 규정사항(꼭 읽어 주세요)</h3>
		<table align='center'>
			<tr><td align='left'><p id="p1">●1. 시험문항수는 총 15문제 입니다.(각 2점)</p></td></tr>
			<tr><td align='left'><p id="p1">●2. 모두 객관식 유형입니다.</p></td></tr>
			<tr><td align='left'><p id="p1">●3. 한문제 당 하나씩 꼭 답을 체크해주세요.</p></td></tr>
			<tr><td align='left'><p id="p1">●4. 시험 제한 시간은 없습니다.</p></td></tr>
			<tr><td align='left'><p id="p1">●5. 시험을 정직하게 봐주세요.</p></td></tr>
			<tr><td align='left'><p id="p1">●6. 시험종료후 점수가 바로 공개됩니다.</p></td></tr>			
			<tr><td align='left'><p id="p1">●7. 시험시작후에는 뒤로가기를 할수 없습니다.</p></td></tr>
		</table>
		<p id="p2">
			<input type="submit" value="시험시작 하기" />
		</p>
	</form>
	</c:if>
</body>
</html>
