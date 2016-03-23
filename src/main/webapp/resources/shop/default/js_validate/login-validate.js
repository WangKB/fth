function regist_check() {
    $.extend(validatePrompt, {

        regName: {
            onFocus: "4-20位字符，由中文、英文、数字组成",
            succeed: "",
            isNull: "必填",
            error: {
                beUsed: "该用户名已被使用，请使用其它用户名注册",
                badLength: "用户名长度只能在4-20位字符之间",
                badFormat: "用户名只能由中文、英文、数字组成",
                fullNumber: "用户名不能全为数字"
            }
        },
        pwd: {
            onFocus: "6-16位字符，可由英文、数字及标点符号组成",
            succeed: "",
            isNull: "请输入密码,6到16位英文,数字及标点符号组成",
            error: {
                badLength: "密码长度只能在6-16位字符之间",
                badFormat: "密码只能由英文、数字组成"
            }
        },
        pwdRepeat: {
            onFocus: "请再次输入密码进行确认密码",
            succeed: "",
            isNull: "不可以为空，必须确认是否与原密码相同",
            error: {
                badLength: "密码长度只能在6-16位字符之间",
                badFormat2: "两次输入密码不一致",
                badFormat1: "密码只能由英文、数字组成"
            }
        },
        email: {
            onFocus: "请输入常用的邮箱，将用来找回密码",
            succeed: "",
            error: {
                beUsed: "该邮箱已被使用，请更换其它邮箱",
                badFormat: "邮箱格式不正确",
                badLength: "您填写的邮箱过长，邮件地址只能在50个字符以内"
            }
        },
        phone: {
            onFocus: "请输入常用的手机，只能是手机号",
            succeed: "",
            isNull: "请输入手机，不可为空",
            error: {
                beUsed: "该手机已被使用，请更换其它手机",
                badFormat: "手机格式不正确",

            }
        },
        emailCode: {
            onFocus: "请填入短信验证码",
            succeed: "",
            isNull: "短信验证码不可为空",
            error: {
                badLength: "长度只能等于4",
            }
        },
        //图形验证吗
        captchaCode: {
            onFocus: "请填入图形验证码",
            succeed: "",
            isNull: "图形验证码不可为空",
            error: {
                badLength: "长度只能等于4"
            }
        }

    });


    $.extend(validateFunction, {
        regName: function (option) {
            var format = validateRules.isUid(option.value);
            var length = validateRules.betweenLength(option.value.replace(/[^\x00-\xff]/g, "**"), 4, 20);
            if (!length && format) {
                validateSettings.error.run(option, option.prompts.error.badLength);
            }
            else if (!length && !format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            }
            else if (length && !format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            } else if (validateRules.fullNumber(option.value)) {
                validateSettings.error.run(option, option.prompts.error.fullNumber);
            } else {
                if (option.value != null) {
                    option.errorEle.html("<span style='color:#999'>检验中……</span>");
                    $.ajax({
                        cache: true,
                        type: "GET",
                        url: "../register/check_username.jhtml",
                        data: {
                            username: option.value
                        },
                        async: false,
                        success: function (data) {
                            if (data) {
                                validateSettings.succeed.run(option);
                            } else {
                                validateSettings.error.run(option, option.prompts.error.beUsed);
                            }
                        }
                    });
                }
                else {
                    validateSettings.succeed.run(option);
                }
            }
        },


        pwd: function (option) {
            var str1 = option.value;
            var str2 = $("#pwdRepeat").val();
            var format = validateRules.isPwd(option.value);
            var length = validateRules.betweenLength(option.value, 6, 16);
            $("#pwdstrength").hide();
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
                $("#pwdRepeat").focus();
            }
        },

        pwdRepeat: function (option) {
            var str1 = option.value;
            var str2 = $("#pwd").val();
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

        email: function (option) {
            var format = validateRules.isEmail(option.value);
            var format2 = validateRules.betweenLength(option.value, 0, 50);
            if (!format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            } else {
                if (!format2) {
                    validateSettings.error.run(option, option.prompts.error.badLength);
                }
                else {
                    if (option.value != null) {
                        option.errorEle.html("<span style='color:#999'>检验中……</span>");
                        $.ajax({
                            cache: true,
                            type: "GET",
                            url: "../register/check_email.jhtml",
                            data: {
                                email: option.value
                            },
                            async: false,
                            success: function (data) {
                                if (data) {
                                    validateSettings.succeed.run(option);
                                } else {
                                    validateSettings.error.run(option, option.prompts.error.beUsed);
                                }
                            }
                        });
                    }
                    else {
                        validateSettings.succeed.run(option);
                    }
                }
            }
        },
        phone: function (option) {
            var format = validateRules.isMobile(option.value);
            if (!format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            } else {
                if (option.value != null) {
                    option.errorEle.html("<span style='color:#999'>检验中……</span>");
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: "../register/check_phone.jhtml",
                        data: {
                            phone: option.value
                        },
                        async: false,
                        success: function (data) {
                            if (data == "success") {
                                validateSettings.succeed.run(option);
                                $("#simple").val(0);
                            } else if (data == "simple") {
                                $("#simple").val(1);
                                alert($("#simple").val());
                                $("#registHead").val("欢迎激活");
                                validateSettings.succeed.run(option);
                            } else {
                                validateSettings.error.run(option, option.prompts.error.beUsed);
                            }
                        }
                    });
                }
                else {
                    validateSettings.succeed.run(option);
                }

            }
        },
        //图形验证吗校验
        captchaCode: function (option) {
            var value = option.value;
            if (value.length < 4) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            }

            if( $("#captchaCode").attr("data-token") == "true" ){
                validateSettings.succeed.run(option);
            }else{
                $.ajax({
                    url: "../common/checkCaptcha.jhtml",
                    type: "post",
                    data: {
                        "captcha": value
                    },
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            //验证成功
                            validateSettings.succeed.run(option);
                            $("#captchaCode").attr("data-token" , "true");
                        } else {
                            //验证失败
                            validateSettings.error.run(option, "验证码输入错误");
                            $("#captchaImage").trigger("click");
                        }
                    }
                })
            }

        },
        //短信的验证码验证
        emailCode: function (option) {
            var format = validateRules.equalLength(option.value, 4);
            if (!format) {
                validateSettings.error.run(option, option.prompts.error.badLength);
            } else {
                validateSettings.succeed.run(option);
            }
        },
        FORM_regist_check: function () {
            /* 验证字段是否必填 */
            $("#username").jdValidate(validatePrompt.regName, validateFunction.regName, true);
            $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true);
            $("#pwdRepeat").jdValidate(validatePrompt.pwdRepeat, validateFunction.pwdRepeat, true);
            $("#phone").jdValidate(validatePrompt.phone, validateFunction.phone, true);
            $("#phoneCode").jdValidate(validatePrompt.emailCode, validateFunction.emailCode, true);
            return validateFunction.FORM_submit(["#username", "#pwd", "#pwdRepeat", "#phone", "#phoneCode"]);
        }
    });

    $("#username").jdValidate(validatePrompt.regName, validateFunction.regName);
    $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd);
    $("#pwdRepeat").jdValidate(validatePrompt.pwdRepeat, validateFunction.pwdRepeat);
    $("#phone").jdValidate(validatePrompt.phone, validateFunction.phone);
    $("#phoneCode").jdValidate(validatePrompt.emailCode, validateFunction.emailCode);
}


