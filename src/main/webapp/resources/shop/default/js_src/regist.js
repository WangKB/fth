//获取倒计时
var count = 60;
function GetNumber() {
    $("#dyPhoneButton").val(count + "秒之后再获取");
    count--;
    if (count > 0) {
        setTimeout(GetNumber, 1000);
        $("#dyPhoneButton").addClass("hasSendCode");
        $("#dyPhoneButton").prop("disabled", true);
    } else {
        $("#dyPhoneButton").prop("disabled", false);
        $("#dyPhoneButton").val("重新获取验证码");
        document.getElementById("dyPhoneButton_error").style.display = 'none';
        $("#dyPhoneButton").removeClass("hasSendCode");
        count = 60;
    }
}

$(function () {
    $password = $("#pwd");
    $submit = $("#registsubmit");

    //更换验证码
    $("#captchaImage").click(function () {
        $(this).attr("src", "../common/captcha.jhtml?timestamp=" + new Date().getTime());
    });


    //表单验证
    regist_check();
    $("#registsubmit").on("click", function () {
        var hobby = new Array();
        for (var i = 0; i <= $(".hobby").length; i++) {
            if ($("#" + i).is(':checked')) {
                hobby.push($("#" + i).val());
            }
        }
        $("#keywords").val(hobby);
        if ($(".hobby:checked").size() == 0) {
            document.getElementById("slabeloMessage").style.display = 'block';
        }

        if ($(".hobby:checked").size() != 0) {
            document.getElementById("slabeloMessage").style.display = 'none';
        }
        //阅读注册协议验证
        if ($(".checkbox:checked").size() == 0) {
            layer.msg("请接受服务条款", {icon: 1, time: 2000});
        }
        //表单验证
        if (validateFunction.FORM_regist_check() && $(".hobby:checked").size() != 0 && $(".checkbox:checked").size() != 0) {
            //对密码进行加密
            $.ajax({
                url: "../common/public_key.jhtml",
                type: "GET",
                dataType: "json",
                cache: false,
                beforeSend: function () {
                    $submit.prop("disabled", true);
                },
                success: function (data) {
                    //对密码加密成功后 执行
                    var rsaKey = new RSAKey();
                    rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
                    var enPassword = hex2b64(rsaKey.encrypt($password.val()));
                    $("#enPassword").val(enPassword);
                    $.ajax({
                        url: "../register/submit.jhtml",
                        type: "POST",
                        data: $("#registerForm").serialize(),
                        cache: false,
                        async: false,
                        success: function (data) {
                            $submit.prop("disabled", false);
                            if (data == "registSuccess") {
                                layer.msg("注册成功", {icon: 1, time: 2000});
                                window.location.href = "../";
                            } else if (data == "activationSuccess") {
                                layer.msg("激活成功", {icon: 1, time: 2000});
                                window.location.href = "../";
                            } else if (data == "errorCode") {
                                layer.msg("对不起，验证码错误", {icon: 2, time: 2000});
                            } else if (data == "errorAttribute") {
                                layer.msg("对不起，没有成功添加会员注册项", {icon: 2, time: 2000});
                            }
                        },
                        error: function () {
                            layer.msg("对不起，服务器异常", {icon: 2, time: 2000});
                            window.location.href = "../register/index.jhtml";
                        }


                    });
                }
            });
        } else {
            return false;
        }
    });

    $(".blue").click(function () {
        var index = layer.open({
            type: 1,
            shade: false,
            area: ['700px', '500px'],
            title: "神器六国用户注册协议",
            content: $('#registerAgreement'), //捕获的元素
            cancel: function (index) {
                layer.close(index);
            }
        });
    });

    sendCheckPhone();

    $("#dyPhoneButton").click(function () {
        if ($(this).hasClass("hasSendCode")) {
            return;
        }
        //表单验证
        var b = validateFunction.FORM_send_checkPhoneButton();
        if ( b ) {
            $("#dyPhoneButton").prop("disabled", true);
            $.ajax({
                cache: true,
                type: "POST",
                url: "../common/phoneCode.jhtml",
                data: {
                    phone: $("#phone").val()
                },
                async: false,
                success: function (data) {
                    if (data.type == "success") {
                        //验证码倒计时
                        GetNumber();
                        document.getElementById("dyPhoneButton_error").style.display = 'block';
                    } else if (data.type = "error") {
                        layer.msg("对不起，验证短信发送失败", {icon: 2, time: 2000});
                        $("#dyPhoneButton").prop("disabled", false);
                        $("#dyPhoneButton").val("重新获取验证码");
                    }
                }
            });
        }
    });
});
    