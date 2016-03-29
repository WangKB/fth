package com.puyuntech.flowerToHome.controller.admin;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.Product;
import com.puyuntech.flowerToHome.entity.ProductChangeLog;
import com.puyuntech.flowerToHome.service.AdminService;
import com.puyuntech.flowerToHome.service.ProductChangeLogService;
import com.puyuntech.flowerToHome.service.ProductService;
import com.puyuntech.flowerToHome.service.SpecificationValueService;

/**
 * 
 * Controller - 货品. Created on 2015-10-14 上午10:19:07
 * 
 * @author 王凯斌
 */
@Controller("adminGoodsController")
@RequestMapping("/admin/goods")
public class GoodsController extends BaseController {

	/**
	 * 引入service层对象
	 */
	@Resource(name = "productServiceImpl")
	private ProductService productService;
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	@Resource(name = "productChangeLogServiceImpl")
	private ProductChangeLogService productChangeLogService;
	
	@Resource(name = "specificationValueServiceImpl")
	private SpecificationValueService specificationValueService;

	/**
	 * 
	 * 保存商品.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午10:19:42
	 * 
	 * @param product
	 *            表单传入商品对象
	 * @return 模板位置
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Product product) {

		productService.save(product);
		return "redirect:list.jhtml";
	}

	/**
	 * 
	 * 进入列表页面.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午10:24:47
	 * 
	 * @param pageable
	 *            分页信息
	 * @param model
	 *            数据模型
	 * @return 模板位置
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable) {

		model.addAttribute("page", productService.findPage(pageable));
		/**
		 * 返回模板位置
		 */
		return "/admin/goods/list";
	}
	
	@RequestMapping(value = "/examine", method = RequestMethod.GET)
	public String examine(ModelMap model,Pageable pageable) {

		model.addAttribute("page", productChangeLogService.findPage(pageable));
		return "/admin/goods/examine";
	}


	/**
	 * 
	 * 删除.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午10:39:07
	 * 
	 * @param ids
	 *            商品id
	 * @return 操作信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Integer[] ids) {

		/**
		 * 删除商品
		 */
		productService.delete(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 
	 * 进入添加页面.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午10:39:47
	 * 
	 * @param model
	 *            数据模型
	 * @return 模板位置
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {

		model.addAttribute("specs", specificationValueService.findAll());
		return "/admin/goods/add";
	}

	/**
	 * 
	 * 进入编辑页面.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午2:47:50
	 * @param id 商品id
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id,ModelMap model) {
		
		model.addAttribute("goods", productService.find(id));
		model.addAttribute("specs", specificationValueService.findAll());
		return "/admin/goods/edit";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Integer id,ModelMap model) {
		
		model.addAttribute("goods", productChangeLogService.find(id));
		model.addAttribute("specs", specificationValueService.findAll());
		model.addAttribute("auditAdmin1",adminService.find(productChangeLogService.find(id).getAuditAdmin1()));
		model.addAttribute("auditAdmin2",adminService.find(productChangeLogService.find(id).getAuditAdmin2()));
		return "/admin/goods/view";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Product product){
		
		productService.update(product,"sn","buytimes");
		return "redirect:list.jhtml";
	}

	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public @ResponseBody Message reject(Integer id,String auditMemo){
		
		ProductChangeLog productChangeLog = productChangeLogService.find(id);
		if(productChangeLog.getApplicationState().equals(ProductChangeLog.state.ApplyingOne)){
			productChangeLog.setAuditAdmin1(adminService.getCurrent().getId());
			productChangeLog.setAuditMemo1(auditMemo);
			productChangeLog.setAuditDate1(new Date());
		}else if(productChangeLog.getApplicationState().equals(ProductChangeLog.state.ApplyingTwo)){
			productChangeLog.setAuditAdmin2(adminService.getCurrent().getId());
			productChangeLog.setAuditMemo2(auditMemo);
			productChangeLog.setAuditDate2(new Date());
		}
		productChangeLog.setApplicationState(ProductChangeLog.state.Rejected);
		productChangeLogService.update(productChangeLog);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(Integer id,Integer actType,String auditMemo){
		
		productChangeLogService.check(productChangeLogService.find(id), actType,auditMemo,adminService.getCurrent().getId());
		return "redirect:examine.jhtml";
	}

}
