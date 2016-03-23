[#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.purchaserequisition.list")} </title>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js_src/productCategory.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/shop/${theme}/layer/layer.js"></script>
<script type="text/javascript">
$().ready(function() {
//入账
	var $inBillButton =$("#inBillButton");
	$inBillButton.click( function() {
		var $this = $(this);
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
		if ($checkedIds.size()<1) {
			$.dialog({
				type: "warn",
				content: "请至少选择一条记录",
				ok: message("admin.dialog.ok"),
			});
			return false;
		}
		$.dialog({
			type: "warn",
			content: "确认入账？（操作不可逆）",
			ok: message("admin.dialog.ok"),
			cancel: message("admin.dialog.cancel"),
			onOk: function() {
				$.ajax({
					url: "inbill.jhtml",
					type: "POST",
					data: $checkedIds.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
								setTimeout(function() {
									location.reload(true);
								}, 1000);
						}
						$inBillButton.addClass("disabled");
						$selectAll.prop("checked", false);
						$checkedIds.prop("checked", false);
					}
				});
			}
		});
		return false;
	});
	});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo;供货商列表
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
            [@shiro.hasPermission name="admin:supplier-edit"]
			<a href="add.jhtml" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>
            [/@shiro.hasPermission]
			
			<div class="buttonGroup">
                [@shiro.hasPermission name="admin:supplier-edit"]
                    <a href="javascript:;" id="deleteButton" class="iconButton disabled">
                        <span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
                    </a>
                [/@shiro.hasPermission]
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div id="pageSizeMenu" class="dropdownMenu">
					<a href="javascript:;" class="button">
						${message("admin.page.pageSize")}<span class="arrow">&nbsp;</span>
					</a>
					<ul>
						<li[#if page.pageSize == 10] class="current"[/#if] val="10">10</li>
						<li[#if page.pageSize == 20] class="current"[/#if] val="20">20</li>
						<li[#if page.pageSize == 50] class="current"[/#if] val="50">50</li>
						<li[#if page.pageSize == 100] class="current"[/#if] val="100">100</li>
					</ul>
				</div>
			</div>
			<div id="searchPropertyMenu" class="dropdownMenu">
				<div class="search">
					<span class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<ul>
					<li[#if page.searchProperty == "name"] class="current"[/#if] val="name">${message("Specification.name")}</li>
				</ul>
			</div>
		</div>
	<table id="listTable" class="list">
		<tr>
			<th class="check">
				<input type="checkbox" id="selectAll" />
			</th>
			<th>
				<span>姓名</span>
			</th>
			<th>
				<span>地址</span>
			</th>
			<th>
				<span>电话</span>
			</th>
			<th>
				<span>联系人</span>
			</th>
    [@shiro.hasPermission name="admin:supplier-edit"]
			<th>
				<span>${message("admin.common.action")}</span>
			</th>
    [/@shiro.hasPermission]
		</tr>
		[#list page.content as supplier]
			<tr>
				<td>
					<input type="checkbox" name="ids" value="${supplier.id}" />
				</td>
				<td>
					${supplier.name}
				</td>
				<td>
					${supplier.address}
				</td>
				<td>
					${supplier.phone}
				</td>
				<td>
					${supplier.supervisor}
				</td>
            [@shiro.hasPermission name="admin:supplier-edit"]
				<td>
					<a href="edit.jhtml?id=${supplier.id}">[${message("admin.common.edit")}]</a>
				</td>
            [/@shiro.hasPermission]
			</tr>
		[/#list]
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</table>
	</form>
</body>
</html>
[/#escape]