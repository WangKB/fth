
$(function () {
    /**日期选择*/
    brith.init();
    /**打开子页面*/
    opt.init();
    /**表单验证*/
    profileEdit_check();
    //取后台猜你喜欢的数据
    var keywords = $("#keywords").val();
    //创建数组
    var strs = new Array();
    //分割字符串
    strs = keywords.split(",");
    //for循环选中猜你喜欢数据
    for (var i = 0; i < strs.length; i++) {
        $("input:checkbox[value=" + strs[i] + "]").attr('checked', true);
    }
    /**表单验证*/
    $("#updateMember-submit").unbind().on("click", function () {
        /**出生日期的年份*/
        var year = $("#birthdayYear").val();
        /**出生日期的月份*/
        var month = $("#birthdayMonth").val();
        /**出生日期的日子*/
        var day = $("#birthdayDay").val();
        /**将日期按照yyyy-MM-dd输出*/
        if (year != null && month != null && day != null) {
            $("#birthday").val(year + '-' + month + '-' + day);
        } else {
            $("#birthday").val(null);
        }
        /**将猜你喜欢输进数组*/
        var hobby = new Array();
        for (var i = 0; i <= $(".hobby").length; i++) {
            if ($("#" + i).is(':checked')) {
                hobby.push($("#" + i).val());
            }
        }
        $("#keywords").val(hobby);

        if ($(".hobby:checked").size() == 0) {
            layer.msg("最少选择一个你喜欢的品牌", {icon: 2, time: 2000});
        }

        /**表单验证*/
        if (validateFunction.FORM_profileEdit_checkSubmit() && $(".hobby:checked").size() != 0) {
            //	通过ajax保存个人信息
            $.ajax({
                cache: true,
                url: "../profile/update.jhtml",
                type: "POST",
                data: $("#profileEditFrom").serialize(),
                async: false,
                success: function (data) {
                    //通过update的函数返回值来判断
                    if (data.type == "success") {
                        layer.msg("修改个人信息成功！", {icon: 1, time: 2000});
                        //修改Cookie和顶部用户名
                        var userNick = $("#nickname").val();
                        $("#headerName").text( userNick );
                        addCookie("nickname" , userNick , null);
                    }
                },
                error: function () {
                    layer.msg("修改失败!", {icon: 2, time: 2000});
                }
            });
        }
    });


    $("#updateHeard").on("click", function () {
        $("#fileupload").trigger("click");
    });
    //选择文件成功则提交表单
    $("#fileupload").change(function () {
        if ($("#fileupload").val() != '') {

            var options = {
                url: '../../controller/upload/1.jhtml',
                success: function (data) {
                    $("#contractForm img").attr("src", data.filePath);
                    $("#photoPath").val(data.filePath);
                }
            };

            $("#contractForm").ajaxSubmit(options);
        }
        ;
    });

});


