
package com.puyuntech.flowerToHome.service;

import java.awt.image.BufferedImage;

import com.puyuntech.flowerToHome.Setting;

/***
 * 
 * @ClassName: CaptchaService
 * @Description: Service - 验证码 
 * @date: 2015-8-12 上午10:11:31 
 * @author: 王凯斌
 */
public interface CaptchaService {

	/***
	 * 
	 * @Description: 生成验证码图片
	 * @author:王凯斌
	 * @date: 2015-8-12 上午10:11:47 
	 * @modify: 修改记录
	 * @param captchaId
	 * 				验证ID
	 * @return 验证码图片
	 */
	BufferedImage buildImage(String captchaId);

	/***
	 * 
	 * @Description: 验证码验证
	 * @author:王凯斌
	 * @date: 2015-8-12 上午10:12:16 
	 * @modify: 修改记录
	 * @param captchaType 验证码类型
	 * @param captchaId 验证ID
	 * @param captcha 验证码(忽略大小写)
	 * @return 验证码验证是否通过
	 */
	boolean isValid(Setting.CaptchaType captchaType, String captchaId, String captcha);

}