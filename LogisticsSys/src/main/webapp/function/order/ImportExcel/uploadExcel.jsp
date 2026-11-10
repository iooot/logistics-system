<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>上传订单数据</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    </head>

    <body>
        <div class="container-fluid">
            <form action="/dispOrder/waitUpload" method="post" enctype="multipart/form-data">
                <div class="row" style="margin-top: 10px;">
                    <div class="col-lg-10 col-md-10">
                        <button type="submit" class="btn btn-default" onclick="sureUpload()">确认上传</button>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-lg-10 col-md-10">
                        <div class="input-group ">
                            <span class="input-group-addon" style="color:red">请选择上传文件</span>
                            <input type="file" class="form-control" name="orderlistfile"/>
                        </div>
                    </div>
                </div>
            </form>

            <div class="jumbotron"
                 style="position: absolute;top:0%;left:0%;
                 display: none;width:98%;height:800px;z-index:1000"
                 id="run">
                <div style="position: relative;top:20%;left:40%;width:100%;height:400px">
                    <img src="<%=request.getContextPath()%>/images/run.gif">
                </div>
            </div>

        </div>
        <script src="<%=request.getContextPath()%>/js/jquery.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>
        <script src="<%=request.getContextPath()%>/BasicFuc/order/js/uploadExcel.js" charset="utf-8"></script>
    </body>
</html>