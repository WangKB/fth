/**
 * 页面加载完 执行
 */
$(function(){
//	$android = "android";
//	$ios = "ios";
//	$.ajax({
//        url:"../common/downApp.jhtml",
//        type:"post",
//        data:{ua:$android},
//        dataType:"json",
//        success:function(data){
//            if(data.code == "200"){
//                var url = data.url;
//                $("#android").attr("href",url);
//            }
//        }
//    })
//    
//    $.ajax({
//        url:"../common/downApp.jhtml",
//        type:"post",
//        data:{ua:$ios},
//        dataType:"json",
//        success:function(data){
//            if(data.code == "200"){
//                var url = data.url;
//                alert(url)
//                $("#ios").attr("href",url);
//            }
//        }
//    })
	
	function isWeiXin(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}
	var flag = isWeiXin();
	
	 var Terminal = {
			 
	            // 辨别移动终端类型
	            platform : function(){
	                var u = navigator.userAgent, app = navigator.appVersion;
	                return {
	                    // android终端或者uc浏览器
	                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
	                    // 是否为iPhone或者QQHD浏览器
	                    iPhone: u.indexOf('iPhone') > -1 ,
	                    // 是否iPad
	                    iPad: u.indexOf('iPad') > -1
	                };
	            }(),
	            // 辨别移动终端的语言：zh-cn、en-us、ko-kr、ja-jp...
//	            language : (navigator.browserLanguage || navigator.language).toLowerCase()
	        }
	 
	 if(flag){
		 location.href='http://openbox.mobilem.360.cn/index/d/sid/3207382';
	 }else{
		// 根据不同的终端，跳转到不同的地址
	     var theUrl = 'https://itunes.apple.com/us/app/shen-qi-liu-guo/id1072297625?l=zh&ls=1&mt=8';
	     if(Terminal.platform.android){
	         theUrl = 'http://openbox.mobilem.360.cn/index/d/sid/3207382';
	     }else if(Terminal.platform.iPhone){
	         theUrl = 'https://itunes.apple.com/us/app/shen-qi-liu-guo/id1072297625?l=zh&ls=1&mt=8';
	     }
	     $("#ios").href=theUrl;
	     location.href = theUrl;
	 }
	
});