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
    <script type="text/javascript" src="${base}/resources/admin/js/webuploader.js"></script>

    <script type="text/javascript">
        $().ready(function() {

            var $inputForm = $("#inputForm");
            var $filePicker = $("#filePicker");
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

            $filePicker.uploader();

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
                <span class="requiredField">*</span>门店名称:
            </th>
            <td>
                ${organization.name}
            </td>
        </tr>

        <tr>
            <th>
                <span class="requiredField">*</span>门店等级:
            </th>
            <td>
                [#list levels as level]
                    [#if organization.level == level]${message("Organization.level."+level)}[/#if]
                [/#list]
            </td>
        </tr>

        <tr>
            <th>
                <span class="requiredField">*</span>联系电话:
            </th>
            <td>
                ${organization.tel}
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>联系邮箱:
            </th>
            <td>
                ${organization.email}
            </td>
        </tr>
        <tr>
            <th>
                开门时间:
            </th>
            <td>
                ${organization.openingStart}
            </td>
        </tr>
        <tr>
            <th>
                关门时间:
            </th>
            <td>
                ${organization.openingEnd}
            </td>
        </tr>
        <tr>
            <th>
                详细地址:
            </th>
            <td>
                ${organization.address}
            </td>
        </tr>
        <tr>
            <th>
                门店介绍:
            </th>
            <td>
                ${organization.intro}
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>经度:
            </th>
            <td>
                ${organization.longitude}
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>纬度:
            </th>
            <td>
                ${organization.latitude}
            </td>
        </tr>
		<tr>
            <th>
                <span class="requiredField">*</span>每月结算日:
            </th>
            <td>
                ${organization.paymentDate}
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>分成比例:
            </th>
            <td>
                ${organization.paymentPercent}
            </td>
        </tr>
        <tr>
            <th>
                组织图片:
            </th>
            <td>
            	<img src='${organization.image}'>
            </td>
        </tr>
        <tr>
			<th>
				<span class="requiredField">*</span>地区:
			</th>
			<td>
				<span class="fieldSet">
					<input type="hidden" id="areaId" name="area" value="${(organization.area)!}" treePath=",1,2," />
				</span>
			</td>
		</tr>
		[#switch organization.applicationState]
				[#case 'ApplyingOne']
					<tr>
						<th>
							一级审核备注:
						</th>
						<td>
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
					</tr>
					<tr>
						<th>
							一级审核人:
						</th>
						<td>
							${auditAdmin1.name}
						</td>
					</tr>
					<tr>
						<th>
							一级审核时间:
						</th>
						<td>
							${organization.auditDate1}
						</td>
					</tr>
					<tr>
						<th>
							二级审核备注:
						</th>
						<td>
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
					</tr>
					<tr>
						<th>
							一级审核人:
						</th>
						<td>
							${auditAdmin1.name}
						</td>
					</tr>
					<tr>
						<th>
							一级审核时间:
						</th>
						<td>
							${organization.auditDate1}
						</td>
					</tr>
					<tr>
						<th>
							二级审核备注:
						</th>
						<td>
							${organization.auditMemo2}
						</td>
					</tr>
					<tr>
						<th>
							二级审核人:
						</th>
						<td>
							${auditAdmin2.name}
						</td>
					</tr>
					<tr>
						<th>
							二级审核时间:
						</th>
						<td>
							${organization.auditDate2}
						</td>
					</tr>
				[#break]
			[/#switch]
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