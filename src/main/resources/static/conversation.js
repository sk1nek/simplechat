var stompClient = null;

var currentUser;
var targetUser;
var currentUserId;
var targetUserId;

$(document).ready(function (){
    currentUser = $("#user").val();
    targetUser = $("#targetuser").val();
    currentUserId = $("#userid").val();
    targetUserId = $("#targetuserid").val();
    connect();
});



function connect() {
    var socket = new SockJS('/spring-websocket');
    var headers = {};
    stompClient = Stomp.over(socket);
    console.log(headers);

    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/private/incoming', function (message) {
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
    showMessage($('#msg').val(), true);
    stompClient.send("/private" , {}, JSON.stringify({'content': $("#msg").val(), 'authorId':currentUserId, 'targetId':targetUserId}));
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
    $( "#send" ).click(function() {

        sendName();
        $("#msg").val('');});


});