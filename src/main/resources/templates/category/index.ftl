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
            <form role="form" id="categoryForm"
            <#--method="post" action="/sell/seller/category/save"-->
            >
                <div class="form-group">
                    <label for="categoryName">类目名字</label><input name="categoryName" type="text" class="form-control" value="${category.categoryName!''}" id="categoryName" />
                </div>
                <div class="form-group">
                            <#if category.categoryId??>
                            <#else>
                                <label for="categoryType">类目编号</label><input name="categoryType" type="number" class="form-control" value="${category.categoryType!''}" id="categoryType" />
                            </#if>

                </div>
                <div class="form-group">
                    <label for="isdelete">生效情况</label>
                    <select name="isdelete" class="form-control">
                        <option value="0"
                                        <#if category.isdelete?? && category.isdelete == 0 >selected="selected"</#if>
                        >有效</option>
                        <option value="1"
                                        <#if category.isdelete?? && category.isdelete == 1 >selected="selected"</#if>
                        >失效</option>
                    </select>
                </div>


             <button type="button" class="btn btn-default" onclick="submitform()">提交</button>
            <input type="hidden" id="categoryId" name="categoryId" value="${category.categoryId!''}">
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



<script src="/sell/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function submitform() {
        $.post(
                "/sell/seller/category/save",
                $("#categoryForm").serialize(),
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