<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>问题发布</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/editormd.min.css" rel="stylesheet">
    <link rel="icon" href="/images/touxiang.ico" type="image/x-icon" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="/js/editormd.min.js"></script>
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
    </style>
</head>
<body style="background-color: #efefef;">
    <#include "../common/navigation.ftl">
    <div class="container-fluid" id="main-contain">
        <div class="row" >
            <div class="col-lg-9 col-md-12 col-sm-12" ><#if qid??>
                <h2><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>编辑</h2>
                <#else >
                <h2><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>发起</h2>
                </#if>
                <form action="/question/publish" id="questionForm" method="post" >
                    <#if qid??>
                    <input type="number" name="qid" value="${qid}" hidden>
                    </#if>
                    <div class="form-group" >
                        <div class="form-group ol-lg-6 col-md-6 col-sm-6">
                            <label class="sr-only" for="title">问题标题</label>
                            <input type="text" name="title" class="form-control " id="title"  placeholder="标题" <#if question??>value="${question.title}"</#if>>
                        </div>
                        <div class="form-group ol-lg-6 col-md-6 col-sm-6">
                            <label class="col-sm-2 control-label" style="font-family:Arial,Helvetica,sans-serif;vertical-align:middle;font-weight:normal;margin-top: 5px">
                                分类
                            </label>
                            <div class="col-sm-10">
                                <select name="categoryId" class="form-control"  style="width: 100%">
                                    <#list listCategory as category>
                                        <option value="${category.id}">${category.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="content">问题描述</label>
                        <div id="question-editor">
                            <textarea style="display:none;" name="content" id="content"><#if question??>${question.content}</#if></textarea>
                            <script type="text/javascript">
                                $(function() {
                                    var editor = editormd("question-editor", {
                                        width  : "100%",
                                        height : 350,
                                        path   : "/lib/"
                                    });
                                });
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="tag">添加标签</label>
                        <input type="text" name="tags" class="form-control" id="tags"  placeholder="选择一个或者多个合适的标签,用,(英文逗号)分开不同的标签" <#if question??>value="${question.tag}"</#if>>
                    </div>
                    <#if message??>
                        <div class="alert alert-success" role="alert">${message}</div>
                    </#if>
                    <#if error??>
                    <div class="alert alert-danger" role="alert">${error}</div>
                    </#if>
                    <#--<button id="push-button" type="submit" class="btn btn-success">发布</button>-->
                    <#if qid??>
                    <a href="/question/${qid}" class="btn btn-default">返回问题页面</a>
                    </#if>
                </form>
                <button type="button" onclick="submit()" class="btn btn-success">发布</button>
            </div>
            <div class="col-lg-3 col-md-12 col-sm-12" >
                <h3>问题发布指南</h3>
                <div>* 问题标题：请用简洁的语言描述你的发布的问题，不超过25字</div>
                <div>* 问题补充，详细补充您的问题内容，并提供一些相关的资料</div>
                <div>* 选择标签：选择一个或者多个合适的标签,用,(英文逗号)分开不同的标签</div>
            </div>
        </div>
    </div>

<script>
    function submit() {
        $.ajax({
            type: "POST",
            url: "/question/publish",
            dataType: "json",//预期服务器返回的数据类型
            data: $("#questionForm").serialize(),
            success: function (data) {
                if(data.code == 200) {
                    alert("发布成功");
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
