$(function () {
    serch("", "", "", "1");
})

var serchContent = function () {
    var beginordertime = $("#beginordertime").val();
    var endordertime = $("#endordertime").val();
    var content = $("#content").val();
    serch(beginordertime, endordertime, content, "1");
}


var serch = function (beginordertime, endordertime, content, page) {
    if (beginordertime == "") {
        beginordertime = "1980/12/31";
    }
    if (endordertime == "") {
        endordertime = "9999/12/31";
    }
    $.ajax({
        type: 'POST',
        url: "/orderAction/allorder",
        async: false,
        dataType: 'json',
        data: {
            content: content,
            beginordertime: beginordertime,
            endordertime: endordertime,
            page: page
        }, success: function (e) {
            var list = e.list;
            var currentPage = e.currentPage;
            var pageCount = e.pageCount;
            var totalCount = e.totalCount;
            var totalPageCount = e.totalPageCount;
            var str = "";
            var fenye;
            //显示列表
            var sum = 0;
            if ((+page) > 1) {
                sum = (+page - 1) * (+pageCount);
            }
            for (var i = 0; i < list.length; i++) {
                str = str +
                    "<tr><td style=\"text-align: center\">" + (i + 1 + sum) + "</td>\n" +
                    "     <td style=\"text-align: center\">" + list[i].ordertime + "</td>\n" +
                    "     <td style=\"text-align: center;color:#5a61ff;cursor: pointer;\" " +
                    "onclick='x_admin_show(\"查看订单信息\",\"/dispOrder/previewOrder?id=" + list[i].id + "\")'>" +
                    list[i].ordernum + "</td>\n" +
                    "     <td style=\"text-align: center\">" + list[i].orderuser + "</td>\n" +
                    "     <td style=\"text-align: center\">" + list[i].ordermoney + "</td>\n" +
                    "     <td style=\"text-align: center\">" + list[i].revicename + "</td>\n" +
                    "     <td style=\"text-align: center\">" + list[i].tel + "</td>\n" +
                    "     <td class=\"td-manage\"  style=\"text-align: center\">\n" +
                    "         <a title=\"编辑订单数据\" onclick=\"x_admin_show('编辑订单数据'," +
                    "'/dispOrder/changeOrder?id=" + list[i].id + "')\" href=\"javascript:;\">\n" +
                    "            <i class=\"layui-icon\">&#xe611;</i>\n" +
                    "         </a>\n" +
                    "         <a title=\"查看订单物流\" onclick=\"x_admin_show('查看订单物流'," +
                    "'/dispOrder/orderWUL?id=" + list[i].id + "')\" href=\"javascript:;\">\n" +
                    "            <i class=\"layui-icon\">&#xe612;</i>\n" +
                    "         </a>\n" +
                    "         <a title=\"删除订单\" onclick=\"deleteOrder('" + list[i].id + "')\" href=\"javascript:;\">\n" +
                    "            <i class=\"layui-icon\">&#xe640;</i>\n" +
                    "         </a>\n" +
                    "     </td>\n" +
                    "</tr>";
            }
            //分页显示
            fenye = "<div>\n";
            if ((+totalPageCount) < 14) {
                for (var i = 1; i < (+totalPageCount + 1); i++) {
                    if ((+currentPage) == i) {//
                        fenye = fenye + "<span class=\"current\">" + i + "</span>\n";
                    } else {
                        fenye = fenye + "<a class=\"num\" " +
                            "href=\"javascript:void(0)\" " +
                            "onclick='serch(\"" + beginordertime + "\",\"" + endordertime + "\",\"" + content + "\",\"" + i + "\")'>" + i + "</a>\n";
                    }
                }
            } else {
                var startPage = 0;
                var endPage = 0;
                if ((+totalPageCount) <= 14) {
                    startPage = 1;
                    endPage = +totalPageCount;
                } else if ((+totalPageCount) > 14) {
                    if ((+currentPage) - 7 > 0) {
                        startPage = +currentPage - 7;
                    } else if ((+currentPage) - 7 <= 0) {
                        startPage = 1;
                    }
                    if ((+currentPage) + 7 > (+totalPageCount)) {
                        endPage = +currentPage;
                    } else if ((+currentPage) + 7 <= (+totalPageCount)) {
                        endPage = (+currentPage) + 7;
                    }
                }
                for (var i = startPage; i <= endPage; i++) {
                    if ((+currentPage) == i) {//
                        fenye = fenye + "<span class=\"current\">" + i + "</span>\n";
                    } else {
                        fenye = fenye + "<a class=\"num\" " +
                            "href=\"javascript:void(0)\" " +
                            "onclick='serch(\"" + beginordertime + "\",\"" + endordertime + "\",\"" + content + "\",\"" + i + "\")'>" + i + "</a>\n";
                    }
                }
            }
            fenye = fenye + "</div>"

            $("#listtbody").html(str);
            $("#totalCount").html("共有数据：" + totalCount + " 条");
            $("#xxlistpage").html(fenye);
        }
    })
}


var deleteOrder = function (id) {
    if (confirm("是否删除该数据")) {
        $.ajax({
            type: 'POST',
            url: "/orderAction/deleteOrder",
            async: false,
            dataType: 'json',
            data: {
                id: id
            }, success: function (e) {
                window.location.href = window.location.href;
            }
        })
    }
}