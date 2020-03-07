<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/global.css">
    <link rel="stylesheet" href="/css/login.css">

    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
</head>
<body>
<#include "common/navigation.ftl">
<div class="main">

    <div class="container pl-5 pr-5 pt-3 pb-3 mt-3 mb-3">
        <h3 class="text-center text-info border-bottom pb-3">登&nbsp;&nbsp;录</h3>
        <form class="mt-5" method="post" action="/login">
            <div class="form-group row">
                <label for="username" class="col-sm-2 col-form-label text-right">账号:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control"
                           id="username" name="username" placeholder="请输入您的账号!" required>
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
                <label for="verifycode" class="col-sm-2 col-form-label text-right">验证码:</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control"
                           id="kaptcha" name="kaptcha" placeholder="请输入验证码!">
                </div>
                <div class="col-sm-4">
                    <img id="kaptcha" src="/kaptcha" style="width:100px;height:40px;" class="mr-2"/>
                    <a href="" onclick="refresh_kaptcha()" class="font-size-12 align-bottom">刷新验证码</a>
                </div>
            </div>
            <div class="form-group row mt-4">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <input type="checkbox" id="remember-me" name="rememberme">
                    <label class="form-check-label" for="remember-me">记住我</label>
                    <#--<a href="forget.html" class="text-danger float-right">忘记密码?</a>-->
                </div>
            </div>
            <#if message??>
                <div class="invalid-feedback" >
                    ${message}
                </div>
            </#if>
            <div class="form-group row mt-4">
                <div class="col-sm-2"></div>
                <div class="col-sm-10 text-center">
                    <button type="submit" class="btn btn-info text-white form-control">立即登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>

    function refresh_kaptcha() {
        var path = "/kaptcha?p=" + Math.random();
        $("#kaptcha").attr("src", path);
    }

    // function register() {
    //     var form = $("#loginForm");
    //     form.attr("action", "/register");
    //     form.submit();
    // }
</script>
</body>
</html>
