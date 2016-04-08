[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.goods.add")}</title>
<meta name="author"/>
<meta name="copyright"/>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/webuploader.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $filePicker1 = $("#filePicker1");
	var $filePicker2 = $("#filePicker2");
	var $filePicker3 = $("#filePicker3");
	var $filePicker4 = $("#filePicker4");

	[@flash_message /]
	
	$filePicker1.uploader({data:{imageType:'product'}});
	$filePicker2.uploader({data:{imageType:'product'}});
	$filePicker3.uploader({data:{imageType:'product'}});
	$filePicker4.uploader({data:{imageType:'product'}});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name:"required",
		}
	});
	
});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.goods.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.goods.base")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.goods.introduction")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.goods.productImage")}" />
			</li>
			<li>
				<input type="button" value="${message("admin.goods.specification")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Goods.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					花语:
				</th>
				<td>
					<input type="text" name="flowerLanguage" class="text" maxlength="200" />
				</td>
			</tr>
			<tr class='none'>
				<th>
					${message("Goods.caption")}:
				</th>
				<td>
					<input type="text" name="caption" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Product.price")}:
				</th>
				<td>
					<input type="text" id="price" name="price" class="text" maxlength="16" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isMarketable" value="1" checked="checked" />${message("Goods.isMarketable")}
						<input type="hidden" name="isMarketable" value="0" />
					</label>
					<input type="hidden" name="isList" value="1" />
					<label>
						<input type="checkbox" checked="checked" disabled = true/>是否标品
						<input type="hidden" name="isStandard" value="1" />
					</label>
					<input type="hidden" name="isVirtual" value="0" />
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<td>
					<textarea name="introduction" class="text"></textarea>
				</td>
			</tr>
		</table>
		<table  class="input tabContent">
			<tr>
				<th>
					商品图片1:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="productImages1" class="text" maxlength="200" title="商品图片1" />
						<a href="javascript:;" id="filePicker1" class="button">商品图片1</a>
						<input type='radio' name='imageNum' value='1' checked=true>设为默认
					</span>
				</td>
			</tr>
			<tr>
				<th>
					商品图片2:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="productImages2" class="text" maxlength="200" title="商品图片2" />
						<a href="javascript:;" id="filePicker2" class="button">商品图片2</a>
						<input type='radio' name='imageNum' value='2'>设为默认
					</span>
				</td>
			</tr>
			<tr>
				<th>
					商品图片3:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="productImages3" class="text" maxlength="200" title="商品图片3" />
						<a href="javascript:;" id="filePicker3" class="button">商品图片3</a>
						<input type='radio' name='imageNum' value='3'>设为默认
					</span>
				</td>
			</tr>
			<tr>
				<th>
					商品图片4:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="productImages4" class="text" maxlength="200" title="商品图片4" />
						<a href="javascript:;" id="filePicker4" class="button">商品图片4</a>
						<input type='radio' name='imageNum' value='4'>设为默认
					</span>
				</td>
			</tr>
		</table>
		<table  class="input tabContent">
			<tr>
				<th>
					规格-目标:
				</th>
				<td>
					<select name="specTargetId">
						[#list specs as spec]
							[#if spec.specification=='Target']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					规格-花卉:
				</th>
				<td>
					<select name="specFlowertId">
						[#list specs as spec]
							[#if spec.specification=='Flower']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					规格-花型:
				</th>
				<td>
					<select name="specSorttId">
						[#list specs as spec]
							[#if spec.specification=='Sort']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					规格-设计:
				</th>
				<td>
					<select name="specDesigntId">
						[#list specs as spec]
							[#if spec.specification=='Design']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					规格-颜色:
				</th>
				<td>
					<select name="specColortId">
						[#list specs as spec]
							[#if spec.specification=='Color']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					规格-系列:
				</th>
				<td>
					<select name="specSeriestId">
						[#list specs as spec]
							[#if spec.specification=='Series']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					规格-主题:
				</th>
				<td>
					<select name="specThemetId">
						[#list specs as spec]
							[#if spec.specification=='Theme']
							<option value=${spec.id}>${spec.specificationValue}</option>
							[/#if]
						[/#list]
					</select>
				</td>
			</tr>
		</table>
		<table class="input">
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