package com.puyuntech.flowerToHome.controller.admin;

import java.security.interfaces.RSAPublicKey;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.Admin;
import com.puyuntech.flowerToHome.service.AdminService;
import com.puyuntech.flowerToHome.service.RSAService;
import com.puyuntech.flowerToHome.util.SystemUtils;
import com.puyuntech.flowerToHome.util.WebUtils;

/**
 * Controller - 管理员登录.
 * 
 * Created on 2015-8-25 下午5:02:01
 * 
 * @author 王凯斌
 * 
 */
@Controller("adminLoginController")
@RequestMapping("/admin/login")
public class LoginController extends BaseController {

	/**
	 * 引入service对象
	 */
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	/**
	 * 
	 * 跳转到登录页面. author: 王凯斌 date: 2015-9-21 下午1:29:55
	 * 
	 * @param request
	 *            请求隐式对象
	 * @param model
	 *            数据模型
	 * @return 模板位置
	 */
	@RequestMapping
	public String index(HttpServletRequest request, ModelMap model) {

		/**
		 * 获得登录令牌
		 */
		String loginToken = WebUtils.getCookie(request,
				Admin.LOGIN_TOKEN_COOKIE_NAME);

		/**
		 * 判断登录令牌是否一致
		 */
		if (!StringUtils.equalsIgnoreCase(loginToken,
				adminService.getLoginToken())) {
			return "redirect:/";
		}

		/**
		 * 判断是否具有权限
		 */
		if (adminService.isAuthenticated()) {
			return "redirect:common/main.jhtml";
		}

		Message failureMessage = null;

		/**
		 * 获得错误类型
		 */
		String loginFailure = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (StringUtils.isNotEmpty(loginFailure)) {
			if (loginFailure
					.equals("com.puyuntech.ycmall.exception.IncorrectCaptchaException")) {
				failureMessage = Message.error("admin.captcha.invalid");
			} else if (loginFailure
					.equals("org.apache.shiro.authc.UnknownAccountException")) {
				failureMessage = Message.error("admin.login.unknownAccount");
			} else if (loginFailure
					.equals("org.apache.shiro.authc.DisabledAccountException")) {
				failureMessage = Message.error("admin.login.disabledAccount");
			} else if (loginFailure
					.equals("org.apache.shiro.authc.LockedAccountException")) {
				failureMessage = Message.error("admin.login.lockedAccount");
			} else if (loginFailure
					.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
				Setting setting = SystemUtils.getSetting();
				if (ArrayUtils.contains(setting.getAccountLockTypes(),
						Setting.AccountLockType.admin)) {
					failureMessage = Message.error(
							"admin.login.accountLockCount",
							setting.getAccountLockCount());
				} else {
					failureMessage = Message
							.error("admin.login.incorrectCredentials");
				}
			} else if (loginFailure
					.equals("com.puyuntech.ycmall.exception.IllegalLicenseException")) {
				failureMessage = Message.error("admin.login.incorrectLicense");
			} else if (loginFailure
					.equals("org.apache.shiro.authc.AuthenticationException")) {
				failureMessage = Message.error("admin.login.authentication");
			}
		}

		/**
		 * 添加私钥到公钥并返回公钥
		 */
		RSAPublicKey publicKey = rsaService.generateKey(request);

		/**
		 * 将数据写入模型
		 */
		model.addAttribute("modulus",
				Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
		model.addAttribute("exponent", Base64.encodeBase64String(publicKey
				.getPublicExponent().toByteArray()));
        model.addAttribute("captchaId", request.getSession().getId());
		model.addAttribute("failureMessage", failureMessage);
		return "/admin/login/index";
	}

}