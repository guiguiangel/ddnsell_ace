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
<body>

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
            <div class="col-md-4 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>订单总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.orderAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        <#--订单详情表数据-->
            <div class="col-md-12 column">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>商品id</th>
                        <th>商品名称</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>总额</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                        <tr>
                            <td>${orderDetail.productId}</td>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productQuantity}</td>
                            <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>

        <#--操作-->
            <div class="col-md-12 column">
                <#if orderDTO.getOrderStatusMessage() == "新订单">
                    <a href="javascript:void(0);" onclick="finish('${orderDTO.orderId}','${orderDTO.buyerOpenid}')" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}&openid=${orderDTO.buyerOpenid}" type="button" class="btn btn-default btn-danger">取消订单</a>
                </#if>
            </div>
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

<#--<div id="wrapper" class="toggled">-->

    <#--&lt;#&ndash;边栏sidebar&ndash;&gt;-->
    <#--<#include "../common/nav.ftl">-->


    <#--&lt;#&ndash;主要内容content&ndash;&gt;-->
    <#--<div id="page-content-wrapper">-->
        <#--<div class="container">-->
            <#--<div class="row clearfix">-->
                <#--<div class="col-md-4 column">-->
                    <#--<table class="table table-bordered">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>订单id</th>-->
                            <#--<th>订单总金额</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->
                        <#--<tr>-->
                            <#--<td>${orderDTO.orderId}</td>-->
                            <#--<td>${orderDTO.orderAmount}</td>-->
                        <#--</tr>-->
                        <#--</tbody>-->
                    <#--</table>-->
                <#--</div>-->

            <#--&lt;#&ndash;订单详情表数据&ndash;&gt;-->
                <#--<div class="col-md-12 column">-->
                    <#--<table class="table table-bordered">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>商品id</th>-->
                            <#--<th>商品名称</th>-->
                            <#--<th>价格</th>-->
                            <#--<th>数量</th>-->
                            <#--<th>总额</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->
                        <#--<#list orderDTO.orderDetailList as orderDetail>-->
                        <#--<tr>-->
                            <#--<td>${orderDetail.productId}</td>-->
                            <#--<td>${orderDetail.productName}</td>-->
                            <#--<td>${orderDetail.productPrice}</td>-->
                            <#--<td>${orderDetail.productQuantity}</td>-->
                            <#--<td>${orderDetail.productQuantity * orderDetail.productPrice}</td>-->
                        <#--</tr>-->
                        <#--</#list>-->
                        <#--</tbody>-->
                    <#--</table>-->
                <#--</div>-->

            <#--&lt;#&ndash;操作&ndash;&gt;-->
                <#--<div class="col-md-12 column">-->
                <#--<#if orderDTO.getOrderStatusMessage() == "新订单">-->
                    <#--<a href="javascript:void(0);" onclick="finish('${orderDTO.orderId}','${orderDTO.buyerOpenid}')" type="button" class="btn btn-default btn-primary">完结订单</a>-->
                    <#--<a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}&openid=${orderDTO.buyerOpenid}" type="button" class="btn btn-default btn-danger">取消订单</a>-->
                <#--</#if>-->
                <#--</div>-->
                <#--<div id="doSuccess" class="alert alert-success hide">-->
                    <#--<a href="#" class="close" data-dismiss="alert">&times;</a>-->
                    <#--<strong>成功！</strong>操作成功。-->
                <#--</div>-->
                <#--<div id="doError" class="alert alert-warning hide" >-->
                    <#--<a href="#" class="close" data-dismiss="alert">&times;</a>-->
                    <#--<strong>失败！</strong>操作失败。-->
                <#--</div-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<script type="text/javascript">
    function finish(orderId,openid) {
        $.post(
                "/sell/seller/order/finish",
                {
                    "orderId":orderId,
                    "openid":openid
                },
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
</script>
</body>
</html>