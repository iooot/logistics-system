var projectName;

$(function () {
    getRequestUrl();
    serch("", "1");
})

var serchContent = function () {
    var content = $("#content").val();
    serch(content, "1");
}


var serch = function (content, page) {
    $.ajax({
        type: 'POST',
        url: "/authorityAction/roleList",
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
                    "     <td>" + list[i].val + "</td>\n" +
                    "     <td>" + list[i].vkey + "</td>\n" +
                    "     <td>" + list[i].bem + "</td>\n" +
                    "     <td>" + list[i].createtime + "</td>\n" +
                    "     <td class=\"td-manage\">\n" +
                    "         <a title=\"查看\" onclick=\"x_admin_show('编辑','/dispAuthority/newRole?id=" + list[i].id + "')\" href=\"javascript:;\">\n" +
                    "            <i class=\"layui-icon\">&#xe63c;</i>\n" +
                    "         </a>\n" +
                    "         <a title=\"删除\" onclick=\"delRole('" + list[i].id + "')\" href=\"javascript:;\">\n" +
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


//获取项目名称
var getRequestUrl = function () {
    var pathName = window.document.location.pathname;
    projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
}


var delRole = function (id) {
    confirm("是否删除该数据")
    {
        $.ajax({
            type: 'POST',
            url: "/authorityAction/delRole",
            async: false,
            dataType: 'json',
            data: {
                id: id
            }, success: function (e) {
                if ((+e) > 0) {
                    alert("删除成功");
                    window.location.href = window.location.href;
                } else {
                    alert("删除失败");
                }
            }
        })
    }
}