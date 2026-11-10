var projectName;

$(function () {
    getRequestUrl();
})

var saveRole = function () {
    var id = $("#id").val();
    var val = $("#val").val();
    var vkey = $("#vkey").val();
    var dicName = $("#dicName").val();
    var did = $("#did").val();
    var bem = $("#bem").val();
    if (val == "" || val == undefined) {
        alert("角色名称不能为空");
        return;
    }
    confirm("是否保存角色?")
    {
        $.ajax({
            type: 'POST',
            url: "/authorityAction/addRole",
            async: false,
            dataType: 'json',
            data: {
                id: id,
                val: val,
                vkey: vkey,
                dicName: dicName,
                did: did,
                bem: bem
            }, success: function (e) {
                if ((+e) > 0) {
                    alert("修改成功");
                    window.parent.location.href = window.parent.location.href;
                } else {
                    alert("修改失败");
                }
            }
        })
    }
}