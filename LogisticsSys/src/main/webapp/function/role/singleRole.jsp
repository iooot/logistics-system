<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>角色管理</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/font.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/xadmin.css">
        <script src="<%=request.getContextPath()%>/common/js/jquery.js" charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/lib/layui/layui.js"
                charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xadmin.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/function/role/js/singleRole.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <![endif]-->
    </head>

    <body>
        <div class="x-body">
            <div class="layui-form">
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" lay-submit="" onclick="saveRole()">
                        保存
                    </button>
                </div>
                <div class="layui-form-item">
                    <label for="val" class="layui-form-label">
                        <span class="x-red">*</span>角色名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="val" style="width:500px;"
                               autocomplete="off" class="layui-input" value="${requestScope.role.val}">
                        <input type="hidden" id="id" value="${requestScope.role.id}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="vkey" class="layui-form-label">
                        <span class="x-red">*</span>角色代码
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="vkey" style="width:500px;" disabled
                               autocomplete="off" class="layui-input" value="${requestScope.role.vkey}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="dicName" class="layui-form-label">
                        <span class="x-red">*</span>所属字典
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="dicName" style="width:500px;"
                               autocomplete="off" class="layui-input" value="用户权限" disabled>
                        <input type="hidden" id="did" value="1">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="dicName" class="layui-form-label">
                        <span class="x-red">*</span>备注
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="bem" style="width:500px;"
                               autocomplete="off" class="layui-input" value="${requestScope.role.bem}">
                    </div>
                </div>
            </div>
        </div>
        </div>
    </body>
</html>