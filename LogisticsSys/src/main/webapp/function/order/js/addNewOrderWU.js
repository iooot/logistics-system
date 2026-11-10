$(function () {
    // 获取当前时间
    const now = new Date();

    // 格式化为本地时间字符串
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const formattedDateTime = `${year}-${month}-${day}T${hours}:${minutes}`;
    $("#ordertime").val(formattedDateTime);
})


var sendOrderWU = function () {
    var ordertitle = $("#ordertitle").val();
    var ordertime = $("#ordertime").val();
    var orderstute = $("#orderuser").val();
    var addr = $("#addr").val();
    if (ordertitle == "请选择" || ordertitle == undefined) {
        alert("请选择添加物流信息的订单");
        return;
    } else if (ordertime == "" || ordertime == undefined) {
        alert("物流送达时间不能为空");
        return;
    } else if (addr == "" || addr == undefined) {
        alert("物流送达地点不能为空");
        return;
    } else {
        if (confirm("是否添加订单'" + ordertitle + "'物流信息?")) {
            $.ajax({
                type: 'POST',
                url: "/orderAction/sendOrderWU",
                async: false,
                dataType: 'text',
                data: {
                    ordertitle: ordertitle,
                    ordertime: ordertime,
                    orderstute: orderstute,
                    addr: addr
                }, success: function (e) {
                    if ((+e) > 0) {
                        alert("添加成功");
                        window.location.href = window.location.href;
                    } else {
                        alert("修改失败，请联系管理员");
                    }
                }
            })
        }
    }
}