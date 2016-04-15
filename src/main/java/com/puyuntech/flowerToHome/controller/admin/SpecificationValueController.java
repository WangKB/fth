package com.puyuntech.flowerToHome.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.SpecificationValue;
import com.puyuntech.flowerToHome.service.SpecificationValueService;

/**
 * 
 * Controller - 邮件模板. Created on 2015-10-13 下午7:51:10
 * 
 * @author 王凯斌
 */
@Controller("adminSpecificationValueController")
@RequestMapping("/admin/specificationValue")
public class SpecificationValueController extends BaseController {
	
	@Resource(name = "specificationValueServiceImpl")
	private SpecificationValueService specificationValueService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Pageable pageable,ModelMap model){
		
		model.addAttribute("page",specificationValueService.findPage(pageable));
		return "/admin/specificationValue/list";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(ModelMap model){
		
		model.addAttribute("types", SpecificationValue.Specification.values());
		return "/admin/specificationValue/add";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(SpecificationValue specificationValue){
		
		specificationValueService.save(specificationValue);
		return "redirect:list.jhtml";
	}
	
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public String edit(Integer id,ModelMap model){
		
		model.addAttribute("specificationValue", specificationValueService.find(id));
		model.addAttribute("types", SpecificationValue.Specification.values());
		return "/admin/specificationValue/edit";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(SpecificationValue specificationValue){
		
		specificationValueService.update(specificationValue);
		return "redirect:list.jhtml";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Integer[] ids) {

		specificationValueService.delete(ids);
		return SUCCESS_MESSAGE;
	}
	
	@RequestMapping(value = "/checkValue", method = RequestMethod.GET)
	public @ResponseBody
	Boolean checkValue(String value,SpecificationValue.Specification type,Integer id) {

		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("specificationValue", value));
		filters.add(Filter.eq("specification", type));
		Integer size = specificationValueService.findList(null, filters, null).size();
		List<Integer> ids = new ArrayList<Integer>();
		for(SpecificationValue specificationValue:specificationValueService.findList(null, filters, null)){
			ids.add(specificationValue.getId());
		}
		
		if(ids.contains(id)){
			return size>1?false:true;
		}
		return size>0?false:true;
	}
}