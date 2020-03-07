<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册</title>

    <style type="text/css">



    </style>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/checkbox.css">
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
    <#include "common/navigation.ftl">
    <div class="main">
        <div class="container pl-5 pr-5 pt-3 pb-3 mt-3 mb-3">
            <h3 class="text-center text-info border-bottom pb-3">注&nbsp;&nbsp;册</h3>
            <form class="mt-5" method="post" action="/register">
                <div class="form-group row">
                    <label for="username" class="col-sm-2 col-form-label text-right">用户名:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" name="username" placeholder="请输入您的用户名!" required>
                    </div>
                </div>
                <div class="form-group row mt-4">
                    <label for="password" class="col-sm-2 col-form-label text-right">密码:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control"
                               id="password" name="password" placeholder="请输入您的密码!" required>
                    </div>
                </div>
                <div class="form-group row mt-4">
                    <label for="confirm-password" class="col-sm-2 col-form-label text-right">确认密码:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="confirmPassword"
                               id="confirm-password" placeholder="请再次输入密码!" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label text-right">邮箱:</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control"
                               id="email" name="email" placeholder="请输入您的邮箱!" required>
                        <#if message??>
                        <div class="invalid-feedback" >
                            ${message}
                        </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row mt-4">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-10 text-center">
                        <button type="submit" class="btn btn-info text-white form-control">立即注册</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>

    // function register() {
    //     var form = $("#loginForm");
    //     form.attr("action", "/register");
    //     form.submit();
    // }
</script>
</body>
</html>
