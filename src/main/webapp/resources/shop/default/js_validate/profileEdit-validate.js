function profileEdit_check(){
	$.extend(validatePrompt,{
    	detailAddress:{
    		onFocus:"请输入你的详细地址",
			succeed:"",
			isNull:"详细地址不能为空",
			error:{
				fullNumberName:"地址不能只为数字或英文"
			}	
		},
    	nickName:{
			onFocus:"4-16位字符，由英文,数字或者汉字组成",
			succeed:"",
			isNull:"完善昵称",
			error:{
				beUsed:"该昵称已被使用",
				badLength:"昵称长度在4-16位字符之间",
				fullNumber:"昵称不能只为数字"
			}
		}, 
		name:{
            onFocus:"使用中文",
            succeed:"", 
            isNull:"必填",
            error:{
				badFormat:"姓名只能由汉字组成"
			}
          },
          lastPwd:{
        	  onFocus:"请输入原始密码",
        	  succeed:"",
        	  isNull:"必填",
        	  error:{
        		  beUsed:"原密码不正确"
        	  }
			 },
        newPwd:{
			onFocus:"请输入新密码",
			succeed:"",
			isNull:"必填",
			error:{
				badLength:"密码在6-16位之间",
				badFormat:"密码只由英文,数字组成"
			}
		},
		enNewPwd:{
			onFocus:"请再次输入密码",
			succeed:"",
			isNull:"必填",
			error:{
				badLength:"密码在6-16位之间",
				badFormat2:"两次输入密码不一致",
				badFormat1:"密码只由英文,数字组成"
			}
		},
		newEmail:{
			onFocus:"请输入常用的邮箱",
			succeed:"",
			isNull:"请输入新邮箱",
			error:{
				beUsed:"该邮箱已被使用",
				badFormat:"邮箱格式不正确",
				badLength:"邮箱长度不正确",
			}
		},
		emailCode:{
			onFocus:"必填",
			succeed:"",
			isNull:"不可为空",
			error:{
				badLength:"长度只为4",
			}
		}, 
		newPhone:{
			onFocus:"请输入常用的手机",
			succeed:"",
			isNull:"请输入手机",
			error:{
				beUsed:"该手机已被使用",
				badFormat:"手机格式不正确"
			}
		},	
 });
$.extend(validateFunction,{
	//member表中详细收货地址验证	
	detailAddress:function(option){
	if (validateRules.fullNumberName(option.value)) {
			validateSettings.error.run(option, option.prompts.error.fullNumberName);
		} else{
			validateSettings.succeed.run(option);
		}
	},
	//昵称验证	
	 nickName:function (option) {
		 		var format = validateRules.isUid(option.value);
				var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"), 4, 16);
				if (!length) {
					validateSettings.error.run(option, option.prompts.error.badLength);
				}else if (validateRules.fullNumber(option.value)) {
					validateSettings.error.run(option, option.prompts.error.fullNumber);
				} else {
					//昵称唯一性验证
						if (option.value != null) {
							option.errorEle.html("<span style='color:#999'>检验中……</span>");
								$.ajax({
									cache:true,
									type:"GET",
									url:"../profile/check_nickName.jhtml",
									data:{
										nickname:option.value
									},
									async:false,
									success:function(data){
										if (data) {
											validateSettings.succeed.run(option);
										} else {
											validateSettings.error.run(option, option.prompts.error.beUsed);
										}
									}
								});
						}
					}	
			},
			//真实姓名验证	
        name:function(option){
            var bool = validateRules.isChinese(option.value);
            if(!bool){
                validateSettings.error.run(option,"用户名只能为中文");
                return false;
            }else{
                validateSettings.succeed.run(option);
            }
        },
        //以前的密码验证        
        lastPwd:function(option){
        	var str = option.value;
        	if (option.value != null) {
				option.errorEle.html("<span style='color:#999'>检验中……</span>");
					$.ajax({
						cache:true,
						type:"GET",
						url:"../profile/check_pwd.jhtml",
						data:{
							password:option.value
						},
						async:false,
						success:function(data){
							if(data){
								validateSettings.succeed.run(option);
							}else{
								validateSettings.error.run(option, option.prompts.error.beUsed);
							}
						}
					});
			}		
         },
        //新密码验证
		newPwd:function (option) {
			var str1 = option.value;
			var str2 = $("#enNewPwd").val();
			var format = validateRules.isPwd(option.value);
			var length = validateRules.betweenLength(option.value, 6, 16);
			if (!length && format) {
				validateSettings.error.run(option, option.prompts.error.badLength);
			}
			else if (!length && !format) {
				validateSettings.error.run(option, option.prompts.error.badFormat);
			}
			else if (length && !format) {
				validateSettings.error.run(option, option.prompts.error.badFormat);
			}
			else {
				validateSettings.succeed.run(option);
			}
			if (str2 == str1) {
				$("#enNewPwd").focus();
			}
		},
		//新密码重新输入验证
		enNewPwd:function (option) {
				var str1 = option.value;
				var str2 = $("#newPwd").val();
				var length = validateRules.betweenLength(option.value, 6, 16);
				var format2 = validateRules.isPwd2(str1, str2);
				var format1 = validateRules.isPwd(str1);
				if (!length) {
					validateSettings.error.run(option, option.prompts.error.badLength);
				} else {
					if (!format1) {
						validateSettings.error.run(option, option.prompts.error.badFormat1);
					} else {
						if (!format2) {
							validateSettings.error.run(option, option.prompts.error.badFormat2);
						}
						else {
							validateSettings.succeed.run(option);
						}
					}
				}
			},
			//新邮箱验证
		newEmail:function (option) {
			var format = validateRules.isEmail(option.value);
			var format2 = validateRules.betweenLength(option.value, 6, 30);
			if (!format) {
				validateSettings.error.run(option, option.prompts.error.badFormat);
			} else {
				if (!format2) {
					validateSettings.error.run(option, option.prompts.error.badLength);
				}
				else {
					//邮箱唯一性验证
				if (option.value != null) {
						option.errorEle.html("<span style='color:#999'>检验中……</span>");
							$.ajax({
								cache:true,
								type:"GET",
								url:"../profile/check_email.jhtml",
								data:{
									email:option.value
								},
								async:false,
								success:function(data){
									if (data) {
										validateSettings.succeed.run(option);
									} else {
										validateSettings.error.run(option, option.prompts.error.beUsed);
									}
								}
							});
					}		
				}
			}
		},
		//邮箱的验证码验证
		emailCode:function (option) {
			var format = validateRules.equalLength(option.value, 4);
			if (!format) {
				validateSettings.error.run(option, option.prompts.error.badLength);
			} else{
				validateSettings.succeed.run(option);
			}
		},
		//新手机验证
		newPhone:function (option) {
			var format = validateRules.isMobile(option.value);
			if (!format){
				validateSettings.error.run(option, option.prompts.error.badFormat);
			}else {
				if (option.value != null) {
					//手机唯一性验证
					option.errorEle.html("<span style='color:#999'>检验中……</span>");
						$.ajax({
							cache:true,
							type:"GET",
							url:"../profile/check_phone.jhtml",
							data:{
							phone:option.value
							},
							async:false,
							success:function(data){
								if (data) {
									validateSettings.succeed.run(option);
								} else {
									validateSettings.error.run(option, option.prompts.error.beUsed);
								}
							}
						});
					}		
				}
		},
		
		FORM_profileEdit_checkEmail:function(){
			 $("#newEmail").jdValidate(validatePrompt.newEmail,validateFunction.newEmail,true); 
			 return validateFunction.FORM_submit(["#newEmail"]); 
		},
		FORM_profileEdit_checkChangeEmail:function(){
			 $("#newEmail").jdValidate(validatePrompt.newEmail,validateFunction.newEmail,true); 
			 $("#emailCode").jdValidate(validatePrompt.emailCode,validateFunction.emailCode,true);
			 return validateFunction.FORM_submit(["#newEmail","#emailCode"]); 
		},
		FORM_profileEdit_checkChangePhone:function(){
			 $("#newPhone").jdValidate(validatePrompt.newPhone,validateFunction.newPhone,true); 
			 $("#mobileCode").jdValidate(validatePrompt.emailCode,validateFunction.emailCode,true);
			  return validateFunction.FORM_submit(["#newPhone","#mobileCode"]); 
		},
		FORM_profileEdit_checkChangeAddress:function(){
			 $("#detailAddress").jdValidate(validatePrompt.detailAddress,validateFunction.detailAddress,true);
			  return validateFunction.FORM_submit(["#detailAddress"]); 
		},
		FORM_profileEdit_checkPhone:function(){
			$("#newPhone").jdValidate(validatePrompt.newPhone,validateFunction.newPhone,true);
			  return validateFunction.FORM_submit(["#newPhone"]); 
		},
		FORM_profileEdit_checkSubmit:function(){
			$("#nickname").jdValidate(validatePrompt.nickName,validateFunction.nickName,true);
			$("#truename").jdValidate(validatePrompt.name,validateFunction.name,true); 			 
			return validateFunction.FORM_submit(["#nickname","#truename"]);
        },
        FORM_profileEdit_checkPwd:function(){
            $("#lastPwd").jdValidate(validatePrompt.lastPwd,validateFunction.lastPwd,true);
            $("#newPwd").jdValidate(validatePrompt.newPwd,validateFunction.newPwd,true);
            $("#enNewPwd").jdValidate(validatePrompt.enNewPwd,validateFunction.enNewPwd,true);
            return validateFunction.FORM_submit(["#lastPwd","#newPwd" , "#enNewPwd"]);
        }
        
 });
	$("#mobileCode").jdValidate(validatePrompt.emailCode,validateFunction.emailCode);
    $("#nickname").jdValidate(validatePrompt.nickName,validateFunction.nickName);
	$("#truename").jdValidate(validatePrompt.name,validateFunction.name);
	$("#detailAddress").jdValidate(validatePrompt.detailAddress,validateFunction.detailAddress);
	$("#lastPwd").jdValidate(validatePrompt.lastPwd,validateFunction.lastPwd);
	$("#newPwd").jdValidate(validatePrompt.newPwd,validateFunction.newPwd);
	$("#enNewPwd").jdValidate(validatePrompt.enNewPwd,validateFunction.enNewPwd);
	$("#newEmail").jdValidate(validatePrompt.newEmail,validateFunction.newEmail);
	$("#emailCode").jdValidate(validatePrompt.emailCode,validateFunction.emailCode);
	$("#newPhone").jdValidate(validatePrompt.newPhone,validateFunction.newPhone);
}