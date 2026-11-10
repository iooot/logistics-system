//选择客户
var choeseCustomer = function (id, username) {
    $("#customerid").val(id);
    $("#customername").val(username);
}


//新增订单界面--暂存、提交审批
var optionOrder = function () {
    var ordertime = $("#ordertime").val();
    var orderuser = $("#orderuser").val();
    var orderuserid = $("#orderuserid").val();
    var ordernum = $("#ordernum").val();
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
    } else if (orderuser == "请选择" || orderuser == undefined) {
        alert("下单用户不能为空");
        return;
    } else if (ordernum == "请选择" || ordernum == undefined) {
        alert("订单编号不能为空");
        return;
    } else if (ordermoney == "" || ordermoney == undefined) {
        alert("物流费用不能为空");
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
    } else if (protype == "" || protype == '请选择') {
        alert("物品类型不能为空");
        return;
    } else {
        if (confirm("是否修改订单信息?")) {
            $.ajax({
                type: 'POST',
                url: "/orderAction/saveOrder",
                async: false,
                dataType: 'text',
                data: {
                    ordertime: ordertime,
                    orderuser: orderuser,
                    orderuserid: orderuserid,
                    ordernum: ordernum,
                    ordermoney: ordermoney,
                    sendWU: sendWU,
                    reviceWU: reviceWU,
                    revicename: revicename,
                    tel: tel,
                    protype: protype,
                    proheight: proheight,
                    bem: bem
                }, success: function (e) {
                    if ((+e) > 0) {
                        alert("添加成功");
                        window.parent.location.href = window.parent.location.href;
                    } else {
                        alert("修改失败，请联系管理员");
                    }
                }
            })
        }
    }
}