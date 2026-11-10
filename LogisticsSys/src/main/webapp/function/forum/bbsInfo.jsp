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
        <script type="text/javascript" src="<%=request.getContextPath()%>/function/forum/js/bbsInfo.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    </head>

    <body>
        <div class="x-body">
            <div class="layui-form-item">
                <c:if test="${requestScope.bbs.state=='1'}">
                    <button class="layui-btn" lay-filter="add" onclick="setTop('${requestScope.bbs.id}')">
                        置顶
                    </button>
                </c:if>
                <c:if test="${requestScope.bbs.state=='0'}">
                    <button class="layui-btn" lay-filter="add" onclick="noTop('${requestScope.bbs.id}')">
                        取消置顶
                    </button>
                </c:if>
            </div>
            <div class="layui-form">
                <div class="layui-form-item">
                    <label for="code" class="layui-form-label">
                        <span class="x-red">*</span>帖子名称
                    </label>

                    <div class="layui-input-inline">
                        <input type="text" id="code" style="width:700px;" disabled
                               autocomplete="off" class="layui-input" value="${requestScope.bbs.bbsname}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="jzFrom" class="layui-form-label">
                        <span class="x-red">*</span>帖子类型
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="jzFrom" style="width:700px;" disabled
                               autocomplete="off" class="layui-input" value="${requestScope.bbs.bbstype}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="recipient" class="layui-form-label">
                        <span class="x-red">*</span>帖子内容
                    </label>
                    <div class="layui-input-inline">
                        <textarea type="text" id="recipient" style="width:700px;height:200px;" disabled
                                  autocomplete="off" class="layui-input">${requestScope.bbs.bbscontent}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="bbscreator" class="layui-form-label">
                        <span class="x-red">*</span>帖子创建人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="bbscreator" style="width:700px;" disabled
                               autocomplete="off" class="layui-input" value="${requestScope.bbs.bbscreator}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="ctime" class="layui-form-label">
                        <span class="x-red">*</span>发帖时间
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="ctime" style="width:700px;" disabled
                               autocomplete="off" class="layui-input" value="${requestScope.bbs.bbscreatetime}">
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>