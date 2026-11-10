var serchContent = function () {
    var content = $("#content").val();
    serch(content, "1");
}

var setTop = function (id) {
    if (confirm("是否设置置顶")) {
        $.ajax({
            type: 'POST',
            url: "/forumAction/setTop",
            async: false,
            dataType: 'json',
            data: {
                id: id
            }, success: function (e) {
                if ((+e) > 0) {
                    window.parent.location.href = window.parent.location.href;
                } else {
                    alert("设置失败");
                }
            }
        })
    }
}

var noTop = function (id) {
    if (confirm("是否设置置顶")) {
        $.ajax({
            type: 'POST',
            url: "/forumAction/noTop",
            async: false,
            dataType: 'json',
            data: {
                id: id
            }, success: function (e) {
                if ((+e) > 0) {
                    window.parent.location.href = window.parent.location.href;
                } else {
                    alert("设置失败");
                }
            }
        })
    }
}