var updateUserInfo = function () {
    var id = $("#id").val();
    var logname = $("#logname").val();
    var username = $("#username").val();
    var sex = $("#sex").val();
    var pwd = $("#pwd").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var bem = $("#bem").val();
    var homeAddress = $("#homeAddress").val();
    var role = $("#role").val();

    if (logname == null || logname == "") {
        alert("登录账号不能为空");
        return;
    } else if (username == null || username == "") {
        alert("用户名称不能为空");
        return;
    } else if (phone == null || phone == "") {
        alert("联系方式不能为空");
        return;
    } else if (email == null || email == "") {
        alert("邮箱不能为空");
        return;
    } else if (bem == null || bem == "") {
        alert("备注不能为空");
        return;
    }
    $.ajax({
        type: 'POST',
        url: "/user/updateUserinfo",
        async: false,
        dataType: 'text',
        data: {
            id: id,
            logname: logname,
            username: username,
            pwd: pwd,
            phone: phone,
            email: email,
            bem: bem,
            sex: sex,
            homeAddress: homeAddress,
            role: role
        }, success: function (e) {
            if ((+e) == -999) {
                alert("账号已存在，请重新输入");
            } else if (+e == 1) {
                alert("操作成功");
                window.parent.location.replace(window.parent.location.href);
            } else if (+e == 0) {
                alert("操作失败");
            }
        }
    })
}
