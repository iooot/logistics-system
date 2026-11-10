//选择客户
var choeseCustomer = function (id, username) {
    $("#customerid").val(id);
    $("#customername").val(username);
}


//新增订单界面
var optionOrder = function () {
    var id = $("#id").val();
    var ordermoney = $("#ordermoney").val();
    var sendWU = $("#sendWU").val();
    var reviceWU = $("#reviceWU").val();
    var revicename = $("#revicename").val();
    var tel = $("#tel").val();
    var protype = $("#protype").val();
    var proheight = $("#proheight").val();
    var bem = $("#bem").val();
    if (ordertime == "" || ordertime == undefined) {
        alert("订单时间不能为空");
        return;
    } else if (sendWU == "" || sendWU == undefined) {
        alert("发货地址不能为空");
        return;
    } else if (reviceWU == "" || reviceWU == undefined) {
        alert("收货地址不能为空");
        return;
    } else if (revicename == "" || revicename == undefined) {
        alert("收件人不能为空");
        return;
    } else if (tel == "" || tel == undefined) {
        alert("联系方式不能为空");
        return;
    } else if (proheight == "" || proheight == undefined) {
        alert("物品重量不能为空");
        return;
    } else {
        if (confirm("是否修改订单信息?")) {
            $.ajax({
                type: 'POST',
                url: "/orderAction/changeSaveOrder",
                async: false,
                dataType: 'text',
                data: {
                    id: id,
                    ordermoney: ordermoney,
                    sendWU: sendWU,
                    reviceWU: reviceWU,
                    revicename: revicename,
                    tel: tel,
                    protype: protype,
                    proheight: proheight,
                    bem: bem,
                }, success: function (e) {
                    if ((+e) > 0) {
                        alert("修改成功");
                        window.parent.location.href = window.parent.location.href;
                    } else {
                        alert("修改失败，请联系管理员");
                    }
                }
            })
        }
    }
}