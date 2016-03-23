package com.puyuntech.flowerToHome.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.util.SystemUtils;


/***
 * 
 * @ClassName: SiteStatusFilter
 * @Description:   Filter - 网站状态
 * 							过滤器， 所有前端url访问的时候都需要判断网站是否开启，若网站未开启则重定向到网站关闭的页面。运维端 除外
 * @date: 2015-8-12 上午9:54:21 
 * @author: 王凯斌
 *	web.xml 文件中
 */
@Component("siteStatusFilter")
public class SiteStatusFilter extends OncePerRequestFilter {

	/** 默认忽略URL */
	private static final String[] DEFAULT_IGNORE_URL_PATTERNS = new String[] { "/admin/**" };

	/** 默认重定向URL */
	private static final String DEFAULT_REDIRECT_URL = "/common/site_close.jhtml";

	/** AntPathMatcher */
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	/** 忽略URL */
	private String[] ignoreUrlPatterns = DEFAULT_IGNORE_URL_PATTERNS;

	/** 重定向URL */
	private String redirectUrl = DEFAULT_REDIRECT_URL;

	/**
	 * 执行
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletRequest
	 * @param filterChain
	 *            FilterChain
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Setting setting = SystemUtils.getSetting();
		if (setting.getIsSiteEnabled()) {
			filterChain.doFilter(request, response);
		} else {
			String path = request.getServletPath();
			if (path.equals(redirectUrl)) {
				filterChain.doFilter(request, response);
				return;
			}
			if (ignoreUrlPatterns != null) {
				for (String ignoreUrlPattern : ignoreUrlPatterns) {
					if (antPathMatcher.match(ignoreUrlPattern, path)) {
						filterChain.doFilter(request, response);
						return;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + redirectUrl);
		}
	}

	/**
	 * 获取忽略URL
	 * 
	 * @return 忽略URL
	 */
	public String[] getIgnoreUrlPatterns() {
		return ignoreUrlPatterns;
	}

	/**
	 * 设置忽略URL
	 * 
	 * @param ignoreUrlPatterns
	 *            忽略URL
	 */
	public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns) {
		this.ignoreUrlPatterns = ignoreUrlPatterns;
	}

	/**
	 * 获取重定向URL
	 * 
	 * @return 重定向URL
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * 设置重定向URL
	 * 
	 * @param redirectUrl
	 *            重定向URL
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}