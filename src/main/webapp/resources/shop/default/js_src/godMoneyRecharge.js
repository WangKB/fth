/**
 * 页面加载完 执行
 */

$(function(){
	 //点击取消进入我的账户页面
	 $(".pointExchengGodMoneyNullCancel").on("click",function(){
			var	url="../account/index.jhtml";
			window.location.href=url;
	 });
	 //	点击取消进入我的账户页面
	 $(".pointRechargeCancel").on("click",function(){
			var	url="../account/index.jhtml";
			window.location.href=url;
	 });
	 //	点击取消进入我的账户页面
	 $(".godMoneyRechargeCancel").on("click",function(){
			var	url="../account/index.jhtml";
			window.location.href=url;
	 });
	 //点击神币兑换页面保存
	$(".pointRechargeSave").on("click",function(){
		//表单验证
		init();
		$(".pointRechargeSave").prop("disabled", true);
		$.ajax({
            cache: true,
            type: "POST",
            url:"../account/exchange.jhtml",
            data:{
           	 	godMoney: $("#useGodMoney").val(),
           	 	point: $("#changePoint").val()
            },
            async:false,
            success:function(data){
            	//兑换成功则跳出提示，并转到新的神币兑换页面
            	if(data=="success"){ 
            		layer.msg("兑换成功，可以继续兑换",{icon: 1,time:2000});
            		setTimeout(function(){
            			location.reload(true);},1000);
            		}
            	//兑换失败则跳出提示
            	if(data=="error"){
            		setTimeout(function(){
            			layer.msg("兑换失败",{icon: 2,time:2000});},1000);
            		$(".pointRechargeSave").prop("disabled", false);
                }
             }
		});
		
	});
	
	
});

	

