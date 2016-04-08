[#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
[#escape x as x?html]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>${message("admin.main.title")}</title>
    <meta name="author"/>
    <meta name="copyright"/>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/admin/css/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
    <style type="text/css">
        * {
            font: 12px tahoma, Arial, Verdana, sans-serif;
        }

        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
    </style>
    <script type="text/javascript">
        $().ready(function () {

            var $nav = $("#nav a");
            var $menu = $("#menu dl");
            var $menuItem = $("#menu a");

            $nav.click(function () {
                var $this = $(this);
                $nav.removeClass("current");
                $this.addClass("current");
                var $currentMenu = $($this.attr("href"));
                $menu.hide();
                $currentMenu.show();
                return false;
            });

            $menuItem.click(function () {
                var $this = $(this);
                $menuItem.removeClass("current");
                $this.addClass("current");
            });

        });
    </script>
</head>
<body>
<script type="text/javascript">
    if (self != top) {
        top.location = self.location;
    }
</script>
<table class="main">
<tr>
    <th class="logo">
        <a href="main.jhtml">
        </a>
    </th>
    <th>
        <div id="nav" class="nav">
            <ul>
	            <li>
	                <a href="#product">${message("admin.main.productNav")}</a>
	            </li>
                <li>
                    <a href="#organization">店铺</a>
                </li>
                <li>
                    <a href="#order">订单</a>
                </li>
                <li>
                    <a href="#member">会员</a>
                </li>
                <li>
                    <a href="#finance">财务</a>
                </li>
            </ul>
        </div>
        <div class="link">
            <strong>${admin.name}</strong>
        ${message("admin.main.hello")}!
            <a class="none" href="../profile/edit.jhtml" target="iframe">[${message("admin.main.profile")}]</a>
            <a href="../logout.jhtml" target="_top">[${message("admin.main.logout")}]</a>
        </div>
    </th>
</tr>
<tr>
<td id="menu" class="menu">
[#-- 商品模块 --]
<dl id="product" class="default">
    <dt>${message("admin.main.productGroup")}</dt>
        <dd>
            <a href="../goods/list.jhtml" target="iframe">${message("admin.main.goods")}</a>
        </dd>
        <dd>
            <a href="../goods/examine.jhtml" target="iframe">上架审核</a>
        </dd>
        <dd>
            <a href="../specificationValue/list.jhtml" target="iframe">${message("admin.main.specification")}</a>
        </dd>
    	<dd>
            <a href="../area/list.jhtml" target="iframe">价格管理</a>
        </dd>
    	<dd class='none'>
            <a href="../review/list.jhtml" target="iframe">商品评价设置</a>
        </dd>
        <dd>
            <a href="../tag/list.jhtml" target="iframe">标签管理</a>
        </dd>
        <dd>
            <a href="../tagDetail/list.jhtml" target="iframe">标签详情管理</a>
        </dd>
</dl>
<dl id="organization">
    <dt>店铺管理</dt>
    	<dd>
            <a href="../organization/list.jhtml" target="iframe">店铺列表</a>
        </dd>
        <dd>
            <a href="../organization/examine.jhtml" target="iframe">店铺审核</a>
        </dd>
</dl>
<dl id="order">
    <dt>订单管理</dt>
    	<dd>
            <a href="../order/list.jhtml" target="iframe">订单列表</a>
        </dd>
        <dd class='none'>
            <a href="../order/statics.jhtml" target="iframe">订单统计</a>
        </dd>
</dl>
<dl id="member">
    <dt>会员管理</dt>
    	<dd>
            <a href="../member/list.jhtml" target="iframe">会员列表</a>
        </dd>
        <dd>
            <a href="../pointSetting/list.jhtml" target="iframe">积分设置</a>
        </dd>
        <dd>
            <a href="../coupon/list.jhtml" target="iframe">优惠券管理</a>
        </dd>
        <dd>
            <a href="../greetingcard/list.jhtml" target="iframe">贺卡管理</a>
        </dd>
</dl>
<dl id="finance">
    <dt>财务管理</dt>
    	<dd>
            <a href="../finRate/list.jhtml" target="iframe">分账设置</a>
        </dd>
        <dd>
            <a href="../order/report.jhtml" target="iframe">报表导出</a>
        </dd>
</dl>
</td>
<td>
    <iframe id="iframe" name="iframe" src="index.jhtml" frameborder="0"></iframe>
</td>
</tr>
</table>
</body>
</html>
[/#escape]