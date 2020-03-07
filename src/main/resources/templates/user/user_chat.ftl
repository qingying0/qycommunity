<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>私信</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="/images/touxiang.ico" type="image/x-icon" />

    <style type="text/css">
        .main-contain {
            margin: auto;
            width: 1000px;
            background-color: white;
        }
    </style>
</head>
<body style="background-color: #efefef;">
<#include "../common/navigation.ftl">
<div class="container-fluid main-contain" >
    <div class="row" >
        <div class="col-8" style="margin-left: 20px">
            <h4><b class="square"></b> 来自 <i class="text-success">${toUser.username}</i> 的私信</h4>
        </div>
        <div class="col-4 text-right" style="margin-right: 20px">
            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#sendModal">给TA私信</button>
        </div>
        <div class="modal fade" id="sendModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">发私信</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="messageForm">
                            <input type="number" value="${toUser.id?c}" name="toId" hidden>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">内容：</label>
                                <textarea class="form-control" id="message-text" name="content" rows="10"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" onclick="submit()" id="sendBtn">发送</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <ul>
        <#list listMessage as message>
        <li>
            <div class="media" style="margin-top: 20px">
                <div class="media-left">
                    <a href="profile.html" style="display: inline!important;">
                        <img src="<#if toUser.id?c = message.fromId?c>${toUser.headerUrl}<#else >${user.headerUrl}</#if>" class="mr-4 rounded-circle user-header" alt="用户头像" >
                    </a>
                </div>
                <div class="media-body" style="margin-left: 20px; background-color: white;padding: 10px">
                    <h4 class="media-heading">
                        <strong class="mr-auto">
                            <#if toUser.id?c = message.fromId?c>
                                ${toUser.username}
                            <#else >
                                ${user.username}
                            </#if>
                        </strong>
                        <small>${message.createTime?string("yyyy-MM-dd HH:mm:ss")}</small>
                    </h4>
                    <div class="toast-body">
                        ${message.content}
                    </div>
                </div>
            </div>
        </li>
        </#list>
    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    function submit() {
        $.ajax({
            type: "POST",
            url: "/message",
            dataType: "json",//预期服务器返回的数据类型
            data: $("#messageForm").serialize(),
            success: function (data) {
                if(data.code == 200) {
                    alert("发送成功");
                    location.reload();
                } else if(data.code == 2001) {
                    alert("用户未登录");
                    window.location.href = "/toLogin";
                }
            }
        })
    }
</script>
</body>
</html>
