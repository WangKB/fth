package com.puyuntech.flowerToHome.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.puyuntech.flowerToHome.entity.Area;
import com.puyuntech.flowerToHome.service.AdminService;
import com.puyuntech.flowerToHome.service.AreaService;
import com.puyuntech.flowerToHome.service.CaptchaService;
import com.puyuntech.flowerToHome.service.MemberService;

/**
 * 
 * Controller - 通用. Created on 2015-10-13 下午8:54:54
 * 
 * @author 王凯斌
 */
@Controller("adminCommonController")
@RequestMapping("/admin/common")
public class CommonController implements ServletContextAware {

	/**
	 * 引入value属性
	 */
	@Value("${system.name}")
	private String systemName;
	@Value("${system.version}")
	private String systemVersion;
	@Value("${system.description}")
	private String systemDescription;

	/**
	 * 引入service层对象
	 */
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "areaServiceImpl")
    private AreaService areaService;
	/**
	 * application隐式对象
	 */
	private ServletContext servletContext;

	/**
	 * 
	 * 构造servletContext.
	 * 
	 * author: 王凯斌 date: 2015-10-13 下午8:57:23
	 * 
	 * @param servletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logout(){
		
		return "redirect:/admin/";
		
	}
	/**
	 * 
	 * 进入主页.
	 * 
	 * author: 王凯斌 date: 2015-10-13 下午8:58:09
	 * 
	 * @return 模板位置
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(ModelMap model) {
		model.addAttribute("admin", adminService.getCurrent());
		return "/admin/common/main";
	}

	/**
	 * 
	 * 进入首页页面.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午9:11:41
	 * 
	 * @param model
	 *            前台数据模型
	 * @return 模板位置
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {

		/**
		 * 将系统数据写入前台数据模型
		 */
		
		model.addAttribute("systemName", systemName);
		model.addAttribute("systemVersion", systemVersion);
		model.addAttribute("systemDescription", systemDescription);
		model.addAttribute("javaVersion", System.getProperty("java.version"));
		model.addAttribute("javaHome", System.getProperty("java.home"));
		model.addAttribute("osName", System.getProperty("os.name"));
		model.addAttribute("osArch", System.getProperty("os.arch"));
		model.addAttribute("serverInfo", servletContext.getServerInfo());
		model.addAttribute("servletVersion", servletContext.getMajorVersion()
				+ "." + servletContext.getMinorVersion());
		model.addAttribute("memberCount", memberService.count());
		
		return "/admin/common/index";
	}

	/**
	 * 
	 * 获得三级地区.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午9:12:32
	 * 
	 * @param parentId
	 *            上级地区id
	 * @return 返回地区数据
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> area(Integer parentId) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Area parent = areaService.find(parentId);
		Collection<Area> areas = parent != null ? parent.getChildren() : areaService.findRoots();
		for (Area area : areas) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("name", area.getName());
			item.put("value", area.getId());
			data.add(item);
		}
		return data;
	}

	/**
	 * 
	 * 获得验证码.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午9:13:51
	 * 
	 * @param request
	 *            请求隐式对象
	 * @param response
	 *            回应隐式对象
	 * @throws IOException
	 *             IO异常
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void captcha(HttpServletRequest request,
			HttpServletResponse response , HttpSession session) throws IOException {

        String captchaId = session.getId();

        /**
         * 生成并输出验证码图片
         */
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		OutputStream outputStream = response.getOutputStream();
		BufferedImage bufferedImage = captchaService.buildImage(captchaId);
		ImageIO.write(bufferedImage, "jpg", outputStream);
		outputStream.flush();
	}

	/**
	 * 
	 * 进入错误提示页面.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午9:23:54
	 * 
	 * @return 模板位置
	 */
	@RequestMapping("/error")
	public String error() {
		return "/admin/common/error";
	}

	/**
	 * 
	 * 进入权限错误页面.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午9:24:15
	 * 
	 * @param request
	 *            隐式对象
	 * @param response
	 *            隐式对象
	 * @return 模板位置
	 * @throws IOException
	 *             IO异常
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		/**
		 * 判断是否为ajax请求，并且与XMLHttpRequest比较，若相同则为无权限
		 */
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null
				&& requestType.equalsIgnoreCase("XMLHttpRequest")) {
			response.addHeader("loginStatus", "unauthorized");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		return "/admin/common/unauthorized";
	}

	
}
