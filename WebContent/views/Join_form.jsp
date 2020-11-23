<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>토익 회원가입</title>
<style>
	body {
		text-align:center;
	}
	td {
		padding: 5px;
		border:1px solid black;
	}
	
</style>
</head>
<body>
	<myTags:Header/>
	<h2>토익  회원가입</h2><hr>
	<br><br>
		<form action='${pageContext.request.contextPath}/toeic?action=join' method="POST">
			<table align="center">
				<thead>
					<tr><td>아이디 :</td><td><input type="text" name="id" required /></td></tr>									
					<tr><td>비밀번호 :</td><td><input type="password" name="pw" required /></td></tr>
					<tr><td>이름 :</td><td><input type="text" name="name" required /></td></tr>
					<tr><td>나이 :</td><td><input type="number" name="age" required /></td></tr>
					<tr><td>성별 :</td><td>
						남자 <input type="radio" name="gender" value="남자" required>&nbsp;&nbsp;
						여자<input type="radio" name="gender" value="여자" required>
					<tr><td>이메일 :</td><td><input type="text" name="email" required /></td></tr>
				</thead>	
			</table>
			<p align='center'><input type="submit" value="회원가입"></p>
		</form>
</body>
</html>