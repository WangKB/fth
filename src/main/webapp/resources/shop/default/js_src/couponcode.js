/**
 * 页面加载完 执行
 */
$(function(){
	var couponType = $("#couponType").val();
	var type = $("#type").val();
	
	$("#couponType"+couponType).addClass("checked");
	
	$(".couponStatusWrap li").removeClass("selected");
	$(".couponStatusWrap li").each(function(index){
		if( (index+1) == type  ){
			$(this).addClass("selected");
		}
	});
	
	//优惠劵状态切换
	$(".couponStatusWrap").on("click" , "li" ,function( ){
		var url="../coupons/index.jhtml?couponType="+$("#couponType").val()+"&type="+($(this).index()+1)+"";
		window.location.href = url;
	});
	
	//去使用优惠券
	$(".item-content").on("click",".to-use-btn",function(){
		var couponId = $(this).attr("data-couponId");
		window.location.href="./useCoupon.jhtml?couponId="+ couponId ;
	});
});

/**
 * 
 * 选择卡券使用范围.
 * @author:yanzhisen
 * @date: 2015-10-14 下午4:39:19 
 * @modify: 修改记录
 * @param id
 */
function couponType(id){
	var url="../coupons/index.jhtml?couponType="+id+"";
	window.location.href = url;
}

/**
 * 
 * 过期卡券删除.
 * @author:
 * @date: 2015-10-15 下午4:18:35 
 * @modify: 修改记录
 * @param id
 */
function del(id){
	layer.confirm('是否确定删除此优惠劵？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			url: "delete.jhtml",
			type: "POST",
			data:{
				couponId:id,
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				layer.msg("删除成功",{icon: 1,time:1000},function(){
					window.location.reload();//刷新当前页面.
				});
			},
		});
	}, function(){
		
	});
}