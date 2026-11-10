$(function () {
    serch("", "1");
})
var serch = function (content, page) {
    $.ajax({
        type: 'POST',
        url: "/forumAction/bbsList",
        async: false,
        dataType: 'json',
        data: {
            content: content,
            page: page
        }, success: function (e) {
            var usr = e.usr;
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
                var s = "";
                if (usr.role == "2") {
                    s = "         <a title=\"查看帖子内容\" onclick=\"x_admin_show('查看帖子内容','/dispForumAction/bbs?id=" + list[i].id + "')\" href=\"javascript:;\">\n" +
                        "            <i class=\"layui-icon\">&#xe63c;</i>\n" +
                        "         </a>\n" +
                        "         <a title=\"评论管理\" onclick=\"x_admin_show('评论管理','/dispForumAction/bbspl?id=" + list[i].id + "')\" href=\"javascript:;\">\n" +
                        "            <i class=\"layui-icon\">x</i>\n" +
                        "         </a>\n" +
                        "         <a title=\"删除帖子\" onclick=\"bbsDel('" + list[i].id + "')\" href=\"javascript:;\">\n" +
                        "            <i class=\"layui-icon\">&#xe640;</i>\n" +
                        "         </a>\n";
                } else {
                    s = "         <a title=\"查看帖子内容\" onclick=\"x_admin_show('查看帖子内容','/dispForumAction/bbs?id=" + list[i].id + "')\" href=\"javascript:;\">\n" +
                        "            <i class=\"layui-icon\">&#xe63c;</i>\n" +
                        "         </a>\n";
                }
                var state = list[i].state;
                var zt = "";
                if (state == "0") {
                    zt = "<span style='color:red;font-size: 18px;'>[置顶]</span>";
                }
                str = str +
                    "<tr><td style='text-align: center;'>" + (i + 1 + sum) + "</td>\n" +
                    "     <td style='text-align: center;'>" + list[i].bbstype + "</td>\n" +
                    "     <td style='text-align: center;'>" + zt + list[i].bbsname + "</td>\n" +
                    "     <td style='text-align: center;'>" + list[i].bbscreator + "</td>\n" +
                    "     <td style='text-align: center;'>" + list[i].bbscreatetime + "</td>\n" +
                    "     <td class=\"td-manage\">\n" +
                    s +
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

            $("#xxlisttbody").html(str);
            $("#totalCount").html("共有数据：" + totalCount + " 条");
            $("#xxlistpage").html(fenye);
        }
    })
}

var bbsDel = function (id) {
    if (confirm("是否删除该数据")) {
        $.ajax({
            type: 'POST',
            url: "/forumAction/bbsDel",
            async: false,
            dataType: 'json',
            data: {
                id: id
            }, success: function (e) {
                if ((+e) > 0) {
                    window.location.href = window.location.href;
                } else {
                    alert("删除失败");
                }
            }
        })
    }
}