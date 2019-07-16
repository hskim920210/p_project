<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 목록</title>
</head>
<body>

<table>
	<tr>
		<th>글 번호</th>	
		<th>닉네임(아이디)</th>	
		<th>글 제목(댓글수)</th>	
		<th>작성일</th>	
		<th>조회수</th>	
		<th>좋아요</th>	
	</tr>
	
<c:forEach items="${ simpleList }" var="article">
	<tr>
		<th>${ article.article_num }</th>
		<th>${ article.writer_nick }(${ simpleList.writer_id })</th>
		<th>${ article.article_title }()</th>
		<th>${ article.write_date }</th>
		<th>${ article.read_count }</th>
		<th>${ article.like_count }</th>
	</tr>
</c:forEach>	

</table>

<a href="<%= request.getContextPath() %>/board/write/${ board_id }">글 쓰기</a>

</body>
</html>