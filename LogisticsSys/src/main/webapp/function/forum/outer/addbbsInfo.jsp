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
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/lib/layui/layui.js"
                charset="utf-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/common/js/xadmin.js"></script>
        <script type="text/javascript"
                src="<%=request.getContextPath()%>/function/forum/outer/js/bbsInfo.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    </head>

    <body>
        <div class="x-body">
            <div class="layui-form-item">
                <button class="layui-btn" lay-filter="add" onclick="addNew()">
                    完成
                </button>

            </div>
            <div class="layui-form">
                <div class="layui-form-item">
                    <label for="bbsname" class="layui-form-label">
                        <span class="x-red">*</span>帖子名称
                    </label>

                    <div class="layui-input-inline">
                        <input type="text" id="bbsname" style="width:500px;"
                               autocomplete="off" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="bbstype" class="layui-form-label">
                        <span class="x-red">*</span>帖子类型
                    </label>
                    <div class="layui-input-inline" style="width:500px;">
                        <select id="bbstype" autocomplete="off" class="layui-input">
                            <option>请选择</option>
                            <option value="知识科普类">知识科普类</option>
                            <option value="新闻资讯类">新闻资讯类</option>
                            <option value="互动问答类">互动问答类</option>
                            <option value="娱乐休闲类">娱乐休闲类</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="bbscontent" class="layui-form-label">
                        <span class="x-red">*</span>帖子内容
                    </label>
                    <div class="layui-input-inline">
                        <textarea type="text" id="bbscontent" style="width:500px;height:200px;"
                                  autocomplete="off" class="layui-input"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>