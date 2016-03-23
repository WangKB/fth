Array.prototype.contains = function(item) {
	return RegExp(item).test(this);
};
/**
 * 订单详情页面 展示
 */
$(function() {
	opt.init();

	/**
	 * 选择优惠券、神币页面
	 */
	var $godcoinbutton = $("#godcoinbutton");
	var $couponbutton = $("#couponbutton");
	var $godcoin = $("#godcoin");
	var $coupon = $("#coupon");
	var $godcoinplus = $godcoinbutton.find(".discounticonplus");
	var $godcoinminus = $godcoinbutton.find(".discounticonminus");
	var $couponplus = $couponbutton.find(".discounticonplus");
	var $couponminus = $couponbutton.find(".discounticonminus");
	$godcoinbutton.on("click", function() {
		if ($godcoin.hasClass("hide")) {
			$godcoin.removeClass("hide");
			$coupon.addClass("hide");
			$godcoinplus.addClass("hide");
			$godcoinminus.removeClass("hide");
			if ($couponplus.hasClass("hide")) {
				$couponplus.removeClass("hide");
				$couponminus.addClass("hide");
			}
		} else {
			$godcoin.addClass("hide");
			$godcoinplus.removeClass("hide");
			$godcoinminus.addClass("hide");
		}
	});
	$couponbutton.on("click", function() {
		if ($coupon.hasClass("hide")) {
			$coupon.removeClass("hide");
			$godcoin.addClass("hide");
			$couponplus.addClass("hide");
			$couponminus.removeClass("hide");
			if ($godcoinplus.hasClass("hide")) {
				$godcoinplus.removeClass("hide");
				$godcoinminus.addClass("hide");
			}
		} else {
			$coupon.addClass("hide");
			$couponplus.removeClass("hide");
			$couponminus.addClass("hide");
		}

	});

	// 输入神币并计算折扣
	var $godMoneyNum = $("#godMoneyNum");

	var $useGodMoney = $("#useGodMoney");

	var $godMoneyHas = $("#godMoneyHas").val();

	$godMoneyNum.keyup(function() {
		this.value = this.value.replace(/[^\d]/g, '');
		var usemoney;
		if (this.value == "") {
			usemoney = 0;
		} else {
			usemoney = parseInt(this.value);
		}
		if (parseInt($godMoneyHas) >= usemoney) {
			$useGodMoney.removeClass("disabled");
			$useGodMoney.addClass("button");
			var value = $godMoneyNum.val() / rate;
			var payValue = $("#payPriceId").attr( "data-value" );
			if( value > Number(payValue)  ){
				$("#rmbvalued").html("&nbsp&nbsp输入的神币金额大于需支付订单金额!");
				return false;
			}
			
			$("#rmbvalued").html("&nbsp&nbsp" + currency( value , true ) );
		} else {
			$useGodMoney.addClass("disabled");
			$useGodMoney.removeClass("button");
			$("#rmbvalued").html("&nbsp&nbsp神币不足!");
		}
	});

	$useGodMoney.click(function() {
		
		var payValue = $("#payPriceId").attr( "data-value" );
		if( Number($("#godMoneyNum").val())   > Number(payValue)  ){
			layer.msg("神币使用金额大于订单支付金额，请修改神币金额" , {"icon":2 , "time":2000 });
			return false;
		}
		
		if (Number($godMoneyHas) >= Number($("#godMoneyNum").val())  ) {
			var value = $("#godMoneyNum").val() / rate;
			var html = " -神币：<em class='price' data-value=" + value.toFixed(2) + ">￥" + value.toFixed(2) + "</em>";
			$("#godMoneyReduce").html(html);

			calpayPrice();
		} else {
			return false;
		}
	});

	//计算优惠券折扣
	var $useCoupon = $("#useCoupon");
	$useCoupon.click(function() {

		var couponitems = [];
		$(".content input[name='couponCodes']:checked").each(function(index) {
			couponitems[index] = $(this).val();
		});

		$.ajax({
			url: "CouponDiscount.jhtml",
			type: "POST",
			data: {
				couponCodeIds: couponitems,
				priceTotal: $("#priceTotal").val()
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				var oldPrice = $("#dicountprice .price").attr("data-price");
				var payPrice = $("#payPriceId");
				if( parseFloat(data.discount) > Number(payPrice) ){
					layer.msg("神币使用金额大于订单支付金额，请修改神币金额" , {"icon":2 , "time":2000 });
					return false;
				}
				
				var html = "-优惠价格： <em class='price' data-value=" + (parseFloat(oldPrice) + parseFloat(data.discount)).toFixed(2) + ">￥" + (parseFloat(oldPrice) + parseFloat(data.discount)).toFixed(2) + "元</em>";
				$(".couponvalue").html("（可以抵：￥" + data.discount.toFixed(2) + "）");
				$("#dicountprice").html(html);
				var oldPrice = $("#dicountprice .price").attr("data-price", oldPrice);

				calpayPrice();

			}
		});
	});

	// 检验优惠券是否冲突
	var $couponitems = $(".couponitems");
	$couponitems.click(function() {
		var $this = $(this);
		var newchecked = $(this).val();
		var couponitems = [];
		$(".content input[name='couponCodes']:checked").each(function(index) {
			couponitems[index] = $(this).val();
		});

		$.ajax({
			url: "isCouponUseful.jhtml",
			type: "POST",
			data: {
				selectingitem: newchecked,
				couponitems: couponitems
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if (data.message.type == "success") {

				} else {
					$this.attr("checked", false);
					layer.msg("该优惠券与已选优惠券冲突", {
						"icon": 2,
						"time": 2000
					});
				}
			}
		});
	});
    /* 订单提交操作 */
	var $submit = $("#order-submit");
	$submit.on("click", function() {
		if ("" == $("#receiverid").val()) {
			layer.msg("收货人信息不能为空", {
				"icon": 2,
				"time": 3000
			});
			return false;
		}

		if ( !$(".pay_way input[name='paymentMethodId']:checked").val()) {
			layer.msg("请选择一种支付方式", {
				"icon": 2,
				"time": 3000
			});
			return false;
		}

		if ( !$(".dispatch_way input[name='shippingMethodId']:checked").val()) {
			layer.msg("请选择一种配送方式", {
				"icon": 2,
				"time": 3000
			});
			return false;
		}
        if( $(".dispatch_way input[name='shippingMethodId']:checked").val() == 2 ){
            if( !$("#organizationId").val()  ){
                layer.msg("请选择自提门店", {
                    "icon": 2,
                    "time": 3000
                });
                return false;
            }
        }

		var url = $submit.attr("url");

		$.ajax({
			url: url,
			type: "post",
			data: $("#orderForm").serialize(),
			dataType: "json",
			cache: false,
            async:false,
			beforeSend: function() {
				$submit.prop("disabled", true);
			},
            success: function (data) {
                if (data.message.type == "success") {
                    if(data.istoPay){
                        window.location.href = "./payment.jhtml?sn=" + data.sn;
                        return false;
                    }else{
                        window.location.href = "./orderCenter/index/0/0.jhtml";
                        return false;
                    }
                } else {
                    var l = layer.msg(data.message.content , {icon: 2 , time:3000} , function(){
                        window.location.reload(true);
                    });
                }
            },
			complete: function() {
				$submit.prop("disabled", false);
			}
		});
	});

    /* 积分提交订单 */
	var $exchangesubmit = $("#exchange-order-submit");

	$exchangesubmit.on("click", function() {
		if ("" == $("#receiverid").val()) {
			layer.msg("收货人信息不能为空", {
				"icon": 2,
				"time": 3000
			});
			return false;
		};
		if ("" == $(".dispatch_way input[name='shippingMethodId']:checked").val()) {
			layer.msg("请选择一种配送方式", {
				"icon": 2,
				"time": 3000
			});
			return false;
		}
		$.ajax({
			url: "create.jhtml",
			type: "POST",
			data: $("#orderForm").serialize(),
			dataType: "json",
            async:false,
			cache: false,
			beforeSend: function() {
				$exchangesubmit.prop("disabled", true);
			},
			success: function(data) {
				if (data.message.type == "success") {
                    if(data.istoPay){
                        window.location.href = "payment.jhtml?sn=" + data.sn;
                    }else{
                        window.location.href = "orderCenter/index/1/0.jhtml";
                    }
				} else {
                    layer.msg(data.message, {
                        "icon": 2,
                        "time": 3000
                    });
				}
			},
			complete: function() {
				$exchangesubmit.prop("disabled", false);
			}
		});
	});

    /* 秒杀订单 */
    var $seckillsubmit = $("#seckill-order-submit");
    $seckillsubmit.on("click" , function(){
        if ("" == $("#receiverid").val()) {
            alert("收货人信息不能为空");
            return false;
        };
        if ("" == $(".pay_way input[name='paymentMethodId']:checked").val()) {
            alert("请选择一种支付方式");
            return false;
        }
        if ("" == $(
            ".dispatch_way input[name='shippingMethodId']:checked")
            .val()) {
            alert("请选择一种配送方式");
            return false;
        }

        $.ajax({
            url: "create.jhtml",
            type: "POST",
            data: $("#orderForm").serialize(),
            dataType: "json",
            async:false,
            cache: false,
            beforeSend: function() {
                $reservesubmit.prop("disabled", true);
            },
            success: function(data) {
                if (data.message.type == "success") {
                    if(data.istoPay){
                        window.location.href = "payment.jhtml?sn=" + data.sn;
                    }else{
                        window.location.href = "orderCenter/index/1/0.jhtml";
                    }
                } else {
                    $.message(data.message);
                    setTimeout(function() {
                        location.reload(true);
                    }, 3000);
                }
            },
            complete: function() {
                $reservesubmit.prop("disabled", false);
            }
        });

    });

    /* 预定订单 */
	var $reservesubmit = $("#reserve-order-submit");
	$reservesubmit.on("click", function() {
		if ("" == $("#receiverid").val()) {
			alert("收货人信息不能为空");
			return false;
		};
		if ("" == $(".pay_way input[name='paymentMethodId']:checked").val()) {
			alert("请选择一种支付方式");
			return false;
		}
		if ("" == $(
				".dispatch_way input[name='shippingMethodId']:checked")
			.val()) {
			alert("请选择一种配送方式");
			return false;
		}
		$.ajax({
				url: "create.jhtml",
				type: "POST",
				data: $("#orderForm").serialize(),
				dataType: "json",
				cache: false,
                async:false,
				beforeSend: function() {
					$reservesubmit.prop("disabled", true);
				},
				success: function(data) {
					if (data.message.type == "success") {

                        if(data.istoPay){
                            window.location.href = "payment.jhtml?sn=" + data.sn;
                        }else{
                            window.location.href = "myPreOrder.jhtml";
                        }

					} else {
						$.message(data.message);
						setTimeout(function() {
							location.reload(true);
						}, 3000);
					}
				},
				complete: function() {
					$reservesubmit.prop("disabled", false);
				}
			});
	});
});

