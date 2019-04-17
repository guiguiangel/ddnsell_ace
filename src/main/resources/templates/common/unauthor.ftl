<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <h3 class="text-center text-warning">
                    403没有访问权限！
                </h3>
                <a href="javascript:void(0);"onclick="goback()" style="font-size: 16px">点击返回</a>
            </div>
        </div>
    </div>
</div>

<script>
    function goback() {
        window.history.back();
    }
</script>
</body>
</html>