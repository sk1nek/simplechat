var stompClient = null;

var headerName = "${_csrf.headerName}";
var token = "${_csrf.token}";

window.onload = loadingSequence();

function loadingSequence(){
    connect();
}




function connect() {
    var socket = new SockJS('/spring-websocket');
    stompClient = Stomp.over(socket);
    var headers = {};
    headers[headerName] = token;
    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (message) {
            console.log("xd");
            showGreeting(JSON.parse(message.body).content);
        });
    });
}


function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});