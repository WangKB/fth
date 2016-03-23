[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.brand.add")}</title>
<meta name="author"  />
<meta name="copyright" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $type = $("#type");
	var $logo = $("#logo");
	var $filePicker = $("#filePicker");
	var $introduction = $("#introduction");
	
	[@flash_message /]
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			logo: {
				required: true,
				pattern: /^(http:\/\/|https:\/\/|\/).*$/i
			},
			url: {
				pattern: /^(http:\/\/|https:\/\/|ftp:\/\/|mailto:|\/|#).*$/i
			},
			order: "digits"
		}
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.brand.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>订单:
				</th>
				<td>
					<input type="text" name="orderSn" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					退款方式:
				</th>
				<td>
					<select name="method">
						[#list types as type]
							<option value="${type}">${message("Refunds.Type." + type)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					支付方式:
				</th>
				<td>
					<select name="paymentMethodId">
						[#list paymentMethods as paymentMethod]
							<option value="${paymentMethod.id}">${paymentMethod.name}</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr>
				<th>
					退款银行:
				</th>
				<td>
					<input type="text" name="bank" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					退款账号:
				</th>
				<td>
					<input type="text" name="account" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					退款金额:
				</th>
				<td>
					<input type="text" name="amount" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					退款人:
				</th>
				<td>
					<input type="text" name="payee" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td>
					<input type="text" name="memo" class="text" maxlength="200" />
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