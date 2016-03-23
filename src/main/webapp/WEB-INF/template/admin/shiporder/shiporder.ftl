[#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.order.list")}</title>
<meta name="author" />
<meta name="copyright"/>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/ecmascript" src="${base}/resources/admin/gird/js/i18n/grid.locale-cn.js"></script>
<script type="text/ecmascript" src="${base}/resources/admin/gird/js/jquery.jqGrid.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${base}/resources/admin/gird/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${base}/resources/admin/gird/css/ui.jqgrid.css" />
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $filterMenu = $("#filterMenu");
	var $filterMenuItem = $("#filterMenu li");
	var $moreButton = $("#moreButton");
	var $print = $("#listTable select[name='print']");
	
	[@flash_message /]
$("#jqGrid").jqGrid({
                url: 'data.jhtml',
                mtype: "GET",
                datatype: "json",
                colModel: [
                    { label: '编号', name: 'sn', width: 50 },
                    { label: '金额', name: 'number', width: 100 },
                    { label: '会员', name: 'number', width: 100 },
                     { label: '收货人', name: 'number', width: 100 },
                    { label: '日期', name: 'number', width: 100 }
                ],
				viewrecords: true,
                width: $(window).width(),
                height: 250,
                rowNum: 10,
                pager: "#jqGridPager",
                onSelectRow: function (rowid) {
                	$("#orderId").val(rowid);
		　　	},
            });
            
             $('#jqGrid').navGrid('#jqGridPager',
                { edit: false, add: false, del: false, search: false, refresh: true, view: false, position: "left", cloneToTop: false }
               ).jqGrid('navButtonAdd',"#jqGridPager",{
                	caption:"发货",
					onClickButton:function(){
						location.href="ship.jhtml?id="+$("#orderId").val();
					},
					id:1001
                });
});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 在线物流订单列表 <span>(${message("admin.page.total", page.total)})</span>
	</div>
	<input type="hidden" name="orderId" id="orderId">
	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>
</body>
</html>
[/#escape]