
package com.puyuntech.flowerToHome;

/**
 * 
 * 公共参数，一些配置文件的基本路径，以及一些基本数据格式 . 
 * Created on 2015-8-14 下午4:53:38 
 * @author 王凯斌
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** flowerToHome.xml文件路径 */
	public static final String puyun_XML_PATH = "/flowerToHome.xml";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}