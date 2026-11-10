var updateCdInfo = function () {
    var id = $("#id").val();
    var cname = $("#cname").val();
    var cpath = $("#cpath").val();
    var fnid = $("#fnid").val();
    var indx = $("#indx").val();
    var level = $("#level").val();
    var nrole = $("#nrole").val();
    if (cname == null || cname == "") {
        alert("菜单名称不能为空");
        return;
    } else if (indx == null || indx == "") {
        alert("排序序号不能为空");
        return;
    } else if (nrole == "请选择" || nrole == "") {
        alert("请选择权限");
        return;
    }
    $.ajax({
        type: 'POST',
        url: "/cdAction/addNewCd",
        async: false,
        dataType: 'text',
        data: {
            id: id,
            cname: cname,
            cpath: cpath,
            fnid: fnid,
            indx: indx,
            level: level,
            nrole: nrole
        }, success: function (e) {
            if (+e == 1) {
                alert("修改成功");
                window.parent.location.href = window.parent.location.href;
            } else if (+e == 0) {
                alert("修改失败");
            }
        }
    })
}
