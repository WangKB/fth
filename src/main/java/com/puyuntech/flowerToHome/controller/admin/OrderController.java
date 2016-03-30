package com.puyuntech.flowerToHome.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Order;
import com.puyuntech.flowerToHome.service.OrderItemService;
import com.puyuntech.flowerToHome.service.OrderService;

/**
 * 
 * Controller - 货品. Created on 2015-10-14 上午10:19:07
 * 
 * @author 王凯斌
 */
@Controller("adminorderController")
@RequestMapping("/admin/order")
public class OrderController extends BaseController {

	/**
	 * 引入service层对象
	 */
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;
	@Resource(name = "orderItemServiceImpl")
	private OrderItemService orderItemService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable) {

		model.addAttribute("page", orderService.findPage(pageable));
		/**
		 * 返回模板位置
		 */
		return "/admin/order/list";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(ModelMap model,Integer id) {

		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("orderId", id));
		model.addAttribute("order", orderService.find(id));
		model.addAttribute("items", orderItemService.findList(null, filters, null));
		model.addAttribute("statuses", Order.Status.values());
		
		/**
		 * 返回模板位置
		 */
		return "/admin/order/view";
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	public String changeStatus(Integer orderId, Order.Status status) {

		Order order = orderService.find(orderId);
		orderService.changeStatus(order, status);
		
		/**
		 * 返回模板位置
		 */
		return "redirect:view.jhtml?id="+orderId;
	}
	
	
}
