<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>新增订单物流信息</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=request.getContextPath()%>/common/bootstrap/css/bootstrap.min.css"
              rel="stylesheet" media="screen">
    </head>

    <body>
        <div class="container-fluid">

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-10 col-md-10">
                    <button type="button" class="btn btn-default" onclick="sendOrderWU()">模拟物流信息写入</button>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-10 col-md-10">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">物流订单</span>
                        <select class="form-control" id="ordertitle">
                            <option>请选择</option>
                            <c:forEach items="${orderlist}" var="order" varStatus="ind">
                                <option>
                                    <td>${order.ordernum}</td>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">物流时间</span>
                        <input type="datetime-local" class="form-control" id="ordertime">
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">物流状态</span>
                        <select class="form-control" id="orderstute">
                            <option>正常流转</option>
                            <option>物流退单</option>
                            <option>订单送达</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-10 col-md-10">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">到达地点</span>
                        <input type="text" class="form-control" id="addr">
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/common/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/function/order/js/addNewOrderWU.js" charset="utf-8"></script>
    </body>
</html>