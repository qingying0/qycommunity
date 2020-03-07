<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <link rel="icon" href="/images/touxiang.ico" type="image/x-icon" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/global.css">
    <style type="text/css">
        #main-contain {
            margin-top: 30px;
            margin-left: 5%;
            margin-right: 5%;
            background-color: white;
        }
    </style>
</head>
<body style="background-color: #efefef;">
    <#include "common/navigation.ftl">
    <div class="container-fluid" id="main-contain">
        <div class="row" >
            <div class="col-lg-9 col-md-12 col-sm-12" >
                <div class="position-relative">
                </div>
                <hr>
                <#list listQuestion as question>
                    <div class="media">
                        <div class="media-left">
                            <a href="/user/question?userId=${question.userId?c}">
                                <img class="media-object img-rounded" src="${question.headerUrl}" alt="#" style="height: 40px">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading"><a href="/question/${question.id?c}">${question.title}</a></h4>
                            <p class="text-muted">${question.username}发起问题·${question.commentCount}人回复·${question.createTime?string("yyyy-MM-dd HH:mm:ss")}发起</p>
                        </div>
                    </div>
                    <hr>
                </#list>
                <#if !search??>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <#if pageInfo.hasPreviousPage>
                        <li>
                            <a href="/?currentPage=1" aria-label="Previous">
                                <span aria-hidden="true">&laquo;&laquo;</span>
                            </a>
                        </li>
                        <li>
                            <a href="/?currentPage=${pageInfo.prePage}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        </#if>
                        <#list pageInfo.navigatepageNums as element>

                            <#if element==pageInfo.pageNum>
                                <li class="active"><a href="/?currentPage=${element}">${element}</a></li>
                            <#else >
                                <li><a href="/?currentPage=${element}">${element}</a></li>
                            </#if>
                        </#list>
                        <#if pageInfo.hasNextPage>
                        <li>
                            <a href="/?currentPage=${pageInfo.nextPage}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li>
                            <a href="/?currentPage=${pageInfo.pages}" aria-label="Next">
                                <span aria-hidden="true">&raquo;&raquo;</span>
                            </a>
                        </li>
                        </#if>
                    </ul>
                </nav>
                <#else >
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <#if pageInfo.hasPreviousPage>
                            <li>
                                <a href="/?currentPage=1&search=${search}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;&laquo;</span>
                                </a>
                            </li>
                            <li>
                                <a href="/?currentPage=${pageInfo.prePage}&search=${search}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </#if>
                        <#list pageInfo.navigatepageNums as element>
                            <#if element==pageInfo.pageNum>
                                <li class="active"><a href="/?currentPage=${element}&search=${search}">${element}</a></li>
                            <#else >
                                <li><a href="/?currentPage=${element}&search=${search}">${element}</a></li>
                            </#if>
                        </#list>
                        <#if pageInfo.hasNextPage>
                            <li>
                                <a href="/?currentPage=${pageInfo.nextPage}&search=${search}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <li>
                                <a href="/?currentPage=${pageInfo.pages}&search=${search}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;&raquo;</span>
                                </a>
                            </li>
                        </#if>
                    </ul>
                </nav>
                </#if>

            </div>
            <div class="col-lg-3 col-md-12 col-sm-12" >
                <h3 style="margin-left: 15px;margin-bottom: 15px">热门问题</h3>
                <ul>
                    <#list listHotQuestion as question>
                        <li><h4 class="media-heading"><a href="/question/${question.id?c}">${question.title}</a></h4></li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>
