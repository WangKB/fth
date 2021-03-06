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
	
			[@flash_message /]
			
			// 地区选择
			$areaId.lSelect({
				url: "${base}/admin/common/area.jhtml"
			});

            $filePicker.uploader({data:{imageType:'shop'}});

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
    <a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 编辑门店
</div>
<form id="inputForm" action="update.jhtml" method="post">
    <input type="hidden" name="shopId" value="${organization.id}" />
    <table class="input">
        <tr>
            <th>
                <span class="requiredField">*</span>门店名称:
            </th>
            <td>
                <input type="text" name="name" class="text" value="${organization.name}" maxlength="200" />
            </td>
        </tr>

		<tr>
            <th>
                门店状态:
            </th>
            <td>
            	<select name="status">
                [#list statuses as status]
                    <option value=${status} [#if organization.status==status]selected=true[/#if]>${message("Organization.Status."+status)}</option>
                [/#list]
                </select>
            </td>
        </tr>

		<tr>
			<th>
				设置:
			</th>
			<td>
				<label>
					<input type="checkbox" name="isOpen" value="1"  [#if  organization.isOpen==1]checked="checked"[/#if] />是否开启
					<input type="hidden" name="isOpen" value="0" />
				</label>
			</td>
		</tr>

        <tr class='none'>
            <th>
                <span class="requiredField">*</span>门店等级:
            </th>
            <td>
            	<select name="level">
                [#list levels as level]
                    <option value=${level} [#if organization.level == level]selected=true[/#if]>${message("Organization.level."+level)}</option>
                [/#list]
                </select>
            </td>
        </tr>

        <tr>
            <th>
                <span class="requiredField">*</span>联系电话:
            </th>
            <td>
                <input type="text" name="tel" class="text" value="${organization.tel}" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>联系邮箱:
            </th>
            <td>
                <input type="text" name="email" value="${organization.email}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                qq:
            </th>
            <td>
                <input type="text" name="qq" class="text" maxlength="200" value="${organization.qq}" />
            </td>
        </tr>
        <tr>
            <th>
                qqKey:
            </th>
            <td>
                <input type="text" name="qqKey" class="text" maxlength="200" value="${organization.qqKey}" />
            </td>
        </tr>
        <tr>
            <th>
                微信:
            </th>
            <td>
                <input type="text" name="wechat" class="text" maxlength="200" value="${organization.wechat}" />
            </td>
        </tr>
        <tr>
            <th>
                开门时间:
            </th>
            <td>
                <input type="text" name="openingStart" value="${organization.openingStart}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                关门时间:
            </th>
            <td>
                <input type="text" name="openingEnd" value="${organization.openingEnd}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                详细地址:
            </th>
            <td>
                <input type="text" name="address" value="${organization.address}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                门店介绍:
            </th>
            <td>
                <input type="text" name="intro" value="${organization.intro}" class="text" maxlength="400" />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>经度:
            </th>
            <td>
                <input type="text" name="longitude" value="${organization.longitude}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>纬度:
            </th>
            <td>
                <input type="text" name="latitude" value="${organization.latitude}" class="text" maxlength="200" />
            </td>
        </tr>
		<tr>
            <th>
                <span class="requiredField">*</span>每月结算日:
            </th>
            <td>
                <input type="text" name="paymentDate" value="${organization.paymentDate}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>分成比例:
            </th>
            <td>
                <input type="text" name="paymentPercent" value="${organization.paymentPercent}" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                组织图片:
            </th>
            <td>
                <input type="text" name="image" class="text"  value="${organization.image}" maxlength="200" title="${message("admin.goods.imageTitle")}" />
                <a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
            </td>
        </tr>
        <tr>
			<th>
				<span class="requiredField">*</span>地区:
			</th>
			<td>
				<span class="fieldSet">
					<input type="hidden" id="areaId" name="area" value="${(organization.area)!}" treePath="${(area.treePath)!}" />
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