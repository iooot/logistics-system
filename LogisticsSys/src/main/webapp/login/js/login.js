var loginSys = function () {
    var uname = $("#uname").val();
    var pwd = $("#pwd").val();
    $.ajax({
        type: 'POST',
        url: "/syslogin/sysUserLogin",
        async: false,
        dataType: 'text',
        data: {
            uname: uname,
            pwd: pwd
        }, success: function (e) {
            if (+e == 1) {
                window.location.href = "/index";
            } else if (+e == 0) {
                alert("账号禁用或账号密码错误");
            }
        }, error: function (e) {

        }
    })
}


$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        loginSys();
    }
});
