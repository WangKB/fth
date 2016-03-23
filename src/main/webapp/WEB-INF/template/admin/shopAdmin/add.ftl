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
	
	var $webUsername = $("#webUsername");
	var $posUsername = $("#posUsername");
	
	[@flash_message /]
	
	$filePicker.uploader();
	
	// 表单验证
	$inputForm.validate({
		groups: {  
        	username: "webUsername posUsername"   //username定义的组名，webUsername posUsername是输入框的名子  
      	},  
      	errorPlacement: function(error, element) {  //错误提示在什么地方  
         	if (element.attr("name") == "webUsername" || element.attr("name") == "posUsername" ){  
           		error.insertAfter("#lastname");    //如果是webUsername和posUsername呢，就#lastname后面  
        	}else{  
           		error.insertAfter(element);  
         	}  
       	},  
		
		rules: {
			webUsername: {
				required: {
				pattern: /^[0-9a-zA-Z_\u4e00-\u9fa5]+$/,
				minlength: 2,
				remote: {
					url: "check_webusername.jhtml",
					cache: false
				},
				depends:function(){ //二选一  
                        return ($('input[name=posUsername]').val().length <= 0);  
                    }
                    }
			},
			cardCode:{
				required: true,
				pattern: /^[1-9]([0-9]{14}|[0-9]{17})$/,
			},
			posUsername: {
				required:{ 
				pattern: /^[0-9a-zA-Z_\u4e00-\u9fa5]+$/,
				minlength: 2,
				remote: {
					url: "check_posusername.jhtml",
					cache: false
				},
				depends:function(){ //二选一  
                        return ($('input[name=webUsername]').val().length <= 0);  
                    }
                    }
			},
			name: "required",
			office: "required",
			jobNumber: "required",
			phone:{
				required: true,
				pattern: /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,
			}
		},
		messages: {
			webUsername: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.validate.exist")}"
			},
			posUsername: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.validate.exist")}"
			}
		},
		debug:true  
	});
	

});
</script>
</head>
<body>
	<div class="breadcrumb">
		<a href="${base}/admin/common/index.jhtml">${message("admin.breadcrumb.home")}</a> &raquo; 添加店员
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
	<input type="hidden" class="text" name="praiseCount" value="0" />
		<table class="input">
			<tr>
				<th>
					web用户名:
				</th>
				<td>
					<input type="text" name="webUsername" class="text" maxlength="200" />
				</td>
			</tr>
		
		
			<tr>
				<th>
					web密码:
				</th>
				<td>
					<input type="password" name="webPassword" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					pos账户名:
				</th>
				<td>
					<input type="text" name="posUsername" id="lastname" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					pos密码:
				</th>
				<td>
					<input type="password" name="posPassword" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>手机号:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>工号:
				</th>
				<td>
					<input type="text" name="jobNumber" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>身份证:
				</th>
				<td>
					<input type="text" name="cardCode" class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description"  class="text" maxlength="400" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>职务:
				</th>
				<td>
					<select id="office" name="office">
						<option value="">${message("admin.common.choose")}</option>
						[#list offices as office]
							<option value="${office}">${message("offices.Type." + office)}</option>
						[/#list]
					</select>
				</td>
			</tr>
			
			<tr>
				<th>
					照片:
				</th>
				<td>
					<input type="text" name="image" class="text" maxlength="200" title="${message("admin.goods.imageTitle")}" />
					<a href="javascript:;" id="filePicker" class="button">${message("admin.upload.filePicker")}</a>
				</td>
			</tr>
			
				
			<tr>
				<th>
					${message("admin.common.order")}:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" />
				</td>
			</tr>
			
			<tr>
				<th>
					选择门店:
				</th>
				<td>
					<select name="organization">
						[#list organizations as organization]
							<option value="${organization.id}">
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
								<input type="checkbox" name="roleIds" value="${role.id}" />${role.name}
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
					<input type="checkbox" name="isEnabled" value="true" checked="checked" />
					<input type="hidden" name="_isEnabled" value="false" />
				</td>
			</tr>
			
			<tr>
				<th>
					是否锁定:
				</th>
				<td>
					<input type="checkbox" name="isLocked" value="true" />
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