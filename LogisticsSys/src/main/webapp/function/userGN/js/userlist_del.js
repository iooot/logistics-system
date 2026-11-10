$(function () {
    serch("", "1");
})

var serchContent = function () {
    var content = $("#content").val();
    serch(content, "1");
}


var serch = function (content, page) {
    $.ajax({
        type: 'POST',
        url: "/user/uinfoDel",
        async: false,
        dataType: 'json',
        data: {
            content: content,
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
                    "<tr><td>" + (i + 1 + sum) + "</td>\n" +
                    "     <td>" + list[i].username + "</td>\n" +
                    "     <td>" + list[i].sex + "</td>\n" +
                    "     <td>" + list[i].phone + "</td>\n" +
                    "     <td>" + list[i].homeAddress + "</td>\n" +
                    "     <td class=\"td-manage\">\n" +
                    "         <a title=\"恢复账号\" onclick=\"resetUser('" + list[i].id + "')\" href=\"javascript:;\">\n" +
                    "            <i class=\"layui-icon\">&#xe67c;</i>\n" +
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
                            "onclick='serch(\"" + content + "\",\"" + i + "\")'>" + i + "</a>\n";
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
                            "onclick='serch(\"" + content + "\",\"" + i + "\")'>" + i + "</a>\n";
                    }
                }
            }
            fenye = fenye + "</div>"

            $("#zylisttbody").html(str);
            $("#totalCount").html("共有数据：" + totalCount + " 条");
            $("#xxlistpage").html(fenye);
        }
    })
}

var resetUser = function (id) {
    if (confirm("是否恢复账号?")) {

        $.ajax({
            type: 'POST',
            url: "/user/resetUserinfodel",
            async: false,
            dataType: 'json',
            data: {
                id: id
            }, success: function (e) {
                if (+e == 1) {
                    window.location.replace(location.href);
                }
            }
        })
    }
}