 <nav class="navbar navbar-default">
    <div class="nk-container" >
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">社区</span>
        </button>
        <a class="navbar-brand" href="/">社区</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="color: white">
        <form class="navbar-form navbar-left" action="/index" method="post">
            <div class="form-group">
            <input type="text" name="search" class="form-control" placeholder="搜索问题">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <#if !user??>
            <#--<li><a href="https://github.com/login/oauth/authorize?client_id=4cc8fa164eb3bf20173a&redirect_uri=http://localhost:8080/oauth/callback&scope=user,public_repo&state=1">登录</a></li>-->
            <li><a href="/register">注册</a> </li>
            <li><a href="/login">登录</a> </li>
            <#else>
            <li><a href="/question/publish">发布</a></li>
            <li><a href="/user/question">消息</a></li>
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="${user.headerUrl}" style="width: 30px; height: 30px"><#--<span class="caret">--></span></a>
            <ul class="dropdown-menu">
                <li><a href="/user/letter?userId=${user.id?c}">个人中心</a></li>
                <li><a href="/logout">退出登录</a></li>
            </ul>
            </li>
            </#if>
        </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
