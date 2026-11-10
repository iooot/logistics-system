<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${requestScope.systeminfo.sysname}</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <link rel="stylesheet" href="css/font.css">
        <link rel="stylesheet" href="css/xadmin.css">
        <script src="js/jquery.js" charset="utf-8"></script>
        <script src="lib/layui/layui.js" charset="utf-8"></script>
        <script src="js/login.js"></script>
        <script type="text/javascript" src="../js/xadmin.js"></script>

    </head>
    <body class="login-bg" style="background-color:yellow;">
        <div class="login">
            <div class="message">${requestScope.systeminfo.sysname}</div>
            <div id="darkbannerwrap"></div>
            <div method="post" class="layui-form">
                <input name="username" id="uname" placeholder="用户名" type="text" value="admin" class="layui-input">
                <hr class="hr15">
                <input name="password" id="pwd" placeholder="密码" type="password" autocomplete="new-password"
                       class="layui-input">
                <hr class="hr15">
                <input value="登录" lay-submit style="width:100%;" type="submit" onclick="loginSys()">
                <input value="注册" lay-submit style="width:100%;margin-top:15px;" type="submit"
                       onclick="x_admin_show('添加用户信息','/disphome/userInfo')">
                <hr class="hr20">
            </div>
        </div>

        <!-- 底部结束 -->

    </body>
</html>