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
            <table class="table table-condensed table-bordered">
                <thead>
                <tr>
                    <th>用户序号</th>
                    <th>用户名称</th>
                    <th>用户头像</th>
                    <th>手机号码</th>
                    <th>注册时间</th>
                    <th>上次登录时间</th>
                    <th>登录次数</th>
                    <th>权限角色</th>
                    <th >操作</th>
                </tr>
                </thead>
                <tbody>
                    <#list sellerUserVoList as sellerUserVo>
                    <tr>
                        <td>${sellerUserVo.id?c}</td>
                        <td>${sellerUserVo.nickname}</td>
                        <td>${sellerUserVo.mobile!''}</td>
                        <td>${sellerUserVo.head!''}</td>
                        <td>${sellerUserVo.registerDate}</td>
                        <td>${sellerUserVo.lastLoginDate!''}</td>
                        <td>${sellerUserVo.loginCount!'0'}</td>
                        <td>${sellerUserVo.roleName!''}</td>
                        <td><a href="/sell/seller/selluser/index?userId=${sellerUserVo.id?c}">修改</a></td>
                    <#--<td>-->
                    <#--<#if productInfo.getProductStatusEnums() == '上架'>-->
                    <#--<a href="javascript:void(0);" onclick="offsale('${productInfo.productId}')">下架</a>-->
                    <#--<#elseif productInfo.getProductStatusEnums() == '下架'>-->
                    <#--<a href="javascript:void(0);" onclick="onsale('${productInfo.productId}')">上架</a>-->
                    <#--</#if>-->
                    <#--</td>-->
                    </tr>
                    </#list>

                </tbody>
            </table>
            <div id="doSuccess" class="alert alert-success hide">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>成功！</strong>操作成功。
            </div>
            <div id="doError" class="alert alert-warning hide" >
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>失败！</strong>操作失败。
            </div
        </div>
    </div>

</div>


</body>
</html>
