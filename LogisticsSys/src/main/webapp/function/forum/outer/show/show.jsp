<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>查看帖子</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/bootstrap/css/bootstrap.css">
        <style>
            .container {
                width: 1000px;
            }

            .commentbox {
                width: 900px;
                margin: 20px auto;
            }

            .mytextarea {
                width: 100%;
                overflow: auto;
                word-break: break-all;
                height: 100px;
                color: #000;
                font-size: 1em;
                resize: none;
            }

            .comment-list {
                width: 900px;
                margin: 20px auto;
                clear: both;
                padding-top: 20px;
            }

            .comment-list .comment-info {
                position: relative;
                margin-bottom: 20px;
                margin-bottom: 20px;
                border-bottom: 1px solid #ccc;
            }

            .comment-list .comment-info header {
                width: 10%;
                position: absolute;
            }

            .comment-list .comment-info header img {
                width: 100%;
                border-radius: 50%;
                padding: 5px;
            }

            .comment-list .comment-info .comment-right {
                padding: 5px 0px 5px 11%;
            }

            .comment-list .comment-info .comment-right h3 {
                margin: 5px 0px;
            }

            .comment-list .comment-info .comment-right .comment-content-header {
                height: 25px;
            }

            .comment-list .comment-info .comment-right .comment-content-header span, .comment-list .comment-info .comment-right .comment-content-footer span {
                padding-right: 2em;
                color: #aaa;
            }

            .comment-list .comment-info .comment-right .comment-content-header span, .comment-list .comment-info .comment-right .comment-content-footer span.reply-btn, .send, .reply-list-btn {
                cursor: pointer;
            }

            .comment-list .comment-info .comment-right .reply-list {
                border-left: 3px solid #ccc;
                padding-left: 7px;
            }

            .comment-list .comment-info .comment-right .reply-list .reply {
                border-bottom: 1px dashed #ccc;
            }

            .comment-list .comment-info .comment-right .reply-list .reply div span {
                padding-left: 10px;
            }

            .comment-list .comment-info .comment-right .reply-list .reply p span {
                padding-right: 2em;
                color: #aaa;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="container">

                <div class="row" style="height:400px;margin-left:3%;width:94%;background-color: #fbfbfb;">
                    <div class="col-xs-12" style="background-color: #f2f2f2;">
                        <h3>${requestScope.bbs.bbsname}
                            <small>${requestScope.bbs.bbscreator}</small>
                            <small>${requestScope.bbs.bbscreatetime}</small>
                        </h3>
                    </div>
                    <div class="col-xs-12">
                        <h4>${requestScope.bbs.bbscontent}</h4>
                    </div>
                </div>

                <div class="row">
                    <div class="commentbox">
                        <textarea cols="80" rows="50" placeholder="来说几句吧......" class="mytextarea"
                                  id="content"></textarea>
                        <div class="btn btn-info pull-right" id="comment">评论</div>
                    </div>
                    <div class="comment-list">
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/function/forum/outer/show/js/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/function/forum/outer/show/js/jquery.comment.js"></script>
        <script src="<%=request.getContextPath()%>/common/bootstrap/js/bootstrap.js"></script>
        <script>
            //初始化数据
            var arr = [
                <c:forEach items="${requestScope.list}" var="its" varStatus="tess">
                {
                    id: ${tess.index+1},
                    img: "<%=request.getContextPath()%>/function/forum/outer/show/images/img.jpg",
                    replyName: "${its.creator}",
                    beReplyName: "匿名",
                    content: "${its.content}",
                    time: "${its.createtime}",
                    address: "",
                    osname: "",
                    browse: "",
                    replyBody: []
                },
                </c:forEach>
            ];
            $(function () {
                $(".comment-list").addCommentList({data: arr, add: ""});
                $("#comment").click(function () {
                    var content = $('#content').val();
                    $.ajax({
                        type: 'POST',
                        url: "/forumAction/addNewPl",
                        async: false,
                        dataType: 'json',
                        data: {
                            id: '${requestScope.bbs.id}',
                            content: content
                        }, success: function (e) {
                            if ((+e) > 0) {
                                alert("评论成功");
                                window.location.href = window.location.href;
                            } else {
                                alert("评论失败");
                            }
                        }
                    })
                });
            })

        </script>
    </body>
</html>