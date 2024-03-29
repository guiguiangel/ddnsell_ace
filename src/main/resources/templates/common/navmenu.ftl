        <#--<script type="text/javascript">-->
            <#--try{ace.settings.check('main-container' , 'fixed')}catch(e){}-->
        <#--</script>-->
        <#--<div class="main-container-inner">-->
            <#--<a class="menu-toggler" id="menu-toggler" href="#">-->
                <#--<span class="menu-text"></span>-->
            <#--</a>-->

            <div class="sidebar" id="sidebar">
                <script type="text/javascript">
                    try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
                </script>

                <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                        <button class="btn btn-success">
                            <i class="icon-signal"></i>
                        </button>

                        <button class="btn btn-info">
                            <i class="icon-pencil"></i>
                        </button>

                        <button class="btn btn-warning">
                            <i class="icon-group"></i>
                        </button>

                        <button class="btn btn-danger">
                            <i class="icon-cogs"></i>
                        </button>
                    </div>

                    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                        <span class="btn btn-success"></span>

                        <span class="btn btn-info"></span>

                        <span class="btn btn-warning"></span>

                        <span class="btn btn-danger"></span>
                    </div>
                </div><!-- #sidebar-shortcuts -->

                <ul class="nav nav-list">
                    <li class="active">
                        <a href="index.html">
                            <i class="icon-dashboard"></i>
                            <span class="menu-text"> 控制台 </span>
                        </a>
                    </li>
                    <li>
                        <a href="/sell/seller/order/list">
                            <i class="icon-text-width"></i>
                            <span class="menu-text"> 订单 </span>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-text-width"></i>
                            <span class="menu-text"> 商品 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="/sell/seller/product/list">
                                    <i class="icon-double-angle-right"></i>
                                    商品列表
                                </a>
                                <a href="/sell/seller/product/index">
                                    <i class="icon-double-angle-right"></i>
                                    新增商品
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-text-width"></i>
                            <span class="menu-text"> 商品分类 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="/sell/seller/category/list">
                                    <i class="icon-double-angle-right"></i>
                                    分类列表
                                </a>
                                <a href="/sell/seller/category/index">
                                    <i class="icon-double-angle-right"></i>
                                    新增分类
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-text-width"></i>
                            <span class="menu-text"> 权限管理 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="/sell/auth/tree">
                                    <i class="icon-double-angle-right"></i>
                                    权限列表
                                </a>
                                <a href="/sell/auth/index">
                                    <i class="icon-double-angle-right"></i>
                                    新增权限
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-text-width"></i>
                            <span class="menu-text"> 角色管理 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="/sell/auth/role/list">
                                    <i class="icon-double-angle-right"></i>
                                    角色列表
                                </a>
                                <a href="/sell/auth/role/index">
                                    <i class="icon-double-angle-right"></i>
                                    新增角色
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-text-width"></i>
                            <span class="menu-text"> 用户管理 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="/sell/seller/selluser/list">
                                    <i class="icon-double-angle-right"></i>
                                    用户列表
                                </a>
                                <a href="/sell/seller/selluser/index">
                                    <i class="icon-double-angle-right"></i>
                                    新增用户
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-tag"></i>
                            <span class="menu-text"> 更多页面 </span>

                            <b class="arrow icon-angle-down"></b>
                        </a>

                        <ul class="submenu">
                            <li>
                                <a href="login.html">
                                    <i class="icon-double-angle-right"></i>
                                    登录 &amp; 注册
                                </a>
                            </li>
                        </ul>
                    </li>

                </ul><!-- /.nav-list -->

                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
                </div>

                <script type="text/javascript">
                    try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
                </script>
            </div>
        <#--</div>-->