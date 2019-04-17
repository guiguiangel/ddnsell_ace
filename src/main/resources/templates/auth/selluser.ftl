<html>
<head>
    <#assign rootPath = request.contextPath/>
    <meta charset="utf-8" />
    <title>控制台 - DDN</title>
    <meta name="keywords" content="DDN" />
    <meta name="description" content="DDN" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
<#---->
    <#include "../common/stricpcss.ftl"/>
    <script type="text/javascript"  src="/sell/js/jquery.min.js"></script>
    <script type="text/javascript"  src="/sell/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript"  src="/sell/jquery-validation/localization/messages_zh.min.js"></script>
    <!-- 导入ztree类库 -->
    <link rel="stylesheet"
          href="/sell/js/ztree/zTreeStyle.css"
          type="text/css" />
    <script src="/sell/js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
    <style type="text/css">
        label.error{
            color: #d58512;
        }
    </style>
</head>

<body>
    <#include "../common/headnav.ftl"/>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>
    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>
         <#include "../common/navmenu.ftl"/>
        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="#">首页</a>
                    </li>
                    <li class="active">控制台</li>
                </ul><!-- .breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- #nav-search -->
            </div>
            <form role="form" id="sellUserform" method="post" >
                <div class="form-group">
                    <label for="nickname">用户名称</label><input name="nickname" type="text" class="form-control" value="${sellerUser.nickname!''}" id="nickname" />
                </div>
                <div class="form-group">
                    <label for="head">用户头像</label><input name="head" type="text" class="form-control" value="${sellerUser.head!''}" id="head" />
                </div>
                <div class="form-group">
                    <label for="mobile">用户手机号码</label><input name="mobile" type="text" class="form-control" value="${sellerUser.mobile!''}" id="mobile" />
                </div>

                <div class="form-group">
                    <label for="rolelists">用户角色</label>
                    <div id="rolelists"></div>
                </div>
                <button type="button" class="btn btn-default" onclick="onSubmit()">提交</button>
                        <#setting number_format="#">
                <input type="hidden" id="id" name="id" value="${sellerUser.id!''}">
            </form>
            <div id="doSuccess" class="alert alert-success hide">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>成功！</strong>操作成功。
            </div>
            <div id="doError" class="alert alert-warning hide" >
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>失败！</strong>操作失败。
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
        $("#roleinfoform").validate({
            rules: {
                nickname: "required",
                mobile: "required"
            },
            messages: {
                nickname: "用户名称不能为空",
                mobile: "用户手机号码不能为空"
            }
        })
    });
    
    // function onAddRole() {
    //     var treeObj = $.fn.zTree.getZTreeObj("authTree");
    //     var nodes = treeObj.getCheckedNodes();
    //     if (nodes){
    //         var array = new Array();
    //         for (var i = 0; i < nodes.length ; i++){
    //             array.push(nodes[i].id);
    //         }
    //         var authIds = array.join(",");
    //         $("input[name='authIds']").val(authIds);
    //     }
    //     // var array = new Array();
    //     // for (var i = 0; i < nodes.length; i++){
    //     //     array.push(nodes[i].id);
    //     // }
    //     // if (array.length > 0){
    //     //     authIds = array.join(",");
    //     //     $("input[name='authPid']").val(authPid);
    //     // }
    //
    //     var param = $("#roleinfoform").serialize();
    //     var url = "/sell/auth/role/save";
    //     if ($("#roleId").val() != null && $("#roleId").val() != ''){
    //         url =  "/sell/auth/role/update"
    //     }
    //     $.post(
    //             url,
    //             param,
    //             function (data) {
    //                 // $("#doSuccess").show();
    //                 console.log(data);
    //                 if (data.type == true){
    //                     $('#doSuccess').removeClass('hide').addClass('in');
    //                     window.setTimeout("window.location.reload()", 1000);
    //                 }
    //                 else{
    //                     $('#doError').removeClass('hide').addClass('in');
    //                 }
    //             },
    //             "json"
    //     );
    // }

    function onSubmit(){
        var id = $("#id").val();
        var url = "/sell/seller/selluser/save";
        if (id != undefined){
            url = "/sell/seller/selluser/update";
        }
        param = $("#sellUserform").serialize();
        $.post(
                url,
                param,
                function (data) {
                    // $("#doSuccess").show();
                    console.log(data);
                    if (data.code == 0){
                        $('#doSuccess').removeClass('hide').addClass('in');
                        window.setTimeout("window.location.reload()", 3000);
                    }
                    else{
                        $('#doError').removeClass('hide').addClass('in');
                    }
                },
                "json"
        );
    }
    $(function () {
        $.get("/sell/auth/role/userrolelist",
                {
                    userId: $("#id").val()
                },
                function (data) {
                    for (var i = 0; i < data.length; i++){
                        var id = data[i].roleId;
                        var name = data[i].roleName;
                        var checked = data[i].checked;
                        var check = "<input type='checkbox' id='"+id+"' name ='roleIds' value='"+id+"' " ;
                        if(checked == true){
                            check += "checked='checked' ";
                        }
                        check = check + ">" +
                        "<label for='"+id+"'>"+name+"</label>";
                        $("#rolelists").append(check);
                    }
                }
        );
    });

</script>

</body>
</html>