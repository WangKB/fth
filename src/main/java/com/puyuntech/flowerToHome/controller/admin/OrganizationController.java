package com.puyuntech.flowerToHome.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.Area;
import com.puyuntech.flowerToHome.entity.Organization;
import com.puyuntech.flowerToHome.entity.ShopAudit;
import com.puyuntech.flowerToHome.service.AdminService;
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
    
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
    
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
    	model.addAttribute("statuses",Organization.Status.values());
    	model.addAttribute("levels",Organization.Level.values());
        return "/admin/organization/add";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id,ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("statuses",Organization.Status.values());
    	model.addAttribute("levels",Organization.Level.values());
    	model.addAttribute("area",areaService.find(organizationService.find(id).getArea()));
    	model.addAttribute("organization",organizationService.find(id));
        return "/admin/organization/edit";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Organization organization) {

    	Area area = areaService.find(organization.getArea());
    	List<Area> areas = area.getParents();
    	areas.add(area);
    	for(Area areaItem :areas){
    		switch(areaItem.getGrade()){
    			case 0:
    				organization.setAddrProvince(areaItem.getName());
    				break;
    			case 1:
    				organization.setAddrCity(areaItem.getName());
    				break;
    			case 2:
    				organization.setAddrDistrict(areaItem.getName());
    				break;
    			default:
    				continue;
    			
    		}
    	}
    	organizationService.save(organization);
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Organization organization) {

    	Area area = areaService.find(organization.getArea());
    	List<Area> areas = area.getParents();
    	areas.add(area);
    	for(Area areaItem :areas){
    		switch(areaItem.getGrade()){
    			case 0:
    				organization.setAddrProvince(areaItem.getName());
    				break;
    			case 1:
    				organization.setAddrCity(areaItem.getName());
    				break;
    			case 2:
    				organization.setAddrDistrict(areaItem.getName());
    				break;
    			default:
    				continue;
    			
    		}
    	}
    	if(area.getGrade()>2){
    		switch(area.getGrade()){
				case 0:
					organization.setAddrCity("");
					organization.setAddrDistrict("");
					break;
				case 1:
					organization.setAddrDistrict("");
					break;
				
			}
    	}
    	organizationService.update(organization,"nextRollDate","lastPaymentDate","designerId");
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
	public @ResponseBody Message reject(Integer id,String auditMemo){
		
    	ShopAudit shopAudit = shopAuditService.find(id);
		if(shopAudit.getApplicationState().equals(ShopAudit.state.ApplyingOne)){
			shopAudit.setAuditAdmin1(adminService.getCurrent().getId());
			shopAudit.setAuditMemo1(auditMemo);
			shopAudit.setAuditDate1(new Date());
		}else if(shopAudit.getApplicationState().equals(ShopAudit.state.ApplyingTwo)){
			shopAudit.setAuditAdmin2(adminService.getCurrent().getId());
			shopAudit.setAuditMemo2(auditMemo);
			shopAudit.setAuditDate2(new Date());
		}
		shopAudit.setApplicationState(ShopAudit.state.Rejected);
		shopAuditService.update(shopAudit);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(Integer id,Integer actType,String auditMemo){
		
		shopAuditService.check(shopAuditService.find(id), actType,auditMemo,adminService.getCurrent().getId());
		return "redirect:examine.jhtml";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Integer id,ModelMap model) {
		
		model.addAttribute("area",areaService.find(shopAuditService.find(id).getArea()));
		model.addAttribute("organization", shopAuditService.find(id));
		model.addAttribute("organizationBefore", organizationService.find(id));
		model.addAttribute("levels",Organization.Level.values());
		model.addAttribute("auditAdmin1",adminService.find(shopAuditService.find(id).getAuditAdmin1()));
		model.addAttribute("auditAdmin2",adminService.find(shopAuditService.find(id).getAuditAdmin2()));
		return "/admin/organization/view";
	}
	
	@RequestMapping(value = "/examine", method = RequestMethod.GET)
	public String examine(ModelMap model,Pageable pageable) {

		model.addAttribute("page", shopAuditService.findPage(pageable));
		return "/admin/organization/examine";
	}
}