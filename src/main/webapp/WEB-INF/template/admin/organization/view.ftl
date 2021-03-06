[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>${message("admin.attribute.add")} </title>
    <meta name="author"/>
    <meta name="copyright"/>
    <link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>

    <script type="text/javascript">
        $().ready(function() {

            var $inputForm = $("#inputForm");
            var $areaId = $("#areaId");
			var $reject=$("#reject");

			[@flash_message /]
			
			$reject.on('click',function(){
				$.ajax({
					url: "reject.jhtml",
					type: "POST",
					data: {id:${organization.id},auditMemo:$('#auditMemo').val()},
					dataType: "json",
					success: function(data) {
						$.message('success','操作成功');
						setTimeout(function(){
							location.href='examine.jhtml';
						},'1000');
					}
				});
			});
			
			// 地区选择
			$areaId.lSelect({
				url: "${base}/admin/common/area.jhtml"
			});


            // 表单验证
            $inputForm.validate({
                rules: {
                    name: "required",
                    tel: "required",
                    longitude: "required",
                    latitude: "required",
                    address: "required"
                }
            });

        });
    </script>
</head>
<body>
<div class="breadcrumb">
    <a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 门店审核
</div>
<form id="inputForm" action="check.jhtml" method="post">
    <input type="hidden" name="id" value="${organization.id}" />
    <table class="input">
    	<tr>
            <th>
            </th>
            <td style="width:150px">
                申请内容
            </td>
            <td>
                当前内容
            </td>
        </tr>
        
        <tr>
            <th>
                申请类型:
            </th>
            <td>
                [#if organization.type=='ADD']
                新增
                [#else]
                修改
                [/#if]
            </td>
            <td>
            </td>
        </tr>
    
        <tr>
            <th>
                <span class="requiredField">*</span>门店名称:
            </th>
            <td>
                ${organization.name}
            </td>
            <td>
                ${organizationBefore.name}
            </td>
        </tr>
        
        <tr>
            <th>
                <span class="requiredField">*</span>联系电话:
            </th>
            <td>
                ${organization.tel}
            </td>
            <td>
                ${organizationBefore.tel}
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>联系邮箱:
            </th>
            <td>
                ${organization.email}
            </td>
            <td>
                ${organizationBefore.email}
            </td>
        </tr>
        
        <tr>
            <th>
                开门时间:
            </th>
            <td>
                ${organization.openingStart}
            </td>
            <td>
                ${organizationBefore.openingStart}
            </td>
        </tr>
        <tr>
            <th>
                关门时间:
            </th>
            <td>
                ${organization.openingEnd}
            </td>
            <td>
                ${organizationBefore.openingEnd}
            </td>
        </tr>
        <tr>
            <th>
                详细地址:
            </th>
            <td>
                ${organization.address}
            </td>
            <td>
                ${organizationBefore.address}
            </td>
        </tr>
        <tr>
            <th>
                门店介绍:
            </th>
            <td>
                ${organization.intro}
            </td>
             <td>
                ${organizationBefore.intro}
            </td>
        </tr>
        <tr>
            <th>
                qq:
            </th>
            <td>
                ${organization.qq}
            </td>
             <td>
                ${organizationBefore.qq}
            </td>
        </tr>
        <tr>
            <th>
                qqKey:
            </th>
            <td>
                ${organization.qqKey}
            </td>
             <td>
                ${organizationBefore.qqKey}
            </td>
        </tr>
        <tr>
            <th>
                微信:
            </th>
            <td>
                ${organization.wechat}
            </td>
             <td>
                ${organizationBefore.wechat}
            </td>
        </tr>
		<tr>
            <th>
                <span class="requiredField">*</span>每月结算日:
            </th>
            <td>
                ${organization.paymentDate}
            </td>
            <td>
                ${organizationBefore.paymentDate}
            </td>
        </tr>
        <tr>
            <th>
                组织图片:
            </th>
            <td>
            	<img src='${organization.image}' class="width125">
            </td>
            <td>
            	<img src='${organizationBefore.image}' class="width125">
            </td>
        </tr>
		[#switch organization.applicationState]
				[#case 'ApplyingOne']
					<tr>
						<th>
							一级审核备注:
						</th>
						<td colspan=2>
							<input type="text" name='auditMemo'  id='auditMemo' class="text" />
							<input type="submit" class="button" value="通过" />
							<input id='reject' type="button" class="button" value="拒绝" />
							<input type="hidden" name="actType" value="1" />
						</td>
					</tr>
				[#break]
				[#case 'ApplyingTwo']
					<tr>
						<th>
							一级审核备注:
						</th>
						<td>
							${organization.auditMemo1}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							一级审核人:
						</th>
						<td>
							${auditAdmin1.name}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							一级审核时间:
						</th>
						<td>
							${organization.auditDate1}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							二级审核备注:
						</th>
						<td colspan=2>
							<input type="text" name='auditMemo'  id='auditMemo' class="text" />
							<input type="submit" class="button" value="通过" />
							<input id='reject' type="button" class="button" value="拒绝" />
							<input type="hidden" name="actType" value="2" />
						</td>
					</tr>
				[#break]
				[#default]
					<tr>
						<th>
							一级审核备注:
						</th>
						<td>
							${organization.auditMemo1}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							一级审核人:
						</th>
						<td>
							${auditAdmin1.name}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							一级审核时间:
						</th>
						<td>
							${organization.auditDate1}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							二级审核备注:
						</th>
						<td>
							${organization.auditMemo2}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							二级审核人:
						</th>
						<td>
							${auditAdmin2.name}
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							二级审核时间:
						</th>
						<td>
							${organization.auditDate2}
						</td>
						<td>
						</td>
					</tr>
				[#break]
			[/#switch]
        <tr>
            <th>
                &nbsp;
            </th>
            <td colspan=2>
                <input type="button" class="button" value="${message("admin.common.back")}" onclick="history.back(); return false;" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
[/#escape]