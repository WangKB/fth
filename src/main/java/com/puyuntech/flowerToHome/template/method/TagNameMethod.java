
package com.puyuntech.flowerToHome.template.method;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.puyuntech.flowerToHome.service.TagService;
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
@Component("tagName")
public class TagNameMethod implements TemplateMethodModelEx {
	
	@Resource(name = "tagServiceImpl")
    private TagService tagService;

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
		if (tagService.find(id)!=null) {
			return tagService.find(id).getName();
		}
		return null;
	}

}