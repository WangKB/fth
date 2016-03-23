var opt={
	init:function(){
		this.changeAddress();
	}
};

function initEditAddr(id){
	var $areaId = $("#"+id);
	// 地区选择
	$areaId.lSelect({
		url: $areaId.attr("url")
	});
}



