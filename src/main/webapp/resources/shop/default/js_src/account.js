/**
 * 页面加载完 执行
 */

$(function(){
		Exchange.init();
		var accountType = $("#accountType").val();
		//选择点击的数据
		switch (accountType) {
			case "1":
				$("#godMoney").addClass("checked");
				break;
			case  "2":
				$("#point").addClass("checked");
				break;
		}
});

/**
 * 
 * 选择我的账户显示内容.
 * @author:yanzhisen
 * @date: 2015-10-21 下午4:39:19 
 * @modify: 修改记录
 * @param value
 */
function account(value){
	var url="../account/index.jhtml?accountType="+value+"";
	window.location.href = url ;
}

/**
 * 
 * 选择我的账户内容的页数.
 * @author:yanzhisen
 * @date: 2015-10-21 下午4:39:19 
 * @modify: 修改记录
 * @param value
 */
function pageNumber(value){
		var url="../account/index.jhtml?accountType="+$("#accountType").val()+"&pageNumber="+value+"";
		window.location.href = url ;
}

var Exchange={
		init:function(){
			this.godMoneyButton();
			this.pointButton();
		},
		godMoneyButton:function(){
			//点击进入神币充值页面		
			$("#godMoneyButton").on("click",function(){ 
				var url="../account/godMoneyRecharge.jhtml";
				window.location.href=url;
			});
		},
		pointButton:function(){
			var godMoney=$("#godMoneyValue").val();
			//	点击进入积分兑换页面		
			$("#pointButton").on("click",function(){
				//神币为0进入神币为0页面
				if(Number(godMoney)==0){
					var	url="../account/pointExchangeNull.jhtml";
				}else{
					//神币不为0进入神币兑换页面
					var	url="../account/pointExchange.jhtml";
				}
				window.location.href=url;
			});
		}
};
