<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<title>게시판 만들기</title>
<script type="text/javascript">
</script>
</head>
<body>

<form:form modelAttribute="board_info">
	<p>
	게시판 이름(1~8글자) : <form:input path="board_name" />
	<input type="submit" value="만들기">
	</p>
</form:form>

</body>
</html>