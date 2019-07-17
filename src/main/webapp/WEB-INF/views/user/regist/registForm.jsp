<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<script type="text/javascript">
	
	// 중복체크의 경우 keyup을 할 때마다 중복체크 버튼을 눌렀는지 확인하는 boolean 타입을 false로 바꾼다.
	var isIdCheck = false;
	var isPwEqual = false;
	var isNickCheck = false;
	// 연락처 또는 이메일 중 하나가 입력됐는지 체크는 가입하기 버튼을 눌렀을 때 한다.
	var isTelOrMail = false;
	
	function registCheck() {
		if( isIdCheck && isPwEqual && isNickCheck && isTelOrMail ){
			return true;
		} else {
			alert("가입 정보에 맞지 않는 정보를 입력하였습니다.");
			alert("ID조건 : " + isIdCheck + "\n" + "PW조건 : " + isPwEqual + "\n" + "닉네임조건 : " + isNickCheck + "\n" + "연락처 또는 이메일조건 : " + isTelOrMail);
			return false;
		}
	}
	
	
	$(function() {
		
		// id 중복확인 부분.
		// 버튼을 눌렀을 때, 해당 input에서 id 값을 가져와서
		// userObject의 user_id에 셋팅하여
		// Json 객체로 만들어진 userJsonObject를 토대로
		// 비동기 통신을 하여 isIdCheck가 false인지 true인지 구별한다.
		$("#idCheckBtn").on("click", function() {
			var userObject = new Object();
			userObject.user_id = $("#user_id_reg").val();
			// JSON 문서 생성 방법
			var userJsonObject = JSON.stringify(userObject);
			$.ajax({
				url : "<%= request.getContextPath() %>/user/idCheck",
				type : "post",
				data : userJsonObject,
				dataType : "json",
				contentType : "application/json",
				success : function (data) {
					if( data.result == "false" ) {
						isIdCheck = false;
						$("#isIdExist").text("이미 사용중인 아이디입니다.");
					} else {
						isIdCheck = true;
						$("#isIdExist").text("사용 가능한 아이디입니다.");
					}
				},
				error : function (request, status, error) {
					$("isIdExist").text("조회에 실패하였습니다. 관리자에게 문의하세요.");
					//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				} 
			});
			
		});
		
		
		
		// nick 중복확인 부분
		$("#nickCheckBtn").on("click", function() {
			var userObject = new Object();
			userObject.user_nick = $("#user_nick").val();
			// JSON 문서 생성 방법
			var userJsonObject = JSON.stringify(userObject);
			$.ajax({
				url : "<%= request.getContextPath() %>/user/nickCheck",
				type : "post",
				data : userJsonObject,
				dataType : "json",
				contentType : "application/json",
				success : function (data) {
					if( data.result == "false" ) {
						isNickCheck = false;
						$("#isNickExist").text("이미 사용중인 닉네임입니다.");
					} else {
						isNickCheck = true;
						$("#isNickExist").text("사용 가능한 닉네임입니다.");
					}
				},
				error : function (request, status, error) {
					$("isNickExist").text("조회에 실패하였습니다. 관리자에게 문의하세요.");
					//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				} 
			});
		});
		
		
		
		// 비밀번호 일치 확인 - 1
		$("#user_pw_reg").on("keyup", function() {
			var user_pw = $("#user_pw_reg").val();
			var user_pw_confirm = $("#user_pw_confirm_reg").val();
			if( user_pw.length > 0 && user_pw.length <= 15 ) {
				if( user_pw == user_pw_confirm ) {
					isPwEqual = true;
					$("#isPwEqual").text("비밀번호가 일치합니다.");
				} else {
					isPwEqual = false;
					$("#isPwEqual").text("비밀번호가 일치하지 않습니다.");
				}
			} else {
				$("#isPwEqual").text("1~15자리 문자와 숫자를 입력해주세요.");
			}
		});
		// 비밀번호 일치 확인 - 2
		$("#user_pw_confirm_reg").on("keyup", function() {
			var user_pw_confirm = $("#user_pw_confirm_reg").val();
			var user_pw = $("#user_pw_reg").val();
			if( user_pw_confirm.length > 0 && user_pw_confirm.length <= 15 ) {
				if( user_pw == user_pw_confirm ) {
					isPwEqual = true;
					$("#isPwEqual").text("비밀번호가 일치합니다.");
				} else {
					isPwEqual = false;
					$("#isPwEqual").text("비밀번호가 일치하지 않습니다.");
				}
			} else {
				$("#isPwEqual").text("1~15자리 문자와 숫자를 입력해주세요.");
			}
		});
		
		
		
		// 이메일이나 연락처 확인
		var user_tel = $("#user_tel").val();
		var user_mail = $("#user_mail").val();
		if( user_tel.length == 0 && user_mail.length == 0 ) {
			isTelOrMail = false;
			$("#isTelOrMail").text("연락처와 이메일 중 하나는 반드시 입력해야 합니다.");
		}
		$("#user_tel").on("keyup", function() {
			user_tel = $("#user_tel").val();
			console.log("user_tel.length : " + user_tel.length);
			if( user_tel.length > 0 && user_tel.length <= 20 ) {
				isTelOrMail = true;
				$("#isTelOrMail").text('');
			} else if( user_tel.length > 20 ){
				isTelOrMail = false;
				$("#isTelOrMail").text("연락처가 너무 깁니다. (1~20자리)");
			} else if( user_tel.length == 0 && user_mail.length == 0 ) {
				isTelOrMail = false;
				$("#isTelOrMail").text("연락처와 이메일 중 하나는 반드시 입력해야 합니다.");
			}
		});
		$("#user_mail").on("keyup", function() {
			user_mail = $("#user_mail").val();
			console.log("user_mail.length : " + user_mail.length);
			if( user_mail.length > 0 && user_mail.length <= 30 ) {
				isTelOrMail = true;
				$("#isTelOrMail").text('');
			} else if( user_mail.length > 30) {
				isTelOrMail = false;
				$("#isTelOrMail").text("이메일이 너무 깁니다. (1~30자리)");
			} else if( user_tel.length == 0 && user_mail.length == 0 ) {
				isTelOrMail = false;
				$("#isTelOrMail").text("연락처와 이메일 중 하나는 반드시 입력해야 합니다.");
			}
		});
		
		
		
	});
	
</script>
</head>
<body>

<form action="<%= request.getContextPath() %>/user/regist" method="post" onsubmit="return registCheck()">
<table>
<caption>회원가입</caption>
	<tr>
		<th>아이디</th>
		<td>
			<input type="text" id="user_id_reg" name="user_id" placeholder="1~10자리 문자와 숫자를 사용" required>
			<button type="button" id="idCheckBtn">중복체크</button>
		</td>
	</tr>
	<tr>
		<th colspan="2"><span id="isIdExist"></span></th>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" id="user_pw_reg" name="user_pw" placeholder="1~15자리 문자와 숫자를 사용" required></td>
	</tr>
	<tr>
		<th>패스워드 확인</th>
		<td><input type="password" id="user_pw_confirm_reg" name="user_pw_confirm" placeholder="1~15자리 문자와 숫자를 사용" required></td>
	</tr>
	<tr>
		<th colspan="2"><span id="isPwEqual"></span></th>
	</tr>
	<tr>
		<th>닉네임</th>
		<td>
			<input type="text" id="user_nick" name="user_nick" placeholder="1~8자리 문자와 숫자를 사용" required>
			<button type="button" id="nickCheckBtn">중복체크</button>
		</td>
	</tr>
	<tr>
		<th colspan="2"><span id="isNickExist"></span></th>
	</tr>
	<tr>
		<th>연락처</th>
		<td><input type="text" id="user_tel" name="user_tel"></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" id="user_mail" name="user_mail"></td>
		
	</tr>
	<tr>
		<th colspan="2"><span id="isTelOrMail"></span></th>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="가입하기">
			<input type="reset" value="초기화">
		</td>
	</tr>
</table>

</form>


</body>
</html>