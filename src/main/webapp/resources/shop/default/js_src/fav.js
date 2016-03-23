/**
 * 页面加载完 执行
 */
$(function(){
	timeouts = {};
	opt.init();
	
});

var opt = {
		init:function(){
			this.del();
			this.toFollow();
			this.toFollows();
			this.chooseall();
            this.clearAll();
		},
		chooseall:function(){
			var $toggle_checkboxes_up = $("#toggle-checkboxes_up");
			var $checkItem = $(".ids");
			$toggle_checkboxes_up.on("click",function(){
				if($toggle_checkboxes_up.attr("checked")){
					$checkItem.attr("checked",true);
				}else{
					$checkItem.attr("checked",false);
				}
			});
		},
		toFollows:function(){
			$(".joincart").on("click",function(){
				var url = $(".joincart").attr("url");
				var $ids = $("input[name='ids']:checked");
				$.ajax({
					url: url ,
					type:"POST",
					data:$ids.serialize(),
					dataType:"json",
					cache: false,
					success:function(data){
						if(data.type=="success"){
							location.reload(true);
                            showHeaderCartQuantity();
						}else{
							layer.msg(data.content,{icon: 2,time:2000});
						}
					}
				});
			});
		},
		del:function(){
			$(".cart-remove").click(function(){
				var $this = $(this);
				var url = $this.attr("url");
				var ids= $(this).parent().parent().parent().find("input[name='ids']").val();
				
				layer.confirm('是否删除该商品?', {icon: 3, title:'提示'}, function(index){
					$.ajax({
						url: url ,
						type:"post",
						data:{ids:ids},
						dataType:"json",
						success:function(data){
							if(data.type=="success"){
								location.reload(true);
							}else{
								layer.msg(data.content,{icon: 2,time:2000});
							}
						}
					});
				    layer.close(index);
				});
				
		
			});
		},
		toFollow:function(){
			$(".item-list").on("click",".cart-follow",function(){
				var $this = $(this);
				var url = $this.attr("url");
				var $item = $this.closest(".item-list");
				var ids = $item.find("input[name='ids']").val();
				
				$.ajax({
					url: url ,
					type:"post",
					data:{
						ids:ids
					},
					dataType:"json",
					success:function(data){
						if(data.type=="success"){
							location.reload(true);
						}else{
							layer.msg(data.content,{icon: 2,time:2000});
						}
					}
				});
			});
		},
        clearAll:function(){
        $("#clearMyFav").on("click",function(){
            layer.open({
                content:"是否清空收藏夹?",
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
    }
};