[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>${message("admin.role.edit")}</title>
    <meta name="author"/>
    <meta name="copyright"/>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css"/>
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
        $().ready(function () {

            var $inputForm = $("#inputForm");
            var $selectAll = $("#inputForm .selectAll");
            var $selectsecondNav = $("#inputForm .second-nav");

            [@flash_message /]

            $selectAll.click(function () {
                var $this = $(this);
                var $thisCheckbox = $this.closest("tr").find("input:checkbox");
                if ($thisCheckbox.filter(":checked").size() > 0) {
                    $thisCheckbox.prop("checked", false);
                } else {
                    $thisCheckbox.prop("checked", true);
                }
                return false;
            });

            $selectsecondNav.click(function () {
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
    <a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.role.edit")}
</div>
<form id="inputForm" action="update.jhtml" method="post">
<input type="hidden" name="id" value="${role.id}" />
<table class="input">
<tr>
    <th>
        <span class="requiredField">*</span>${message("Role.name")}:
    </th>
    <td>
        <input type="text" name="name" class="text" value="${role.name}" maxlength="200"/>
    </td>
</tr>
<tr>
    <th>
    ${message("Role.description")}:
    </th>
    <td>
        <input type="text" name="description" class="text" value="${role.description}" maxlength="200"/>
    </td>
</tr>
<tr>
    <td colspan="2">
        &nbsp;
    </td>
</tr>
<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.productGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
                        [#--商品管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:goods")] checked="checked"[/#if]
                                           value="admin:goods"/>${message("admin.role.goods")}
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:goods-review")] checked="checked"[/#if]
                                           value="admin:goods-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:goods-review")] checked="checked"[/#if]
                                           value="admin:goods-edit"/>编辑功能
                                </label>
                                】
                            </p>
                        [#--商品分类管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:productCategory")] checked="checked"[/#if]
                                           value="admin:productCategory"/>${message("admin.role.productCategory")}
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:productCategory-review")] checked="checked"[/#if]
                                           value="admin:productCategory-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:productCategory-edit")] checked="checked"[/#if]
                                           value="admin:productCategory-edit"/>编辑功能
                                </label>
                                】
                            </p>
                        [#--商品参数管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:parameter")] checked="checked"[/#if]
                                           value="admin:parameter"/>${message("admin.role.parameter")}
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:parameter-review")] checked="checked"[/#if]
                                           value="admin:parameter-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:parameter-edit")] checked="checked"[/#if]
                                           value="admin:parameter-edit"/>编辑功能
                                </label>
                                】
                            </p>
                        [#--商品属性管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:attribute")] checked="checked"[/#if]
                                           value="admin:attribute"/>${message("admin.role.parameter")}
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:attribute-review")] checked="checked"[/#if]
                                           value="admin:attribute-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:attribute-edit")] checked="checked"[/#if]
                                           value="admin:attribute-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--商品规格管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:specification")] checked="checked"[/#if]
                                           value="admin:specification"/>${message("admin.role.parameter")}
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:specification-review")] checked="checked"[/#if]
                                           value="admin:specification-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:specification-edit")] checked="checked"[/#if]
                                           value="admin:specification-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--品牌管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:brand")] checked="checked"[/#if]
                                           value="admin:brand"/>${message("admin.role.parameter")}
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:brand-review")] checked="checked"[/#if]
                                           value="admin:brand-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:brand-edit")] checked="checked"[/#if]
                                           value="admin:brand-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--型号管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:model")] checked="checked"[/#if]
                                           value="admin:model"/>型号管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:model-review")] checked="checked"[/#if]
                                           value="admin:model-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:model-edit")] checked="checked"[/#if]
                                           value="admin:model-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--售价修改申请--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:price-appl")] checked="checked"[/#if]
                                           value="admin:price-appl"/>售价修改申请
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:price-appl-review")] checked="checked"[/#if]
                                           value="admin:price-appl-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:price-appl-edit")] checked="checked"[/#if]
                                           value="admin:price-appl-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--售价修改 审核--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:price-appr")] checked="checked"[/#if]
                                           value="admin:price-appr"/>售价修改审核
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:price-appr-review")] checked="checked"[/#if]
                                           value="admin:price-appr-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:price-appr-edit")] checked="checked"[/#if]
                                           value="admin:price-appr-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#-- 历史进价 --]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:costprice")] checked="checked"[/#if]
                                           value="admin:costprice"/>历史进价
                                </label>
                            </p>

                        [#--供应商管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:supplier")] checked="checked"[/#if]
                                           value="admin:supplier"/>供应商管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:supplier-review")] checked="checked"[/#if]
                                           value="admin:supplier-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:supplier-edit")] checked="checked"[/#if]
                                           value="admin:supplier-edit"/>编辑功能
                                </label>
                                】
                            </p>
					</span>
    </td>
</tr>
[#-- 订单 --]
<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.orderGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
                        [#--在线物流订单--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:onlineDelivery")] checked="checked"[/#if]
                                           value="admin:onlineDelivery"/> 在线物流订单
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onlineDelivery-review")] checked="checked"[/#if]
                                           value="admin:onlineDelivery-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onlineDelivery-edit")] checked="checked"[/#if]
                                           value="admin:onlineDelivery-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onlineDelivery-print")] checked="checked"[/#if]
                                           value="admin:onlineDelivery-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--在线自提订单--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:onLineSelfList")] checked="checked"[/#if]
                                           value="admin:onLineSelfList"/> 在线自提订单
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onLineSelfList-review")] checked="checked"[/#if]
                                           value="admin:onLineSelfList-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onLineSelfList-edit")] checked="checked"[/#if]
                                           value="admin:onLineSelfList-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onLineSelfList-receive-price")] checked="checked"[/#if]
                                           value="admin:onLineSelfList-receive-price"/>收款功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onLineSelfList-receive-goods")] checked="checked"[/#if]
                                           value="admin:onLineSelfList-receive-goods"/>收货功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:onLineSelfList-print")] checked="checked"[/#if]
                                           value="admin:onLineSelfList-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--订单分发管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:allotOrder")] checked="checked"[/#if]
                                           value="admin:allotOrder"/> 订单分发管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:allotOrder-review")] checked="checked"[/#if]
                                           value="admin:allotOrder-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:allotOrder-edit")] checked="checked"[/#if]
                                           value="admin:allotOrder-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:allotOrder-distribute")] checked="checked"[/#if]
                                           value="admin:allotOrder-distribute"/>分发功能
                                </label>
                                】
                            </p>

						  [#--线下订单管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineOrder")] checked="checked"[/#if]
                                           value="admin:offLineOrder"/> 线下订单管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineOrder-review")] checked="checked"[/#if]
                                           value="admin:offLineOrder-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineOrder-edit")] checked="checked"[/#if]
                                           value="admin:offLineOrder-edit"/>编辑功能
                                </label>
                                】
                            </p>
                            
                        [#--线下退货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineReturn")] checked="checked"[/#if]
                                           value="admin:offLineReturn"/> 线下退货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineReturn-review")] checked="checked"[/#if]
                                           value="admin:offLineReturn-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineReturn-edit")] checked="checked"[/#if]
                                           value="admin:offLineReturn-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:offLineReturn-goods")] checked="checked"[/#if]
                                           value="admin:offLineReturn-goods"/>退货
                                </label>
                                】
                            </p>

                        [#--物流公司--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:deliveryCorp")] checked="checked"[/#if]
                                           value="admin:deliveryCorp"/> 物流公司
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:deliveryCorp-review")] checked="checked"[/#if]
                                           value="admin:deliveryCorp-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:deliveryCorp-edit")] checked="checked"[/#if]
                                           value="admin:deliveryCorp-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--发货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipping")] checked="checked"[/#if]
                                           value="admin:shipping"/>
                                    发货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipping-review")] checked="checked"[/#if]
                                           value="admin:shipping-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipping-edit")] checked="checked"[/#if]
                                           value="admin:shipping-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipping-print")] checked="checked"[/#if]
                                           value="admin:shipping-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--在线售后管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:cus-service")] checked="checked"[/#if]
                                           value="admin:cus-service"/> 收款管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:cus-service-review")] checked="checked"[/#if]
                                           value="admin:cus-service-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:cus-service-edit")] checked="checked"[/#if]
                                           value="admin:cus-service-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:cus-service-print")] checked="checked"[/#if]
                                           value="admin:cus-service-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--快递单模版管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:deliveryTemplate")] checked="checked"[/#if]
                                           value="admin:deliveryTemplate"/> 快递单管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:deliveryTemplate-review")] checked="checked"[/#if]
                                           value="admin:deliveryTemplate-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:deliveryTemplate-edit")] checked="checked"[/#if]
                                           value="admin:deliveryTemplate-edit"/>编辑功能
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
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseReq")] checked="checked"[/#if]
                                           value="admin:purchaseReq"/> 采购申请管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseReq-review")] checked="checked"[/#if]
                                           value="admin:purchaseReq-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseReq-edit")] checked="checked"[/#if]
                                           value="admin:purchaseReq-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--采购汇总管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchase")] checked="checked"[/#if]
                                           value="admin:purchase"/>
                                    采购汇总管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchase-review")] checked="checked"[/#if]
                                           value="admin:purchase-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchase-edit")] checked="checked"[/#if]
                                           value="admin:purchase-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchase-print")] checked="checked"[/#if]
                                           value="admin:purchase-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--采购入库管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseCheck")] checked="checked"[/#if]
                                           value="admin:purchaseCheck"/> 采购入库管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseCheck-review")] checked="checked"[/#if]
                                           value="admin:purchaseCheck-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseCheck-edit")] checked="checked"[/#if]
                                           value="admin:purchaseCheck-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:purchaseCheck-print")] checked="checked"[/#if]
                                           value="admin:purchaseCheck-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--调拨申请管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:transfer")] checked="checked"[/#if]
                                           value="admin:transfer"/>
                                    调拨申请管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transfer-review")] checked="checked"[/#if]
                                           value="admin:transfer-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transfer-edit")] checked="checked"[/#if]
                                           value="admin:transfer-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--调拨单管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferslip")] checked="checked"[/#if]
                                           value="admin:transferslip"/> 调拨汇总管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferslip-review")] checked="checked"[/#if]
                                           value="admin:transferslip-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferslip-edit")] checked="checked"[/#if]
                                           value="admin:transferslip-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferslip-print")] checked="checked"[/#if]
                                           value="admin:transferslip-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--调拨验收管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferCheck")] checked="checked"[/#if]
                                           value="admin:transferCheck"/> 调拨验收管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferCheck-review")] checked="checked"[/#if]
                                           value="admin:transferCheck-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferCheck-edit")] checked="checked"[/#if]
                                           value="admin:transferCheck-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:transferCheck-print")] checked="checked"[/#if]
                                           value="admin:transferCheck-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--配送管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:distribution")] checked="checked"[/#if]
                                           value="admin:distribution"/> 配送管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:distribution-review")] checked="checked"[/#if]
                                           value="admin:distribution-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:distribution-edit")] checked="checked"[/#if]
                                           value="admin:distribution-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--配送验收管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:distributionCheck")] checked="checked"[/#if]
                                           value="admin:distributionCheck"/> 配送验收管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:distributionCheck-review")] checked="checked"[/#if]
                                           value="admin:distributionCheck-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:distributionCheck-edit")] checked="checked"[/#if]
                                           value="admin:distributionCheck-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:distributionCheck-print")] checked="checked"[/#if]
                                           value="admin:distributionCheck-print"/>打印功能
                                </label>
                                】
                            </p>

                        [#--发货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipOrder")] checked="checked"[/#if]
                                           value="admin:shipOrder"/> 发货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipOrder-review")] checked="checked"[/#if]
                                           value="admin:shipOrder-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipOrder-edit")] checked="checked"[/#if]
                                           value="admin:shipOrder-edit"/>编辑功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shipOrder-deliver")] checked="checked"[/#if]
                                           value="admin:shipOrder-deliver"/>发货
                                </label>
                                】
                            </p>

                        [#--退货管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:wareHouseChange")] checked="checked"[/#if]
                                           value="admin:wareHouseChange"/> 退货管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:wareHouseChange-review")] checked="checked"[/#if]
                                           value="admin:wareHouseChange-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:wareHouseChange-edit")] checked="checked"[/#if]
                                           value="admin:wareHouseChange-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--库存记录查询--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeLog")] checked="checked"[/#if]
                                           value="admin:storeLog"/>
                                    库存记录查询
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
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:point")] checked="checked"[/#if]
                                           value="admin:point"/>
                                    积分管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:point-review")] checked="checked"[/#if]
                                           value="admin:point-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:point-edit")] checked="checked"[/#if]
                                           value="admin:point-edit"/>积分调整功能
                                </label>
                                】
                            </p>

                        [#--神币管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:deposit")] checked="checked"[/#if]
                                           value="admin:deposit"/>
                                    神币管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:deposit-review")] checked="checked"[/#if]
                                           value="admin:deposit-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:deposit-edit")] checked="checked"[/#if]
                                           value="admin:deposit-edit"/>神币调整功能
                                </label>
                                】
                            </p>

                        [#--收款管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:payment")] checked="checked"[/#if]
                                           value="admin:payment"/>
                                    收款管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:payment-review")] checked="checked"[/#if]
                                           value="admin:payment-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:payment-edit")] checked="checked"[/#if]
                                           value="admin:payment-edit"/>编辑功能
                                </label>
                                】
                            </p>

                        [#--退款管理--]
                            <p>
                                <label>
                                    <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:refunds")] checked="checked"[/#if]
                                           value="admin:refunds"/>
                                    退款管理
                                </label>
                                【
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:refunds-review")] checked="checked"[/#if]
                                           value="admin:refunds-review"/>查看功能
                                </label>
                                <label>
                                    <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:refunds-edit")] checked="checked"[/#if]
                                           value="admin:refunds-edit"/>编辑功能
                                </label>
                                】
                            </p>

					</span>
    </td>
</tr>

[#-- 会员管理 --]
<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.memberGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:member")] checked="checked"[/#if]
                                       value="admin:member"/>${message("admin.role.member")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:member-review")] checked="checked"[/#if]
                                       value="admin:member-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:member-edit")] checked="checked"[/#if]
                                       value="admin:member-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:memberRank")] checked="checked"[/#if]
                                       value="admin:memberRank"/>${message("admin.role.memberRank")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:memberRank-review")] checked="checked"[/#if]
                                       value="admin:memberRank-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:memberRank-edit")] checked="checked"[/#if]
                                       value="admin:memberRank-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:review")] checked="checked"[/#if]
                                       value="admin:review"/>${message("admin.role.review")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:review-review")] checked="checked"[/#if]
                                       value="admin:review-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:review-edit")] checked="checked"[/#if]
                                       value="admin:review-edit"/>编辑功能
                            </label>
                            】
                        </p>

						<p>
                            <label>
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:growthvalue")] checked="checked"[/#if]
                                       value="admin:growthvalue"/>成长值管理
                            </label>
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:recomLog")] checked="checked"[/#if]
                                       value="admin:recomLog"/>邀请历史记录
                            </label>
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:pointRule")] checked="checked"[/#if]
                                       value="admin:pointRule"/>积分规则管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:pointRule-review")] checked="checked"[/#if]
                                       value="admin:pointRule-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:pointRule-edit")] checked="checked"[/#if]
                                       value="admin:pointRule-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:pointBehaviour")] checked="checked"[/#if]
                                       value="admin:pointBehaviour"/>积分行为管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:pointBehaviour-review")] checked="checked"[/#if]
                                       value="admin:pointBehaviour-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:pointBehaviour-edit")] checked="checked"[/#if]
                                       value="admin:pointBehaviour-edit"/>编辑功能
                            </label>
                            】
                        </p>
					</span>
    </td>
</tr>
<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.contentGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:helpCenter")] checked="checked"[/#if]
                                       value="admin:helpCenter"/>帮助中心
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:helpCenter-review")] checked="checked"[/#if]
                                       value="admin:helpCenter-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:helpCenter-edit")] checked="checked"[/#if]
                                       value="admin:helpCenter-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAnnoun")] checked="checked"[/#if]
                                       value="admin:storeAnnoun"/>门店公告管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAnnoun-review")] checked="checked"[/#if]
                                       value="admin:storeAnnoun-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAnnoun-edit")] checked="checked"[/#if]
                                       value="admin:storeAnnoun-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeInfo")] checked="checked"[/#if]
                                       value="admin:storeInfo"/>资讯管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeInfo-review")] checked="checked"[/#if]
                                       value="admin:storeInfo-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeInfo-edit")] checked="checked"[/#if]
                                       value="admin:storeInfo-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:friendLink")] checked="checked"[/#if]
                                       value="admin:friendLink"/>${message("admin.role.friendLink")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:friendLink-review")] checked="checked"[/#if]
                                       value="admin:friendLink-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:friendLink-edit")] checked="checked"[/#if]
                                       value="admin:friendLink-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:index")] checked="checked"[/#if]
                                       value="admin:index"/>${message("admin.role.index")}
                            </label>
                        </p>

					</span>
    </td>
</tr>
<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.marketingGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:promotion")] checked="checked"[/#if]
                                       value="admin:promotion"/>${message("admin.role.promotion")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:promotion-review")] checked="checked"[/#if]
                                       value="admin:promotion-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:promotion-edit")] checked="checked"[/#if]
                                       value="admin:promotion-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:coupon")] checked="checked"[/#if]
                                       value="admin:coupon"/>${message("admin.role.coupon")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:coupon-review")] checked="checked"[/#if]
                                       value="admin:coupon-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:coupon-edit")] checked="checked"[/#if]
                                       value="admin:coupon-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:promotion")] checked="checked"[/#if]
                                       value="admin:promotion"/>${message("admin.role.promotion")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:promotion-review")] checked="checked"[/#if]
                                       value="admin:promotion-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:promotion-edit")] checked="checked"[/#if]
                                       value="admin:promotion-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:bonus")] checked="checked"[/#if]
                                       value="admin:bonus"/>红包管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:bonus-review")] checked="checked"[/#if]
                                       value="admin:bonus-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:bonus-edit")] checked="checked"[/#if]
                                       value="admin:bonus-edit"/>编辑功能
                            </label>
                            】
                        </p>

                         <p>
                             <label>
                                 <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:todayGrab")] checked="checked"[/#if]
                                        value="admin:todayGrab"/>今日抢管理
                             </label>
                         </p>

                        <p>
                            <label>
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:secKill")] checked="checked"[/#if]
                                       value="admin:secKill"/>秒杀管理
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
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:operator")] checked="checked"[/#if]
                                       value="admin:operator"/>运营商管理
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
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:template")] checked="checked"[/#if]
                                       value="admin:template"/>模版管理
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
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:store")] checked="checked"[/#if]
                                       value="admin:store"/>门店管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:store-review")] checked="checked"[/#if]
                                       value="admin:store-review"/>门店查看
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:store-add")] checked="checked"[/#if]
                                       value="admin:store-add"/>门店添加
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:store-edit")] checked="checked"[/#if]
                                       value="admin:store-edit"/>门店编辑
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:store-del")] checked="checked"[/#if]
                                       value="admin:store-del"/>门店删除
                            </label>
                            】
                        </p>

                    [#-- 门店活动 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAct")] checked="checked"[/#if]
                                       value="admin:storeAct"/>门店活动
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAct-review")] checked="checked"[/#if]
                                       value="admin:storeAct-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAct-edit")] checked="checked"[/#if]
                                       value="admin:storeAct-edit"/>编辑功能
                            </label>
                            】
                        </p>

                    [#-- 服务管理 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeService")] checked="checked"[/#if]
                                       value="admin:storeService"/>服务管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeService-review")] checked="checked"[/#if]
                                       value="admin:storeService-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeService-edit")] checked="checked"[/#if]
                                       value="admin:storeService-edit"/>编辑功能
                            </label>
                            】
                        </p>

                    [#-- 门店服务 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeSetService")] checked="checked"[/#if]
                                       value="admin:storeSetService"/>门店服务管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeSetService-review")] checked="checked"[/#if]
                                       value="admin:storeSetService-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeSetService-edit")] checked="checked"[/#if]
                                       value="admin:storeSetService-edit"/>编辑功能
                            </label>
                            】
                        </p>

                    [#-- 门店服务 --]
                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAdmin")] checked="checked"[/#if]
                                       value="admin:storeAdmin"/>门店店员管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAdmin-review")] checked="checked"[/#if]
                                       value="admin:storeAdmin-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:storeAdmin-edit")] checked="checked"[/#if]
                                       value="admin:storeAdmin-edit"/>编辑功能
                            </label>
                            】
                        </p>

					</span>
    </td>
</tr>

[#-- 统计管理 --]
[#--<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.statisticGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
						<label>
                            <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:statistics")] checked="checked"[/#if]
                                   value="admin:statistics"/>${message("admin.role.statistics")}
                        </label>
						<label>
                            <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:memberStatistic")] checked="checked"[/#if]
                                   value="admin:memberStatistic"/>${message("admin.role.memberStatistic")}
                        </label>
						<label>
                            <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:orderStatistic")] checked="checked"[/#if]
                                   value="admin:orderStatistic"/>${message("admin.role.orderStatistic")}
                        </label>
						<label>
                            <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:memberRanking")] checked="checked"[/#if]
                                   value="admin:memberRanking"/>${message("admin.role.memberRanking")}
                        </label>
						<label>
                            <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:goodsRanking")] checked="checked"[/#if]
                                   value="admin:goodsRanking"/>${message("admin.role.goodsRanking")}
                        </label>
					</span>
    </td>
</tr>--]
<tr class="authorities">
    <th>
        <a href="javascript:;" class="selectAll"
           title="${message("admin.role.selectAll")}">${message("admin.role.systemGroup")}</a>
    </th>
    <td>
					<span class="fieldSet">
                        <p>
                            <label>
                                <input type="checkbox" name="authorities" [#if role.authorities?seq_contains("admin:setting")] checked="checked"[/#if]
                                       value="admin:setting"/>${message("admin.role.setting")}
                            </label>
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:navigation")] checked="checked"[/#if]
                                       value="admin:navigation"/>前台导航管理
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:navigation-review")] checked="checked"[/#if]
                                       value="admin:navigation-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:navigation-edit")] checked="checked"[/#if]
                                       value="admin:navigation-edit"/>编辑功能
                            </label>
                            】
                        </p>

                         <p>
                             <label>
                                 <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:area")] checked="checked"[/#if]
                                        value="admin:area"/>${message("admin.role.area")}
                             </label>
                             【
                             <label>
                                 <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:area-review")] checked="checked"[/#if]
                                        value="admin:area-review"/>查看功能
                             </label>
                             <label>
                                 <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:area-edit")] checked="checked"[/#if]
                                        value="admin:area-edit"/>编辑功能
                             </label>
                             】
                         </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:paymentMethod")] checked="checked"[/#if]
                                       value="admin:paymentMethod"/>${message("admin.role.paymentMethod")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:paymentMethod-review")] checked="checked"[/#if]
                                       value="admin:paymentMethod-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:paymentMethod-edit")] checked="checked"[/#if]
                                       value="admin:paymentMethod-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:shippingMethod")] checked="checked"[/#if]
                                       value="admin:shippingMethod"/>${message("admin.role.shippingMethod")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shippingMethod-review")] checked="checked"[/#if]
                                       value="admin:shippingMethod-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:shippingMethod-edit")] checked="checked"[/#if]
                                       value="admin:shippingMethod-edit"/>编辑功能
                            </label>
                            】
                        </p>

					    <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:admin")] checked="checked"[/#if]
                                       value="admin:admin"/>${message("admin.role.admin")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:admin-review")] checked="checked"[/#if]
                                       value="admin:admin-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:admin-edit")] checked="checked"[/#if]
                                       value="admin:admin-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:role")] checked="checked"[/#if]
                                       value="admin:role"/>${message("admin.role.role")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:role-review")] checked="checked"[/#if]
                                       value="admin:role-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:role-edit")] checked="checked"[/#if]
                                       value="admin:role-edit"/>编辑功能
                            </label>
                            】
                        </p>

                        <p>
                            <label>
                                <input type="checkbox" class="frist-nav" name="authorities" [#if role.authorities?seq_contains("admin:log")] checked="checked"[/#if]
                                       value="admin:log"/>${message("admin.role.log")}
                            </label>
                            【
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:adminLog-review")] checked="checked"[/#if]
                                       value="admin:adminLog-review"/>查看功能
                            </label>
                            <label>
                                <input type="checkbox" class="second-nav" name="authorities" [#if role.authorities?seq_contains("admin:adminLog-edit")] checked="checked"[/#if]
                                       value="admin:adminLog-edit"/>编辑功能
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
        <input type="submit" class="button" value="${message("admin.common.submit")}"/>
        <input type="button" class="button" value="${message("admin.common.back")}"
               onclick="history.back(); return false;"/>
    </td>
</tr>
</table>
</form>
</body>
</html>
[/#escape]