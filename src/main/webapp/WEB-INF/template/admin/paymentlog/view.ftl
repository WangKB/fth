[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.paymentLog.view")}</title>
<meta name="author"  />
<meta name="copyright"  />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.paymentLog.view")}
	</div>
	<table class="input">
		<tr>
			<th>
				${message("PaymentLog.sn")}:
			</th>
			<td>
				${paymentLog.sn}
			</td>
			<th>
				${message("admin.common.createDate")}:
			</th>
			<td>
				${paymentLog.createDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>
		<tr>
			<th>
				${message("PaymentLog.type")}:
			</th>
			<td>
				${message("PaymentLog.Type." + paymentLog.method)}
			</td>
			<th>
				${message("PaymentLog.status")}:
			</th>
			<td>
				${message("PaymentLog.Status." + paymentLog.status)}
			</td>
		</tr>
		<tr>
			<th>
				流水号:
			</th>
			<td>
				${paymentLog.pingXXSN!"-"}
			</td>
			<th>
				${message("PaymentLog.paymentPluginId")}:
			</th>
			<td>
				${paymentLog.paymentPluginId!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("PaymentLog.amount")}:
			</th>
			<td>
				${currency(paymentLog.amount, true)}
				[#if paymentLog.fee > 0]
					<span class="silver">${message("PaymentLog.fee")}: (${currency(paymentLog.fee, true)})</span>
				[/#if]
			</td>
			<th>
				${message("PaymentLog.paymentPluginName")}:
			</th>
			<td>
				${paymentLog.paymentPluginName!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("PaymentLog.order")}:
			</th>
			<td>
				${paymentLog.order.sn}
			</td>
			<th>
				${message("PaymentLog.member")}:
			</th>
			<td>
				${paymentLog.member.username!"-"}
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td colspan="3">
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
			</td>
		</tr>
	</table>
</body>
</html>
[/#escape]