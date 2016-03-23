var validateRegExp = {
    decimal:"^([+-]?)\\d*\\.\\d+$", //浮点数
//    decmal1:"^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数   
    decimal6:"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$", //正浮点数
    decimal1:"(^\\d{1,13}\\.\\d{1,5}$)|(^\\d{1,13}$)",//最多13位整数，5位小数的浮点数
    decimal11:"(^\\d{1,13}\\.\\d{1,20}$)|(^\\d{1,13}$)",//最多13位整数，20位小数的浮点数
    decimal2:"^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
    decimal3:"^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
    decimal4:"(^\\d+\\.\\d{1,5}$)|(^\\d+$)", //非负浮点数（正浮点数 + 0）
    decimal5:"^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
    decimal7:"^([0]+(.[0-9]{1,5})?)|([1]+(.[0]{1,5})?)$",//比例用的 小数点后5位
    decimal8:"^([0]+(.[0-9]{1,5})?)$",//小于0-1数字 小数点后5位
    decimal9:"^([0]+(.[0-9]{1,20})?)$",//小于0-1数字 小数点后20位
    integer:"^-?[0-9]\\d*$", //整数
    integer1:"^[1-9](\\d*)$", //正整数
    integer2:"^-[1-9]\\d*$", //负整数
    num:"^([+-]?)\\d*\\.?\\d+$", //数字
    num1:"^([1-9]+)(\\d*)$|(^0$)",//自然数(正整数+0)
    num2:"^-[1-9]\\d*|0$", //负整数 + 0
    num3:"^(([0-9]+[\.]?[0-9]+)|[1-9])$", //正数
    ascii:"^[\\x00-\\xFF]+$", //仅ACSII字符
    chinese:"^[\\u4e00-\\u9fa5]+$", //仅中文
    color:"^[a-fA-F0-9]{6}$", //颜色
    date:"^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
    email:"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
    idcard:"^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
    ip4:"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter:"^[A-Za-z]+$", //字母 ^[A-Za-z]+$|^([1-9]+)(\\d*)$|(^0$)
    letter_l:"^[a-z]+$", //小写字母
    letter_u:"^[A-Z]+$", //大写字母
    mobile: "^0?(13|15|18|14|17)[0-9]{9}$", //手机
    notempty:"^\\S+$", //非空
    password:"^.*[A-Za-z0-9\\w_-]+.*$", //密码
    fullNumber:"^[0-9]+$", //数字
    picture:"(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq:"^[1-9]*[1-9][0-9]*$", //QQ号码
    rar:"(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel:"^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username:"^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //用户名
    deptname:"^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
    zipcode:"^\\d{6}$", //邮编
    realname:"^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
   //验证公司名称，由中文、英文、数字及“_”、“-”、()、（）组成
    companyname:"^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
  //验证地址，由中文、英文、数字及“_”、“#”,“-”、()、（）组成
    companyaddr:"^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
    //网址验证
    companysite:"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$",  
//	prealnum:"^[0-9]{1,13}?(.[0-9]{0,5})?$", //保留最多5位小数的正实数
	//0-100之间的数，可保留5为小数
	percent:"(^[1-9][0-9]([.][0-9]{1,5})?$)|(^[0-9]([.][0-9]{1,5})?$)|(^100([.]0{1,5})?$)",
	//percent:"(^[1-9][0-9]([.][0-9]{1,2})?$)|(^[0-9]([.][0-9]{1,2})?$)|(^100([.]0{1,2})?$)",
	//可以以0开头的数字组合
	szbm:"^[0-9]*$",
	//匹配中文，英文字母和数字及_:
	zysx:"^[\u4e00-\u9fa5_a-zA-Z0-9]+$",	
	//匹配 起抵地：“ 起始地/目的地”
	qdd:"^[A-Za-z\\u4e00-\\u9fa5]+[/]+[A-Za-z\\u4e00-\\u9fa5]+$",
	//排除非法字符
	ffzf:"[='`&]+",
	zmsz:"^[0-9a-zA-Z]+$",//数字或者字母
	telorphone:"^(0?1[358]\d{9})$|^((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7})$",//手机或者座机
	specialsymbol:"/^[\u4e00-\u9fa5a-z]+$/gi"
		
};

