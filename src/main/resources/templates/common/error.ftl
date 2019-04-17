<html>
<head>
    <meta charset="utf-8">
    <title>错误提示</title>
    <link rel="stylesheet" href="/sell/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    错误!
                </h4> <strong>${msg}</strong>
                <#if url??>
                     <a href="${url!''}" class="alert-link">3s后自动跳转</a>
                    <#else>
                    <a href="javascript:void(0);"onclick="goback()" style="font-size: 16px">点击返回</a>
                </#if>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    <#if url??>
        setTimeout('location.href="${url}"', 3000);
    </#if>
    function goback() {
        window.history.back();
    }
</script>

</html>