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
	
	[@flash_message /]
	
	$filePicker.uploader();
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			office: "required",
			jobNumber: "required",
			phone:{
				required: true,
				pattern: /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,
				
			},
			cardCode:{
				required: true,
				pattern: /^[1-9]([0-9]{14}|[0-9]{17})$/,
			}
		}
	});
	
	//初始化地址插件
	var $areaId = $("#area");
	$areaId.lSelect({
		url: "${base}/common/area.jhtml"
	});

	$("#isResetWeb").click(function(){
		if($("#isResetWeb").attr("checked")){
			$("#webPassword").attr("disabled",false);
		}else{
			$("#webPassword").attr("disabled",true);
		}
	});
	
	$("#isResetPos").click(function(){
		if($("#isResetPos").attr("checked")){
			$("#posPassword").attr("disabled",false);
		}else{
			$("#posPassword").attr("disabled",true);
		}
	});
	
});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 编辑店员
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
	<input type="hidden" name="id" value="${admin.id}" />
	<input type="hidden" class="text" name="praiseCount" value="${admin.praiseCount}" />
	<table class="input">
			<tr>
				<th>
					web用户名:
				</th>
				<td>
					${admin.webUsername}
				</td>
			</tr>
		
		
			<tr>
				<th>
					web密码:
				</th>
				<td>
					<input type="text" name="webPassword" id="webPassword" class="text" maxlength="200" disabled="true"/>
					<input type="checkbox" id="isResetWeb" name="isResetWeb" value="true">是否重置
					<input type="hidden" name="_isResetWeb" value="false">
				</td>
			</tr>
			<tr>
				<th>
					pos账户名:
				</th>
				<td>
					${admin.posUsername}
				</td>
			</tr>
			<tr>
				<th>
					pos密码:
				</th>
				<td>
					<input type="text" name="posPassword" id="posPassword" class="text" maxlength="400"  disabled="true"/>
					<input type="checkbox" id="isResetPos" name="isResetPos" value="true">是否重置
					<input type="hidden" name="_isResetPos" value="false">
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="name" value="${admin.name}" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>手机号:
				</th>
				<td>
					<input type="text" name="phone" value="${admin.phone}" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>工号:
				</th>
				<td>
					<input type="text" name="jobNumber" value="${admin.jobNumber}" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>身份证:
				</th>
				<td>
					<input type="text" name="cardCode" value="${admin.cardCode}" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" value="${admin.description}" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>职务:
				</th>
				<td>
					<select id="office" name="office">
						[#list offices as office]
							<option value="${office}" [#if admin.office == office]selected="true"[/#if]>${message("offices.Type." + office)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			
			<tr>
				<th>
					照片:
				</th>
				<td>
					<input type="text" name="image" class="text" maxlength="200" title="${message("admin.goods.imageTitle")}" value="${admin.image}"/>
					<a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
				</td>
			</tr>
			
				
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" value="${admin.order}"/>
				</td>
			</tr>
			
			
				
			<tr>
				<th>
					点赞数:
				</th>
				<td>
					${admin.praiseCount}
				</td>
			</tr>
			
			<tr>
				<th>
					选择门店:
				</th>
				<td>
					<select name="organization">
						<option value="">${message("admin.common.choose")}</option>
						[#list organizations as organization]
							<option value="${organization.id}"[#if organization.id == admin.organization] selected="selected"[/#if]>
								${organization.name}
							</option>
						[/#list]
					</select>
				</td>
			</tr>
			<tr class="roles">
				<th>
					<span class="requiredField">*</span>${message("Admin.roles")}:
				</th>
				<td>
					<span class="fieldSet">
						[#list roles as role]
							<label>
								<input type="checkbox" name="roleIds" value="${role.id}"[#if admin.roles?seq_contains(role)] checked="checked"[/#if] />${role.name}
							</label>
						[/#list]
					</span>
				</td>
			</tr>
			<tr>
				<th>
					是否启用:
				</th>
				<td>
					<input type="checkbox" name="isEnabled" value="true"[#if admin.isEnabled] checked="checked"[/#if] />
					<input type="hidden" name="_isEnabled" value="false" />
				</td>
			</tr>
			
			<tr>
				<th>
					是否锁定:
				</th>
				<td>
					<input type="checkbox" name="isLocked" value="true"[#if admin.isLocked] checked="checked"[/#if] />
					<input type="hidden" name="_isLocked" value="false" />
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