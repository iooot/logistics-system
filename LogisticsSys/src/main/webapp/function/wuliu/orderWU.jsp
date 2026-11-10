<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>订单物流信息</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/font.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/common/css/xadmin.css">
    </head>

    <body>
        <div class="x-body">
            <table class="layui-table">
                <thead>
                    <tr>
                        <th style="text-align: center;width:5%;">序号</th>
                        <th style="text-align: center;width:15%;">时间</th>
                        <th style="text-align: center;width:80%;">物流信息</th>
                    </tr>
                </thead>
                <tbody id="listtbody" style="overflow-x:auto">
                        <c:forEach items="${addrList}" var="addr" varStatus="ind">
                            <tr>
                                <td style="text-align: center"> ${ind.index+1}</td>
                                <td style="text-align: center"> ${addr.createtime}</td>
                                <td>${addr.cartaddress}</td>
                            </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="<%=request.getContextPath()%>/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/common/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/function/order/js/addNewOrder.js" charset="utf-8"></script>
    </body>
</html>