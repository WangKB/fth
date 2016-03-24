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
 * Controller - 财务设置. Created on 2015-10-13 下午7:51:10
 * 
 * @author 王凯斌
 */
@Controller("adminFinRateController")
@RequestMapping("/admin/finRate")
public class FinRateController extends BaseController {
	
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(ModelMap model){
		
		model.addAttribute("platformRate",SystemUtils.getSetting().getPlatformRate());
		model.addAttribute("shopRate",SystemUtils.getSetting().getShopRate());
		model.addAttribute("supplierRate",SystemUtils.getSetting().getSupplierRate());
		return "/admin/finrate/list";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(String platformRate,String shopRate,String supplierRate){
		
		Setting preSetting =SystemUtils.getSetting();
		
		preSetting.setPlatformRate(platformRate);
		preSetting.setShopRate(shopRate);
		preSetting.setSupplierRate(supplierRate);
		
		SystemUtils.setSetting(preSetting);
		cacheService.clear();
		return "redirect:list.jhtml";
	}
}