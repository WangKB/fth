
package com.puyuntech.flowerToHome.template.method;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.util.FreeMarkerUtils;
import com.puyuntech.flowerToHome.util.SystemUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 
 * @ClassName: CurrencyMethod
 * @Description: 模板方法 - 货币格式化
 * @date: 2015-8-12 上午10:40:43 
 * @author: 王凯斌
 *  applicationContext.xml 文件
 */
@Component("currencyMethod")
public class CurrencyMethod implements TemplateMethodModelEx {

	/**
	 * 执行
	 * 
	 * @param arguments
	 *            参数
	 * @return 结果
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		BigDecimal amount = FreeMarkerUtils.getArgument(0, BigDecimal.class, arguments);
		Boolean showSign = FreeMarkerUtils.getArgument(1, Boolean.class, arguments);
		Boolean showUnit = FreeMarkerUtils.getArgument(2, Boolean.class, arguments);
		if (amount != null) {
			Setting setting = SystemUtils.getSetting();
			String price = setting.setScale(amount).toString();
			if (showSign != null && showSign) {
				price = setting.getCurrencySign() + price;
			}
			if (showUnit != null && showUnit) {
				price += setting.getCurrencyUnit();
			}
			return new SimpleScalar(price);
		}
		return null;
	}

}