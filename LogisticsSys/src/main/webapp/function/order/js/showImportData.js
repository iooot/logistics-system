var projectName;

$(function () {
    getRequestUrl();

})

var importData = function (state) {
    var version = $("#version").val();
    $.ajax({
        type: 'POST',
        url: "/orderAction/importData",
        async: false,
        dataType: 'json',
        data: {
            version: version,
            state: state
        }, success: function (e) {
            if ((+e) > 0) {
                window.parent.location.href = window.parent.location.href;
            }
        }
    })
}


//获取项目名称
var getRequestUrl = function () {
    var pathName = window.document.location.pathname;
    projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
}