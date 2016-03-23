package com.puyuntech.flowerToHome;


import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.puyuntech.flowerToHome.util.FreeMarkerUtils;

/**
 * 
 * FreeMarker视图解析器 . 
 * Created on 2015-8-14 下午4:59:08 
 * @author 王凯斌
 */
public class FreeMarkerViewResolver extends AbstractTemplateViewResolver {

	/**
	 * 构造方法
	 */
	public FreeMarkerViewResolver() {
		setViewClass(requiredViewClass());
	}

	/**
	 * 视图类型
	 * 
	 * @return 视图类型
	 */
	@Override
	protected Class<FreeMarkerView> requiredViewClass() {
		return FreeMarkerView.class;
	}

	/**
	 * 创建视图
	 * 
	 * @param viewName
	 *            视图名称
	 * @return 视图
	 */
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		return super.buildView(FreeMarkerUtils.process(viewName));
	}

}