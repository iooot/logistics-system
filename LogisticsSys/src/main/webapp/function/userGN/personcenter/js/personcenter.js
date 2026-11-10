var projectName;

$(function () {
    getInitData()
})

var getInitData = function () {
    $.ajax({
        type: 'POST',
        url: "/user/singleUsr",
        async: false,
        dataType: 'json',
        data: {}, success: function (e) {
            $("#phone").html(e.phone);
            $("#logname").html(e.logname);
            $("#username").html(e.username);
            $("#content").html(e.bem);

        }
    })
}