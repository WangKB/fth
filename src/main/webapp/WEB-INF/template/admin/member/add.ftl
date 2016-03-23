[#escape x as x?html]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${message("admin.member.add")}</title>
<meta name="author"/>
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
	 
	$filePicker.uploader();
	
	[@flash_message /]
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/admin/common/area.jhtml"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			username: {
				required: true,
				pattern: /^[0-9a-zA-Z_\u4e00-\u9fa5]+$/,
				remote: {
					url: "check_username.jhtml",
					cache: false
				}
			},
			password: {
				required: true,
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			phone: {
				required: true,
				pattern: /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			username: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.member.disabledExist")}"
			},
			phone: {
				pattern: "${message("admin.validate.phone.illegal")}",
				remote: "${message("admin.validate.exist")}"
			}
		}
	});

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; ${message("admin.member.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
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
					<input type="text" name="username" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.password")}:
				</th>
				<td>
					<input type="password" id="password" name="password" class="text" autocomplete="off" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("admin.member.rePassword")}:
				</th>
				<td>
					<input type="password" name="rePassword" class="text" autocomplete="off" />
				</td>
			</tr>
			<tr>
				<th>
					姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					OpenId:
				</th>
				<td>
					<input type="text" name="openId" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Member.email")}:
				</th>
				<td>
					<input type="text" name="email" class="text" maxlength="200" />
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>手机号:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="11" />
				</td>
			</tr>
			
			<tr>
				<th>
					${message("Member.nickname")}:
				</th>
				<td>
					<input type="text" name="nickname" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					性别:
				</th>
				<td>
					<input type="radio" name="gender" value="male"/>男
					<input type="radio" name="gender" value="female"/>女
				</td>
			</tr>
			<tr>
	            <th>
	                头像:
	            </th>
	            <td>
	                <input type="text" name="photoPath" class="text" maxlength="200" title="${message("admin.goods.imageTitle")}" />
	                <a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
	            </td>
	        </tr>
			<tr>
				<th>
					积分:
				</th>
				<td>
					<input type="text" name="point" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
            <th>
                <span class="requiredField">*</span>经度:
            </th>
            <td>
                <input type="text" name="pointX" class="text" maxlength="200" />
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>纬度:
            </th>
            <td>
                <input type="text" name="pointY" class="text" maxlength="200" />
            </td>
        </tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="1" checked="checked" />${message("Member.isEnabled")}
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