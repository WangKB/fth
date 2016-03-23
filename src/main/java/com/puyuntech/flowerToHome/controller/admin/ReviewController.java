package com.puyuntech.flowerToHome.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.service.CacheService;
import com.puyuntech.flowerToHome.util.SystemUtils;

/**
 * 
 * Controller - 邮件模板. Created on 2015-10-13 下午7:51:10
 * 
 * @author 王凯斌
 */
@Controller("adminReviewController")
@RequestMapping("/admin/Review")
public class ReviewController extends BaseController {
	
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(ModelMap model){
		
		model.addAttribute("reviewOne",SystemUtils.getSetting().getReviewOne());
		model.addAttribute("reviewTwo",SystemUtils.getSetting().getReviewTwo());
		model.addAttribute("reviewThree",SystemUtils.getSetting().getReviewThree());
		return "/admin/review/list";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(String reviewOne,String reviewTwo,String reviewThree){
		
		Setting preSetting =SystemUtils.getSetting();
		
		preSetting.setReviewOne(reviewOne);
		preSetting.setReviewTwo(reviewTwo);
		preSetting.setReviewThree(reviewThree);
		
		SystemUtils.setSetting(preSetting);
		cacheService.clear();
		return "redirect:list.jhtml";
	}
}