[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.coupon.generate")}</title>
<meta name="author" />
<meta name="copyright"/>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $totalCount = $("#totalCount");
	var $count = $("#count");
	var totalCount = ${totalCount};
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			count: {
				required: true,
				integer: true,
				min: 1
			}
		},
		submitHandler: function(form) {
			totalCount = totalCount + parseInt($count.val());
			$totalCount.text(totalCount);
			form.submit();
		}
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 统计优惠码
	</div>
	<form id="inputForm" action="download.jhtml" method="post">
		<input type="hidden" name="id" value="${coupon.id}" />
		<table class="input">
			<tr>
				<th>
					${message("Coupon.name")}:
				</th>
				<td>
					${coupon.name}
				</td>
			</tr>
			<tr>
				<th>
					${message("Coupon.beginDate")}:
				</th>
				<td>
					[#if coupon.beginDate??]
						${coupon.beginDate?string("yyyy-MM-dd")}
					[#else]
						-
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("Coupon.endDate")}:
				</th>
				<td>
					[#if coupon.endDate??]
						${coupon.endDate?string("yyyy-MM-dd")}
					[#else]
						-
					[/#if]
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.coupon.totalCount")}:
				</th>
				<td>
					<span id="totalCount">${totalCount}</span>
				</td>
			</tr>
			<tr>
				<th>
					已发放数量:
				</th>
				<td>
					${givenCount}
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.coupon.usedCount")}:
				</th>
				<td>
					${usedCount}
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
				</td>
			</tr>
			[#list couponCodes as couponCode]
			<tr>
				<th>
					${couponCode.member.name}
					[#if couponCode.isUsed]
					[已使用]
					[#else]
					[未使用]
					[/#if]
				</th>
				<td>
					${couponCode.code}
				</td>
			</tr>
			[/#list]
		</table>
	</form>
</body>
</html>
[/#escape]