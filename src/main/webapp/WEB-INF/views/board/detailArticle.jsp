<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.js"></script>
<title>게시판 글 목록</title>
<script type="text/javascript">
	var like_count_tot = ${detailArticle.like_count};
	var loginUser = '${loginUser.user_id}';
	var isLogin;
	
	$(function() {
		
		
		$("#likeBtn").on("click", function () {
			if( loginUser != null ) {
				isLogin = true;
			} else {
				isLogin = false;
			}
			
			if( isLogin == false ) {
				alert("로그인이 필요한 기능입니다.")
			}
			
			var userLikeObject = new Object();
			userLikeObject.user_id = '${loginUser.user_id}';
			userLikeObject.article_num = ${detailArticle.article_num};
			userLikeObject.like_count = ${detailArticle.like_count};
			var userLikeJsonObject = JSON.stringify(userLikeObject);
			alert(userLikeJsonObject);
				$.ajax({
					url:'<%=request.getContextPath()%>/board/like',
					type:"post",
					data:userLikeJsonObject,
					contentType:"application/json",
					dataType:"json",
					success:function (result){ // result 는 불린 타입
						var msg='';
						// 성공 시 확인창과 보여줄 메세지를 설정
						if( eval(result) ){
							alert('좋아요 완료');
							msg='좋아요 취소';
							like_count_tot += 1;
							$("#like").text(msg);
							$("#likeCountArea").text(like_count_tot);
						} else { // 가입할 수 있는 ID
							alert('좋아요 취소 완료');
							msg='좋아요';
							like_count_tot -= 1;
							$("#like").text(msg);
							$("#likeCountArea").text(like_count_tot);
						}
					},
					error:function (result){
						alert('좋아요 과정에서 문제 발생');
					}
				});
			});
		
		
	});
	
	
</script>
</head>
<body>
<table border="1">
	<tr>
		<th>글 번호 : ${ detailArticle.article_num }</th>
		<th>글 제목(댓글수) : ${ detailArticle.article_title }(${ commentCount })</th>	
		<th>조회수 : ${ detailArticle.read_count }</th>	
	</tr>
	<tr>
		<th>닉네임(아이디) : ${ detailArticle.writer_nick }(${ detailArticle.writer_id })</th>	
		<th>작성일 : ${ detailArticle.write_date }</th>	
		<th>좋아요(<span id="likeCountArea">${ detailArticle.like_count }</span>)</th>	
	</tr>
	<tr>
		<th colspan="3">${ detailArticle.article_content }</th>
	</tr>
	<tr>
		<th colspan="3">
			<button id="likeBtn"><span id="like">
			<c:if test="${like_check==true}" var="p">
				좋아요 취소
			</c:if>
			<c:if test="${not p}">
				좋아요
			</c:if>
		</span></button>
		</th>
	</tr>
</table>

<h4>댓글 (<span id="comment_count_bottom">${ commentCount }</span>)</h4>

	<c:if test="${ empty commentList }" var="p">
		<table border="1" id="comment_table">
				<tr id="comment_start">
					<td>닉네임(ID)</td>
					<td>내용</td>
					<td colspan="2">작성일</td>
				</tr>
		</table>
	</c:if>
	
	<c:if test="${ not p }">
	<table border="1" id="comment_table">
				<tr id="comment_start">
					<td>닉네임(ID)</td>
					<td>내용</td>
					<td colspan="2">작성일</td>
				</tr>
			<c:forEach items="${ commentList }" var="comment">
				<tr id="comment_${ comment.comment_id }">
					<td>${ comment.user_nick } (${ comment.user_id })</td>
					<td>${ comment.content }</td>
					<td>${ comment.write_time })</td>
					<td><c:if test="${ login_user.user_id eq comment.user_id }"><button onclick="delete_comment(${comment.comment_id});">삭제</button></c:if></td>
				</tr>
			</c:forEach>
	</table>
	</c:if>



<a href="<%= request.getContextPath() %>/board/write/${ board_id }">글 쓰기</a>
</body>
</html>