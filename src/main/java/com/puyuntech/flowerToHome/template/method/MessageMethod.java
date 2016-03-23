
package com.puyuntech.flowerToHome.template.method;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.puyuntech.flowerToHome.util.FreeMarkerUtils;
import com.puyuntech.flowerToHome.util.SpringUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;

/**
 * 
 * @ClassName: MessageMethod
 * @Description: 模板方法 - 多语言 
 * @date: 2015-8-12 上午10:37:19 
 * @author: 王凯斌
 *  applicationContext.xml 文件
 */
@Component("messageMethod")
public class MessageMethod implements TemplateMethodModelEx {

	/**
	 * 执行
	 * 
	 * @param arguments
	 *            参数
	 * @return 结果
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		String code = FreeMarkerUtils.getArgument(0, String.class, arguments);
		if (StringUtils.isNotEmpty(code)) {
			String message;
			if (arguments.size() > 1) {
				Object[] args = new Object[arguments.size() - 1];
				for (int i = 1; i < arguments.size(); i++) {
					Object argument = arguments.get(i);
					if (argument != null && argument instanceof TemplateModel) {
						args[i - 1] = DeepUnwrap.unwrap((TemplateModel) argument);
					} else {
						args[i - 1] = argument;
					}
				}
				message = SpringUtils.getMessage(code, args);
			} else {
				message = SpringUtils.getMessage(code);
			}
			return new SimpleScalar(message);
		}
		return null;
	}

}