var brith = {
    init: function () {
        /***
         * 日期选择
         */
        // 根据所选择的年份、月份计算月最大天数,并重新填充生日下拉框中的日期项
        var nowdate = new Date(); // 获取当前时间的年份
        var nowYear = nowdate.getFullYear();// 当前年份
        var nowMonth = nowdate.getMonth() + 1;// 当前月份
        // 清空年份、月份的下拉框 进行重新添加选项
        $("#birthdayYear").empty();
        $("#birthdayMonth").empty();
        // 首先为年份字段 添加选项

        for (var startYear = nowYear; startYear >= 1930; startYear--) {
            $("<option value='" + startYear + "'>" + startYear + "</option>").appendTo(
                "#birthdayYear");
        }
        //	为月份字段添加选项

        for (var startMonth = 1; startMonth <= 12; startMonth++) {
            //将月份变成2个字母
            if (startMonth < 10) {
                startMonth = "0" + startMonth;
            }
            $("<option value='" + startMonth + "'>" + startMonth + "</option>").appendTo("#birthdayMonth");
        }

        //将原始日期输入页面的日期中
        if (originalBirthdayYear == null || originalBirthdayYear == ""
            || originalBirthdayYear == "1") {
            $("#birthdayYear").val(0);
            $("#birthdayMonth").val(0);
            $("#birthdayDay").val(0);
        } else {
            $("#birthdayYear").val(originalBirthdayYear);
            //小于10的在数值前面加个“0”
            if (originalBirthdayMonth < 10) {
                $("#birthdayMonth").val("0" + originalBirthdayMonth);
            } else {
                $("#birthdayMonth").val(originalBirthdayMonth);
            }
            changeSelectBrithdayDay();
        }
        // 选择生日年份后触发
        $("#birthdayYear").change(function () {
            changeSelectBrithdayDay();
        });
        // 选择生日月份后触发
        $("#birthdayMonth").change(function () {
            changeSelectBrithdayDay();
        });
        //计算一个月的天数
        function changeSelectBrithdayDay() {
            var maxNum;
            var month = $("#birthdayMonth").val();
            var year = $("#birthdayYear").val();
            if (year == 0) { // 如果年份没有选择，则按照闰年计算日期(借用2004年为闰年)
                year = 2004;
            }
            if (month == 0) {
                maxNum = 31;
            } else if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { // 判断闰年
                    maxNum = 29;
                } else {
                    maxNum = 28;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                maxNum = 30;
            } else {
                maxNum = 31;
            }
            // 清空日期的下拉框 进行重新添加选项
            $("#birthdayDay").empty();
            //	为day字段添加选项
            for (var startDay = 1; startDay <= maxNum; startDay++) {
                //将日期变成2个字母
                if (startDay < 10) {
                    startDay = "0" + startDay;
                }
                $("<option value='" + startDay + "'>" + startDay + "</option>").appendTo("#birthdayDay");
            }
            if (maxNum >= originalBirthdayDay) {
                setTimeout(function () {
                    //小于10的在数值前面加个“0”
                    if (originalBirthdayMonth < 10) {
                        $("#birthdayDay").val("0" + originalBirthdayDay);
                    } else {
                        $("#birthdayDay").val(originalBirthdayDay);
                    }
                }, 1);
                //设置当前年份为所选
            } else {
                setTimeout(function () {
                    $("#birthdayDay").val(1);
                }, 1);
                //设置当前年份为所选
                originalBirthdayDay = 1;
            }
        }

        /*日期选择*/
    }
};
var opt = {
    init: function () {
        this.changeAddress();
        this.changeEmail();
        this.changePhone();
        this.changePwd();
    },
    //修改收货地址
    changeAddress: function () {
        //点击收货地址修改按钮
        $("#changeAddress").unbind().on("click", function () {
            var addrId = $(this).attr("addrid");
            //进入修改收货地址页面
            $.post("../profile/toChangeAddr.jhtml", function (str) {
                var index = layer.open({
                    title: "修改家庭住址",
                    type: 1,
                    area: ['470px', '230px'],
                    content: str,
                    success: function () {
                        initEditAddr("area");
                        profileEdit_check();
                        $(".changeAddress-save").unbind().on("click", function () {
                            //表单验证
                            if (validateFunction.FORM_profileEdit_checkChangeAddress()) {
                                //	保存收货地址的信息
                                $.ajax({
                                    cache: true,
                                    type: "POST",
                                    url: "../profile/saveAddress.jhtml",
                                    data: $("#changeAddressAreaFrom").serialize(),
                                    async: false,
                                    success: function (data){
                                        if (data.type == "success") {
                                            layer.msg("修改家庭住址成功！", {icon: 1, time: 2000});
                                            $("#area").remove();
                                            layer.close(index);
                                            $("#addressBody").empty().text(data.address);
                                        } else {
                                            $("#detailAddress_error").removeClass().addClass("error");
                                            $("#detailAddress_error").empty().text("所在地区不能为空");
                                        }
                                    }
                                });
                            }
                        });
                        //	关闭修改收货地址页面
                        $(".changeAddress-cancel").unbind().on("click", function () {
                            $("#area").remove();
                            layer.close(index);
                         });
                    }
                });
            });
        });
    },
    //修改邮箱
    changeEmail: function () {
        $phone = $("#phoneBody").val();
        $("#changeEmail").unbind().on("click", function () {
            //进入修改邮箱页面
            $.post("../profile/toChangeEmail.jhtml", function (str) {
                var index = layer.open({
                    title: "修改邮箱",
                    type: 1,
                    area: ['440px', '245px'],
                    content: str,
                    success: function () {
                        //表单验证
                        profileEdit_check();
                        $("#dyEmailButton").click(function () {
                            //表单验证
                            if (validateFunction.FORM_profileEdit_checkEmail()) {
                                $("#dyEmailButton").prop("disabled", true);
                                //	获得短信验证码
                                $.ajax({
                                    cache: true,
                                    type: "POST",
                                    url: "../../common/phoneCode.jhtml",
                                    data: {
                                        phone: $("#phoneBody").val()
                                    },
                                    async: false,
                                    success: function (data) {
                                        if (data.type == "success") {
                                            //验证码倒计时
                                            GetEmailNumber();
                                            $("#dyEmailButton_error").show();
                                        } else if (data.type = "error") {
                                            layer.msg("对不起，验证短信发送失败", {icon: 2, time: 2000});
                                            $("#dyEmailButton").prop("disabled", false);
                                            $("#dyEmailButton").val("重新获取验证码");
                                            $(".changeEmail-cancel").unbind().on("click", function () {
                                                layer.close(index);
                                            });
                                        }

                                    }
                                });
                            }
                        });

                        $(".changeEmail-save").unbind().on("click", function () {
                            //表单验证
                            if (validateFunction.FORM_profileEdit_checkChangeEmail()) {
                                // 保存邮箱
                                $.ajax({
                                    cache: true,
                                    type: "POST",
                                    url: "../profile/saveEmail.jhtml",
                                    data: {
                                        email: $("#newEmail").val(),
                                        emailCode: $("#emailCode").val()
                                    },
                                    async: false,
                                    success: function (data) {
                                        // 修改成功时将邮箱填到个人页面中
                                        if (data == "success") {
                                            layer.msg("修改邮箱成功", {icon: 1, time: 2000}, function(){
                                                location.reload()
                                            });
                                        }else{
                                            $("#emailCode_error").text("验证码错误").addClass("error");
                                        }
                                    },
                                    error: function () {
                                        layer.msg("对不起，没有修改成功,请重新输入验证码！", {icon: 2, time: 2000});
                                    }
                                });
                            }
                        });

                        //关闭邮箱页面
                        $(".changeEmail-cancel").unbind().on("click", function () {
                            layer.close(index);
                        });
                    }
                });
            });
        });
    },
    //修改手机
    changePhone: function () {
        $phone = $("#phoneBody").val();
        $("#changePhone").on("click", function () {
            //打开手机修改页面
            $.post("../profile/toChangePhone.jhtml", function (str) {
                var index = layer.open({
                    title: "修改手机号",
                    type: 1,
                    area: ['440px', '245px'],
                    content: str,
                    success: function () {
                        //表单验证
                        profileEdit_check();
                        $("#dyMobileButton").click(function () {
                            // 表单验证
                            if (validateFunction.FORM_profileEdit_checkPhone()) {
                                // 将获得短信验证码的按钮锁定
                                $("#dyMobileButton").prop("disabled", true);
                                //	获得短信验证码
                                $.ajax({
                                    cache: true,
                                    type: "POST",
                                    url: "../../common/phoneCode.jhtml",
                                    data: {
                                        phone: $("#phoneBody").val()
                                    },
                                    async: false,
                                    success: function (data) {
                                        if (data.type == "success") {
                                            //得到手机验证倒计时
                                            GetPhoneNumber();
                                            $("#dyMobileCode_error").show();
                                        } else if (data.type = "error") {
                                            layer.msg("对不起，验证短信发送失败！", {icon: 2, time: 2000});
                                            $("#dyMobileButton").prop("disabled", false);
                                            $("#dyMobileButton").val("重新获取验证码");
                                            $(".changePhone-cancel").unbind().on("click", function () {
                                                layer.close(index);
                                            });
                                        }
                                    }
                                });
                            }
                        });

                        $(".changePhone-save").unbind().on("click", function () {
                            //	 表单验证
                            if (validateFunction.FORM_profileEdit_checkChangePhone()) {
                                //保存手机号码
                                $.ajax({
                                    cache: true,
                                    type: "POST",
                                    url: "../profile/savePhone.jhtml",
                                    data: {
                                        phone: $("#newPhone").val(),
                                        mobileCode: $("#mobileCode").val()
                                    },
                                    async: false,
                                    success: function (data) {
                                        if (data == "success") {
                                            layer.msg("手机修改成功！", {icon: 1, time: 2000} , function(){
                                                location.reload();
                                            });
                                        }else{
                                            $("#mobileCode_error").text("验证码错误").addClass("error");
                                        }
                                    },
                                    error: function () {
                                        layer.msg("对不起，手机没有修改成功，请重新输入验证码！", {icon: 2, time: 2000});
                                    }
                                });
                            }
                        });

                        //关闭手机修改页面
                        $(".changePhone-cancel").unbind().on("click", function () {
                            layer.close(index);
                        });
                    }
                });
            });
        });
    },
    changePwd: function () {
        $("#changePwd").on("click", function () {
            var keyurl = $(this).attr("keyurl");
            var url = $(this).attr("url");
            //打开密码修改页面
            $.post("../profile/toChangePwd.jhtml", function (str) {
                var index = layer.open({
                    title: "修改密码",
                    type: 1,
                    area: ['420px', '280px'],
                    content: str,
                    success: function () {
                        profileEdit_check();
                        $(".changePwd-save").on("click", function () {
                           if(validateFunction.FORM_profileEdit_checkPwd()){
                            /**加密*/
                            $.ajax({
                                url: prefix+"/common/public_key.jhtml",
                                type: "GET",
                                dataType: "json",
                                cache: false,
                                beforeSend: function () {
                                    $(".changeAddress-save").prop("disable", true);
                                },
                                success: function (data) {
                                    var rsaKey = new RSAKey();
                                    rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
                                    var enPassword = hex2b64(rsaKey.encrypt($("#newPwd").val()));
                                    $.ajax({
                                        url: "../profile/savePwd.jhtml",
                                        type: "POST",
                                        data: {
                                            enPassword: enPassword
                                        },
                                        dataType: "json",
                                        cache: false,
                                        success: function (data) {
                                            if (data == "success") {
                                                layer.msg("修改密码成功！", {icon: 1, time: 2000} ,function(){
                                                    layer.close( index );
                                                });
                                            }
                                        }
                                    });
                                }
                            });
                           }
                        });
                        
                        $(".changePwd-cancel").unbind().on("click", function () {
                            $("#dyEmailButton").prop("disabled", false);
                            layer.close(index);
                        });
                    }
                });
            });
        });
    }
};

function initEditAddr(id) {
    var $areaId = $("#" + id);
    // 地区选择
    $areaId.lSelect({
        url: $areaId.attr("url")
    });
}

/**获得验证码倒计时*/
var count = 60;
function GetPhoneNumber() {
    $("#dyMobileButton").val(count + "秒之后再获取");
    count--;
    if (count > 0) {
        setTimeout(GetPhoneNumber, 1000);
    } else {
        $("#dyMobileButton").prop("disabled", false);
        $("#dyMobileButton").val("重新获取验证码");
        $("#dyMobileCode_error").hide();
        count = 60;
    }
}
function GetEmailNumber() {
    $("#dyEmailButton").val(count + "秒之后再获取");
    count--;
    if (count > 0) {
        setTimeout(GetEmailNumber, 1000);
    } else {
        $("#dyEmailButton").prop("disabled", false);
        $("#dyEmailButton").val("重新获取验证码");
        $("#dyMobileCode_error").hide();
        count = 60;
    }
}

