<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>

<form:form modelAttribute="regResultUser">
<table>
<caption>회원가입 완료 !</caption>
	<tr>
		<th>아이디</th>
		<td>
			<form:input path="user_id" readonly="true" />
		</td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><form:password path="user_pw" readonly="true" /></td>
	</tr>
	<tr>
		<th>닉네임</th>
		<td>
			<form:input path="user_nick" readonly="true" />
		</td>
	</tr>
	<tr>
		<th>연락처</th>
		<td><form:input path="user_tel" readonly="true" /></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><form:input path="user_mail" readonly="true" /></td>
		
	</tr>
</table>
</form:form>


</body>
</html>