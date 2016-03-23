function receiver_check(){
	$.extend(validatePrompt,{
		consignee:{
			onFocus:"请输入收货人，其由中文组成",
			success:"",
			isNull:"必填",
			error:{
				badLength:"收货人长度只能在2-6位字符之间",
				badFormat:"收货人只能由中文组成",
			}
		},
		phone:{
			onFocus:"请输入常用手机",
			success:"",
			isNull:"请输入手机号，不能为空",
			error:{
				badFormat:"手机格式不正确",
			}
		},
		address:{
			onFocus:"请输入你的详细地址",
			succeed:"",
			isNull:"详细地址不能为空",
			error:{
				fullNumberName:"地址不能只为数字或英文"
			}	
		}
	});

$.extend(validateFunction,{
	address:function(option){
		if (validateRules.fullNumberName(option.value)) {
				validateSettings.error.run(option, option.prompts.error.fullNumberName);
			} else{
				validateSettings.succeed.run(option);
			}
		},
		consignee:function(option){
			var format = validateRules.isChinese(option.value);
			var length = validateRules.betweenLength(option.value, 2, 6);
			if (!format) {
				validateSettings.error.run(option, option.prompts.error.badFormat);
			} else if (!length) {
				validateSettings.error.run(option, option.prompts.error.badLength);
			}else{
				validateSettings.succeed.run(option);
			}
		},
		phone:function(option){
			if(!validateRules.isMobile(option.value)) {
					validateSettings.error.run(option, option.prompts.error.badFormat);
				} else{
					validateSettings.succeed.run(option);
				}
			},
	 FORM_receiver_check:function(){
		 $("#edit-username").jdValidate(validatePrompt.consignee,validateFunction.consignee,true);
		 $("#edit-address").jdValidate(validatePrompt.address,validateFunction.address,true);
		 $("#edit-call").jdValidate(validatePrompt.phone,validateFunction.phone,true);
            return validateFunction.FORM_submit(["#edit-username","#edit-address" , "#edit-call"]);
        }	
		
			
});
$("#edit-username").jdValidate(validatePrompt.consignee,validateFunction.consignee);
$("#edit-address").jdValidate(validatePrompt.address,validateFunction.address);
$("#edit-call").jdValidate(validatePrompt.phone,validateFunction.phone);
	
}