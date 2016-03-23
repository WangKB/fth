
/**
 * 
 * 预订单取消.
 * @author:王凯斌
 * @date: 2015-12-14 下午5:17:24 
 * @param sn
 */
function checkShip(id){
	var ruzhang = function(){
		$.ajax({
			url: "../../admin/order/checkShip.jhtml",
			type: "POST",
			data:{
				id:id
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				location.reload(true);
			}
		});
	}
	confirmPy( "您是否确认？" , ruzhang );
}
