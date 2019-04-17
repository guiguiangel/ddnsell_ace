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
            <div id="page-content">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 column">
                        <table class="table table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>商品名称</th>
                                <th>商品价格</th>
                                <th>商品库存</th>
                                <th>描述</th>
                                <th>小图</th>
                                <th>商品类别</th>
                                <th>创建时间</th>
                                <th>更新时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list productInfoPage.content as productInfo>
                    <tr>
                        <td>${productInfo.productId}</td>
                        <td>${productInfo.productName}</td>
                        <td>${productInfo.productPrice}</td>
                        <td>${productInfo.productStock}</td>
                        <td>${productInfo.productDescription}</td>
                        <td><img style="width: 100px; height: 100px" src="${productInfo.productIcon}"></td>
                        <td>${productInfo.categoryType}</td>
                        <td>${productInfo.createTime}</td>
                        <td>${productInfo.updateTime}</td>
                        <td><a href="/sell/seller/product/toupdate/${productInfo.productId}">修改</a></td>
                        <td>
                            <#if productInfo.getProductStatusEnums() == '上架'>
                                <a href="javascript:void(0);" onclick="offsale('${productInfo.productId}')">下架</a>
                            <#elseif productInfo.getProductStatusEnums() == '下架'>
                                <a href="javascript:void(0);" onclick="onsale('${productInfo.productId}')">上架</a>
                            </#if>
                        </td>
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
                        </div>

                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>

</div>
    <script type="text/javascript">
        function onsale(id) {
            $.post(
                    "/sell/seller/product/onsale/" + id,
                    // {"productId":id},
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
        function offsale(id) {
            $.post(
                    "/sell/seller/product/offsale/" + id,
                    // {"productId":id},
                    function (data) {
                        //$("#doSuccess").show();
                        console.log(data);
                        if (data.type == true){
                            $('#doSuccess').removeClass('hide').addClass('in');
                            // window.location.reload();
                            window.setTimeout("window.location.reload()", 1000);
                        }
                        else{
                            $('#doError').removeClass('hide').addClass('in');
                        }
                    },
                    "json"
            );
        }
    </script>
</body>

</html>
