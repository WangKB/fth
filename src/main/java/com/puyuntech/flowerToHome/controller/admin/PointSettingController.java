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
 * Controller - 积分设置. Created on 2015-10-13 下午7:51:10
 * 
 * @author 王凯斌
 */
@Controller("adminPointSettingController")
@RequestMapping("/admin/pointSetting")
public class PointSettingController extends BaseController {
	
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(ModelMap model){
		
		model.addAttribute("rewardRate",SystemUtils.getSetting().getRewardRate());
		model.addAttribute("expenseRate",SystemUtils.getSetting().getExpenseRate());
		model.addAttribute("minimumAmount",SystemUtils.getSetting().getMinimumAmount());
		return "/admin/pointsetting/list";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Integer rewardRate,Integer expenseRate,Integer minimumAmount){
		
		Setting preSetting =SystemUtils.getSetting();
		
		preSetting.setRewardRate(rewardRate);
		preSetting.setExpenseRate(expenseRate);
		preSetting.setMinimumAmount(minimumAmount);
		
		SystemUtils.setSetting(preSetting);
		cacheService.clear();
		return "redirect:list.jhtml";
	}
}