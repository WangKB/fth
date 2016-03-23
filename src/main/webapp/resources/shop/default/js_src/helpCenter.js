$(function(){
	$(".all-selfservice-box .lump-list").hover(
			function(){
				$(this).addClass("hover");
				},function(){
					$(this).removeClass("hover");
					});
	
	
//  $(".subside-mod .title").click(
//    function(){
//      if($(this).parent(".subside-mod").hasClass("on")){
//    	  $(this).parent(".subside-mod").removeClass("on");
//    	  }else{
//    		  $(this).parent(".subside-mod").addClass("on");
//    	  }
//    });
});

$(".subside-mod .title").unbind().on("click",function(){
	var id=$(this).find("input[name='proClassId']").attr("value");
	var $li="";
	$.ajax({
		url: "../helpCenter/findChildren.jhtml",
		type: "POST",
		data:{
			id:id,
		},
		dataType: "json",
		async:false,
		cache: true,
		success: function(data) {
			$.each(data, function(k, v){
				$li +="<dd class='subside-cnt'>" +
						"<ul class='subside-list'>" +
						"<li class='list-item' data-id='28' data-name='交易条款' data-parent-id='21' data-parent-name='购物指南'>" +
						"<a href='../helpCenter/findTitle.jhtml?id="+v.id+"&ids="+id+"' target='iframe''>"+v.name+"</a>" +
						"</li></dd>";
			});
		}
		
	});
	if($(this).parent(".subside-mod").hasClass("on")){
		$(this).parent(".subside-mod").children("dd").remove();
		$(this).parent(".subside-mod").removeClass("on");
  	  
	}else{
		$(this).parent(".subside-mod").append($li);
  		$(this).parent(".subside-mod").addClass("on");
  	}
});




$(".subside-mods .title-content").unbind().on("click",function(){
	if($(this).parent(".subside-mods").hasClass("on")){
		$(this).parent(".subside-mods").removeClass("on");
  	  
	}else{
  		$(this).parent(".subside-mods").addClass("on");
  	}
});