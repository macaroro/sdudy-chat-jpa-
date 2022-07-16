<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>채팅을 위한 로그인</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function login()
{
	var serData = $('#login').serialize();
	$.ajax({
		url:'/ws/login',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			if(res.login){
				alert(res.uid+'님 환영합니다.')
				location.href="/ws/chat"
			}else{
				alert('로그인실패')
				location.href="/ws/login"
			}
		},
		error:function(xhr,status,err){
			alert(err);
		}
	});
	return false;
}

</script>
</head>
<body>
<form id="login" onsubmit="return login();">
아이디:<input type="text" name="uid" id="uid">
비밀번호:<input type="text" name="upw" id="upw">
<button type="submit">로그인</button>
<button type="reset">취소</button>
</form>
</body>
</html>