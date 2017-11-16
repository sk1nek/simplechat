var stompClient = null;

$(document).ready(function () {
    connect();

    $('#friend-input-box').keypress(function (e) {
        // noinspection EqualityComparisonWithCoercionJS
        if (e.which == '13' && $('#friend-input-box').val() !== '') {
            $(this).attr("disabled", "disabled");
            stompClient.send("/addFriend", {}, $('#friend-input-box').val());
            $(this).val('');
            $(this).removeAttr("disabled");

        }

    });

    setContextMenuAction();
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
            // noinspection EqualityComparisonWithCoercionJS
            if(obj.success && obj.type=="add"){
                // noinspection Annotator
                showNewFriend(obj.friendName);
            }
        });
    });
}


function showNewFriend(name) {

    $('#friendlist-table').append('<tr><td><a class="nice-button" href=\"'+  name + '\">'+name+'</a> </td></tr>');

    setContextMenuAction();
}

function setContextMenuAction(){
    $('.nice-button').contextmenu(function (e) {

        stompClient.send("/removeFriend", {}, this.text);
        e.preventDefault();
        this.remove();
    });
}