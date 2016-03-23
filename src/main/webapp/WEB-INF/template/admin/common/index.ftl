[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.index.title")}</title>
<meta name="author"/>
<meta name="copyright" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<style type="text/css">
.input .powered {
	font-size: 11px;
	text-align: right;
	color: #cccccc;
}
</style>
</head>
<body>
	<div class="breadcrumb">
		${message("admin.index.title")}
	</div>
	<table class="input">
		<tr>
			<th>
				${message("admin.index.daiziti")}:
			</th>
			<td>
				${pendingDaizitiOrderCount}
			</td>
			<th>
				${message("admin.index.pendingShipmentOrderCount")}:
			</th>
			<td>
				${pendingShipmentOrderCount}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.pendingReceiveOrderCount")}:
			</th>
			<td>
				${pendingReceiveOrderCount}
			</td>
			<th>
				${message("admin.index.pendingRefundsOrderCount")}:
			</th>
			<td>
				${pendingRefundsOrderCount}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.marketableProductCount")}:
			</th>
			<td>
				${marketableProductCount}
			</td>
			<th>
				${message("admin.index.notMarketableProductCount")}:
			</th>
			<td>
				${notMarketableProductCount}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.stockAlertProductCount")}:
			</th>
			<td>
				${stockAlertProductCount}
			</td>
			<th>
				${message("admin.index.outOfStockProductCount")}:
			</th>
			<td>
				${outOfStockProductCount}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.index.memberCount")}:
			</th>
			<td>
				${memberCount}
			</td>
		</tr>
		<tr>
			<td class="powered" colspan="4">
				COPYRIGHT © 2005-2015 PUYUN ALL RIGHTS RESERVED.
			</td>
		</tr>
	</table>
</body>
</html>
[/#escape]