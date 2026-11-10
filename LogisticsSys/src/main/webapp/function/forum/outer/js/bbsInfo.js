var serchContent = function () {
    var content = $("#content").val();
    serch(content, "1");
}

var addNew = function () {
    var bbsname = $("#bbsname").val();
    var bbstype = $("#bbstype").val();
    var bbscontent = $("#bbscontent").val();
    if (bbsname == "" || bbsname == undefined) {
        alert("帖子名称不能为空");
        return;
    }else if (bbstype == "请选择" ) {
        alert("请选择帖子类型");
        return;
    }else if (bbscontent == "" || bbscontent == undefined) {
        alert("帖子内容不能为空");
        return;
    }
    if (confirm("是否发布该帖子?")) {
        $.ajax({
            type: 'POST',
            url: "/forumAction/publish",
            async: false,
            dataType: 'json',
            data: {
                bbsname: bbsname,
                bbstype: bbstype,
                bbscontent: bbscontent
            }, success: function (e) {
                if ((+e) > 0) {
                    alert("设置成功");
                    window.parent.location.href = window.parent.location.href;
                } else if ((+e) == -1) {
                    alert("请先进行登录");
                } else {
                    alert("设置失败");
                }
            }
        })
    }
}
