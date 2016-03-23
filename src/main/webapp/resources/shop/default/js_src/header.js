/**
* 头部信息 判断用户当前是否是登录 或者 未登录状态
*  同时调整 header中相关的状态
* 
*/
$(function(){
	var username = getCookie("username");
	var nickname = getCookie("nickname");
	
	var $headerName = $("#headerName");
	var $headerLogin = $("#headerLogin");
	var $headerRegister = $("#headerRegister");
	var $headerLogout = $("#headerLogout");

	if ($.trim(nickname) != "") {
		$headerName.text(nickname).show();
		$headerLogout.show();
	} else if ($.trim(username) != "") {
		//用户名和退出功能显示
		$headerName.text(username).show();
		$headerLogout.show();
	} else {
		//登录和注册功能显示
		$headerLogin.show();
		$headerRegister.show();
	}

    /**
     * 鼠标浮动显示 热门搜索
     */
   /* $(document).on("hover", ".input-wrap" , function(){
        $("#hodSearch").show();
    });*/

  /*  $(document).on("mouseout" , function(){
        if($(this).closest(".header-search-wrap").length > 0){

        }else{
            $("#hodSearch").hide();
        }
    })*/

	 /**
     * 搜索功能 
     */
    $(document).on("click", ".search-bt" , function(){
    	var url = $(this).attr("url");
		var keywords = $("#keywords_input").val();
		if("undefined"!=typeof(keywords) && keywords.length > 0 && keywords.trim().length >0){
			url =url +"?keyword=" + encodeURIComponent(keywords);
			window.location.href=url;
		}
    });

    /**
     * input 绑定回车事件
     */
    $(document).on("keyup", "#keywords_input" , function(event){
    	var which = event.which;
    	if( 13 === which){
    		$(".search-bt").trigger("click");
    	}
    });

    /**
     * 获取焦点 或者 失去 触发热门搜索
     */
    $("#keywords_input").on("focus" , function(){
        $("#hodSearch").show();
    });

    $(document).click(function() {
        var e = e || window.event || arguments.callee.caller.arguments[0];
        var target = e.target || e.srcElement;
        if ($(target).attr("id") == "hodSearch" || $(target).parents("#hodSearch").length == 1) {
            return;
        }
        if ($(target).attr("id") == "keywords_input" || $(target).parents("#keywords_input").length == 1) {
            return;
        }
        $("#hodSearch").hide()
    })


});