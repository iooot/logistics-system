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
        <script src="<%=request.getContextPath()%>/common/js/jquery.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xadmin.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/function/userGN/js/userinfo.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <![endif]-->
    </head>

    <body>
        <div class="x-body">
            <div class="layui-form">
                <div class="layui-form-item">
                    <label for="logname" class="layui-form-label">
                        <span class="x-red">*</span>登录账号
                    </label>
                    <div class="layui-input-inline">
                        <input type="hidden" id="id" value="${requestScope.user.id}">
                        <input type="text" id="logname" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="${requestScope.user.logname}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                        <span class="x-red">*</span>用户名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="${requestScope.user.username}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="pwd" class="layui-form-label">
                        <span class="x-red">*</span>登录密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="pwd" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="phone" class="layui-form-label">
                        <span class="x-red">*</span>联系方式
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="phone" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="${requestScope.user.phone}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="email" class="layui-form-label">
                        <span class="x-red">*</span>电子邮箱
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="email" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="${requestScope.user.email}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="sex" class="layui-form-label">
                        <span class="x-red">*</span>性别
                    </label>
                    <div class="layui-input-inline" style="width:500px;">
                        <select type="text" id="sex"
                                autocomplete="off" class="layui-input">
                            <option>${requestScope.user.sex}</option>
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="bem" class="layui-form-label">
                        <span class="x-red">*</span>个人介绍
                    </label>
                    <div class="layui-input-inline">
                    </div>
                    <textarea type="text" id="bem" style="width:500px;height:150px;"
                              autocomplete="off" class="layui-input"
                    > ${requestScope.user.bem}</textarea>
                </div>
                <div class="layui-form-item">
                    <label for="homeAddress" class="layui-form-label">
                        <span class="x-red">*</span>家庭地址
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="homeAddress" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="${requestScope.user.homeAddress}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" onclick="updateUserInfo()">
                        提交注册
                    </button>
                </div>
            </div>
        </div>
    </body>
</html>