var stompClient = null;





function getTargetUser(){
    var urlSplit = currentUrl.split("/");
    urlSplit.pop();
    return urlSplit[urlSplit.length - 1];
}

var currentUrl = document.URL;

window.onload = loadingSequence();

var currentUser = $("#user").val();
var targetUser = $("#targetuser").val();

function loadingSequence(){
    console.log(currentUser);
    console.log(targetUser);
    loadModelVariables();
    connect();
}

function loadModelVariables(){

}

function connect() {
    var socket = new SockJS('/spring-websocket');
    var headers = {};
    stompClient = Stomp.over(socket);
    // headers[headerName] = token;
    console.log(headers);

    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (message) {
            var obj = JSON.parse(message.body);
            var rightFlag = true;
            if(obj.authorId !== currentUser){
                rightFlag = false;
            }

            showMessage(obj.content, rightFlag );
        });
    });
}


function sendName() {
    // stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    stompClient.send("/chat/private/" , {}, JSON.stringify({'content': $("#msg").val()}));
}

function showMessage(message, onRight) {
    if(onRight === false){
        $("#messages").append("<tr><td class='message message-left'>" + message + "</td></tr>");
    }else{
        $("#messages").append("<tr><td class='message message-right'>" + message + "</td></tr>");
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});