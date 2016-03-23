/**
 *  定义变量
 */
var $headerCart = $("#headerCart");
//放大镜
var $zoom = $("#zoom");

var $specification = $(".good-price-wrap .specification");
var $specificationValue = $(".good-price-wrap .specification .item");
var $specificationTips = $(".good-price-wrap .title");
//销售价 市场价 积分 
var $price = $("#price");
var $marketPrice = $("#marketPrice");
var $rewardPoint = $("#rewardPoint");
	
//加入购物车 
var $joincart  = $(".joincart ");

//立即支付
var $quickpay = $(".quickpay");

//立即预定
var $quickresver = $("#quickresver");

//购买数量
var $quantity = $("#buy-num");

//当前商品的ID
var productId;


$(function(){
    $("#buy-num").val(1);

	/**  进入页面 对规格值 进行锁定 start   **/
	var currentSpecificationValueIds = $specification.map(function() {
		$selected = $(this).find(".selected");
		return $selected.size() > 0 ? $selected.attr("val") : [null];
	}).get();
	
	$specification.each(function(i) {
		$(this).find(".item").each(function(j) {
			var $this = $(this);
			var specificationValueIds = currentSpecificationValueIds.slice(0);
			
			specificationValueIds[i] = $this.attr("val");
			if (isValid(specificationValueIds)) {
				$this.removeClass("locked");
			} else {
				$this.addClass("locked");
			}
			
		});
	});
	/** 进入页面 对规格值 进行锁定 end  **/
	
	opt.init();
	
	productId = $("#defaultProductId").val();
	specification = $("#defaultProductSpecificationItem").val();
	/**
	 * 初始化 购买版本
	 */
	var  payHtml = "";
	var tempClass="";
	for(var data in productData ){
		if(productData[data].specificationValueIds == specification ){
			if(productData[data].id == productId ){
				payHtml = payHtml + "<dd><div class='item selected'  val=" +productData[data].operatorId+ "><b></b><a href='#none'><i>"+productData[data].operator+"</i></a></div></dd>";
			}else{
				payHtml = payHtml + "<dd><div class='item'  val=" +productData[data].operatorId+ "><b></b><a href='#none'><i>"+productData[data].operator+"</i></a></div></dd>";
			}
			
		}
	}
	$(".pay-method").nextAll().remove();
	$(".pay-method").after(payHtml);

	/**
	 * 绑定选择商品参数
	 */
	$specification.on("click" , ".item" , function(){
		var $this = $(this);
		var $oldItem = null;
		
		if($this.hasClass("selected") || $this.hasClass("locked") ){
			return false;
		}else{
			$oldItem = $this.closest(".specification").find(".selected");
			$this.parent().siblings().children(".item").removeClass("selected");
			$this.addClass("selected");
			var currentSpecificationValueIds = $specification.map(function() {
				$selected = $(this).find(".selected");
				return $selected.size() > 0 ? $selected.attr("val") : [null];
			}).get();
			
			$specification.each(function(i) {
				$(this).find(".item").each(function(j) {
					var $this = $(this);
					var specificationValueIds = currentSpecificationValueIds.slice(0);
					
					specificationValueIds[i] = $this.attr("val");
					if (isValid(specificationValueIds)) {
						$this.removeClass("locked");
					} else {
						$this.addClass("locked");
					}
					
				});
			});
			
			var currentSpecificationValueIds = currentSpecificationValueIds.join(",");
			var product = productData[currentSpecificationValueIds];
			if(typeof product == "undefined"){

			}else{
				window.location.href = product.href ;
			}
			
		}
		return false;
	
	});
	
	/**
	 * 收藏商品
	 */
	$(".favinfo a").on("click",function(){
		var url = $(this).attr("url");
		var id = $(this).attr("data");
		$.ajax({
			url: prefix+"/member/favorite/add.jhtml",
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
	
	//全部评论缩略图
	$(".allreviewsmall").on("click",function(){
		$this = $(this);
		 if ($this.hasClass("current")) {
             return false;
         }
		 var $allreviewbig= $this.closest("div").find(".allreviewbig");
		 $allreviewbig.attr("src",$this.attr("src"));
		 $allreviewbig.show();
		 $(".allreviewsmall").removeClass("current");
         $this.addClass("current");
	});
	
	$(".allreviewbig").on("click",function(){
		$(this).hide();
	});
	/**
	 * 加入到购物车
	 */
	$joincart.on("click",function(){
		if (productId == null) {
			$specificationTips.fadeIn(150).fadeOut(150).fadeIn(150);
			return false;
		}
		var quantity = $quantity.val();
		var url = $(this).attr("url");
		//验证数量数值是否有效，有效则加入到购物车中
		if (/^\d*[1-9]\d*$/.test(quantity)) {
			$.ajax({
				url: url,
				type: "POST",
				data: {productId: productId, quantity: quantity},
				dataType: "json",
				cache: false,
				success: function(message) {
					//添加成功 触发 js 添加效果
					if (message.type == "success" && $headerCart.size() > 0 && window.XMLHttpRequest) {
						var $image = $zoom.find("img");
						var cartOffset = $headerCart.offset();
						var imageOffset = $image.offset();
						$image.clone().css({
							width: 300,
							height: 300,
							position: "absolute",
							"z-index": 20,
							top: imageOffset.top,
							left: imageOffset.left,
							opacity: 0.8,
							border: "1px solid #dddddd",
							"background-color": "#eeeeee"
						}).appendTo("body").animate({
							width: 30,
							height: 30,
							top: cartOffset.top,
							left: cartOffset.left,
							opacity: 0.2
						}, 1000, function() {
							$(this).remove();
						});
					}else{
						layer.msg(message.content);
					}
					
				}
			});
		} else {
			layer.msg( "购买数量必须为正整数");
			$("#buy-num").val(1);
		}
	
	});

	
	$(".fieldSet").on("change","select",function(){
		var $tempSelect = $(".fieldSet select");
		var address = "";
		$tempSelect.each(function(){
			if("" != $(this).val() ){
				address += $(this).find("option:selected").text();
			}
		});
		$("#toAddress").empty().text(address);
	});

    /** 查看商品规格信息 **/
    $(".good-extend").on("click","#goodsSpecification",function(){
        layer.open({
            type: 1,
            title:"商品参数",
            scrollbar: false,
            area: ['auto', 'auto'], 
            content: $("#parameter")
        });
    });
    
    /** 线下体验 **/
    $("#lineExpe").on("click" , function(){
    	layer.open({
            type: 1,
            title:"线下体验",
            scrollbar: false,
            area: ['auto', 'auto'], 
            content: $("#lineExpeCon")
        });
    });

    /** 查看售后规格信息 **/
    $("#salesupport").on("click",function(){
        layer.open({
            type: 1,
            title:"售后保障",
            scrollbar: false,
            area: ['auto', 'auto'], 
            content: $("#aftersale")
        });
    });
    
    /** 门店服务 **/
    $("#storeservice").on("click" ,function(){
    	var sn = $(this).attr("data-sn");
        var url = $(this).attr("data-url");
		$.post( url , {"productId" :  sn}, function(str){
				var index=layer.open({
						title:"门店服务",
						type:1,
						area:['auto','atuo'],
						content:str,
						success:function(){
							$(".storeService").on("click"  , ".serviceItem" , function(){
								$(this).siblings().removeClass("select");
								$(this).addClass("select");
								
								//服务 Id 
								var serviceId = $(this).attr("data");
								//获取满足服务的门店
								$.ajax({
									url:"../getStoreByProductService.jhtml",
									type:"post",
									dataType:"json",
									data:{
										productSn : sn,
										serviceId : serviceId
									},
									success:function(data){
										$storeinfos = $(".storeinfos");
										if( data.success ){
											var html = "";
											for( var org in data.data){
												html += "<li class='storeinfo' data='"+data.data[org].id+"'><p class='name'>"+ data.data[org].name +"</p>" +
														"<p class='addr'>"+data.data[org].address+"</p></li>";
											}
											$storeinfos.empty().append( html );
										}
									}
								});
							});
							
							if( $(".storeService li").length > 0 ){
								$(".storeService li:eq(0)").trigger("click");
							}
							
						}
				});
		});
	
    	
    } );
    
    /** 查看优惠绑定套餐信息 **/
    $(".good-extend").on("click",".productbind-warp",function(){
        layer.open({
            type: 1,
            title:"绑定促销",
            scrollbar: false,
            area: ['auto' , '350px'], //宽高
            content: $("#productbind")
        });
    });
    
    /**
     * 计算绑定销售总计
     */
    $("#productbind").on("click",".choose input",function(){
        var $checked = $("#productbind .choose input:checked") ;
        var length = $checked.length;
        $("#bindProductCount").text(length);
        var allPrice =parseFloat( $("#product-price").attr("data-jp"));
        var price = 0.0;
        $checked.each(function(){
            price = parseFloat($(this).attr("data-jp"));
            if(!isNaN(price)){
                allPrice+= price;
            }
        });
        var num = new Number(allPrice);
        $("#bindProductPrice").text(currency(num.toFixed(2) , true) );
    });
    
    /**
     * 选号选套餐
     */
    $(".good-opts").on("click" , ".selectcontract" , function(){
    	var url = $(this).attr("data-url");
    	window.location.href = url ;
    });
    
    /**
     * 兑换积分商品
     */
    $("#quickexchange").click(function(){
        var url = $(this).attr("url");
        var quantity = $("#buy-num").val();
        //校验库存
        if (/^\d*[1-9]\d*$/.test(quantity)) {
            $.ajax({
                url: "../../cart/checkStock.jhtml",
                type: "POST",
                data: {productId: productId, quantity: quantity},
                dataType: "json",
                cache: false,
                success: function(message) {
                    //添加成功 触发 js 添加效果
                    if (message.success == true) {
                        //校验成功
                        window.location.href = url+"&quantity="+quantity;
                    }else{
                        layer.msg(message.msg);
                    }

                }
            });
        } else {
            layer.msg( "购买数量必须为正整数");
            $("#buy-num").val(1);
        }
    });
    
    /**
     *  立即购买
     */
    $quickpay.on("click" , function(){
        var url = $(this).attr("url");
        var quantity = $("#buy-num").val();
        //校验库存
        if (/^\d*[1-9]\d*$/.test(quantity)) {
            $.ajax({
                url: "../../../cart/checkStock.jhtml",
                type: "POST",
                data: {productId: productId, quantity: quantity},
                dataType: "json",
                cache: false,
                success: function(message) {
                    //添加成功 触发 js 添加效果
                    if (message.success == true) {
                       //校验成功
                        window.location.href = url+"&quantity="+quantity;
                    }else{
                        layer.msg(message.msg);
                    }

                }
            });
        } else {
            layer.msg( "购买数量必须为正整数");
            $("#buy-num").val(1);
        }
    });
    
    /**
     * 优惠套餐 选择提交
     */
    $(".opts a").on("click",function(){
    	var bindIds = new Array();
    	var bindId ;
    	$(".choose input[name='bindproduct']").each(function(){
    		if($(this).attr("checked")){
    			bindId = $(this).val();
    			bindIds.push(Number(bindId));
    		}
    	});
    	if( bindIds.length < 1){
    		layer.msg("请选择促销商品" ,{"icon":2,"time":2000});
    		return false;
    	}
    	$.ajax({
    		url:'../../cart/add.jhtml',
    		type:"post",
    		data:{
    			productId:$("#product-price").val(),
    			quantity:1,
    			productBinds:bindIds
    		},
    		dataType:"json",
    		success:function(data){
    			if(data.type=="success"){
    				layer.msg("购物车添加成功" ,{"icon":1 ,"time":2000} , function(){
    					window.location.reload();//刷新当前页面.
    				} );
    			}else{
    				layer.msg(data.content ,{"icon":2,"time":2000});
    			}
    		}
    	});
    });
    
});


/**
 * 初始化 界面图片放大器功能
 */
var opt = {
    init:function(){
        this.initJQzoom();
        this.initArea();
        this.review();
    },
    review:function(){

        /** 评论效果 **/
        $(".good-review-list").on("click" , ".has-img", function(){
            var imgs = $(this).attr("data-imgs");
            if( imgs.length < 1 ){
                return false;
            }
            var imgArray = imgs.split(",");

            $(".reviewshowimage").attr("src" ,imgArray[0]);

            $("#reviewItems").empty();

            var $a ;
            var $img;
            for( var i=0 ;i<imgArray.length ; i++ ){
                $a = $("<a></a>");
                $img = $("<img />");
                if( i == 0){
                    $a.addClass( "current" );
                }
                $img.attr("src" , imgArray[i] );
                $a.append( $img );
                $("#reviewItems").append( $a );
            }

            var $reviewThumbnailScrollable = $(".reviewThumbnailScrollable");
            $reviewThumbnailScrollable.scrollable();
            var $reviewThumbnail = $("#reviewThumbnailScrollable a");

            $reviewThumbnail.bind().hover(function() {
                var $this = $(this);
                if ($this.hasClass("current")) {
                    return false;
                }
                $this.closest(".productReviewImage").find(".reviewshowimage").attr("src",$this.find("img").attr("src"));
                $reviewThumbnail.removeClass("current");
                $this.addClass("current");
            });

            $(".good-review-list").hide();
            $(".good-review-item").show();
        });

        $(".good-review-item").on("click" , function(){
            $(".good-review-list").show();
            $(".good-review-item").hide();
        });

        /** 评论效果 end **/
        var $showallreviews = $("#showallreviews");
        $showallreviews.on("click",function(){
            layer.open({
                type: 1,
                title:"全部评论",
                skin: 'layui-layer-rim',
                scrollbar :false,
                area: ['700px', '600px'],
                content: $("#allreviews")
            });
        });

    },
    initJQzoom:function(){
        var $zoom = $("#zoom");
        var $thumbnailScrollable = $("#thumbnailScrollable");
        var $thumbnail = $("#thumbnailScrollable a");

        // 商品图片放大镜
        $zoom.jqzoom({
            zoomWidth: 368,
            zoomHeight: 368,
            title: false,
            preloadText: null,
            preloadImages: false,
            lens:true
        });

        // 商品缩略图滚动
        $thumbnailScrollable.scrollable();
        
        $thumbnail.hover(function() {
            var $this = $(this);
            if ($this.hasClass("current")) {
                return false;
            }

            $thumbnail.removeClass("current");
            $this.addClass("current").click();
        });
        
        $zoom.on('click',function(){
            $(this).attr("href","");
            return false;
        });
    },
    initArea:function(){
        var $areaId = $("#areaId");
        $areaId.lSelect({
            url: $areaId.attr("url")
        });
    }

};

/**
 * 将 setAmount 方法绑定到window中
 */
window.setAmount = {
		min:1,
		max:199,
		count:1,
		countEl:$("#buy-num"),
		targetLink:$("#choose-btn-append .btn-append"),
		/***
		 * 增加数量
		 */   
		add: function() {
			return this.disabled ? !1 : this.count >= this.max ? !1 : (this.count++, this.countEl.val(this.count), void this.setBuyLink());
		},
		/**
		 * 修改数量 
		 */
		modify: function() {
			var a = parseInt(this.countEl.val(), 10);
			return this.disabled ? !1 : "" == this.countEl.val() ? !1 : isNaN(a) || a < this.min || a > this.max ? (this.countEl.val(this.count), !1) : (this.count = a, void this.setBuyLink());
		},
		/***
		 * 删减数量
		 */
		reduce: function() {
			return this.disabled ? !1 : this.count <= this.min ? !1 : (this.count--, this.countEl.val(this.count), void this.setBuyLink());
		},
		
		setBuyLink: function() {
			
		}
	};

// 判断规格值ID是否有效
function isValid(specificationValueIds) {
	for(var key in productData) {
		var ids = key.split(",");
		if (match(specificationValueIds, ids)) {
			return true;
		}
	}
	return false;
}

// 判断数组是否配比
function match(array1, array2) {
	if (array1.length != array2.length) {
		return false;
	}
	for(var i = 0; i < array1.length; i ++) {
		if (array1[i] != null && array2[i] != null && array1[i] != array2[i]) {
			return false;
		}
	}
	return true;
}
