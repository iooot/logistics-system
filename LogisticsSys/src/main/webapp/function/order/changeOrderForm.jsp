<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>编辑订单表单</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=request.getContextPath()%>/common/bootstrap/css/bootstrap.min.css" rel="stylesheet"
              media="screen">
    </head>

    <body>
        <div class="container-fluid">

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-10 col-md-10">
                    <button type="button" class="btn btn-default" onclick="optionOrder()">修改</button>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">订单时间</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.ordertime}"
                               id="ordertime" disabled>
                        <input type="hidden" value="${requestScope.orderInfo.id}" id="id">
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">下单用户</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.orderuser}"
                               id="orderuser" disabled>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">订单编号</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.ordernum}"
                               id="ordernum" disabled>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">物流费用</span>
                        <input type="number" class="form-control" value="${requestScope.orderInfo.ordermoney}"
                               id="ordermoney">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">发货地址</span>
                        <input type="text" class="form-control" id="sendWU" value="${requestScope.orderInfo.sendWU}">
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">收货地址</span>
                        <input type="text" class="form-control" id="reviceWU"
                               value="${requestScope.orderInfo.reviceWU}">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">收件人</span>
                        <input type="text" class="form-control" id="revicename"
                               value="${requestScope.orderInfo.revicename}">
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">联系方式</span>
                        <input type="text" class="form-control" id="tel" value="${requestScope.orderInfo.tel}">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">物品类型</span>
                        <select type="text" class="form-control" id="protype">
                            <option>${requestScope.orderInfo.protype}</option>
                            <option>文件</option>
                            <option>电器</option>
                            <option>衣物</option>
                            <option>食品</option>
                            <option>其他</option>
                        </select>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">物品重量</span>
                        <input type="text" class="form-control" id="proheight"
                               value="${requestScope.orderInfo.proheight}">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-10 col-md-10">
                    <div class="input-group">
                        <span class="input-group-addon">备注</span>
                        <textarea type="text" class="form-control" id="bem">${requestScope.orderInfo.bem}</textarea>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/common/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/function/order/js/changeOrder.js" charset="utf-8"></script>
    </body>
</html>