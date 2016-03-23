/**
 * 炫酷神器页面
 */
$(function(){
	goodsCool.init();
});
var goodsCool = {
		init : function(){
			this.toIndex();
			this.search();
		},
		toIndex:function(){
			$(".toindex .downicon").on("click",function(){
				window.open(prefix+"/index.jhtml","_self");
				return false;
			});
		},
		search:function(){
			$(".search-bt").on("click",function(){
				var url = $(this).attr("url");
				var keywords = $("#keywords_input").val().trim();
				if(""!=keywords && "undefined"!=typeof(keywords)){
					url =url +"?keyword=" + keywords;
					window.location.href=url;
				}
			});
		}
};
