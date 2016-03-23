/**
 * 页面加载完 执行
 */
$(function(){
	$("#state"+$("#state").val()+"").addClass("checked");
});

/**
 * 
 * 选择抢购类型
 * @author:yanzhisen
 * @date: 2015-10-19 下午14:09:45 
 * @modify: 修改记录
 * @param id
 */
function typea(id){
	var url="../shoppingRush/index.jhtml?type="+id+"";
	$("#goodsForm").attr("action", url).submit();
}

function show(id){
	//加载红包详情
	layer.open({
	    type: 2,
	    title: '红包详情',
	    area: ['329px', '393px'],
	    content: "../shoppingRush/bonusDetail.jhtml?bonusId="+id+"" //iframe的url
	});
	
}
function zhanghu(){
	alert(1)
}