//主函数
(function ($) {
    $.fn.jdValidate = function(option, callback, def) {
        var ele = this;
        var id = ele.attr("id");
        var type = ele.attr("type");
        var rel = ele.attr("rel");
        var _onFocus = $("#" + id + validateSettings.onFocus.container);
        var _succeed = $("#" + id + validateSettings.succeed.container);
        var _isNull = $("#" + id + validateSettings.isNull.container);
        var _error = $("#" + id + validateSettings.error.container);
        if (def == true) {
            var str = ele.val();
            var tag = ele.attr("sta");
            if (str == "" || str == "-1"||str ==null) {
                validateSettings.isNull.run({
                    prompts:option,
                    element:ele,
                    isNullEle:_isNull,
                    succeedEle:_succeed
                }, option.isNull);
            } else if (tag == 1 || tag == 2) {
                return;
            } else {
                callback({
                    prompts:option,
                    element:ele,
                    value:str,
                    errorEle:_error,
                    succeedEle:_succeed
                });
            }
        } else {
            if (typeof def == "string") {
                ele.val(def);
            }
            if (type == "checkbox" || type == "radio") {
                if (ele.attr("checked") == true) {
                    ele.attr("sta", validateSettings.succeed.state);
                }
            }
            switch (type) {
                case "text":
                case "password":
                    ele.bind("focus", function () {
                        var str = ele.val();
                        if (str == def) {
                            ele.val("");
                        }
                        if (id == "pwd") {
                            $("#pwdstrength").hide();
                        }
                        validateSettings.onFocus.run({
                            prompts:option,
                            element:ele,
                            value:str,
                            onFocusEle:_onFocus,
                            succeedEle:_succeed
                        }, option.onFocus);
                    })
                        .bind("blur", function () {
                            var str = ele.val();
                            if (str == "") {
                                ele.val(def);
                            }
                            if (validateRules.isNull(str)) {
                                validateSettings.isNull.run({
                                    prompts:option,
                                    element:ele,
                                    value:str,
                                    isNullEle:_isNull,
                                    succeedEle:_succeed
                                }, option.isNull);
                            } else {
                                callback({
                                    prompts:option,
                                    element:ele,
                                    value:str,
                                    errorEle:_error,
                                    isNullEle:_isNull,
                                    succeedEle:_succeed
                                });
                            }
                        });
                    break;
                default:
                    if (rel && rel == "select") {
                        ele.bind("change", function () {
                            var str = ele.val();
                            callback({
                                prompts:option,
                                element:ele,
                                value:str,
                                errorEle:_error,
                                isNullEle:_isNull,
                                succeedEle:_succeed
                            });
                        });
                    } else {
                        ele.bind("click", function () {
                            callback({
                                prompts:option,
                                element:ele,
                                errorEle:_error,
                                isNullEle:_isNull,
                                succeedEle:_succeed
                            });
                        });
                    }
                    break;
            }
        }
    };
})(jQuery);

//配置
var validateSettings = {
    onFocus:{
        state:null,
        container:"_error",
        style:"focus",
        run:function (option, str) {
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style2).addClass(validateSettings.INPUT_style1);
            }
            option.onFocusEle.removeClass().addClass(validateSettings.onFocus.style).html(str);
        }
    },
    isNull:{
        state:0,
        container:"_error",
        style:"null",
        run:function (option, str) {
            option.element.attr("sta", 0);
            if (!validateRules.checkType(option.element)) {
                if (str != "") {
                    option.element.removeClass(validateSettings.INPUT_style1).addClass(validateSettings.INPUT_style2);
                } else {
                	option.element.attr("sta", 2);
                    option.element.removeClass(validateSettings.INPUT_style2).removeClass(validateSettings.INPUT_style1);
                }
            }
            option.succeedEle.removeClass(validateSettings.succeed.style);
            option.isNullEle.removeClass().addClass(validateSettings.isNull.style).html(str);
        }
    },
    error:{
        state:1,
        container:"_error",
        style:"error",
        run:function (option, str) {
            option.element.attr("sta", 1);
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style1).addClass(validateSettings.INPUT_style2);
            }
            option.succeedEle.removeClass(validateSettings.succeed.style);
            option.errorEle.removeClass().addClass(validateSettings.error.style).html(str);
        }
    },
    succeed:{
        state:2,
        container:"_error",
        style:"succeed",
        run:function (option,str) {
            option.element.attr("sta", 2);
            option.errorEle.empty();
            if (!validateRules.checkType(option.element)) {
                option.element.removeClass(validateSettings.INPUT_style1).removeClass(validateSettings.INPUT_style2);
            }
            if (option.element.attr("id") == "schoolinput" && $("#schoolid").val() == "") {
                return;
            }
            option.succeedEle.removeClass().addClass(validateSettings.succeed.style).html(str);
        }
    },
    INPUT_style1:"highlight1",
    INPUT_style2:"highlight2"

};

