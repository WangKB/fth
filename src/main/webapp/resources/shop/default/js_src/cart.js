/**
 * 页面加载完 执行
 */
$(function(){
	timeouts = {};
	opt.init();
	
});


/**
 * 绑定 所有按钮的操作事件
 */
var opt = {
		init:function(){
			this.del();
            this.delByIds();
			this.notify();
			this.follow();
            this.followByIds();
			this.addQuantity();
			this.delQuantity();
			this.inputQuantity();
			this.clearAll();
			this.selectAll();
			this.clickCheckBox();
			this.submint();
		},
        delByIds:function(){
            $(".cart-item-opt").on("click" ,".cart-remove" , function(){
                layer.open({
                    content:"是否删除选中的商品?",
                    btn:["确认","取消"],
                    yes:function(index){
                        var $submit = $("#submit");

                        var ids = new Array();
                        $(".cart-checkbox input[name='checkItem']:checked").each(function(){
                            ids.push(Number( $(this).val() ));
                        });

                        $.ajax({
                            url: "delete.jhtml",
                            type: "POST",
                            data:{id:ids},
                            dataType: "json",
                            cache: false,
                            beforeSend:function(){
                                $submit.prop("disabled",true);
                            },
                            success: function(data) {
                                if(data.message.type == "success"){
                                    location.reload(true);
                                }else{
                                    $.message(data.message);
                                    setTimeout(function() {
                                        location.reload(true);
                                    }, 3000);
                                }
                                layer.close(index);
                            },
                            complete:function(){
                                $submit.prop("disabled",false);
                            }
                        });
                    },
                    cancel:function(index){
                        layer.close(index);
                    }
                });
            });
        },
        followByIds:function(){
            $(".cart-item-opt").on("click" ,".cart-follow" , function(){
                var url = $(this).attr("url");
                var id = $(this).attr("data");

                var ids = new Array();
                $(".cart-checkbox input[name='checkItem']:checked").each(function(){
                    ids.push(Number( $(this).attr("data-goodsId") ));
                });

                $.ajax({
                    url: url,
                    type: "POST",
                    data: {id: ids},
                    dataType: "json",
                    cache: false,
                    success:function(data){
                        if(data.type=="warn"){
                            layer.msg(data.content, {icon: 2,time: 2000});
                        }else{
                            layer.msg("商品已成功添加到收藏夹", {icon: 1,time: 2000});
                        }
                    }
                });

            });
        },
		submint:function(){
			
			$("#submit").on("click",function(){
				
				var ids = new Array();
				$(".cart-checkbox input[name='checkItem']:checked").each(function(){
					ids.push(Number( $(this).val() ));
				});
				if( ids.length == 0){
					layer.msg( "请勾选商品" ,{"icon":7 ,"time":"2000"} );
					return false;
				}
				var url = $(this).attr("data-url");
				$.ajax({
					url: "./selectToOrderProduct.jhtml" ,
					type:"post",
					data:{
						ids:ids
					},
					success:function(data){
						if(data.success){
							window.location.href = url;
						}else{
							layer.msg( "以下商品【"+data.message.join(",") +"】库存不足" , {"icon":2, "time":2000} );
						}
					}
				});
			});
			
		},
		del:function(){
			$(".item-list").on("click",".cart-remove",function(){
				var $this = $(this);
				layer.open({
					content:"是否删除该商品?",
					btn:["确认","取消"],
					yes:function(index){
						var $submit = $("#submit");
						var $item = $this.closest(".item-list");
						var id = $item.find("input[name='checkItem']").val();
						clearTimeout(timeouts[id]);

						$.ajax({
							url: "delete.jhtml",
							type: "POST",
							data:{id:id},
							dataType: "json",
							cache: false,
							beforeSend:function(){
								$submit.prop("disabled",true);
							},
							success: function(data) {
								if(data.message.type == "success"){
									if(data.quantity>0){
										$item.remove();
										$("#totalCount").text(data.quantity);
										$("#effectiveRewardPoint").text(data.effectiveRewardPoint);
										$("#effectivePrice").text(currency(data.effectivePrice));
									}else{
										location.reload(true);
									}
								}else{
									$.message(data.message);
									setTimeout(function() {
										location.reload(true);
									}, 3000);
								}
								layer.close(index);
							},
							complete:function(){
								$submit.prop("disabled",false);
							}
						});
					},
					cancel:function(index){
						layer.close(index);
					}
				});
			});
		},
		notify:function(){
			$(".item-list").on("click",".cart-notify",function(){
				console.log("到货通知");
			});
		},
		follow:function(){
			$(".item-list").on("click",".cart-follow",function(){

				var url = $(this).attr("url");
				var id = $(this).attr("data");
				$.ajax({
					url: url,
					type: "POST",
					data: {id: id},
					dataType: "json",
					cache: false,
					success:function(data){
						if(data.type=="warn"){
							layer.msg(data.content, {icon: 2,time: 2000});
						}else{
							layer.msg(data.content, {icon: 1,time: 2000});
						}
					}
				});
				
			});
		},
		clearAll:function(){
			$("#clearMyCart").on("click",function(){
			layer.open({
					content:"是否清空购物车?",
					btn:["确认","取消"],
					yes:function(){
						$.each(timeouts, function(i, timeout) {
							clearTimeout(timeout);
						});
						$.ajax({
							url: "clear.jhtml",
							type: "POST",
							dataType: "json",
							cache: false,
							success: function(data) {
								location.reload(true);
							}
						});
					},
					cancel:function(index){
						layer.close(index);
					}
				});
			});
		},
		addQuantity:function(){
			$(".item-list").on("click",".increment",function(){
				var $quantity = $(this).prev("input");
				var quantity = $quantity.val();
				
				if (/^\d*[1-9]\d*$/.test(quantity)) {
					$quantity.val(parseInt(quantity) + 1);
				} else {
					$quantity.val(1);
				}
				
				var $checkbox = $(this).closest(".item-form").find("input[name='checkItem']")  ;
				if( !$checkbox.is(':checked') ){
					$checkbox.attr('checked' , true );
				}
				
				opt.edit($quantity);
			});
		},
		delQuantity:function(){
			$(".item-list").on("click",".decrement",function(){
				var $quantity = $(this).next("input");
				var quantity = $quantity.val();
				if (/^\d*[1-9]\d*$/.test(quantity) && parseInt(quantity) > 1) {
					$quantity.val(parseInt(quantity) - 1);
				} else {
					$quantity.val(1);
				}
				
				var $checkbox = $(this).closest(".item-form").find("input[name='checkItem']")  ;
				if( !$checkbox.is(':checked') ){
					$checkbox.attr('checked' , true );
				}
				
				opt.edit($quantity);
				
			});
		},
		inputQuantity:function(){
			$(".item-list").on("keyup",".itxt",function(event){
				var a = parseInt($(this).val(), 10);
				if(this.disabled ? !1 : "" == $(this).val() ? !1 : isNaN(a) || a < 1 || a > 999 ){
					($(this).val(1));
				};
				setTimeout(opt.edit($(this)),1000);
			});
		},
		edit:function($quantity){
			$submit = $("#submit");
			var quantity = $quantity.val();
			if (/^\d*[1-9]\d*$/.test(quantity)) {
				var $item = $quantity.closest(".item-item");
				
				var $list = $quantity.closest(".item-list");
				
				var id = $item.find("input[name='checkItem']").val();
				clearTimeout(timeouts[id]);
				timeouts[id] = setTimeout(function() {
					
					var ids = new Array();
					$(".cart-checkbox input[name='checkItem']:checked").each(function(){
						ids.push(Number( $(this).val() ));
					});
					
					var url = $("#cart").attr("editurl");
					
					$.ajax({
						url: url,
						type: "POST",
						data: { id: id, quantity: quantity , ids:ids},
						dataType: "json",
						cache: false,
						beforeSend: function() {
							$submit.prop("disabled", true);
						},
						success: function(data) {
							if (data.message.type == "success") {
								$quantity.data("value", quantity);
								
								$item.find(".p-sum strong").text(currency(data.subtotal, true, true));
								
								$list.find(".binds").each(function(){
									var dataId = $(this).find("strong").attr("data-id");
									$(this).find("strong").text( currency( data["bindProduct"+dataId]  ) );
								});
								
								$("#totalCount").text(data.quantity);
						
								if (!data.isLowStock) {
									$item.find("span.lowStock").hide();
								}
								//积分
								$("#effectiveRewardPoint").text(data.effectiveRewardPoint);
								//价格
								$("#effectivePrice").text(currency(data.effectivePrice));
								//折扣
								$("#discountPrice").text(currency(data.discountPrice));
								$list.find(".num").empty().text("X"+quantity );
							} else if (data.message.type == "warn") {
								$item.find("span.lowStock").text(data.message.content).show();
							} else if (data.message.type == "error") {
								$.message(data.message);
								setTimeout(function() {
									location.reload(true);
								}, 3000);
							};
						},
						complete: function() {
							$submit.prop("disabled", false);
						}
					});
				}, 500);
			} else {
				$quantity.val($quantity.data("value"));
			}
		},
		selectAll:function(){
			$("#toggle-checkboxes_up").on("click",function(){
				
				if($(this).attr("checked")){
					//全不选
					$(".cart-checkbox input:checkbox ").attr("checked", true); 
				}else{
					//全选
					$(".cart-checkbox input:checkbox ").attr("checked", false);
				}
				
				opt.selectCartItem();
				
			});
		},
		clickCheckBox:function(){
			$(".cart-checkbox input[name='checkItem']").on("click",function(){
				if($(this).attr("checked")){
					//全不选
					$(this).attr("checked", true); 
				}else{
					//全选
					$(this).attr("checked", false);
				}
				if($(".cart-tbody input[name='checkItem']").length == $(".cart-checkbox input[name='checkItem']:checked").length ){
					$("#toggle-checkboxes_up").attr("checked", true); 
				} else{
					$("#toggle-checkboxes_up").attr("checked", false);
				}
				
				opt.selectCartItem();
			});
		},
		selectCartItem:function(){
			//触发请求重新计算总价
			var url = $("#cart").attr("editurl");
			var ids = new Array();
			$(".cart-checkbox input[name='checkItem']:checked").each(function(){
				ids.push(Number( $(this).val() ));
			});
			
			$.ajax({
				url: "./choose.jhtml",
				type: "POST",
				data: {
					ids: ids
					},
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$("#submit").prop("disabled", true);
				},
				success: function(data) {
					if (data.message.type == "success") {
						
						$("#totalCount").text(data.quantity);
					
						//积分
						$("#effectiveRewardPoint").text(data.effectiveRewardPoint);
						//价格
						$("#effectivePrice").text(currency(data.effectivePrice));
						//折扣
						$("#discountPrice").text(currency(data.discountPrice));
						
					} else if (data.message.type == "warn") {
						$item.find("span.lowStock").text(data.message.content).show();
						$quantity.val($quantity.data("value"));
					} else if (data.message.type == "error") {
						$.message(data.message);
						$quantity.val($quantity.data("value"));
						setTimeout(function() {
							location.reload(true);
						}, 3000);
					};
				},
				complete: function() {
					$("#submit").prop("disabled", false);
				}
			});
			
		}
		
};
