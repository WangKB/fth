package com.puyuntech.flowerToHome.enmu;

/**
 * 
 * 货品类型 枚举 【tag标签】 . 
 * Created on 2015-9-2 下午5:12:53 
 * @author 王凯斌
 */
public enum GoodsTagType {
	COOLGOODS("1","炫酷神器"),
	LIMITGOODS("2","限购神器"),
	SECKILLGOODS("3","秒杀神器")
	;
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private GoodsTagType(String id, String name) {
		this.id = id;
		this.name = name;
	}

}
