<!DOCTYPE HTML>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <#assign rootPath = request.contextPath/>
    <!-- jquery -->
    <script type="text/javascript" src="${rootPath}/js/jquery.min.js"></script>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${rootPath}/css/bootstrap.min.css">
    <!-- jquery-validator -->
    <script type="text/javascript"  src="${rootPath}/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript"  src="${rootPath}/jquery-validation/localization/messages_zh.min.js"></script>
    <!-- layer -->
    <script type="text/javascript"  src="${rootPath}/layer/layer.js"></script>
    <!-- md5.js -->
    <script type="text/javascript"  src="${rootPath}/js/md5.min.js"></script>
    <!-- common.js -->
    <script type="text/javascript"  src="${rootPath}/js/common.js"></script>

	<style type="text/css">
		html,body{
			height:100%;
			width:100%;
		}
		body{
			background:url('${rootPath}/img/bg.jpg') no-repeat;
			background-size:100% 100%;
			padding-top:100px;
		}
	</style>

    </head>
<body>

<form name="loginForm" id="loginForm" method="post"  style="width:30%; margin:0 auto;">

	<h2 style="text-align:center; margin-bottom: 20px">用户登录</h2>
	
	<div class="form-group">
       	<div class="row">
	       	<label class="form-label col-md-4">请输入手机号码</label>
	        <div class="col-md-8">
	        	<input id="username" name = "username" class="form-control" type="text" placeholder="用户账号" required="true"  minlength="5" maxlength="11" />
	    	</div>
	    	<div class="col-md-1">
	    	</div>
    	</div>
    </div>
    
    <div class="form-group">
     		<div class="row">
		        <label class="form-label col-md-4">请输入密码</label>
		        <div class="col-md-8">
		        	<input id="password" name="password" class="form-control" type="password"  placeholder="密码" required="true" minlength="6" maxlength="16" />
	       		</div>
      		</div>
	</div>
	
	<div class="row" style="margin-top:40px;">
		     	<div class="col-md-6">
	       	 		<button class="btn btn-primary btn-block" type="reset" onclick="reset()">重置</button>
	       	 	</div>
	       	 	<div class="col-md-6">
	       	 		<button class="btn btn-primary btn-block" type="submit" onclick="login()">登录</button>
	       	 	</div>
	 </div>
	 
</form>
</body>
<script>
function login(){
	 $("#loginForm").validate({
        submitHandler:function(form){
             doLogin();
         }
     });
}
function doLogin(){
	g_showLoading();

	//return;
	var inputPass = $("#password").val();
	var salt = g_passsword_salt;
	var str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
	var password = md5(str);
	
	$.ajax({
		url: "${rootPath}/seller/user/login",
	    type: "GET",
	    data:{
            username: $("#username").val(),
	    	password: password
	    },
	    success:function(data){
	    	layer.closeAll();
	    	if(data.code == 0){
	    		layer.msg("成功");
	    		window.location.href="${rootPath}/seller/product/list";
	    	}else{
	    		layer.msg(data.msg);
	    	}
	    },
	    error:function(){
	    	layer.closeAll();
	    }
	});
}
</script>
</html>