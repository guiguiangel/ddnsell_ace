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
        <#include "../common/websocket.ftl">-
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
                                    <th>订单编号</th>
                                    <th>买家名字</th>
                                    <th>买家手机号</th>
                                    <th>买家地址</th>
                                    <th>买家微信Openid</th>
                                    <th>订单总金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th>查看详情</th>
                                </tr>
                                </thead>
                                <tbody>
                    <#list orderDTOPage.content as order>
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.buyerName}</td>
                        <td>${order.buyerPhone}</td>
                        <td>${order.buyerAddress}</td>
                        <td>${order.buyerOpenid}</td>
                        <td>${order.orderAmount}</td>
                        <td>${order.getOrderStatusMessage()}</td>
                        <td>${order.getPayStatusMessage()}</td>
                        <td>${order.createTime}</td>
                        <td>${order.updateTime}</td>
                        <td><a href="/sell/seller/order/detail/?orderId=${order.orderId}&openid=${order.buyerOpenid}">查看详情</a></td>
                    <#--<td><a href="javascript:void(0);)" onclick="detail('${order.orderId}','${order.buyerOpenid}' )">查看详情</a></td>-->
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
<#--<div id="wrapper" class="toggled">-->
    <#--<#include "../common/nav.ftl">-->
    <#--<#include "../common/websocket.ftl">-->
    <#--<div id="page-content-wrapper">-->
        <#--<div class="container-fluid">-->
            <#--<div class="row clearfix">-->
                <#--<div class="col-md-12 column">-->
                    <#--<table class="table table-condensed table-bordered">-->
                        <#--<thead>-->
                        <#--<tr>-->
                            <#--<th>订单编号</th>-->
                            <#--<th>买家名字</th>-->
                            <#--<th>买家手机号</th>-->
                            <#--<th>买家地址</th>-->
                            <#--<th>买家微信Openid</th>-->
                            <#--<th>订单总金额</th>-->
                            <#--<th>订单状态</th>-->
                            <#--<th>支付状态</th>-->
                            <#--<th>创建时间</th>-->
                            <#--<th>更新时间</th>-->
                            <#--<th>查看详情</th>-->
                        <#--</tr>-->
                        <#--</thead>-->
                        <#--<tbody>-->
                    <#--<#list orderDTOPage.content as order>-->
                    <#--<tr>-->
                        <#--<td>${order.orderId}</td>-->
                        <#--<td>${order.buyerName}</td>-->
                        <#--<td>${order.buyerPhone}</td>-->
                        <#--<td>${order.buyerAddress}</td>-->
                        <#--<td>${order.buyerOpenid}</td>-->
                        <#--<td>${order.orderAmount}</td>-->
                        <#--<td>${order.getOrderStatusMessage()}</td>-->
                        <#--<td>${order.getPayStatusMessage()}</td>-->
                        <#--<td>${order.createTime}</td>-->
                        <#--<td>${order.updateTime}</td>-->
                        <#--<td><a href="/sell/seller/order/detail/?orderId=${order.orderId}&openid=${order.buyerOpenid}">查看详情</a></td>-->
                        <#--&lt;#&ndash;<td><a href="javascript:void(0);)" onclick="detail('${order.orderId}','${order.buyerOpenid}' )">查看详情</a></td>&ndash;&gt;-->
                    <#--</tr>-->
                    <#--</#list>-->

                        <#--</tbody>-->
                    <#--</table>-->
                    <#--&lt;#&ndash;<div id="doSuccess" class="alert alert-success hide">&ndash;&gt;-->
                        <#--&lt;#&ndash;<a href="#" class="close" data-dismiss="alert">&times;</a>&ndash;&gt;-->
                        <#--&lt;#&ndash;<strong>成功！</strong>操作成功。&ndash;&gt;-->
                    <#--&lt;#&ndash;</div>&ndash;&gt;-->
                    <#--&lt;#&ndash;<div id="doError" class="alert alert-warning hide" >&ndash;&gt;-->
                        <#--&lt;#&ndash;<a href="#" class="close" data-dismiss="alert">&times;</a>&ndash;&gt;-->
                        <#--&lt;#&ndash;<strong>失败！</strong>操作失败。&ndash;&gt;-->
                    <#--&lt;#&ndash;</div&ndash;&gt;-->

                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<script type="text/javascript">
    function detail(orderId, openid) {
        $.post(
                "/sell/seller/order/detail",
                {
                    "orderId":orderId,
                    "openid": openid
                },
                function (data) {
                    // $("#doSuccess").show();
                    console.log(data);
                    // if (data.type == true){
                    //     $('#doSuccess').removeClass('hide').addClass('in');
                    //     window.setTimeout("window.location.reload()", 1000);
                    // }
                    // else{
                    //     $('#doError').removeClass('hide').addClass('in');
                    // }
                },
                "json"
        );
    }
</script>
</body>
</html>
