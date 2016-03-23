
package com.puyuntech.flowerToHome.template.method;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.puyuntech.flowerToHome.service.OrganizationService;
import com.puyuntech.flowerToHome.util.FreeMarkerUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @ClassName: ShopNameMethod
 * @Description: 模板方法 - 多语言 
 * @date: 2015-8-12 上午10:37:19 
 * @author: 王凯斌
 *  applicationContext.xml 文件
 */
@Component("shopNameMethod")
public class ShopNameMethod implements TemplateMethodModelEx {
	
	@Resource(name = "organizationServiceImpl")
	private OrganizationService organizationService;

	/**
	 * 执行
	 * 
	 * @param arguments
	 *            参数
	 * @return 结果
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		Integer id = FreeMarkerUtils.getArgument(0, Integer.class, arguments);
		if (organizationService.find(id)!=null) {
			return organizationService.find(id).getName();
		}
		return null;
	}

}