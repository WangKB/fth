package com.puyuntech.flowerToHome.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.Area;
import com.puyuntech.flowerToHome.service.AreaService;

/**
 * 
 * Controller - 地区. 
 * Created on 2015-12-2 下午1:53:00 
 * @author 王凯斌
 */
@Controller("adminAreaController")
@RequestMapping("/admin/area")
public class AreaController extends BaseController {

	@Resource(name = "areaServiceImpl")
	private AreaService areaService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Integer parentId, ModelMap model) {
		model.addAttribute("parent", areaService.find(parentId));
		return "/admin/area/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Area area, Integer parentId, RedirectAttributes redirectAttributes) {
		
		area.setParent(areaService.find(parentId));
		if (!isValid(area)) {
			addFlashMessage(redirectAttributes, Message.error("数据格式错误"));
			return "redirect:list.jhtml";
		}
		area.setTreePath(null);
		area.setGrade(null);
		area.setChildren(null);
		areaService.save(area);
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("name", area.getName()));
		filters.add(Filter.eq("grade", area.getGrade()));
		List<Area> areas =areaService.findList(null, filters, null);
		if(areas.size()>1){
			areaService.delete(area);
			addFlashMessage(redirectAttributes, Message.error("同一层级不允许同名地区"));
			return "redirect:list.jhtml";
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, ModelMap model) {
		model.addAttribute("area", areaService.find(id));
		return "/admin/area/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Area area, RedirectAttributes redirectAttributes) {
		
		Area areaTemp=areaService.find(area.getId());
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("name", area.getName()));
		filters.add(Filter.eq("grade", areaTemp.getGrade()));
		List<Area> areas =areaService.findList(null, filters, null);
		if(areas.size()!=0){
			addFlashMessage(redirectAttributes, Message.error("同一层级不允许同名地区"));
			return "redirect:list.jhtml";
		}
		
		if (!isValid(area)) {
			addFlashMessage(redirectAttributes, Message.error("数据格式错误"));
			return "redirect:list.jhtml";
		}
		areaService.update(area, "fullName", "treePath", "grade", "parent", "children", "members", "receivers", "orders", "activity", "organizations");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer parentId, ModelMap model) {
		Area parent = areaService.find(parentId);
		if (parent != null) {
			model.addAttribute("parent", parent);
			model.addAttribute("areas", new ArrayList<Area>(parent.getChildren()));
		} else {
			model.addAttribute("areas", areaService.findRoots());
		}
		return "/admin/area/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Integer id) {
		areaService.delete(id);
		return SUCCESS_MESSAGE;
	}

}