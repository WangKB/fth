[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.coupon.add")}</title>
<meta name="author" />
<meta name="copyright" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $productSelect = $("#productSelect");
	var $productTable = $("#productTable");
	var $productTitle = $("#productTable tr:first");
	var $introduction = $("#introduction");
	var $couponType = $("#couponType");
	var $thirdParty = $("#thirdParty");
	
	[@flash_message /]
	
	
	$couponType.on("change",function(){
		if($couponType.val()==1){
			$thirdParty.parent().parent().hide();
		}else{
			$thirdParty.parent().parent().show();
		}
	});
	
	// 商品选择
	$productSelect.autocomplete("product_select.jhtml", {
		dataType: "json",
		extraParams: {
			excludeIds: function() {
				return $productTable.find("input:hidden").map(function() {
					return $(this).val();
				}).get();
			}
		},
		cacheLength: 0,
		max: 20,
		delay:1000,
		width: 218,
		scrollHeight: 300,
		parse: function(data) {
			return $.map(data, function(item) {
				return {
					data: item,
					value: item.name
				}
			});
		},
		formatItem: function(item) {
			return '<span title="' + escapeHtml(item.name) + '">' + escapeHtml(abbreviate(item.name, 50, "...")) + '<\/span>' + (item.specifications.length > 0 ? ' <span class="silver">[' + escapeHtml(item.specifications.join(", ")) + ']<\/span>' : '');
		}
	}).result(function(event, item) {
		var $productTr = (
			[@compress single_line = true]
				'<tr>
					<td>
						<input type="hidden" name="productIds" value="' + item.id + '" \/>
						' + item.sn + '
					<\/td>
					<td>
						<span title="' + escapeHtml(item.name) + '">' + escapeHtml(abbreviate(item.name, 50, "...")) + '<\/span>' + (item.specifications.length > 0 ? ' <span class="silver">[' + escapeHtml(item.specifications.join(", ")) + ']<\/span>' : '') + '
					<\/td>
					<td>
						<a href="javascript:;" class="remove">[${message("admin.common.remove")}]<\/a>
					<\/td>
				<\/tr>'
			[/@compress]
		);
		$productTitle.show();
		$productTable.append($productTr);
	});
	
	// 删除赠品
	$productTable.on("click", "a.remove", function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "${message("admin.dialog.deleteConfirm")}",
			onOk: function() {
				$this.closest("tr").remove();
				if ($productTable.find("tr").size() <= 1) {
					$productTitle.hide();
				}
				$productSelect.val("");
			}
		});
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			beginDate:"required",
            endDate:"required",
			name: "required",
			prefix: "required",
			minimumPrice: {
				min: 0,
				required: true,
				decimal: {
					integer: 12,
					fraction: ${setting.priceScale}
				}
			},
			countPrice: {
				min: 0,
				required: true,
				decimal: {
					integer: 12,
					fraction: ${setting.priceScale}
				}
			}
		}
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.coupon.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Coupon.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Coupon.prefix")}:
				</th>
				<td>
					<input type="text" name="prefix" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Coupon.beginDate")}:
				</th>
				<td>
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Coupon.endDate")}:
				</th>
				<td>
					<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Coupon.minimumPrice")}:
				</th>
				<td colspan="2">
					<input type="text" id="minimumPrice" name="minimumPrice" class="text" maxlength="16" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>优惠金额:
				</th>
				<td colspan="2">
					<input type="text" id="countPrice" name="countPrice" class="text" maxlength="16" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>门店:
				</th>
				<td colspan="2">
					<select name="shopId">
						[#list shops as shop]
							<option value="${shop.id}">${shop.name}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="1" checked="checked" />${message("Coupon.isEnabled")}
						<input type="hidden" name="isEnabled" value="0" />
					</label>
				</td>
			</tr>
			<tr >
				<th>
					介绍:
				</th>
				<td>
					<textarea rows="5" cols="64" id="introduction" class="text" name="introduction"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					关联商品:
				</th>
				<td>
					<input type="text" id="productSelect" name="productSelect" class="text" maxlength="200" title="请输入商品编码或者名称"/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<table id="productTable" class="item">
						<tr class="hidden">
							<th>
								${message("Product.sn")}
							</th>
							<th>
								${message("Product.name")}
							</th>
							<th>
								${message("admin.common.action")}
							</th>
						</tr>
					</table>
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