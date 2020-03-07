<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${question.title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/editormd.preview.css" />
    <link rel="icon" href="/images/touxiang.ico" type="image/x-icon" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="/js/editormd.js"></script>
    <script src="/lib/marked.min.js"></script>
    <script src="/lib/prettify.min.js"></script>
    <style type="text/css">
        #main-contain {
            margin-top: 30px;
            margin-left: 5%;
            margin-right: 5%;
            background-color: white;
        }
        #push-button {
            float: right;
            margin-bottom: 10px;
        }
        .white-button {
            color: #999;
            font-size: 13px;
        }
        .menu .icon {
            margin-right: 10px;
            font-size: 15px;
            cursor: pointer;
        }
        .menu .icon:hover {
            color: #499ef3;
        }
        .d-inline {
            display: inline!important;
        }

        .float-right {
            float: right!important;
        }
    </style>
</head>
<body style="background-color: #efefef;">
<#include "../common/navigation.ftl">
<div class="container-fluid" id="main-contain">
    <div class="row" >
        <div class="col-lg-9 col-md-12 col-sm-12" >
            <h3>${question.title}</h3>
            <p class="text-muted">
                作者：<span>${question.username}|</span>
                发布于:<span>${question.createTime?string("yyyy-MM-dd HH:mm:ss")}|</span>
                <#--浏览数:<span>${question.viewCount}</span></p>-->
            <hr>
            <div class="col-lg-12 col-md-12 col-sm-12" style="margin-left: 15px">
                <div id="markdown-view">
                    <textarea style="display:none;">${question.content}</textarea>
                </div>
                <script type="text/javascript">
                    $(function() {
                        editormd.markdownToHTML("markdown-view")
                    });
                </script>
                <hr>
            </div>

            <div class="col-lg-12 col-md-12 col-sm-12" style="margin-left: 15px">
                <#list question.tags?split(",") as t>
                    <span class="label label-info"  style="margin-right: 10px"><span class="glyphicon glyphicon-tags"></span> ${t}</span>
                </#list>
                <hr>
            </div>
            <#if user??>
                <#if question.userId?c=user.id?c>
                <a href="/question/edit/${question.id?c}" class="white-button"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>
                </#if>
            <hr>
            <h3>${question.commentCount}条回复</h3>
            <hr>
            <#list listComment as comment>
                <div class="col-lg-12 col-md-12 col-sm-12" >
                    <div class="media">
                        <div class="media-left">
                            <a href="/user/question?userId=${comment.userId?c}">
                                <img class="media-object img-rounded" src="${comment.headerUrl}" alt="#" style="height: 40px">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${comment.username}</h4>
                            ${comment.content}
                            <div class="menu" style="color: #999">
                                <span>${comment.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                <ul class="d-inline float-right">
                                    <li class="d-inline ml-2"><a href="javascript:;" onclick="like(this, '${comment.userId?c}', 1, '${comment.id?c}')" class="text-primary">
                                        <b><#if comment.likeStatus = 0>赞<#else>已赞</#if></b><i>${comment.likeCount}</i>
                                        </a>
                                    </li>
                                    <li class="d-inline ml-2">|</li>
                                    <li class="d-inline ml-2"><a href="#collapse${comment.id?c}" data-toggle="collapse" class="text-primary">回复</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 collapse" id="collapse${comment.id?c}">
                        <div class="well">
                            <#list comment.listReply as reply>
                            <div class="media">
                                <div class="media-left">
                                    <a href="/user/question?userId=${reply.userId?c}">
                                        <img class="media-object img-rounded" src="${reply.headerUrl}" alt="#" style="height: 40px">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <#if reply.targetId?c = 0?c>
                                    <h5 class="media-heading"><a href="#">${reply.username}</a> </h5>
                                    <#else >
                                    <h5 class="media-heading"><a href="#">${reply.username}</a> 回复 <a href="#">${reply.targetName}</a></h5>
                                    </#if>
                                    ${reply.content}
                                    <div class="menu" style="color: #999">
                                        <span>${reply.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                        <ul class="d-inline float-right">
                                            <li class="d-inline ml-2"><a href="javascript:;" onclick="like(this, '${reply.userId?c}', 1, '${reply.id?c}')" class="text-primary">
                                                    <b><#if reply.likeStatus = 0>赞<#else>已赞</#if></b><i>${reply.likeCount}</i>
                                                </a></li>
                                            <li class="d-inline ml-2">|</li>
                                            <li class="d-inline ml-2"><a href="#${reply.id?c}" data-toggle="collapse" class="text-primary">回复</a></li>
                                        </ul>
                                        <div id="${reply.id?c}" class="mt-4 collapse" style="margin-top: 20px">
                                            <form action="/comment/${question.id?c}" method="post">
                                            <div class="d-inline">
                                                <input type="number" name="entityId" value="${comment.id?c}" hidden>
                                                <input type="number" name="entityType" value="2" hidden>
                                                <input type="number" name="targetId" value="${reply.userId?c}" hidden>
                                                <input type="text" name="content" placeholder="回复${reply.username}"/>
                                            </div>
                                            <div class="d-inline" style="margin-left: 10px; margin-bottom: 10px">
                                                <button type="submit" class="btn btn-primary btn-sm" onclick="#">&nbsp;&nbsp;回&nbsp;&nbsp;复&nbsp;&nbsp;</button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </#list >
                            <form id="commentForm" action="/comment/${question.id?c}" method="post" style="margin-top: 15px">
                                <input type="number" name="entityId" value="${comment.id?c}" hidden>
                                <input type="number" name="entityType" value="2" hidden>
                                <input type="number" name="targetId" value="0" hidden>
                                <textarea class="form-control" rows="2" name="content" placeholder="评论一下吧"></textarea>
                                <button type="submit" class="btn btn-success" style="margin-top: 15px">回复</button>
                            </form>

                        </div>
                    </div>
                    <hr>
                </div>
            </#list>
            <hr>
            <div class="col-lg-12 col-md-12 col-sm-12" >
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object img-rounded" src="${user.headerUrl}" alt="#" style="height: 40px">
                        </a>
                    </div>
                    <div class="media-body" style="margin-top: 10px">
                        <h4 class="media-heading">${user.username}</h4>
                    </div>
                </div>
                <form id="commentForm" action="/comment/${question.id?c}" method="post" style="margin-top: 15px">
                    <input type="number" name="entityId" value="${question.id?c}" hidden>
                    <input type="number" name="entityType" value="1" hidden>
                    <input type="number" name="targetId" value="0" hidden>
                    <textarea class="form-control" rows="5" name="content"></textarea>
                    <button type="submit" class="btn btn-success" style="margin-top: 15px">回复</button>
                </form>

            </div>
            </#if>
        </div>



        <div class="col-lg-3 col-md-12 col-sm-12" >
            <h4>发起人</h4>
            <div class="media">
                <div class="media-left">
                    <a href="/user/question?userId=${question.userId}">
                        <img class="media-object img-rounded" src="${question.headerUrl}" alt="#" style="height: 40px">
                    </a>
                </div>
                <div class="media-body">
                    <h4 class="media-heading">${question.username}</h4>
                </div>
            </div>
            <hr>
            <#--<h4>相关话题</h4>-->
            <#--<div class="col-lg-12 col-md-12 col-sm-12">-->
                <#--<ul>-->
                <#--<#list listConcernQuestion as question>-->
                    <#--<li><h4 class="media-heading"><a href="/question/${question.id?c}">${question.title}</a></h4></li>-->
                <#--</#list>-->
                <#--</ul>-->
            <#--</div>-->
        </div>
    </div>
</div>



<script>
    function like(btn, userId, entityType, entityId) {

        $.ajax({
            type: "POST",
            url: "/user/like",
            dataType: "json",//预期服务器返回的数据类型
            data: {userId: userId,entityType: entityType, entityId: entityId},
            success: function (data) {
                if(data.code == 200) {
                    alert("操作成功");
                    console.info(data.data.count);
                    console.info(data.data.status);
                    $(btn).children("b").text(data.data.status == 0 ? "赞" : "已赞");
                    $(btn).children("i").text(data.data.count);
                } else if(data.code == 2001) {
                    alert("用户未登录");
                    window.location.href = "/toLogin";
                } else {
                    alert("操作失败");
                }
            }
        })
    }

</script>
</body>
</html>
