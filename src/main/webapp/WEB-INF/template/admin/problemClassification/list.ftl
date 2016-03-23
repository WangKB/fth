[#assign shiro = JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.productCategory.list")} </title>
<meta name="author" />
<meta name="copyright"  />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js_src/problemClassification.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/shop/${theme}/layer/layer.js"></script>

</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 帮助中心分类列表
	</div>
	<div class="bar">
        [@shiro.hasPermission name="admin:helpCenter-edit"]
		<a href="add.jhtml" class="iconButton">
			<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
		</a>
        [/@shiro.hasPermission]
		<a href="javascript:;" id="refreshButton" class="iconButton">
			<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
		</a>
	</div>
	<table id="listTable" class="list">
		<tr>
			<th>
				<span>${message("ProductCategory.name")}</span>
			</th>
			<th>
				<span>${message("admin.common.order")}</span>
			</th>
    [@shiro.hasPermission name="admin:helpCenter-edit"]
			<th>
				<span>${message("admin.common.action")}</span>
			</th>
    [/@shiro.hasPermission]
		</tr>
		[#list problemClassificationTree as problemClassification]
			<tr>
				<td>
					<span style="margin-left: ${problemClassification.grade * 20}px;[#if problemClassification.grade == 0] color: #000000;[/#if]">
						${problemClassification.name}
					</span>
				</td>
				<td>
					${problemClassification.order}
				</td>
            [@shiro.hasPermission name="admin:helpCenter-edit"]
				<td>
					<a href="edit.jhtml?id=${problemClassification.id}">[${message("admin.common.edit")}]</a>
					<a href="javascript:;" class="delete" id="delete" value="${problemClassification.id}" url="${base}/admin/problemClassification/delete.jhtml">[${message("admin.common.delete")}]</a>
				</td>
            [/@shiro.hasPermission]
			</tr>
		[/#list]
	</table>
</body>
</html>
[/#escape]