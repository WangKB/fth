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
		},
		del:function(){
			$(".bar").on("click","#deleteButton",function(){
				var $this = $(this);
				var url = $this.attr("url");
				var $item = $(".list");
				var id = $item.find("input[name='ids']:enabled:checked");
				
				layer.confirm('是否删除选中商品?', {icon: 3, title:'提示'}, function(index){
					$.ajax({
						url: url ,
						type:"get",
						data:id.serialize(),
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
};