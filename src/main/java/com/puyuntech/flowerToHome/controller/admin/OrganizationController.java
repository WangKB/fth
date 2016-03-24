package com.puyuntech.flowerToHome.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.Organization;
import com.puyuntech.flowerToHome.entity.ShopAudit;
import com.puyuntech.flowerToHome.service.AreaService;
import com.puyuntech.flowerToHome.service.OrganizationService;
import com.puyuntech.flowerToHome.service.ShopAuditService;

/**
 *
 * Controller - 门店管理. Created on 2015-11-10 上午11:05:48
 *
 * @author 王凯斌
 */
@Controller("adminOrganizationController")
@RequestMapping("/admin/organization")
public class OrganizationController extends BaseController {

    /**
     * 写入service对象
     */
    @Resource(name = "organizationServiceImpl")
    private OrganizationService organizationService;
    
    @Resource(name = "shopAuditServiceImpl")
    private ShopAuditService shopAuditService;

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    /**
     *
     * 进入列表页面.
     *
     * author:吴玉章 date: 2015-11-10 上午11:13:00
     *
     * @param model
     *            模型数据
     * @return 模板位置
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {

        /**
         * 将数据写入数据模型
         */
        model.addAttribute("page", organizationService.findPage(pageable));
        return "/admin/organization/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("levels",Organization.Level.values());
        return "/admin/organization/add";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id,ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("levels",Organization.Level.values());
    	model.addAttribute("organization",organizationService.find(id));
        return "/admin/organization/edit";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Organization organization) {

    	organizationService.save(organization);
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Organization organization) {

    	organizationService.update(organization,"status","nextRollDate","lastPaymentDate");
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
	public @ResponseBody Message reject(Integer id){
		
    	ShopAudit shopAudit = shopAuditService.find(id);
		shopAudit.setApplicationState(ShopAudit.state.Rejected);
		shopAuditService.update(shopAudit);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(Integer id,Integer actType,String auditMemo){
		
		shopAuditService.check(shopAuditService.find(id), actType,auditMemo);
		return "redirect:examine.jhtml";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Integer id,ModelMap model) {
		
		model.addAttribute("organization", shopAuditService.find(id));
		model.addAttribute("levels",Organization.Level.values());
		return "/admin/organization/view";
	}
	
	@RequestMapping(value = "/examine", method = RequestMethod.GET)
	public String examine(ModelMap model,Pageable pageable) {

		model.addAttribute("page", shopAuditService.findPage(pageable));
		return "/admin/organization/examine";
	}
}