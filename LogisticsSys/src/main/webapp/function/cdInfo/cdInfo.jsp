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
        <script type="text/javascript" src="<%=request.getContextPath()%>/function/cdInfo/js/singleTopCd.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <![endif]-->
    </head>

    <body>
        <div class="x-body">
            <div class="layui-form">
                <div class="layui-form-item">
                    <input type="hidden" id="id" value="${requestScope.cdInfo.id}">
                    <label for="cname" class="layui-form-label">
                        <span class="x-red">*</span>菜单名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="cname" style="width:500px;"
                               autocomplete="off" class="layui-input" value="${requestScope.cdInfo.cname}">
                    </div>
                </div>
                <div class="layui-form-item" hidden>
                    <label for="cpath" class="layui-form-label">
                        <span class="x-red">*</span>菜单路径
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="cpath" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="0">
                    </div>
                </div>
                <div class="layui-form-item" hidden>
                    <label for="fnid" class="layui-form-label">
                        <span class="x-red">*</span>父级节点名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="fnid" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="0">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="indx" class="layui-form-label">
                        <span class="x-red">*</span>排序
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="indx" style="width:500px;"
                               autocomplete="off" class="layui-input"
                               value="${requestScope.cdInfo.indx}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="level" class="layui-form-label">
                        <span class="x-red">*</span>菜单层级
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="level" style="width:500px;" disabled
                               autocomplete="off" class="layui-input"
                               value="1">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="nrole" class="layui-form-label">
                        <span class="x-red">*</span>菜单权限
                    </label>
                    <div class="layui-input-inline" style="width:500px;">
                        <select type="text" id="nrole"
                                autocomplete="off" class="layui-input">
                            <c:if test="${requestScope.cdInfo.id ==null}">
                                <option>请选择</option>
                                <c:forEach items="${requestScope.roleList}" var="role">
                                    <option value="${role.vkey}">${role.val}</option>
                                </c:forEach>
                            </c:if>
                            <c:if test="${requestScope.cdInfo.id !=null}">
                                <c:forEach items="${requestScope.roleList}" var="role">
                                    <c:if test="${role.vkey==cdInfo.nrole}">
                                        <option value="${role.vkey}">${role.val}</option>
                                    </c:if>
                                </c:forEach>
                                <c:forEach items="${requestScope.roleList}" var="role">
                                    <c:if test="${role.vkey!=cdInfo.nrole}">
                                        <option value="${role.vkey}">${role.val}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" onclick="updateCdInfo()">
                        保存
                    </button>
                </div>
            </div>
        </div>

    </body>

</html>