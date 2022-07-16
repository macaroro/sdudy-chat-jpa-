<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹소켓 테스트 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<script type="text/javascript">
var g_webSocket = null;
var uid = '${uid}';
//세션에 넣은 uid가 가져오기(맵에 담은 것)
var open = false;

window.onload = function() 
//서버가 돌아옴
{
    //ezen_host = "192.168.56.1";   /* 배포시에 호스트 주소로 변경 */
   host = "localhost";
    g_webSocket = new WebSocket("ws://"+host+"/websocket");
    //내 로컬 호스트에 주소를 넣음
    
    /* 웹소켓 접속 성공시 실행 */
    g_webSocket.onopen = function(message) {
        addLineToChatBox("Server is connected.");
        //위의 메세지가 뜨게한다.
    };
    
    /* 웹소켓 서버로부터 메시지 수신시 실행 */
    g_webSocket.onmessage = function(message) {
    	var obj = JSON.parse(message.data);
    	//서버에서 맵에 넣은걸 다시 json문자열로 보낸건을 받아
    	//json객체로 만듬
    	var sender = obj.from;
        addLineToChatBox('['+sender+']'+obj.contents);
        //화면에[유저이름] 보낸 메세지 형식으로 뜨게 함
    };

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행 */
    g_webSocket.onclose = function(message) {
        addLineToChatBox("Server is disconnected.");
    };

    /* 웹소켓 에러 발생시 실행 */
    g_webSocket.onerror = function(message) {
        addLineToChatBox("Error!");
    };
}

/* 채팅 메시지를 화면에 표시 */
function addLineToChatBox(_line)
//메세지를 보낼때
{
    if (_line == null) {
        _line = "";
    }
    
    var chatBoxArea = document.getElementById("chatBoxArea");
    //<body>에 id chatBoxArea의 값을 가져온다
    chatBoxArea.value += _line + "\n";
    //그 값에 계속 로그가 남게, 그 전의 메세지에 다음줄에 작성된다.
    chatBoxArea.scrollTop = chatBoxArea.scrollHeight; 
    //계속 메세지가 쌓이게 되면 스크롤해야하니
    //메세제가 오게 되면 그  자리로 넘어감?
}

/* Send 버튼 클릭하면 서버로 메시지 전송 */
function sendButton_onclick() {
    var inputMsgBox = document.getElementById("inputMsgBox");
    //id inputMsBox의 값을 가져온다
    var to = document.getElementById("to");//$('#to').val(); 제이쿼리
    //id to의 값을 가져온다
    if (inputMsgBox == null || inputMsgBox.value == null || inputMsgBox.value.length == 0) {
        return false;
    }
    
    var chatBoxArea = document.getElementById("chatBoxArea");
    //checkBoxArea의 값을 가져옴
    
    if (g_webSocket == null || g_webSocket.readyState == 3) {
        chatBoxArea.value += "Server is disconnected.\n";
        return false;
    }
    
    
    // 서버로 메시지 전송
    var msg = {};
    //json문자열 형태로 서버에 보내기 위한 작엄
    msg.from = uid;
    msg.to = to.value;
    msg.contents = inputMsgBox.value;
    //각각 from,to,contents의 값(키)에 
    //값을 넣어줌(value)
    
    g_webSocket.send(JSON.stringify(msg));
    //서버에게 json객체를 문자열로 변환하여 보내줌
    inputMsgBox.value = "";
    inputMsgBox.focus();
    to.value = "";
    
    return true;
}

/* Disconnect 버튼 클릭하는 경우 호출 */
function disconnectButton_onclick() {
    if (g_webSocket != null) {
        g_webSocket.close();    
        //socket 닫기
    }
}

/* inputMsgBox 키 입력하는 경우 호출 */
function inputMsgBox_onkeypress() {
    if (event == null) {
        return false;
    }
    
    // 엔터키 누를 경우 서버로 메시지 전송
    var keyCode = event.keyCode || event.which;
    if (keyCode == 13) {
    	//엔터키가 13 즉 엔터를 눌러도 전송 가능하게함
        sendButton_onclick();
    }
}
</script>
</head>
<body>

    <input id="inputMsgBox" style="width: 250px;" type="text" onkeypress="inputMsgBox_onkeypress()">
    to : <input type="text" id="to" name="to">
    <input id="sendButton" value="Send" type="button" onclick="sendButton_onclick()">
    <input id="disconnectButton" value="Disconnect" type="button" onclick="disconnectButton_onclick()">
    <br/>
    <textarea id="chatBoxArea" style="width: 100%;" rows="10" cols="50" readonly="readonly"></textarea>
</body>
</html>