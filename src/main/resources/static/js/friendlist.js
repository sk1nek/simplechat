var stompClient = null;

$(document).ready(function () {
    connect();

    $('#friend-input-box').keypress(function (e) {
        if (e.which == '13' && $('#friend-input-box').val() !== '') {
            $(this).attr("disabled", "disabled");
            stompClient.send("/addFriend", {}, $('#friend-input-box').val());
            $(this).val('');
            $(this).removeAttr("disabled");

        }

    })
});

function connect() {
    var socket = new SockJS('/spring-websocket');
    var headers = {};
    stompClient = Stomp.over(socket);
    console.log(headers);

    stompClient.connect(headers, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/friendFeedback', function (feedback) {
            var obj = JSON.parse(feedback.body);
            if(obj.success){
                showNewFriend(obj.friendName);
            }
        });
    });
}


function showNewFriend(name) {

    var currentLocation = $('#location').attr('href');

    $('#friendlist-table').append('<tr><td><a class="nice-button" href=\"'+  name + '\">'+name+'</a> </td></tr>');
}

