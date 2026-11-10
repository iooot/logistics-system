<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>物流订单预览界面</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=request.getContextPath()%>/common/bootstrap/css/bootstrap.min.css"
              rel="stylesheet" media="screen">
    </head>

    <body>
        <div class="container-fluid">

            <div class="row" style="margin-top: 10px;">
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group ">
                        <span class="input-group-addon" style="color:red">订单时间</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.ordertime}"
                               id="ordertime" disabled>
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
                               disabled>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">发货地址</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.sendWU}" disabled>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">收货地址</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.reviceWU}" disabled>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">收件人</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.revicename}" disabled>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">联系方式</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.tel}" disabled>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">物品类型</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.protype}" disabled>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5">
                    <div class="input-group">
                        <span class="input-group-addon" style="color:red">物品重量</span>
                        <input type="text" class="form-control" value="${requestScope.orderInfo.proheight}" disabled>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-10 col-md-10">
                    <div class="input-group">
                        <span class="input-group-addon">备注</span>
                        <textarea type="text" class="form-control" id="bem"
                                  disabled>${requestScope.orderInfo.bem}</textarea>
                    </div>
                </div>
            </div>
        </div>


        <!-- 选择客户 -->
        <div class="modal fade" id="chooseCustomer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">选择客户名称</h4>
                    </div>
                    <c:forEach items="${cusList}" var="customer">
                        <div class="modal-body" style="border-bottom: grey 1px solid" data-dismiss="modal"
                             onclick="choeseCustomer('${customer.id}','${customer.username}')">
                                ${customer.username}
                        </div>
                    </c:forEach>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <script src="<%=request.getContextPath()%>/common/js/jquery-3.2.1.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/common/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/function/order/js/addNewOrder.js" charset="utf-8"></script>
    </body>
</html>