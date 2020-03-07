<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${toUser.username}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="/images/touxiang.ico" type="image/x-icon" />
    <style type="text/css">
        .main-contain {
            margin: auto;
            width: 1000px;
            background-color: white;
        }

        #list-button {
            margin-top: 20px;
        }
    </style>
</head>
<body style="background-color: #efefef;">
<#include "../common/navigation.ftl">
<div class="container-fluid main-contain">
    <div class="row" style="padding: 20px">
        <div class="col-sm-3">
            <img class="media-object img-rounded" src="http://images.nowcoder.com/head/511t.png" alt="#" style="height: 160px; width: 160px">
        </div>
        <#include "user_info.ftl">
    </div>
</div>
<div class="container-fluid main-contain" style="margin-top: 20px">
    <div class="row" >
        <div class="col-lg-9 col-md-12 col-sm-12" >
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="/user/question?userId=${toUser.id?c}">提问</a></li>
                <li role="presentation" class="active"><a href="/user/follows?userId=${toUser.id?c}">关注的人</a></li>
                <li role="presentation"><a href="/user/fans?userId=${toUser.id?c}">粉丝</a></li>
                <#if !user.id??>
                <#elseif toUser.id?c = user.id?c>
                    <li role="presentation"><a href="/user/letter">私信</a></li>
                </#if>
            </ul>
            <hr>
            <#list listUser as followee>
                <div class="media">
                    <div class="media-left">
                        <img class="media-object img-rounded" src="${followee.headerUrl}" alt="#" style="height: 40px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading"><a href="/user/question?userId=${followee.id?c}">${followee.username}</a></h4>
                    </div>
                </div>
                <hr>
            </#list>

        </div>
        <#include "user_right_info.ftl">
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<#include "user_common_js.ftl">
</body>
</html>
