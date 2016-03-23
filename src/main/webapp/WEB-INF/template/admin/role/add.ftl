[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.role.add")}</title>
<meta name="author"/>
<meta name="copyright"/>
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<style type="text/css">
.authorities label {
	min-width: 120px;
	_width: 120px;
	display: inline-block;
	padding-right: 4px;
	_white-space: nowrap;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");

    var $selectsecondNav = $("#inputForm .second-nav");

	[@flash_message /]
	
	$selectAll.click(function() {
		var $this = $(this);
		var $thisCheckbox = $this.closest("tr").find("input:checkbox");
		if ($thisCheckbox.filter(":checked").size() > 0) {
			$thisCheckbox.prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
		}
		return false;
	});

    $selectsecondNav.click(function(){
        var $this = $(this);
        var $thisCheckbox = $this.closest("p").find(".frist-nav");
        if ($this.closest("p").find(".second-nav").filter(":checked").size() > 0) {
            $thisCheckbox.prop("checked", true);
        } else {
            $thisCheckbox.prop("checked", false);
        }
    });
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			authorities: "required"
		}
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.role.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Role.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("Role.description")}:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.productGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
                        [#--商品管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:goods" />${message("admin.role.goods")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:goods-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:goods-edit"/>编辑功能
                            </label>
                            】
                        </p>
                        [#--商品分类管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:productCategory" />${message("admin.role.productCategory")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:productCategory-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:productCategory-edit"/>编辑功能
                            </label>
                            】
                        </p>
                        [#--商品参数管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:parameter" />${message("admin.role.parameter")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:parameter-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:parameter-edit"/>编辑功能
                            </label>
                            】
                        </p>
                        [#--商品属性管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:attribute" />${message("admin.role.parameter")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:attribute-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:attribute-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        [#--商品规格管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:specification" />${message("admin.role.parameter")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:specification-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:specification-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        [#--品牌管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:brand" />${message("admin.role.parameter")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:brand-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:brand-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        [#--型号管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:model" />型号管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:model-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:model-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        [#--售价修改申请--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:price-appl" />售价修改申请
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:price-appl-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:price-appl-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        [#--售价修改 审核--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:price-appr" />售价修改审核
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:price-appr-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:price-appr-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        [#-- 历史进价 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:costprice" />历史进价
                            </label>
                        </p>

                        [#--供应商管理--]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:supplier" />供应商管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:supplier-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:supplier-edit"/>编辑功能
                            </label>
                            】
                        </p>
					</span>
				</td>
			</tr>
            [#-- 订单 --]
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.orderGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
                        [#--在线物流订单--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:onlineDelivery" /> 在线物流订单
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onlineDelivery-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onlineDelivery-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onlineDelivery-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--在线自提订单--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:onLineSelfList" /> 在线自提订单
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onLineSelfList-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onLineSelfList-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onLineSelfList-receive-price"/>收款功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onLineSelfList-receive-goods"/>收货功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:onLineSelfList-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--订单分发管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:allotOrder" /> 订单分发管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:allotOrder-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:allotOrder-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:allotOrder-distribute"/>分发功能
                                </label>
                                】
                            </p>

                        [#--线下退货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:offLineReturn" /> 线下退货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:offLineReturn-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:offLineReturn-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:offLineReturn-goods"/>退货
                                </label>
                                】
                            </p>

						 [#--线下订单管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" 
                                           value="admin:offLineOrder"/> 线下订单管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" 
                                           value="admin:offLineOrder-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities"
                                           value="admin:offLineOrder-edit"/>编辑功能
                                </label>
                                】
                            </p>
                            
                        [#--物流公司--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:deliveryCorp" /> 物流公司
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:deliveryCorp-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:deliveryCorp-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--发货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:shipping" /> 发货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:shipping-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:shipping-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:shipping-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--在线售后管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:cus-service" /> 收款管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:cus-service-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:cus-service-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:cus-service-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--快递单模版管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:deliveryTemplate" /> 快递单管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:deliveryTemplate-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:deliveryTemplate-edit"/>编辑功能
                                </label>
                                】
                            </p>
					</span>
				</td>
			</tr>

            [#-- 库存 --]
            <tr class="authorities">
                <th>
                    <a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">库存管理</a>
                </th>
                <td>
					<span class="fieldSet">
                        [#--采购申请管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:purchaseReq" /> 采购申请管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchaseReq-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchaseReq-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--采购汇总管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:purchase" /> 采购汇总管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchase-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchase-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchase-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--采购入库管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:purchaseCheck" /> 采购入库管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchaseCheck-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchaseCheck-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:purchaseCheck-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--调拨申请管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:transfer" /> 调拨申请管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transfer-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transfer-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--调拨单管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:transferslip" /> 调拨汇总管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transferslip-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transferslip-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transferslip-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--调拨验收管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:transferCheck" /> 调拨验收管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transferCheck-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transferCheck-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:transferCheck-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--配送管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:distribution" /> 配送管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:distribution-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:distribution-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--配送验收管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:distributionCheck" /> 配送验收管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:distributionCheck-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:distributionCheck-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:distributionCheck-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--发货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:shipOrder" /> 发货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:shipOrder-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:shipOrder-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:shipOrder-deliver"/>发货
                                </label>
                                】
                            </p>

                            [#--退货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:wareHouseChange" /> 退货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:wareHouseChange-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:wareHouseChange-edit"/>编辑功能
                                </label>
                                】
                            </p>

                            [#--库存记录查询--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeLog" /> 库存记录查询
                                </label>
                            </p>

					</span>
                </td>
            </tr>

            [#-- 财务管理 --]
            <tr class="authorities">
                <th>
                    <a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">财务管理</a>
                </th>
                <td>
					<span class="fieldSet">
                        [#--积分管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:point" /> 积分管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:point-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:point-edit"/>积分调整功能
                                </label>
                                】
                            </p>

                        [#--神币管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:deposit" /> 神币管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:deposit-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:deposit-edit"/>神币调整功能
                                </label>
                                】
                            </p>

                        [#--收款管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:payment" /> 收款管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:payment-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:payment-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--退款管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" value="admin:refunds" /> 退款管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:refunds-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" value="admin:refunds-edit"/>编辑功能
                                </label>
                                】
                            </p>

					</span>
                </td>
            </tr>

            [#-- 会员管理 --]
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.memberGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox"  class="frist-nav" name="authorities" value="admin:member" />${message("admin.role.member")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:member-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:member-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox"  class="frist-nav" name="authorities" value="admin:memberRank" />${message("admin.role.memberRank")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:memberRank-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:memberRank-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:review" />${message("admin.role.review")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:review-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:review-edit"/>编辑功能
                            </label>
                            】
                        </p>

						<p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:growthvalue" />成长值管理
                            </label>
						</p>

                        <p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:recomLog" />邀请历史记录
                            </label>
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:pointRule" />积分规则管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:pointRule-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:pointRule-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:pointBehaviour" />积分行为管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:pointBehaviour-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:pointBehaviour-edit"/>编辑功能
                            </label>
                            】
                        </p>
					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.contentGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:helpCenter" />帮助中心
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:helpCenter-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:helpCenter-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeAnnoun" />门店公告管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeAnnoun-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeAnnoun-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeInfo" />资讯管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeInfo-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeInfo-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:friendLink" />${message("admin.role.friendLink")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:friendLink-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:friendLink-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:index" />${message("admin.role.index")}
                            </label>
                        </p>

					</span>
				</td>
			</tr>
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.marketingGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:promotion" />${message("admin.role.promotion")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:promotion-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:promotion-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:coupon" />${message("admin.role.coupon")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:coupon-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:coupon-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:promotion" />${message("admin.role.promotion")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:promotion-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:promotion-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:bonus" />红包管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:bonus-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:bonus-edit"/>编辑功能
                            </label>
                            】
                        </p>

                         <p>
                             <label>
                                 <input type="checkbox" name="authorities" value="admin:todayGrab" />今日抢管理
                             </label>
                         </p>

                        <p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:secKill" />秒杀管理
                            </label>
                        </p>

					</span>
				</td>
			</tr>
            [#-- 运营商管理 --]
            <tr class="authorities">
                <th>
                    <a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">运营商管理</a>
                </th>
                <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:operator" />运营商管理
                            </label>
                        </p>
					</span>
                </td>
            </tr>

            [#-- 模版管理 --]
            <tr class="authorities">
                <th>
                    <a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">模版管理</a>
                </th>
                <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:template" />模版管理
                            </label>
                        </p>
					</span>
                </td>
            </tr>

            [#-- 门店管理 --]
            <tr class="authorities">
                <th>
                    <a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">门店管理</a>
                </th>
                <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:store" />门店管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:store-review"/>门店查看
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:store-add"/>门店添加
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:store-edit"/>门店编辑
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:store-del"/>门店删除
                            </label>
                            】
                        </p>

                        [#-- 门店活动 --]
                         <p>
                             <label>
                                 <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeAct" />门店活动
                             </label>
                             【
                             <label>
                                 <input type="checkbox" class="second-nav" name="authorities" value="admin:storeAct-review"/>查看功能
                             </label>
                             <label>
                                 <input type="checkbox" class="second-nav" name="authorities" value="admin:storeAct-edit"/>编辑功能
                             </label>
                             】
                         </p>

                    [#-- 服务管理 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeService" />服务管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeService-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeService-edit"/>编辑功能
                            </label>
                            】
                        </p>

                    [#-- 门店服务 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeSetService" />门店服务管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeSetService-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeSetService-edit"/>编辑功能
                            </label>
                            】
                        </p>

                    [#-- 门店服务 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:storeAdmin" />门店店员管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeAdmin-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:storeAdmin-edit"/>编辑功能
                            </label>
                            】
                        </p>

					</span>
                </td>
            </tr>

            [#-- 统计管理 --]
			[#--<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.statisticGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="authorities" value="admin:statistics" />${message("admin.role.statistics")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:memberStatistic" />${message("admin.role.memberStatistic")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:orderStatistic" />${message("admin.role.orderStatistic")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:memberRanking" />${message("admin.role.memberRanking")}
						</label>
						<label>
							<input type="checkbox" name="authorities" value="admin:goodsRanking" />${message("admin.role.goodsRanking")}
						</label>
					</span>
				</td>
			</tr>--]
			<tr class="authorities">
				<th>
					<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">${message("admin.role.systemGroup")}</a>
				</th>
				<td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" name="authorities" value="admin:setting" />${message("admin.role.setting")}
                            </label>
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:navigation" />前台导航管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:navigation-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:navigation-edit"/>编辑功能
                            </label>
                            】
                        </p>

                         <p>
                             <label>
                                 <input type="checkbox" class="frist-nav" name="authorities" value="admin:area" />${message("admin.role.area")}
                             </label>
                             【
                             <label>
                                 <input type="checkbox" class="second-nav" name="authorities" value="admin:area-review"/>查看功能
                             </label>
                             <label>
                                 <input type="checkbox" class="second-nav" name="authorities" value="admin:area-edit"/>编辑功能
                             </label>
                             】
                         </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:paymentMethod" />${message("admin.role.paymentMethod")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:paymentMethod-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:paymentMethod-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:shippingMethod" />${message("admin.role.shippingMethod")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:shippingMethod-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:shippingMethod-edit"/>编辑功能
                            </label>
                            】
                        </p>

					    <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:admin" />${message("admin.role.admin")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:admin-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:admin-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:role" />${message("admin.role.role")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:role-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:role-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" value="admin:log" />${message("admin.role.log")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:adminLog-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" value="admin:adminLog-edit"/>编辑功能
                            </label>
                            】
                        </p>
					</span>
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