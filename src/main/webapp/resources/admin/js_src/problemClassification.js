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
		},
		del:function(){
			$(".list").on("click",".delete",function(){
				var $this = $(this);
				var url = $this.attr("url");
				var id = $this.attr("value");
				
				layer.confirm('是否删除该分类?', {icon: 3, title:'提示'}, function(index){
					$.ajax({
						url: url ,
						type:"post",
						data:{
							id:id
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
				    layer.close(index);
				});
				
		
			});
		},
};