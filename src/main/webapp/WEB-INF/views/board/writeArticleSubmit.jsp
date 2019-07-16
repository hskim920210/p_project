<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 쓰기</title>
</head>
<body>

<c:if test="${ result == true }" var="r">
	<p>글 작성이 완료되었습니다.</p>
	<form:form modelAttribute="t_b">
		<p>작성자 아이디 : <form:input readonly="true" path="writer_id" /></p>
		<p>작성자 닉네임 : <form:input readonly="true" path="writer_nick" /></p>
		<p>글 제목 : <form:input readonly="true" path="article_title" /></p>
		<p>글 내용 : <form:input readonly="true" path="article_content" /></p>
	</form:form>
</c:if>

<c:if test="${ not r }">
	<p>글 작성에 실패했습니다. 작성사항을 확인해주세요.</p>
	<form:form modelAttribute="t_b">
		<p>작성자 아이디 : <form:input readonly="true" path="writer_id" /></p>
		<p>작성자 닉네임 : <form:input readonly="true" path="writer_nick" /></p>
		<p>글 제목 : <form:input readonly="true" path="article_title" /></p>
		<p>글 내용 : <form:input readonly="true" path="article_content" /></p>
		<p>작성일 : <form:input readonly="true" path="write_date" /></p>
	</form:form>
</c:if>

</body>
</html>