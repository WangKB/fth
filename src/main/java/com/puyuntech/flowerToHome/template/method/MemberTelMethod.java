
package com.puyuntech.flowerToHome.template.method;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.puyuntech.flowerToHome.service.MemberService;
import com.puyuntech.flowerToHome.service.TagService;
import com.puyuntech.flowerToHome.util.FreeMarkerUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @date: 2015-8-12 上午10:37:19 
 * @author: 王凯斌
 *  applicationContext.xml 文件
 */
@Component("memberTel")
public class MemberTelMethod implements TemplateMethodModelEx {
	
	@Resource(name = "memberServiceImpl")
    private MemberService memberService;

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
		if (memberService.find(id)!=null) {
			return memberService.find(id).getPhone();
		}
		return null;
	}

}