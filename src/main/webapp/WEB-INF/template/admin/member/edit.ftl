[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.member.edit")} </title>
<meta name="author" />
<meta name="copyright" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/temp_common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/webuploader.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $areaId = $("#areaId");
	var $filePicker = $("#filePicker");
	 
	$filePicker.uploader({data:{imageType:'member'}});
	
	[@flash_message /]
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/admin/common/area.jhtml"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			phone: {
				required: true,
				pattern: /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,
			},
			email: {
				required: true,
				email: true
			}
		},
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.member.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${member.id}" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.member.base")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.username")}:
				</th>
				<td>
					${member.username}
				</td>
			</tr>
			<tr>
				<th>
					姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" value='${member.name}'/>
				</td>
			</tr>
			<tr>
				<th>
					OpenId:
				</th>
				<td>
					<input type="text" name="openId" class="text" maxlength="200"  value='${member.openId}'/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.email")}:
				</th>
				<td>
					<input type="text" name="email" class="text" maxlength="200" value='${member.email}'/>
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>手机号:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="11"  value='${member.phone}'/>
				</td>
			</tr>
			
			<tr>
				<th>
					电话:
				</th>
				<td>
					<input type="text" name="mobile" class="text" value='${member.mobile}'/>
				</td>
			</tr>
			
			<tr>
				<th>
					${message("Member.nickname")}:
				</th>
				<td>
					<input type="text" name="nickname" class="text" maxlength="200"   value='${member.nickname}'/>
				</td>
			</tr>
			<tr>
				<th>
					性别:
				</th>
				<td>
					<input type="radio" name="gender" value="male" [#if member.gender=='male']checked="checked"[/#if]/>男
					<input type="radio" name="gender" value="female" [#if member.gender=='female']checked="checked"[/#if]/>女
				</td>
			</tr>
			<tr>
	            <th>
	                头像:
	            </th>
	            <td>
	                <input type="text" name="photoPath" class="text" maxlength="200" title="${message("admin.goods.imageTitle")}" value='${member.photoPath}'/>
	                <a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
	            </td>
	        </tr>
			<tr>
				<th>
					积分:
				</th>
				<td>
					<input type="text" name="point" class="text" maxlength="200"  value='${member.point}'/>
				</td>
			</tr>
			<tr>
            <th>
                <span class="requiredField">*</span>经度:
            </th>
            <td>
                <input type="text" name="pointX" class="text" maxlength="200"  value='${member.pointX}' />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>纬度:
            </th>
            <td>
                <input type="text" name="pointY" class="text" maxlength="200" value='${member.pointY}' />
            </td>
        </tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="1" [#if member.isEnabled==1]checked="checked"[/#if] />${message("Member.isEnabled")}
						<input type="hidden" name="isEnabled" value="0" />
					</label>
				</td>
			</tr>
		</table>
		<table class="input">
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