//验证规则
var validateRules = {
		isIllChar:function(str){//非法字符
			return new RegExp(validateRegExp.ffzf).test(str);
		},
		isPrealnum:function(str){//13位整数,可保留最多5位小数的正实数	
			if(new RegExp(validateRegExp.integer1).test(str)){
				var zl=validateRules.betweenLength(str.replace(/[^\x00-\xff]/g, "**"), 1, 13);
				return zl;
				}else if(new RegExp(validateRegExp.decmal1).test(str)){
					var array=str.split(".");
					var zl=validateRules.betweenLength(array[0].replace(/[^\x00-\xff]/g, "**"), 1, 13);
					var xl=validateRules.betweenLength(array[1].replace(/[^\x00-\xff]/g, "**"), 1, 5);
					return zl&&xl;
				}else{
					return false;
				}
		},
	
	isPercent:function(str){//0-100保留最多2位小数的正实数
        return new RegExp(validateRegExp.percent).test(str);
	},
    isNull:function (str) {
        return (str == "" || typeof str != "string");
    },
    equalLength:function(str,_length){
    	return(str.length ==  _length);
    },
    betweenLength:function (str, _min, _max) {
        return (str.length >= _min && str.length <= _max);
    },
    isUid:function (str) {
        return new RegExp(validateRegExp.username).test(str);
    },
    isnum:function (str) {
    	return new RegExp(validateRegExp.num).test(str);
    },
    isdecmal1:function (str) {
    	return new RegExp(validateRegExp.decmal1).test(str);
    },
    isnum1:function (str) {
    	return new RegExp(validateRegExp.num1).test(str);
    },
    fullNumber:function (str) {
        return new RegExp(validateRegExp.num).test(str);
    },
    fullNumberName:function (str) {
        return new RegExp(validateRegExp.zmsz).test(str);
    },
    isPwd:function (str) {
        return new RegExp(validateRegExp.zmsz).test(str);
    },
    isPwd2:function (str1, str2) {
        return (str1 == str2);
    },
    isEmail:function (str) {
        return new RegExp(validateRegExp.email).test(str);
    },
    isTel:function (str) {
        return new RegExp(validateRegExp.tel).test(str);
    },
    isMobile:function (str) {
        return new RegExp(validateRegExp.mobile).test(str);
    },
    checkType:function (element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isChinese:function (str) {
        return new RegExp(validateRegExp.chinese).test(str);
    },
    isRealName:function (str) {
        return new RegExp(validateRegExp.realname).test(str);
    },
    isDeptname:function (str) {
        return new RegExp(validateRegExp.deptname).test(str);
    },
    isCompanyname:function (str) {
        return new RegExp(validateRegExp.companyname).test(str);
    },
    isCompanyaddr:function (str) {
        return new RegExp(validateRegExp.companyaddr).test(str);
    },
    isCompanysite:function (str) {
        return new RegExp(validateRegExp.companysite).test(str);
    },
    isPositiveInteger:function (str) {
        return new RegExp(validateRegExp.integer1).test(str);
    },
    isPositive:function (str) {
        return new RegExp(validateRegExp.decimal4).test(str);
    },
    isBili:function (str) {
        return new RegExp(validateRegExp.decimal7).test(str);
    },
    isBili2:function (str) {
        return new RegExp(validateRegExp.decimal9).test(str);
    },
    isSequence:function(str){
    	return new RegExp(validateRegExp.zmsz).test(str);
    },
    isTelorMobile:function(str){
    	return new RegExp(validateRegExp.telorphone).test(str);
    },
    isWeight:function(str){
    	return new RegExp(validateRegExp.num3).test(str);
    },
    isLegalNum:function(str){
    	var bool1 = new RegExp(validateRegExp.decimal1).test(str);
    	var bool2 =(0!==parseFloat(str));
    	return bool1&&bool2;
    },
    isLegalNum2:function(str){
    	var bool1 = new RegExp(validateRegExp.decimal11).test(str);
    	var bool2 =(0!==parseFloat(str));
    	return bool1&&bool2;
    },
    isspecialsymbol:function(str){
    	return new RegExp(validateRegExp.specialsymbol).test(str);
    }
};
//验证文本
var validatePrompt = {

};

var nameold, emailold, phoneold, authcodeold;
var namestate = false, emailstate = false, phonestate = false, authcodestate = false;
//回调函数
var validateFunction = {
   
    protocol:function (option) {
        if (option.element.attr("checked") == true) {
            option.element.attr("sta", validateSettings.succeed.state);
            option.errorEle.html("");
        } else {
            option.element.attr("sta", validateSettings.isNull.state);
            option.succeedEle.removeClass(validateSettings.succeed.style);
        }
    },
   
    checkGroup:function (elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].checked) {
                return true;
            }
        }
        return false;
    },
    checkSelectGroup:function (elements) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].value == -1) {
                return false;
            }
        }
        return true;
    },
    showPassword:function (type) {
        var v1 = $("#pwd").val(), s1 = $("#pwd").attr("sta"), c1 = document.getElementById("pwd").className, t1 = $("#pwd").attr("tabindex");
        var v2 = $("#pwd2").val(), s2 = $("#pwd2").attr("sta"), c2 = document.getElementById("pwd2").className, t2 = $("#pwd2").attr("tabindex");
        var P1 = $("<input type='" + type + "' value='" + v1 + "' sta='" + s1 + "' class='" + c1 + "' id='pwd' name='pwd' tabindex='" + t1 + "'/>");
        $("#pwd").after(P1).remove();
        $("#pwd").bind("keyup",
            function () {
                validateFunction.pwdstrength();
            }).jdValidate(validatePrompt.pwd, validateFunction.pwd);
        var P2 = $("<input type='" + type + "' value='" + v2 + "' sta='" + s2 + "' class='" + c2 + "' id='pwd2' name='pwd2' tabindex='" + t2 + "'/>");
        $("#pwd2").after(P2).remove();
        $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2);
    },
    FORM_submit:function (elements) {
        var bool = true;
        for (var i = 0; i < elements.length; i++) {
            if ($(elements[i]).attr("sta") == 2) {
                bool = true;
            } else {
                bool = false;
                break;
            }
        }
        return bool;
    }
};

