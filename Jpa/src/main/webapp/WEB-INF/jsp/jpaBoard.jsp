<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 글 입력폼</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto;}
	main>h3 { width:fit-content; margin:1em auto; border-bottom:3px double black;}
	form {padding:1em; border:1px solid black; border-radius:7px;}
	div>label { display:inline-block; width:2em; padding-right:1em; text-align:right; vertical-align:top;  }
	div>input {width:30em; margin-bottom:0.5em; }
	div>textarea { width:30em; height:10em; }
	form>div:last-child { margin:1em auto; margin-bottom:0; width:fit-content; text-align:center;}
	nav { width:fit-content; margin:1em auto; }
	nav > a { text-decoration:none; }
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function save()
{
	var serData = $('#input_form').serialize();
	$.ajax({
		url:'/jpaboard/save',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			alert(res.saved==true? '저장성공':'실패');
	
				location.href="/jpaboard/list";
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
<main>
<h3>게시판 글 입력 폼</h3>
<form id="input_form" onsubmit="return save();">
	<div>
		<label for="title">제목</label>
		<input type="text" id="title" name="title">
	</div>
	<div>
		<label for="author">작성자</label>
		<input type="text" id="author" name="author" value="min">
	</div>
	<div>
		<label for="contents">내용</label>
		<textarea id="contents" name="contents" placeholder="글 입력..."></textarea>
	</div>
	<div>
		<button type="reset">취소</button>
		<button type="submit">저장</button>
	</div>
</form>
</main>

</body>
</html>