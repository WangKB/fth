package com.puyuntech.flowerToHome.controller.admin;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Member;
import com.puyuntech.flowerToHome.service.MemberService;

/**
 * 
 * Controller - Member. Created on 2015-10-14 上午10:19:07
 * 
 * @author 王凯斌
 */
@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController extends BaseController {

	/**
	 * 引入service层对象
	 */
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable) {

		model.addAttribute("page", memberService.findPage(pageable));
		/**
		 * 返回模板位置
		 */
		return "/admin/member/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String view(ModelMap model,Integer id) {

		model.addAttribute("member", memberService.find(id));
		/**
		 * 返回模板位置
		 */
		return "/admin/member/edit";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {

		/**
		 * 返回模板位置
		 */
		return "/admin/member/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Member member, HttpServletRequest request) {

		member.setPassword(DigestUtils.md5Hex(member.getPassword()));
		member.setAmount(BigDecimal.ZERO);
		member.setLoginFailureCount(0);
		member.setIsLocked(0);
		member.setRegisterIp(request.getRemoteAddr());
		memberService.save(member);
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Member member) {

    	memberService.update(member,"username","password","amount","loginFailureCount","lockedDate","registerIp"
    			,"loginDate","loginPluginId","value","cartId","isLocked");
        return "redirect:list.jhtml";
    } 
    
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		return !memberService.usernameExists(username);
	}
}
