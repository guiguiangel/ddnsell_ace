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
                            <form role="form" method="post" action="/sell/seller/product/save">
                                <div class="form-group">
                                    <label for="productName">商品名称</label><input name="productName" type="text" class="form-control" value="${productInfo.productName!''}" id="productName" />
                                </div>
                                <div class="form-group">
                                    <label for="productPrice">商品价格</label><input name="productPrice" type="number" class="form-control" value="${productInfo.productPrice!''}" id="productPrice" />
                                </div>
                                <div class="form-group">
                                    <label for="productStock">商品库存</label><input name="productStock" type="number" class="form-control" value="${productInfo.productStock!''}" id="productStock" />
                                </div>
                                <div class="form-group">
                                    <label for="productDescription">描述</label><input name="productDescription" type="text" class="form-control" value="${productInfo.productDescription!''}" id="productDescription" />
                                </div>
                                <div class="form-group">
                                    <img src="${productInfo.productIcon!''}" style="width: 100px;height: 100px">
                                    <label for="productIcon">图片地址</label><input name="productIcon" type="text" class="form-control" value="${productInfo.productIcon!''}" id="productIcon" />
                                </div>
                                <div class="form-group">
                                    <label for="productDescription">商品分类</label>
                                    <select id="categoryType" name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if productInfo.categoryType?? && category.categoryType == productInfo.categoryType>selected="selected"</#if>
                                    >${category.categoryName}
                                    </option>
                                </#list>

                                    </select>
                                </div>
                                 </div> <button type="submit" class="btn btn-default">提交</button>
                                <input type="hidden" id="productId" name="productId" value="${productInfo.productId!''}">
                             </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>



</body>
</html>