var opt = {
	init: function() {
		this.paymentMethodOpt();
		this.shippingMethodOpt();
		this.addNewAddr();
		this.changeAddr();
		this.modifyAddr();
		this.selectAddrClick();
		this.selectComeform();
        this.initMethodOpt();
	},
    initMethodOpt:function(){
        $(".pay_way .selected").trigger("click");
        $(".dispatch_way .selected").trigger("click");
    },
	paymentMethodOpt: function() {
		$(".pay_way .item").on("click",function() {
				var $this = $(this).find("input");
				if ($this.prop("disabled")) {
					return false;
				}
				$this.closest("li").addClass("selected").siblings()
					.removeClass("selected");
				var paymentMethodId = $this.val();

				var shippingmethods = paymentMethodIds[paymentMethodId];
				// 已经选择的配送方式
				var selectShippingMethod = $(
					".dispatch_way .selected input").val();
				if (shippingmethods.length > 0) {
					if (shippingmethods.contains(selectShippingMethod)) {

					} else {
						$("#shippingMethod_" + shippingmethods[0]).trigger(
							"click");
					}
				}
				$this.attr("checked", true);
			});
	},
	shippingMethodOpt: function() {
		$(".dispatch_way .item").on("click", function() {
			var $this = $(this).find("input");
			if ($this.prop("disabled")) {
				return false;
			}

			if (typeof(paymentMethodIds) == "undefined") {
				return false;
			}

			var shippingMethodId = $this.val();
			// 已经选择的支付方式
			var selectPayWatMethod = $(".pay_way .selected input").val();
			var shippingmethods = paymentMethodIds[selectPayWatMethod];

			if (shippingmethods.length > 0) {
				if (!shippingmethods.contains(shippingMethodId)) {
					return false;
				}
			}

			$this.closest("li").addClass("selected").siblings()
				.removeClass("selected");

			if ("shippingMethod_2" === $this.attr("id")) {
				$("#comeforminfo").show();
			} else {
				$("#comeforminfo").hide();
			}

			$this.attr("checked", true);
		});
	},
	changeAddr: function() {
		// 用户切换地址
		$("#changeAddr").on("click", function() {
			var receiverId = $("#receiverId").val();
			$.post("../member/receiver/toAddrList.jhtml", {
				receiverId: receiverId
			}, function(str) {
				layer.open({
					title: "选择收货人信息",
					type: 1,
					area: ['500px', '300px'], // 宽高
					content: str,
					success: function() {
						var that = opt;
						that.selectAddrClick();
						that.modifyAddr();
						that.addNewAddr();
						that.selectAddr();
					}
				});
			});

		});
	},
	addNewAddr: function() {
		// 添加新地址
		$(".select-opt-btn .opt-btn-add")
			.on(
				"click",
				function() {
					layer.closeAll();
					$.post("../member/receiver/toAddAddrIndex.jhtml", function(str) {
						var index = layer.open({
							title: "添加收货人信息",
							type: 1,
							area: ['600px', '305px'], // 宽高
							content: str,
							success: function() {
								initEditAddr("edit-areaId");
								receiver_check();
								$(".select-opt-btn .opt-btn-save").unbind().on("click", function() {
									if(validateFunction.FORM_receiver_check()){
									$.ajax({
										cache: true,
										type: "POST",
										url: "../member/receiver/save.jhtml",
										data: $('#userAddrFrom').serialize(),
										async: false,
										success: function(data) {
											layer.close(index);
											if (data.type == "success") {
                                                if( $("#receiverId").length < 1  ){
                                                    window.location.reload();
                                                }else{
                                                    $("#receiverId").val(data.data.id);
                                                    $("#receiverName").empty().html(
                                                            data.data.consignee + "&nbsp;&nbsp;&nbsp;&nbsp; " + data.data.phone + "&nbsp;&nbsp;&nbsp;&nbsp; " + data.data.address);
                                                }
											}
										},
										error:function(){
											$("#edit-areaId_error").removeClass().addClass("error");
	                                        $("#edit-areaId_error").empty().text("所在地区不能为空");
	                                    }
									});
								 }
								});

								$(".select-opt-btn .opt-btn-cancel").unbind()
									.on("click",function() {
											layer.close(index);
										});
							}
						});
					});

				});
	},
	modifyAddr: function() {
		$(".modifyaddr").unbind().on("click",function() {
					layer.closeAll();
					$.post("../member/receiver/toAddAddrIndex.jhtml", {
								id: $("#receiverId").val()
							},
							function(str) {
								var index = layer
									.open({
										title: "修改收货人信息",
										type: 1,
										area: ['600px',
											'305px'
										], // 宽高
										content: str,
										success: function() {
											initEditAddr("edit-areaId");
											receiver_check();
											$(".select-opt-btn .opt-btn-save").unbind().on("click",function() {
												if(validateFunction.FORM_receiver_check()){
													$.ajax({
														cache: true,
														type: "POST",
														url: "../member/receiver/save.jhtml",
														data: $('#userAddrFrom').serialize(), 
														async: false,
														success: function(data) {
															layer.close(index);
															if (data.type == "success") {
																$("#receiverId").val(data.data.id);
																$("#receiverName").empty().html(data.data.consignee + "&nbsp;&nbsp;&nbsp;&nbsp; " + data.data.phone + "&nbsp;&nbsp;&nbsp;&nbsp; " + data.data.address);
															}
														},
														error:function(){
															$("#edit-areaId_error").removeClass().addClass("error");
					                                        $("#edit-areaId_error").empty().text("所在地区不能为空");
					                                    }
													});
												  }
												});

											$(".select-opt-btn .opt-btn-cancel").unbind().on("click",function() {
												layer.close(index);
												});
											}
									});
							});
				});
	},
	selectAddrClick: function() {
		// 选择收获人 鼠标点击事件
		$(".useraddrall .item").on(
			"click",
			function() {
				if ($(this).hasClass("selected")) {
					return false;
				} else {
					$(this).closest("ul").find(".selected").removeClass(
						"selected");
					$(this).addClass("selected");
				}
			});
	},
	selectAddr: function() {
		// 地址选择
		$(".select-opt-btn .opt-btn-commit").on("click", function() {
			var $item = $(".useraddrall .selected");
			if ($item.length == 0) {
				return false;
			}
			var receiverId = $item.find(".modifyaddr").attr("addrid");
			var text = $item.find("label").text();
			$("#receiverId").val(receiverId);
			$("#receiverName").empty().html(text);
			layer.closeAll();
		});
	},
	selectComeform: function() {
		$("#selectComefrom").on("click",function() {
			// 选择自提点
			var organizationId = $("#organizationId").val();
			var date = $("#collectTime").val();
			$.post("../member/receiver/toStoreList.jhtml",
					{organizationId: organizationId,date: date},
					function(str) {
						var index = layer.open({
									title: "自提信息选择",
									type: 1,
									area: ['800px',
										'420px'
									], // 宽高
									content: str,
									success: function() {
										var that = opt;
										that.selectTime();
										that.selectOrganization();
										initEditAddr("getarea");
										$(".fieldSet select").on("change", function() {
											opt.reflushOrganization($("#getTime").val(), $("#getarea").val());
										});
										$(".select-opt-btn .opt-btn-save").on("click", function() {
											var $time = $(".sincetime .select");
											var $addr = $(".organizationlist .selected label");
											$("#comeformaddr").empty().text($addr.text());
											$("#comeformtime").empty().text($time.attr("data-value"));
											$("#organizationId").val($addr.attr("orgId"));
											$("#collectTime").val($time.attr("data-value"));
											layer.close(index);
										});

										$(".select-opt-btn .opt-btn-cancel").on("click", function() {
											layer.close(index);
										});
									}

								});
						});
				});
	},
	selectTime: function() {
		// 自提信息 选择自提时间
		$(".sincetime").on("click", "li", function() {
			$(this).siblings(".select").removeClass("select");
			$(this).addClass("select");
			var value = $(this).attr("time");
			$("#getTime").val(value);

			opt.reflushOrganization($("#getTime").val(), $("#getarea").val());
		});
	},
	selectOrganization: function() {
		// 自提信息 选择自提点
		$(".organizationlist").on("click",".org-name",
			function() {
				$(this).closest(".organizationlist").find(".selected")
					.removeClass("selected");
				$(this).addClass("selected");
				$("#getorganization").val(
					$(this).find("label").attr("orgid"));
			});
	},
	reflushOrganization: function(time, areaid) {
		$.ajax({
			url: "../member/receiver/storeList.jhtml",
			type: "get",
			data: {
				date: time,
				areaId: areaid
			},
			dataType: "json",
			success: function(data) {
				var orgValue = $(".organizationlist .selected").attr("orgId");
				if (data.type == "success") {
					var html = "";
					for (var i = 0; i < data.data.length; i++) {
						var item = data.data[i];
						if (item.id == orgValue) {
							html = html + "<li> <span class='org-name selected'> <label orgid="+item.id+">" + item.name + "</label><b></b></span><span class='org-info'>" + item.address + item.tel + "</span></li>";
						} else {
							html = html + "<li> <span class='org-name'> <label orgid="+item.id+">" + item.name + "<label><b></b></span><span class='org-info'>" + item.address + item.tel + "</span></li>";
						}
					}
					$(".organizationlist").empty().append(html);
				}
			}
		});
	}
};

/**
 * 初始化 修改/新增 收货人地址
 */
function initEditAddr(id) {
	var $areaId = $("#" + id);
	// 地区选择
	$areaId.lSelect({
		url: $areaId.attr("url")
	});

}

/**
 *
 * @Description: 计算应付总金额
 * @author: 王凯斌
 * @date: 2015-10-29 下午4:05:05
 * @modify: 修改记
 * 	商品总金额 - 优惠价格 - 神币
 */

function calpayPrice() {

	var payPrice = $("#totalPrice").attr("data-value");
	var dicountprice = $("#dicountprice em").attr("data-value");
	var godprice = $("#godMoneyReduce em").attr("data-value");

	var payprice = (parseFloat(payPrice) - parseFloat(dicountprice) - parseFloat(godprice)).toFixed(2);
	
	$("#payPriceId").text("￥" + payprice + "元");

}
