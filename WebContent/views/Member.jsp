<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/myTagFilesLib.tld" prefix="onlyDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 환영</title>
<style>
	body {
		text-align:center;
	}
	td {
		padding: 5px;
		border:1px solid black;
	}
	form{
		display: table;
		margin-left: auto;
		margin-right: auto;
	}
	table {
		margin-left: 40px;
	}
</style>
</head>
<body>
	<myTags:Header/>
	<h2>반갑습니다.</h2><hr>
	<form id="member_form" action="${pageContext.request.contextPath}/toeic?action=start_test" method='POST'>			
		<div>	
		<c:if test='${test == 1}'>		
			<h3>지금까지 ${sessionScope.id}님의 시험기록</h3>
				<c:forEach var="scorelist" items="${first_list}" varStatus="status">
					<table>
						<tr>
							<td style= 'width:50px; height:17px;'>${scorelist.count} 회 </td>
							<td style= 'width:50px; height:17px;'>${scorelist.score} 점</td>
						</tr>
					</table>
				</c:forEach>
			</c:if>	
		</div>
		<c:if test='${test == 0}'>
			시험기록이 없습니다.
		</c:if>	
		<br><br>	
		<input type="submit" value="시험 시작하기"/>
	</form>
</body>
</html>			

