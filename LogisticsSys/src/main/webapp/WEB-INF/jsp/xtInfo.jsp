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
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xtinfo.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <![endif]-->
    </head>

    <body>
        <div class="x-body">
            <form class="layui-form">
                <div class="layui-form-item">
                    <label for="sysname" class="layui-form-label">
                        <span class="x-red">*</span>系统名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="sysname" style="width:500px;"
                               autocomplete="off" class="layui-input" value="${requestScope.systemInfo.sysname}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="leibie" class="layui-form-label">
                        <span class="x-red">*</span>类型
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="leibie" style="width:500px;"
                               autocomplete="off" class="layui-input" value="${requestScope.systemInfo.leibie}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="content" class="layui-form-label">
                        <span class="x-red">*</span>内容
                    </label>
                    <div class="layui-input-inline">
                        <textarea type="text" id="content" style="width:500px;height: 400px"
                                  autocomplete="off" class="layui-input">${requestScope.systemInfo.content}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" lay-submit="" onclick="updateSysInfo()">
                        保存
                    </button>
                </div>
            </form>
        </div>

    </body>

</html>