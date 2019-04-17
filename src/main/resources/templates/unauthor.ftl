<!DOCTYPE HTML>
<html>
<head>
    <title>提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <!-- jquery -->
    <script type="text/javascript" src="/sell/js/jquery-1.4.2.min.js"></script>
    <!-- bootstrap -->
    <link rel="stylesheet" href="/sell/css/bootstrap.min.css">

	<style type="text/css">
		html,body{
			height:100%;
			width:100%;
		}
		body{
			background:url('/sell/img/bg.jpg') no-repeat;
			background-size:100% 100%;
			padding-top:100px;
		}
	</style>

    </head>
<body>

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
</body>
<script>
    function goback() {
        window.history.back();
    }
</script>
</html>