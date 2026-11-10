<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>${requestScope.systeminfo.sysname}</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/font.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/xadmin.css">
        <script src="<%=request.getContextPath()%>/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    </head>

    <body>
        <div class="x-body">
            <div class="layui-form">
                <table class="layui-table">
                    <thead>
                        <tr>
                            <th style="width:10%;text-align: center;">序号</th>
                            <th style="width:15%;text-align: center;">回复人</th>
                            <th style="width:25%;text-align: center;">回复内容</th>
                            <th style="width:25%;text-align: center;">回复时间</th>
                            <th style="width:10%;text-align: center;">操作</th>
                        </tr>
                    </thead>
                    <tbody id="xxlisttbody">
                        <c:forEach items="${requestScope.plist}" var="its" varStatus="itss">
                            <tr>
                                <td style="text-align: center">${itss.index+1}</td>
                                <td style="text-align: center">${its.creator}</td>
                                <td style="text-align: center">${its.content}</td>
                                <td style="text-align: center">${its.createtime}</td>
                                <td style="text-align: center">
                                    <a title="删除评论" onclick="DelbbsPl('${its.id}')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
<script>
    var DelbbsPl = function (id) {
        if (confirm("是否删除该数据")) {
            $.ajax({
                type: 'POST',
                url: "/forumAction/DelbbsPl",
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
</script>