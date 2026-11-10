var projectName;

$(function () {
    getRequestUrl();
    getCurrentUser();
})

//获取项目名称
var getRequestUrl = function () {
    var pathName = window.document.location.pathname;
    projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
}


var getCurrentUser = function () {

    $.ajax({
        type: 'POST',
        url: "/home/index",
        async: false,
        dataType: 'text',
        data: {
            init: "init"
        }, success: function (e) {
            if (+e == -1) {
                window.top.location.href = "/login";
            } else {
                $("#username").html(e);
            }
        }, error: function (e) {

        }
    })
}
