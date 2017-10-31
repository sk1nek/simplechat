var stompClient = null;

var currentUser;
var targetUser;
var currentUserId;
var targetUserId;


function getTargetUser(){
    var urlSplit = currentUrl.split("/");
    urlSplit.pop();
    return urlSplit[urlSplit.length - 1];
}

var currentUrl = document.URL;

$(document).ready(function (){
    currentUser = $("#user").val();
    targetUser = $("#targetuser").val();
    currentUserId = $("#userid").val();
    targetUserId = $("#targetuserid").val();


    console.log(currentUser);
    console.log(targetUser);
    loadModelVariables();
    connect();
})



function loadModelVariables(){

}

function connect() {
    var socket = new SockJS('/spring-websocket');
    var headers = {};
    stompClient = Stomp.over(socket);
    console.log(headers);

    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (message) {
            var obj = JSON.parse(message.body);
            var rightFlag = true;
            if(obj.authorId != currentUserId){
                rightFlag = false;
            }
            console.log(obj.authorId, obj.targetId, rightFlag);
            console.log(obj.authorId, currentUserId);

            showMessage(obj.content, rightFlag );
        });
    });
}


function sendName() {
    stompClient.send("/chat/private" , {}, JSON.stringify({'content': $("#msg").val(), 'authorId':currentUserId, 'targetId':targetUserId}));
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