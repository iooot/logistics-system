<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${requestScope.systeminfo.sysname}</title>
        <link rel="stylesheet" href="/common/css/font.css">
        <link rel="stylesheet" href="/common/css/xadmin.css">
        <script src="/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script src="/common/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="/common/js/xadmin.js"></script>
        <script src="/common/js/index.js"></script>

    </head>
    <body>
        <!-- 顶部开始 -->
        <div class="container">
            <div class="logo"><a>
                ${requestScope.systeminfo.sysname}</a></div>
            <div class="left_open">
                <i title="展开左侧栏" class="iconfont">&#xe699;</i>
            </div>

            <ul class="layui-nav right" lay-filter="">
                <li class="layui-nav-item">
                    <a href="javascript:;" id="username">admin</a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="/login">退出</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
        <!-- 顶部结束 -->
        <!-- 中部开始 -->
        <!-- 左侧菜单开始 -->
        <div class="left-nav">
            <div id="side-nav">
                <ul id="nav">
                    <c:forEach var="item" items="${items}">
                        <li>
                            <a href="javascript:;">
                                <i class="iconfont">${item.bem}</i>
                                <cite>${item.cname}</cite>
                                <i class="iconfont nav_right">&#xe697;</i>
                            </a>
                            <ul class="sub-menu">
                                <c:forEach var="ite" items="${item.list}">
                                    <li>
                                        <a _href="${ite.cpath}">
                                            <i class="iconfont">&#xe6a7;</i>
                                            <cite>${ite.cname}</cite>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <!-- <div class="x-slide_left"></div> -->
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
            <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
                <ul class="layui-tab-title">
                    <li>我的桌面</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">
                        <iframe src='/welcome/index.html' frameborder="0" scrolling="yes"
                                class="x-iframe"></iframe>
                    </div>
                </div>
            </div>
        </div>
        <div class="page-content-bg"></div>
        <!-- 右侧主体结束 -->
        <!-- 中部结束 -->
        <!-- 底部开始 -->
        <div class="footer">
            <div class="copyright">欢迎登录${requestScope.systeminfo.sysname}</div>
        </div>
    </body>
    <script>
        var gobackdun = function () {
            if (${requestScope.user.id==null}) {
                alert("请选登录");
                window.location.href = "/login";
            } else {
                window.open("/function/reservation/reservationIndex.html","_blank");

            }
        }
    </script>
</html>