
package com.puyuntech.flowerToHome.template.method;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.entity.TagDetail;
import com.puyuntech.flowerToHome.service.TagDetailService;
import com.puyuntech.flowerToHome.service.TagService;
import com.puyuntech.flowerToHome.util.FreeMarkerUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @ClassName: TagDetailName 
 * @date: 2015-8-12 上午10:37:19 
 * @author: 王凯斌
 *  applicationContext.xml 文件
 */
@Component("tagDetailName")
public class TagDetailName implements TemplateMethodModelEx {
	
	@Resource(name = "tagServiceImpl")
    private TagService tagService;
    
	@Resource(name = "tagDetailServiceImpl")
	private TagDetailService tagDetailService;

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
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("tagsId", id));
		List<TagDetail> tagDetails = tagDetailService.findList(null, filters, null);
		if(tagDetails!=null&&tagDetails.size()!=0){
			return tagDetails.get(0).getName();
		}
		return null;
	}

}