package com.puyuntech.flowerToHome.controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.SpecificationValue;
import com.puyuntech.flowerToHome.entity.TagDetail;
import com.puyuntech.flowerToHome.service.ProductService;
import com.puyuntech.flowerToHome.service.SpecificationValueService;
import com.puyuntech.flowerToHome.service.TagDetailService;
import com.puyuntech.flowerToHome.service.TagService;
import com.puyuntech.flowerToHome.util.JsonUtils;

/**
 *
 * Controller - Tag. Created on 2015-11-10 上午11:05:48
 *
 * @author 王凯斌
 */
@Controller("adminTagDetailController")
@RequestMapping("/admin/tagDetail")
public class TagDetailController extends BaseController {

    /**
     * 写入service对象
     */
    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    
	@Resource(name = "tagDetailServiceImpl")
	private TagDetailService tagDetailService;
    
    @Resource(name = "specificationValueServiceImpl")
	private SpecificationValueService specificationValueService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {

        /**
         * 将数据写入数据模型
         */
        model.addAttribute("page", tagDetailService.findPage(pageable));
        return "/admin/tagdetail/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("tags",tagService.findAll());
    	String[] specs = {"targetSpecs","flowerSpecs","sortSpecs","designSpecs","colorSpecs","seriesSpecs","themeSpecs"};
    	SpecificationValue.Specification[] specTypes = {SpecificationValue.Specification.Target,SpecificationValue.Specification.Flower,SpecificationValue.Specification.Sort,
    			SpecificationValue.Specification.Design,SpecificationValue.Specification.Color,SpecificationValue.Specification.Series,SpecificationValue.Specification.Theme};
    	List<Filter> filters  = new ArrayList<Filter>();
    	
    	for(int i=0;i<specs.length;i++){
    		filters.clear();
    		filters.add(Filter.eq("specification", specTypes[i]));
    		model.addAttribute(specs[i],specificationValueService.findList(null, filters, null));
    	}
    	
    	
        return "/admin/tagdetail/add";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id,ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("tags",tagService.findAll());
    	model.addAttribute("tagsDetail",tagDetailService.find(id));
    	String[] specs = {"targetSpecs","flowerSpecs","sortSpecs","designSpecs","colorSpecs","seriesSpecs","themeSpecs"};
    	SpecificationValue.Specification[] specTypes = {SpecificationValue.Specification.Target,SpecificationValue.Specification.Flower,SpecificationValue.Specification.Sort,
    			SpecificationValue.Specification.Design,SpecificationValue.Specification.Color,SpecificationValue.Specification.Series,SpecificationValue.Specification.Theme};
    	List<Filter> filters  = new ArrayList<Filter>();
    	
    	for(int i=0;i<specs.length;i++){
    		filters.clear();
    		filters.add(Filter.eq("specification", specTypes[i]));
    		model.addAttribute(specs[i],specificationValueService.findList(null, filters, null));
    	}
    	
    	Set<Integer> specTargetIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecTargetId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecTargetId().split(",");
    		for(String str:idStr){
    			specTargetIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specTargetIds",specTargetIds);
    	
    	Set<Integer> specFlowertIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecFlowertId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecFlowertId().split(",");
    		for(String str:idStr){
    			specFlowertIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specFlowertIds",specFlowertIds);
    	
    	Set<Integer> specSorttIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecSorttId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecSorttId().split(",");
    		for(String str:idStr){
    			specSorttIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specSorttIds",specSorttIds);
    	
    	Set<Integer> specDesigntIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecDesigntId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecDesigntId().split(",");
    		for(String str:idStr){
    			specDesigntIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specDesigntIds",specDesigntIds);
    	
    	Set<Integer> specColortIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecColortId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecColortId().split(",");
    		for(String str:idStr){
    			specColortIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specColortIds",specColortIds);
    	
    	Set<Integer> specSeriestIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecSeriestId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecSeriestId().split(",");
    		for(String str:idStr){
    			specSeriestIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specSeriestIds",specSeriestIds);
    	
    	Set<Integer> specThemetIds = new HashSet<Integer>();
    	if(tagDetailService.find(id).getSpecThemetId()!=null){
    		String[] idStr = tagDetailService.find(id).getSpecThemetId().split(",");
    		for(String str:idStr){
    			specThemetIds.add(Integer.valueOf(str));
    		}
    	}
    	model.addAttribute("specThemetIds",specThemetIds);
    	
        return "/admin/tagdetail/edit";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(TagDetail tagDetail,Integer[] specTargetIds,Integer[] specFlowertIds,
    		Integer[] specSorttIds,Integer[] specDesigntIds,Integer[] specColortIds,
    		Integer[] specSeriestIds,Integer[] specThemetIds ) {

    	String specTargetId = null;
    	if(specTargetIds!=null&&specTargetIds.length!=0){
	    	specTargetId = JsonUtils.toJson(specTargetIds);
	    	specTargetId = specTargetId.substring(1, specTargetId.length()-1);
    	}
    	tagDetail.setSpecTargetId(specTargetId);
    	
    	String specFlowertId = null;
    	if(specFlowertIds!=null&&specFlowertIds.length!=0){
    		specFlowertId = JsonUtils.toJson(specFlowertIds);
    		specFlowertId = specFlowertId.substring(1, specFlowertId.length()-1);
    	}
    	tagDetail.setSpecFlowertId(specFlowertId);
    	
    	String specSorttId = null;
    	if(specSorttIds!=null&&specSorttIds.length!=0){
    		specSorttId = JsonUtils.toJson(specSorttIds);
    		specSorttId = specSorttId.substring(1, specSorttId.length()-1);
    	}
    	tagDetail.setSpecSorttId(specSorttId);
    	
    	String specDesigntId = null;
    	if(specDesigntIds!=null&&specDesigntIds.length!=0){
    		specDesigntId = JsonUtils.toJson(specDesigntIds);
    		specDesigntId = specDesigntId.substring(1, specDesigntId.length()-1);
    	}
    	tagDetail.setSpecDesigntId(specDesigntId);
    	
    	String specColortId = null;
    	if(specColortIds!=null&&specColortIds.length!=0){
    		specColortId = JsonUtils.toJson(specColortIds);
    		specColortId = specColortId.substring(1, specColortId.length()-1);
    	}
    	tagDetail.setSpecColortId(specColortId);
    	
    	String specSeriestId = null;
    	if(specSeriestIds!=null&&specSeriestIds.length!=0){
    		specSeriestId = JsonUtils.toJson(specSeriestIds);
    		specSeriestId = specSeriestId.substring(1, specSeriestId.length()-1);
    	}
    	tagDetail.setSpecSeriestId(specSeriestId);
    	
    	String specThemetId = null;
    	if(specThemetIds!=null&&specThemetIds.length!=0){
    		specThemetId = JsonUtils.toJson(specThemetIds);
    		specThemetId = specThemetId.substring(1, specThemetId.length()-1);
    	}
    	tagDetail.setSpecThemetId(specThemetId);
    	
    	tagDetailService.save(tagDetail);
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(TagDetail tagDetail,Integer[] specTargetIds,Integer[] specFlowertIds,
    		Integer[] specSorttIds,Integer[] specDesigntIds,Integer[] specColortIds,
    		Integer[] specSeriestIds,Integer[] specThemetIds ) {

    	String specTargetId = null;
    	if(specTargetIds!=null&&specTargetIds.length!=0){
	    	specTargetId = JsonUtils.toJson(specTargetIds);
	    	specTargetId = specTargetId.substring(1, specTargetId.length()-1);
    	}
    	tagDetail.setSpecTargetId(specTargetId);
    	
    	String specFlowertId = null;
    	if(specFlowertIds!=null&&specFlowertIds.length!=0){
    		specFlowertId = JsonUtils.toJson(specFlowertIds);
    		specFlowertId = specFlowertId.substring(1, specFlowertId.length()-1);
    	}
    	tagDetail.setSpecFlowertId(specFlowertId);
    	
    	String specSorttId = null;
    	if(specSorttIds!=null&&specSorttIds.length!=0){
    		specSorttId = JsonUtils.toJson(specSorttIds);
    		specSorttId = specSorttId.substring(1, specSorttId.length()-1);
    	}
    	tagDetail.setSpecSorttId(specSorttId);
    	
    	String specDesigntId = null;
    	if(specDesigntIds!=null&&specDesigntIds.length!=0){
    		specDesigntId = JsonUtils.toJson(specDesigntIds);
    		specDesigntId = specDesigntId.substring(1, specDesigntId.length()-1);
    	}
    	tagDetail.setSpecDesigntId(specDesigntId);
    	
    	String specColortId = null;
    	if(specColortIds!=null&&specColortIds.length!=0){
    		specColortId = JsonUtils.toJson(specColortIds);
    		specColortId = specColortId.substring(1, specColortId.length()-1);
    	}
    	tagDetail.setSpecColortId(specColortId);
    	
    	String specSeriestId = null;
    	if(specSeriestIds!=null&&specSeriestIds.length!=0){
    		specSeriestId = JsonUtils.toJson(specSeriestIds);
    		specSeriestId = specSeriestId.substring(1, specSeriestId.length()-1);
    	}
    	tagDetail.setSpecSeriestId(specSeriestId);
    	
    	String specThemetId = null;
    	if(specThemetIds!=null&&specThemetIds.length!=0){
    		specThemetId = JsonUtils.toJson(specThemetIds);
    		specThemetId = specThemetId.substring(1, specThemetId.length()-1);
    	}
    	tagDetail.setSpecThemetId(specThemetId);
    	
    	tagDetailService.update(tagDetail);
        return "redirect:list.jhtml";
    }
    
}