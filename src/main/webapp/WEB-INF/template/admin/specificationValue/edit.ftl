[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.specification.edit")} </title>
<meta name="author"  />
<meta name="copyright"  />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		onkeyup: false,
        onclick: false,
        onfocusout: false,
		rules: {
			specificationValue: {
				required:true,
				remote: {
					url: "checkValue.jhtml",
					cache: false,
					data:{
				    	value: function(){
				          	return $('#inputForm :input[name="specificationValue"]').val();
				       	},
				       	type: function(){
				          	return $('#inputForm :input[name="specification"]').val();
				       	},
				       	id:"${specificationValue.id}"
				   	}
				}
			},
			specification: "required"
		},
		messages:{
			specificationValue:{
				remote:"同一类型下，规格名称必须唯一"
			}
		}
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.specification.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${specificationValue.id}" />
		<table class="input">
			<tr>
				<th>
					规格值:
				</th>
				<td>
					<input type="text" name="specificationValue" class="text" value='${specificationValue.specificationValue}' maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					分类:
				</th>
				<td>
					<select name="specification">
						[#list types as type]
						<option value=${type} [#if specificationValue.specification==type]selected=true[/#if]>${message("SpecificationValue.Specification."+type)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
[/#escape]