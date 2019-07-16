<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<title>게시판 글 쓰기</title>
<script type="text/javascript">
	function submitCheck() {
		var title_result = false;
		var content_result = false;
		var title = $("#article_title").val();
		var content = $("#article_content").val();
		var result = false;
		
		if( title.length > 0 && title.length <= 30 ) {
			title_result = true;
		}
		if( content.length > 0 && content.length <= 500 ){
			content_result = true;
		}
		result = title_result && content_result;
		return result;
	}
</script>
</head>
<body>
<form action="<%= request.getContextPath() %>/board/writeArticle" method="post" onsubmit="return submitCheck()">
<table>
	<tr>
		<th>제목(1~30자)</th>
		<td><input type="text" id="article_title" name="article_title" ></td>
	</tr>
	<tr>
		<th>닉네임(아이디)</th>
		<td><input type="text" id="writer_nick" value="${ loginUser.user_nick }" readonly="readonly" />(<input type="text" id="writer_id" value="${ loginUser.user_id }" readonly="readonly" />)</td>
	</tr>
	<tr>
		<th>내용(500자 이내)</th>
		<td><textarea rows="20" cols="25" id="article_content" name="article_content" style="resize: none;"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
		<input type="submit" value="작성완료">
		<input type="reset" value="초기화">
		</th>
	</tr>
	<tr>
		<td><input type="hidden" name="board_id" value="${ board_id }"></td>
	</tr>
</table>
</form>
</body>
</html>