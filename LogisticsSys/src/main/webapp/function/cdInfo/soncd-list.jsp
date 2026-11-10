<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>系统</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/font.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/xadmin.css">
        <script src="<%=request.getContextPath()%>/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <![endif]-->
    </head>

    <body>
        <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">系统管理</a>
        <a href="">子菜单管理</a>
      </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
               href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <div class="layui-row">
                <div class="layui-form layui-col-md12 x-so">
                </div>
            </div>
            <xblock>
                <button class="layui-btn"
                        onclick="x_admin_show('新增子菜单','/discd/addSonCd?id=xxxxx&fnid=${requestScope.fnid}')"><i
                        class="layui-icon"></i>添加
                </button>
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th style="width:15%;text-align: center">序号</th>
                        <th style="width:25%;text-align: center">菜单名称</th>
                        <th style="width:25%;text-align: center">排序</th>
                        <th style="width:25%;text-align: center">菜单权限</th>
                        <th style="width:10%;text-align: center">操作</th>
                    </tr>
                </thead>
                <tbody id="xxlisttbody">
                    <c:forEach items="${requestScope.cdList}" var="cdInfo" varStatus="s">
                        <tr>
                            <td style="text-align: center">${s.index+1}</td>
                            <td style="text-align: center">${cdInfo.cname}</td>
                            <td style="text-align: center">${cdInfo.indx}</td>
                            <td style="text-align: center">${cdInfo.nrole}</td>
                            <td style="text-align: center">
                                <a title="查看"
                                   onclick="x_admin_show('编辑','/discd/addSonCd?id=${cdInfo.id}&fnid=${requestScope.fnid}')"
                                   href="javascript:;">
                                    <i class="layui-icon">&#xe63c;</i>
                                </a>
                                <a title="删除" onclick="cdDelete('${cdInfo.id}')" href="javascript:;">
                                    <i class="layui-icon">&#xe640;</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
<script>

    var cdDelete = function (id) {
        if (confirm("是否删除该菜单")) {
            $.ajax({
                type: 'POST',
                url: "/home/cdDelete",
                async: false,
                dataType: 'json',
                data: {
                    id: id
                }, success: function (e) {
                    if (+e == 1) {
                        alert("删除成功");
                        window.location.replace(location.href);
                    } else {
                        alert("请删除所有子菜单后再进行一级菜单删除");
                    }
                }
            })
        }
    }
</script>