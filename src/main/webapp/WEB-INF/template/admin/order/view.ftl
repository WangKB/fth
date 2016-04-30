[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.order.view")}</title>
<meta name="author" />
<meta name="copyright" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	$("#saveRemark").click(function() {
		var remark = $("#remark").val();
		$.ajax({
			url: "remark.jhtml",
			type: "POST",
			data: {id:${order.id},remark:remark},
			dataType: "json",
			success: function(data) {
				$.message('success','操作成功');
			}
		});
		
	});

	$("#changeButton").click(function() {
		$.dialog({
			title: "改变状态",
			content: 
				[@compress single_line = true]
					'<form id="statusForm" action="changeStatus.jhtml" method="post">
						<input type="hidden" name="token" value="${token}" \/>
						<input type="hidden" name="orderId" value="${order.id}" \/>
						<table class="input">
							<tr>
								<th>
									状态:
								<\/th>
								<td>
									<select name="status">
										[#list statuses as status]
											[#if status.id!=0&&status.id!=1&&status.id>order.status.id]
											<option value="${status}">${message("Order.Status." + status)}<\/option>
											[/#if]
										[/#list]
									<\/select>
								<\/td>
							<\/tr>
						<\/table>
					<\/form>'
				[/@compress]
			,
			width: 350,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "${message("admin.dialog.cancel")}",
			onOk: function() {
				$("#statusForm").submit();
				return false;
			}
		});
	});
	
	$("#changeShop").click(function() {
		$.dialog({
			title: "改变店铺",
			content: 
				[@compress single_line = true]
					'<form id="changeShopForm" action="changeShop.jhtml" method="post">
						<input type="hidden" name="token" value="${token}" \/>
						<input type="hidden" name="orderId" value="${order.id}" \/>
						<table class="input">
							<tr>
								<th>
									店铺:
								<\/th>
								<td>
									<select name="shopId">
										[#list shops as shop]
											[#if shop.id!=order.shopId]
											<option value="${shop.id}">${shop.name}<\/option>
											[/#if]
										[/#list]
									<\/select>
								<\/td>
							<\/tr>
						<\/table>
					<\/form>'
				[/@compress]
			,
			width: 350,
			modal: true,
			ok: "${message("admin.dialog.ok")}",
			cancel: "${message("admin.dialog.cancel")}",
			onOk: function() {
				$.ajax({
					url: "changeShop.jhtml",
					type: "POST",
					data: $("#changeShopForm").serialize(),
					dataType: "json",
					success: function(data) {
						$.message(data);
					}
				});
			}
		});
	});
});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.order.view")}
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="${message("admin.order.orderInfo")}" />
		</li>
		<li>
			<input type="button" value="订单项" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<td>
				&nbsp;
			</td>
			<td colspan="3">
				<input type="button" id="changeButton" class="button" value="改变状态" />
				<input type="button" id="changeShop" class="button" value="改变店铺" />
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.sn")}:
			</th>
			<td width="360">
				${order.sn}
			</td>
			<th>
				${message("admin.common.createDate")}:
			</th>
			<td>
				${order.createDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>
		<tr>
			<th>
				来源:
			</th>
			<td>
				${message("Order.FromType." + order.fromType)}
			</td>
			<th>
				${message("Order.status")}:
			</th>
			<td>
				${message("Order.Status." + order.status)}
					[#if order.expire??]
						<span class="silver">(${message("Order.expire")}: ${order.expire?string("yyyy-MM-dd HH:mm:ss")})</span>
					[/#if]
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.price")}:
			</th>
			<td>
				${currency(order.price, true)}
			</td>
			<th>
				使用积分:
			</th>
			<td>
				${order.pointCount}
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.amount")}:
			</th>
			<td>
				<span class="red">${currency(order.amount, true)}</span>
			</td>
			<th>
				${message("Order.amountPaid")}:
			</th>
			<td>
				${currency(order.amountPaid, true)}
				<span class="silver">(${message("Order.amountPayable")}: ${currency(order.amountPayable, true)})</span>
			</td>
		</tr>
			<tr>
				<th>
					${message("Order.refundAmount")}:
				</th>
				<td>
					${currency(order.refundAmount, true)}
				</td>
				<th>
					${message("Order.quantity")}:
				</th>
				<td>
					${order.quantity}
				</td>
			</tr>
		<tr>
			<th>
				${message("admin.order.coupon")}:
			</th>
			<td>
				${(order.couponCodeId)!"-"}
			</td>
			<th>
				${message("Order.couponDiscount")}:
			</th>
			<td>
				${currency(order.couponDiscount, true)}
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.fee")}:
			</th>
			<td>
				${currency(order.fee, true)}
			</td>
			<th>
				${message("Order.freight")}:
			</th>
			<td>
				${currency(order.freight, true)}
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.offsetAmount")}:
			</th>
			<td>
				${currency(order.offsetAmount, true)}
			</td>
			<th>
				${message("Order.rewardPoint")}:
			</th>
			<td>
				${order.rewardPoint}
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.paymentMethod")}:
			</th>
			<td>
				${order.paymentMethodName!"-"}
			</td>
			<th>
				${message("Order.shippingMethod")}:
			</th>
			<td>
				${order.shippingMethodName!"-"}
			</td>
		</tr>
		[#if order.invoice??]
			<tr>
				<th>
					${message("Invoice.title")}:
				</th>
				<td>
					${order.invoice.title}
				</td>
				<th>
					${message("Order.tax")}:
				</th>
				<td>
					${currency(order.tax, true)}
				</td>
			</tr>
		[/#if]
		<tr>
			<th>
				${message("Order.consignee")}:
			</th>
			<td>
				${order.consignee}
			</td>
			<th>
				${message("Order.area")}:
			</th>
			<td>
				${order.areaId}
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.address")}:
			</th>
			<td>
				${order.address}
			</td>
			<th>
				${message("Order.zipCode")}:
			</th>
			<td>
				${order.zipCode}
			</td>
		</tr>
		<tr>
			<th>
				${message("Order.phone")}:
			</th>
			<td>
				${order.mobile}
			</td>
			<th>
				备注:
			</th>
			<td>
				<input type='text' class='text' id="remark"  value='${order.remark}'>
				<input type="button" id="saveRemark" class="button" value="保存备注" />
			</td>
		</tr>
		<tr>
			<th>
				发货图片:
			</th>
			<td>
				<img src='${order.deliveryImages}' style="width:350px">
			</td>
			<th>
				所在店铺:
			</th>
			<td>
				${shop_name(order.shopId)}
			</td>
		</tr>
	</table>
	<table class="item tabContent">
		<tr>
			<th>
				${message("OrderItem.sn")}
			</th>
			<th>
				${message("OrderItem.name")}
			</th>
			<th>
				${message("OrderItem.price")}
			</th>
			<th>
				${message("OrderItem.quantity")}
			</th>
		</tr>
		[#list items as orderItem]
			<tr>
				<td>
					${orderItem.sn}
				</td>
				<td width="400">
					${orderItem.name}
				</td>
				<td>
					${currency(orderItem.price, true)}
				</td>
				<td>
					${orderItem.quantity}
				</td>
			</tr>
		[/#list]
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
			</td>
		</tr>
	</table>
</body>
</html>
[/#escape]