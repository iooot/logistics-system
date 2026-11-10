<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <title></title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/login/css/bot.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/login/css/bot2.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/login/css/style.css">


        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/font.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/xadmin.css">
        <script src="<%=request.getContextPath()%>/common/js/jquery.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/common/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xadmin.js"></script>

        <script src="<%=request.getContextPath()%>/login/js/login.js"></script>
    </head>

    <body style="background-image: url('/login/images/bg.png');
    background-size: cover;background-repeat: no-repeat;background-position: center;height: 100vh;margin: 0;">
        <div class="container">
            <div class="row full-height">
                <div class="col-12 text-center align-self-center py-5">
                    <div class="section pb-5 pt-5 pt-sm-2 text-center">
                        <input class="checkbox" type="checkbox" id="reg-log" name="reg-log">
                        <div class="card-3d-wrap mx-auto">
                            <div class="card-3d-wrapper bgk">
                                <div class="card-front">
                                    <div class="center-wrap">
                                        <div class="section text-center">
                                            <h4 class="mb-4 pb-3"
                                                style="font-size: 26px">${requestScope.sysname}</h4>
                                            <div class="form-group">
                                                <input type="email" name="logemail" class="form-style"
                                                       placeholder="请输入账号" id="uname"
                                                       autocomplete="new-password">
                                                <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                                            </div>
                                            <div class="form-group mt-2">
                                                <input type="password" name="logpass" class="form-style"
                                                       placeholder="请输入密码" id="pwd"
                                                       autocomplete="new-password">
                                                <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                                            </div>
                                            <a href="javascript:void(0)" class="btn mt-4"
                                               onclick="loginSys()">登录</a>
                                            <div class="d-flex justify-content-between mt-4 text-left">
                                                <div class="flex-fill">
                                                    <p>没有帐号？<label for="reg-log"
                                                                       onclick="x_admin_show('添加用户信息','/disphome/userInfo','1000','900')">立即注册</label>
                                                    </p>
                                                </div>
                                                <div class="flex-fill text-right">
                                                    <p><a href="javascript:"
                                                          class="forgot link">忘记密码请联系管理员重置</a></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>