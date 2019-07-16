<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<th>${ article.article_num }<input type="hidden" name="article_num" value="${ article.article_num }" /></th>
		<th>${ article.writer_nick }(${ article.writer_id })<input type="hidden" name="writer_nick" value="${ article.writer_nick }" /><input type="hidden" name="writer_id" value="${ article.writer_id }" /></th>
		<th><a href="<%= request.getContextPath() %>/board/detailArticle/${ article.article_num }">${ article.article_title }()</a><input type="hidden" name="article_title" value="${ article.article_title }" /></th>
		<th>${ article.write_date }<input type="hidden" name="write_date" value="${ article.write_date }" /></th>
		<th>${ article.read_count }<input type="hidden" name="read_count" value="${ article.read_count }" /></th>
		<th>${ article.like_count }<input type="hidden" name="like_count" value="${ article.like_count }" /></th>
	</tr>
</c:forEach>	

</table>
<a href="<%= request.getContextPath() %>/board/write/${ board_id }">글 쓰기</a>

</body>
</html>