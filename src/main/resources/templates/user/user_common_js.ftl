<script>
    function follow(btn, entityType, entityId) {
        if($(btn).hasClass("btn-info")) {
            $.ajax({
                type: "POST",
                url: "/user/follow",
                dataType: "json",//预期服务器返回的数据类型
                data: {entityType: entityType, entityId: entityId},
                success: function (data) {
                    if(data.code == 200) {
                        // $(btn).text("已关注").removeClass("btn-info").addClass("btn-secondary");
                        window.location.reload();
                    } else if(data.code == 2001) {
                        alert("用户未登录");
                        window.location.href = "/toLogin";
                    } else {
                        alert("操作失败");
                    }
                }
            })
        } else {
            $.ajax({
                type: "POST",
                url: "/user/unFollow",
                dataType: "json",//预期服务器返回的数据类型
                data: {entityType: entityType, entityId: entityId},
                success: function (data) {
                    if(data.code == 200) {
                        // $(btn).text("关注").removeClass("btn-secondary").addClass("btn-info");
                        window.location.reload();
                    } else if(data.code == 2001) {
                        alert("用户未登录");
                        window.location.href = "/toLogin";
                    } else {
                        alert("操作失败");
                    }
                }
            })

        }
    }
</script>