function sendCheckPhone() {
    $.extend(validatePrompt, {
        phone: {
            onFocus: "请输入常用的手机，只能是手机号",
            succeed: "",
            isNull: "请输入手机，不可为空",
            error: {
                beUsed: "该手机已被使用，请更换其它手机",
                badFormat: "手机格式不正确"
            }
        },
        emailCode: {
            onFocus: "请填入短信验证码",
            succeed: "",
            isNull: "短信验证码不可为空",
            error: {
                badLength: "长度只能等于4",
            }
        },
        //图形验证吗
        captchaCode: {
            onFocus: "请填入图形验证码",
            succeed: "",
            isNull: "图形验证码不可为空",
            error: {
                badLength: "长度只能等于4"
            }
        }

    });


    $.extend(validateFunction, {
        phone: function (option) {
            var format = validateRules.isMobile(option.value);
            if (!format) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            } else {
                if (option.value != null) {
                    option.errorEle.html("<span style='color:#999'>检验中……</span>");
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: "../register/check_phone.jhtml",
                        data: {
                            phone: option.value
                        },
                        async: false,
                        success: function (data) {
                            if (data == "success") {
                                validateSettings.succeed.run(option);
                                $("#simple").val(0);
                            } else if (data == "simple") {
                                $("#simple").val(1);
                                alert($("#simple").val());
                                $("#registHead").val("欢迎激活");
                                validateSettings.succeed.run(option);
                            } else {
                                validateSettings.error.run(option, option.prompts.error.beUsed);
                            }
                        }
                    });
                }
                else {
                    validateSettings.succeed.run(option);
                }

            }
        },
        //图形验证吗校验
        captchaCode: function (option) {
            var value = option.value;
            if (value.length < 4) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            }

            $.ajax({
                url: "../common/checkCaptcha.jhtml",
                type: "post",
                data: {
                    "captcha": value
                },
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.success) {
                        //验证成功
                        validateSettings.succeed.run(option);
                    } else {
                        //验证失败
                        validateSettings.error.run(option, "验证码输入错误");
                        $("#captchaImage").trigger("click");
                    }
                }
            })
        },
        //图形验证吗校验
        captchaCodeOther: function (option) {
            var value = option.value;
            if (value.length < 4) {
                validateSettings.error.run(option, option.prompts.error.badFormat);
            }
            validateSettings.succeed.run(option);
        },
        FORM_send_checkPhoneButton: function () {
            $("#phone").jdValidate(validatePrompt.phone, validateFunction.phone, true);
            $("#captchaCode").jdValidate(validatePrompt.captchaCode, validateFunction.captchaCode, true);
            return validateFunction.FORM_submit(["#captchaCode" , "#phone"]);
        }
    });

    $("#phone").jdValidate(validatePrompt.phone, validateFunction.phone);
    $("#captchaCode").jdValidate(validatePrompt.captchaCode, validateFunction.captchaCodeOther);

}

