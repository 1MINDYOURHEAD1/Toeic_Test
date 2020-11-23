<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 로그인 화면</title>
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
	${pageContext.request.requestURL}
	<h2>모의 토익 로그인 페이지</h2><hr>
	<br><br>
	<form id='login_form' action='${pageContext.request.contextPath}/toeic?action=login' method='POST'>				
		<table align='center'>
			<thead>
				<tr><td width="280dp">아이디  : &nbsp;&nbsp;<input type="text" name="ID" required></td></tr>
				<tr><td width="280dp">비밀번호 : <input type="password" name="PW" required></td></tr>
				<tr><td colspan="2">
					<input type="submit" value="로그인">&nbsp;&nbsp;
					<input type="button" value="회원가입"
					onclick='location.href="${pageContext.request.contextPath}/toeic?action=join_form"'>						
			</thead>
		</table>
	</form>
</body>
</html>