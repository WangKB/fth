/**
 * 初始化 首页 页面
 */
$(function(){
	
	$(window).on("resize" , function(){
		var contetHeight = $(window).height() - 150 ;
		contetHeight = contetHeight > 750 ? contetHeight  :750;
		$(".fsvs-content").height( contetHeight );
	});
	
	var contetHeight = $(window).height() - 150 ;
	contetHeight = contetHeight > 750 ? contetHeight  :750;
	$(".fsvs-content").height( contetHeight );
	
    index_opt.init();

	var isGoodsContent = window.location.hash;
	if( isGoodsContent == "#slide-3" || isGoodsContent == "#slide-2" || isGoodsContent == "#slide-1"){
		$("#product").trigger("click");
	}
	
});

var slider = {};

var index_opt = {
    init:function(){
        var slider = {};
        var $index = $(".index");
        this.initLoginStatus();
        this.productClick(  );
        this.toIndex();
        this.roundabout();
        this.remindTime();
        this.toSeckillGoodsContent();
        this.search();
        this.navClick();
        this.initCoolProduct();
        this.navHover();
    },
    initCoolProduct:function(){
    	$(".diamonGoodInfo :eq(1) ").find(".diamonGoodInfoPrice , .diamonGoodInfoValue").show();
    	$(".diamonGoodInfo").on("hover" , function(){
    		$(".diamonGoodInfo .diamonGoodInfoPrice").hide();
    		$(".diamonGoodInfoPrice , .diamonGoodInfoValue").hide();
    		
    		$(this).find(".diamonGoodInfoPrice , .diamonGoodInfoValue").show();
    	});
    },
    initLoginStatus:function(){
        var username = getCookie("username");
        var nickname = getCookie("nickname");
    },
    toIndex:function(){
        $(".downicon").on("click",function(){
            $(".search-header").css("top" , "0").css("opacity" , "0");
            $(".myproduct").css("top" , "1200px");
            window.location.hash="";
            setTimeout( function(){
                $("#products-content").hide();
            } , 2000 );
        });
    },
    roundabout:function(){
        $('#myroundabout').roundabout({
            minScale: 0.8,
            autoplay: true,
            autoplayDuration: 3000,
            autoplayPauseOnHover: true,
            shape: 'lazySusan',
            minOpacity: 1,
            dropDuration: 3000
        });
    },
    remindTime:function(){
        $(".startingWrap .remTime").each(function(){
            $(this).countDown({
                omitWeeks:true,
                diffSecs:$(this).attr("diffSecs"),
                onComplete:function(sec){
                    location.reload();
                }
            });
        });
    },
    toSeckillGoodsContent:function(){
        $(".startingWrap .reskill-bt a").on("click",function(){
            var activeId = $(this).attr("activeId");
            var url = $(this).attr("url");
            //校验活动是否结束，是否还有剩余数量，如果有则跳转到商品详细，若活动已经结束 则提示用户
            $.ajax({
                url:"./secKill/checkSecKill.jhtml",
                type:"get",
                data:{
                    id:activeId
                },
                dataType:"json",
                success:function(data){
                    if(data.type == "success"){
                        //重定向到商品详情
                        window.location.href = url;
                    }else{
                        //提示用户活动结束 并刷新页面
                        layer.msglayer.msg(data.content , {icon: 2});

                        setTimeout(function() {
                            window.location.href = href;
                        }, 2000);
                    }
                }
            });
        });
    },
    search:function(){
        $(".search-bt").on("click",function(){
            var url = $(this).attr("url");
            var keywords = $(this).closest(".input-bg").find(".keywords_input").val().trim();
            if(""!=keywords && "undefined"!=typeof(keywords)){
                url =url +"?keyword=" + keywords;
                window.location.href=url;
            }
        });


    },
    showSearch:function(){
        //显示 搜索框
        $(".search-header").css("top" , "40px").css("opacity" , "1");
    },
    search:function(){
        //搜索操作
        $(".search-bt").on("click",function(){
            var url = $(this).attr("url");
            var keywords = $("#keywords_input").val().trim();
            if(""!=keywords && "undefined"!=typeof(keywords)){
                url =url +"?keyword=" + keywords;
                window.location.href=url;
            }
        });
    },
    initFullScreen:function( ){
        slider = $.fn.fsvs({
            speed : 1000,
            bodyID : 'fsvs-body',
            selector : '> .slide',
            mouseSwipeDisance : 40,
            afterSlide : function(){},
            beforeSlide : function(){},
            endSlide : function(){},
            mouseWheelEvents : true,
            mouseWheelDelay : false,
            scrollabelArea : 'scrollable',
            mouseDragEvents : true,
            touchEvents : true,
            arrowKeyEvents : true,
            pagination : true,
            nthClasses : false,
            detectHash : true
        });
    },
    productClick:function(){
        $(".nav-bottom").on("click" , "#product" , function(){
            $("#products-content").show();
            $(".myproduct").css("top" , "150px");
            index_opt.showSearch();
            index_opt.initFullScreen();
        });
    },
    navClick:function(){
        $(".nav-bottom").on("click" , "li" , function(){
           if( $(this).hasClass("nav-item") ){
               return false;
           }else{
               $(".nav-item").removeClass("nav-item");
               $(this).addClass("nav-item");
           }
        });
    },
    navHover:function(){
        $(".nav-bottom").on("mouseover" , "li" ,function(event){
            //鼠标浮动效果
            if( $(this).hasClass("nav-item") ){
                return false;
            }
            $(this).addClass("nav-item-hover");
        }).on("mouseout" , "li" , function(){
            $(this).removeClass("nav-item-hover");
        });
    }

};
