$(function(){
	if($("#hasExpired").val() == "true"){
		
	}else{
		if($("#status").val()=="等待付款" || $("#status").val()=="等待审核"){
			$("#tijiao").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#fukuan").css("background-image","url("+"../resources/shop/default/img/icon/underwayOK_icon.png"+")");
			$("#tijiaos").css('color','#91a5e8');
			$("#fukuans").css('color','#147ab6');
		}
		
		if($("#status").val()=="等待发货"){
			$("#tijiao").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#fukuan").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#chuku").css("background-image","url("+"../resources/shop/default/img/icon/underwayOK_icon.png"+")");
			$("#tijiaos").css('color','#91a5e8');
			$("#fukuans").css('color','#91a5e8');
			$("#chukus").css('color','#147ab6');
		}
		
		if($("#status").val()=="已发货" || $("#status").val()=="待自提" || $("#status").val()=="待收货"){
			$("#tijiao").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#fukuan").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#chuku").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#dengdai").css("background-image","url("+"../resources/shop/default/img/icon/underwayOK_icon.png"+")");
			$("#tijiaos").css('color','#91a5e8');
			$("#fukuans").css('color','#91a5e8');
			$("#chukus").css('color','#91a5e8');
			$("#dengdais").css('color','#147ab6');
		}
		
		if($("#status").val()=="已收货"){
			$("#tijiao").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#fukuan").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#chuku").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#dengdai").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#wancheng").css("background-image","url("+"../resources/shop/default/img/icon/underwayOK_icon.png"+")");
			$("#tijiaos").css('color','#91a5e8');
			$("#fukuans").css('color','#91a5e8');
			$("#chukus").css('color','#91a5e8');
			$("#dengdais").css('color','#91a5e8');
			$("#wanchengs").css('color','#147ab6');
		}

		if($("#status").val()=="已完成" || $("#status").val()=="已退货" ){
			$("#tijiao").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#fukuan").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#chuku").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#dengdai").css("background-image","url("+"../resources/shop/default/img/icon/finishOK_icon.png"+")");
			$("#wancheng").css("background-image","url("+"../resources/shop/default/img/icon/underwayOK_icon.png"+")");
			$("#tijiaos").css('color','#91a5e8');
			$("#fukuans").css('color','#91a5e8');
			$("#chukus").css('color','#91a5e8');
			$("#dengdais").css('color','#91a5e8');
			$("#wanchengs").css('color','#91a5e8');
		}
	}
	

    $("#refundsStatus").on("click" ,function(){
        var index = layer.open({
            type: 1,
            title:"退款记录",
            area:["auto" , "auto"],
            content:$("#refundWrap")
        });
    })
});