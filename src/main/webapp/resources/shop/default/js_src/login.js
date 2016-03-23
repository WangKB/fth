$(function(){
	opt.init();
});

var opt ={
		init:function(){
			this.initfocus();
			this.onfoucs();
			this.validate();
			this.initUsername();
		},
		initUsername:function(){
			var $isRememberUsername = $("#isRememberUsername");
			if (getCookie("memberUsername") != null) {
				$isRememberUsername.prop("checked", true);
				$("#username").val(getCookie("memberUsername"));
				$("#password").focus();
			} else {
				$isRememberUsername.prop("checked", false);
				$("#username").focus();
			}
		},
		initfocus:function(){
			//登录默认获取焦点
			$("#username").focus();
		},
		onfoucs:function(){
			//获取焦点
			$("#username").on("focus",function(){
				$(".focus").removeClass("focus");
				$(this).closest("div").addClass("focus");
				$("#errormsg").hide();
			});
			$("#password").on("focus",function(){
				$(".focus").removeClass("focus");
				$(this).closest("div").addClass("focus");
				$("#errormsg").hide();
			});
		},
		validate:function(){
			var $submit = $("input:submit");
			var $loginForm = $("#loginForm");
			var $username = $("#username");
			var $password = $("#password");
			var keyurl = $loginForm.attr("keyurl");
			$loginForm.validate({
				rules: {
					username: "required",
					password: "required"
				},
				submitHandler: function(form) {
					$.ajax({
						url: keyurl,
						type: "GET",
						dataType: "json",
						cache: false,
						beforeSend: function() {
							$submit.prop("disabled", true);
						},
						success: function(data) {
							var rsaKey = new RSAKey();
							rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
							var enPassword = hex2b64(rsaKey.encrypt($password.val()));
							$.ajax({
								url: $loginForm.attr("action"),
								type: "POST",
								data: {
									username: $username.val(),
									password: enPassword
								},
								dataType: "json",
								cache: false,
								success: function(message) {
									$submit.prop("disabled", false);
									
									var $isRememberUsername = $("#isRememberUsername");
									if ($isRememberUsername.prop("checked")) {
										addCookie("memberUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
									} else {
										removeCookie("memberUsername");
									}
									
									if (message.type == "success") {
										var url = $loginForm.attr("okurl");
                                        window.location.href = url ;
										
									} else {
										$("#errormsg").empty().append(message.content);
										$("#errormsg").show();
									}
								}
							});
						}
					});
				}
			});
		}
};