/**
 * 验证邮箱格式
 */
function emailValue(){
    var email = $("#email").val();
    if(null!=email&&""!=email){

        var format = validateRules.isEmail( email );
        var format2 = validateRules.betweenLength( email , 0, 50);
        if (!format) {
            $("#email_succeed").removeClass().addClass("blank");
            $("#email_error").removeClass().addClass("error").html("邮箱格式有误");
            $("#email_error").show();
            return;

        } else {
            if (!format2) {
                $("#email_succeed").removeClass().addClass("blank");
                $("#email_error").removeClass().addClass("error").html("邮箱长度太长");
                $("#email_error").show();
                return;
            }
            else {
                if (email!= null) {
                    $.ajax({
                        cache: true,
                        type: "GET",
                        url: "../register/check_email.jhtml",
                        data: {
                            email: email
                        },
                        async: false,
                        success: function (data) {
                            if (data) {

                            } else {
                                $("#email_succeed").removeClass().addClass("blank");
                                $("#email_error").removeClass().addClass("error").html("该邮箱已被使用");
                                $("#email_error").show();
                                return;

                            }
                        }
                    });
                }

            }
        }
    }
    if(""==email){
        $("#email_error").hide();
        $("#email_succeed").hide();
    }else{
        $("#email_error").removeClass().addClass("error").html("");
        $("#email_error").hide();
        $("#email_succeed").show();
        $("#email_succeed").removeClass().addClass("blank succeed");
    }
}