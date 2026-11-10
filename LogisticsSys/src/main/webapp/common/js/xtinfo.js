var updateSysInfo = function () {
    var sysname = $("#sysname").val();
    var leibie = $("#leibie").val();
    var content = $("#content").val();
    if (sysname == null || sysname == "") {
        alert("系统名称不能为空");
        return;
    } else if (leibie == null || leibie == "") {
        alert("系统类别不能为空");
        return;
    } else if (content == null || content == "") {
        alert("系统内容不能为空");
        return;
    }
    $.ajax({
        type: 'POST',
        url: "/home/updateSysInfo",
        async: false,
        dataType: 'text',
        data: {
            sysname: sysname,
            leibie: leibie,
            content: content
        }, success: function (e) {
            if (+e == 1) {
                alert("修改成功");
            } else if (+e == 0) {
                alert("修改失败");
            }
        }
    })
}
