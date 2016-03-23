package com.puyuntech.flowerToHome;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.puyuntech.flowerToHome.entity.Admin;
import com.puyuntech.flowerToHome.exception.IncorrectCaptchaException;
import com.puyuntech.flowerToHome.service.AdminService;
import com.puyuntech.flowerToHome.service.CaptchaService;
import com.puyuntech.flowerToHome.util.SystemUtils;
/**
 * 
 * 权限认证  校验用户登录信息 -- 只用于运维端用户登录 . 
 * Created on 2015-8-14 下午4:37:06 
 * @author 王凯斌
 * 
 * applicationContext-shiro.xml  中使用
 */
public class AuthenticationRealm extends AuthorizingRealm {

  	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	/**
	 * 
	 * <p>Title: doGetAuthenticationInfo.</P>
	 * <p>获取认证信息: </P>
	 * @param token 令牌
	 * @return 认证信息
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		
		 String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		String captchaId = authenticationToken.getCaptchaId();
		String captcha = authenticationToken.getCaptcha();
		String ip = authenticationToken.getHost();

        if (!captchaService.isValid(Setting.CaptchaType.adminLogin, captchaId, captcha)) {
			throw new IncorrectCaptchaException();
		}
		if (username != null && password != null) {
			Admin admin = adminService.findByUsername(username);
			if (admin == null) {//输入的用户名不存在
				throw new UnknownAccountException();
			}
			if (admin.getIsEnabled()!=1) {//账户是否启动
				throw new DisabledAccountException();
			}
			Setting setting = SystemUtils.getSetting();
			if (admin.getIsLocked()==1) {//账户是否被锁
				if (ArrayUtils.contains(setting.getAccountLockTypes(), Setting.AccountLockType.admin)) {
					int loginFailureLockTime = setting.getAccountLockTime();
					if (loginFailureLockTime == 0) {
						throw new LockedAccountException();
					}
					Date lockedDate = admin.getLockedDate();
					Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					if (new Date().after(unlockDate)) {
						admin.setIsLocked(0);
						admin.setLockedDate(null);
						adminService.update(admin);
					} else {
						throw new LockedAccountException();
					}
				} else {
					admin.setIsLocked(0);
					admin.setLockedDate(null);
					adminService.update(admin);
				}
			}
			admin.setLoginIp(ip);
			admin.setLoginDate(new Date());
			adminService.update(admin);
			return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * 
	 * <p>Title: doGetAuthorizationInfo.</P>
	 * <p>获取授权信息 </P>
	 * @param principalCollection
	 * @return 授权信息
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		 Principal principal = (Principal) principalCollection.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = adminService.findAuthorities(principal.getId());
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}