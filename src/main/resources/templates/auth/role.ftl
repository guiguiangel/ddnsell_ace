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
            <form role="form" id="roleinfoform" method="post" >
                <div class="form-group">
                    <label for="roleName">角色名称</label><input name="roleName" type="text" class="form-control" value="${roleInfo.roleName!''}" id="roleName" />
                </div>
                <div class="form-group">
                    <label for="roleCode">角色编号</label><input name="roleCode" type="text" class="form-control" value="${roleInfo.roleCode!''}" id="roleCode" />
                </div>
                <div class="form-group">
                    <label for="roleDescription">角色描述</label><input name="roleDescription" type="text" class="form-control" value="${roleInfo.roleDescription!''}" id="roleDescription" />
                </div>

                <div class="form-group">
                    <label for="productDescription">角色关联权限</label>
                    <input type="hidden" id="authIds" name="authIds">
                <#--<select id="authPid" name="authPid" class="form-control">-->
                <#--<#list authInfoList as auth>-->
                <#--<option value="${auth.authId}"-->
                <#--<#if authInfo.authPid?? && auth.authId == authInfo.authPid>selected="selected"</#if>-->
                <#-->${auth.authName}-->
                <#--</option>-->
                <#--</#list>-->
                <#--</select>-->
                    <ul id="authTree" class="ztree"></ul>
                </div>
                <button type="button" class="btn btn-default" onclick="onAddRole()">提交</button>
                <input type="hidden" id="roleId" name="roleId" value="${roleInfo.roleId!''}">
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
                roleName: "required",
                roleCode: "required",
                roleDescription: {
                    required: true,
                    minlength: 5
                }
            },
            messages: {
                roleName: "权限名称不能为空",
                roleCode: "权限编号不能为空",
                roleDescription: {
                    required: "请填写权限描述",
                    minlength: "最少写5个字"
                }
            }
        })
    });
    
    function onAddRole() {
        var treeObj = $.fn.zTree.getZTreeObj("authTree");
        var nodes = treeObj.getCheckedNodes();
        if (nodes){
            var array = new Array();
            for (var i = 0; i < nodes.length ; i++){
                array.push(nodes[i].id);
            }
            var authIds = array.join(",");
            $("input[name='authIds']").val(authIds);
        }
        // var array = new Array();
        // for (var i = 0; i < nodes.length; i++){
        //     array.push(nodes[i].id);
        // }
        // if (array.length > 0){
        //     authIds = array.join(",");
        //     $("input[name='authPid']").val(authPid);
        // }

        var param = $("#roleinfoform").serialize();
        var url = "/sell/auth/role/save";
        if ($("#roleId").val() != null && $("#roleId").val() != ''){
            url =  "/sell/auth/role/update"
        }
        $.post(
                url,
                param,
                function (data) {
                    // $("#doSuccess").show();
                    console.log(data);
                    if (data.type == true){
                        $('#doSuccess').removeClass('hide').addClass('in');
                        window.setTimeout("window.location.reload()", 1000);
                    }
                    else{
                        $('#doError').removeClass('hide').addClass('in');
                    }
                },
                "json"
        );
    }

    $(function() {
        // 授权树初始化
        var setting = {
            data: {
                key: {
                    title: "权限数据"
                },
                simpleData: {
                    enable: true
                    // idKey: "authId",
                    // pIdKey: "authPid",
                    // rootPId: 0
                }
            },
            check: {
                enable: true
            }
        };

        var roleId = $("#roleId").val();
        if (roleId == undefined){
            roleId = null;
        }
        $.ajax({
            url: '/sell/auth/authtree',
            type: 'GET',
            dataType: 'json',
            data:{
                    roleId: roleId
                },
            success: function (data) {
                // var zNodes = eval("(" + data + ")");
                $.fn.zTree.init($("#authTree"), setting, data);
                <#--<#if authInfo.authPid??>-->
                     <#--var zTree = $.fn.zTree.getZTreeObj("authTree");-->
                     <#--var node = zTree.getNodeByParam("id", ${authInfo.authPid});-->
                     <#--if(node){-->
                         <#--zTree.selectNode(node);-->
                     <#--}-->

                <#--</#if>-->
            },
            error: function (msg) {
                alert('树加载异常!');
            }
        });
    });
</script>

</body>
</html>