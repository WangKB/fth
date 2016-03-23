function map(longitude,latitude , orgName){
	//加载地图
	layer.open({
	    type: 2,
	    title: '门店地图',
        scrollbar:false,
        area: ['680px', '465px'],
	    content: "../drugstore/map.jhtml?longitude="+longitude+"&latitude="+latitude+"&orgName='"+orgName+"'" //iframe的url
	});
	
}