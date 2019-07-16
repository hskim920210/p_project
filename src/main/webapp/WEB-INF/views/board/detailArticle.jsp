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
<table border="1">
	<tr>
		<th>글 번호 : ${ detailArticle.article_num }</th>
		<th>글 제목(댓글수) : ${ detailArticle.article_title }()</th>	
		<th>조회수 : ${ detailArticle.read_count }</th>	
	</tr>
	<tr>
		<th>닉네임(아이디) : ${ detailArticle.writer_nick }(${ detailArticle.writer_id })</th>	
		<th>작성일 : ${ detailArticle.write_date }</th>	
		<th>좋아요(${ detailArticle.like_count })</th>	
	</tr>
	<tr>
		<th colspan="3">${ detailArticle.article_content }</th>
	</tr>
</table>
<a href="<%= request.getContextPath() %>/board/write/${ board_id }">글 쓰기</a>

</body>
</html>