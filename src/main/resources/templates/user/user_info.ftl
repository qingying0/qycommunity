<div class="col-sm-9">
    <h3><span>${toUser.username}</span></h3>
    <div><span>${toUser.email}</span></div>
    <div>创建时间: ${toUser.createTime?string("yyyy-MM-dd HH:mm:ss")}</div>
    <#if !user.id??>
    <#elseif toUser.id?c = user.id?c>
        <#--<div style="float:right; margin-right: 20px"><a class="btn btn-info">修改资料</a> </div>-->
    <#else>
        <div style="float:right; margin-right: 20px"><a href="/user/chat?userId=${toUser.id?c}" class="btn btn-info">私信</a> </div>
        <#if isFollow >
        <div style="float:right; margin-right: 20px"><button onclick="follow(this, 2,  '${toUser.id?c}')" class="btn btn-secondary">已关注</button> </div>
        <#else >
            <div style="float:right; margin-right: 20px"><button onclick="follow(this, 2, '${toUser.id?c}')" class="btn btn-info">关注</button> </div>
        </#if>
    </#if>
</div>
