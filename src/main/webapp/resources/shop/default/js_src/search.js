$(function(){
	//初始化 事件绑定
	opt.init();
	
});


/**
 * 绑定 js 事件操作
 */
var opt = {
		init : function(){
			this.submit();
			this.prevPage();
			this.nextPage();
			this.serach();
		},
		prevPage:function(){
			//上一页
			$("#prevPage").on("click",function(){
				var pageNumer = $(this).attr("pageNum");
				$("#pageNumber").val(pageNumer);
				$("#goodsForm").submit();
				return false;
			});
		},
		nextPage:function(){
			//下一页
			$("#nextPage").on("click",function(){
				var pageNumer = $(this).attr("pageNum");
				$("#pageNumber").val(pageNumer);
				$("#goodsForm").submit();
				return false;
			});
		},
		submit:function(){
			//下一页内容  事件提交
			$("#goodsForm").submit(function() {
                window.location.href = location.pathname;
			});
		},
		serach:function(){
		    $(".search-bt").on("click",function(){
		    	var url = $(this).attr("url");
				var keywords = $("#keywords_input").val().trim();
				if(""!=keywords && "undefined"!=typeof(keywords)){
					url =url +"?keyword=" + encodeURIComponent(keywords);
					window.location.href=url;
				}
		    });
		}
};
