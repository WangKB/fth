package com.puyuntech.flowerToHome.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.puyuntech.flowerToHome.ExcelView;
import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Order;
import com.puyuntech.flowerToHome.entity.Order.FromType;
import com.puyuntech.flowerToHome.service.AdminService;
import com.puyuntech.flowerToHome.service.MemberService;
import com.puyuntech.flowerToHome.service.OrderItemService;
import com.puyuntech.flowerToHome.service.OrderService;
import com.puyuntech.flowerToHome.service.OrganizationService;

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
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "organizationServiceImpl")
	private OrganizationService organizationrService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "orderItemServiceImpl")
	private OrderItemService orderItemService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable) {

		if("memberTel".equals(pageable.getSearchProperty())){
			model.addAttribute("page", orderService.pageByTel(pageable));
		}else{
			model.addAttribute("page", orderService.findPage(pageable));
		}
		
		/**
		 * 返回模板位置
		 */
		return "/admin/order/list";
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(ModelMap model,Pageable pageable, Order.Status status, Integer shopId, String memberTel, 
			String province,String city, String distract, Integer isBlance, FromType fromType) {

		model.addAttribute("beginDate",pageable.getBeginDate());
		model.addAttribute("endDate",pageable.getEndDate());
		model.addAttribute("statusBefore",status);
		model.addAttribute("shopBefore",shopId);
		model.addAttribute("memberTel",memberTel);
		model.addAttribute("province",province);
		model.addAttribute("city",city);
		model.addAttribute("distract",distract);
		model.addAttribute("isBlance",isBlance);
		model.addAttribute("fromTypeBefore",fromType);
		
		model.addAttribute("statuses",Order.Status.values());
		model.addAttribute("fromTypes",Order.FromType.values());
		model.addAttribute("shops",organizationrService.findAll());
		model.addAttribute("page", orderService.report(pageable, status, shopId, memberTel,
				province, city, distract, isBlance, fromType));
		/**
		 * 返回模板位置
		 */
		return "/admin/order/report";
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
	
	@RequestMapping(value = "/excelReport", method = RequestMethod.GET)
	public ModelAndView excelReport(Pageable pageable, Order.Status status, Integer shopId, String memberTel, 
			String province,String city, String distract, Integer isBlance, FromType fromType, ModelMap model) {
		
		pageable.setPageSize(1000);
		List<Order> data = orderService.report(pageable, status, shopId, memberTel,
				province, city, distract, isBlance, fromType).getContent();
		String filename = "order_" + DateFormatUtils.format(new Date(), "yyyyMM") + ".xls";
		String[] contents = new String[2];
		contents[0] = "操作人" + ": " + adminService.getCurrent().getName();
		contents[1] = "日期" + ": " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		return new ModelAndView(new ExcelView(filename, null, 
				new String[] { "sn","createDate","status","addrCity","shopId","memberId","amount","promotionDiscount","amountPaid","isBalance","remark" },
				new String[] { "编号","创建时间","状态","市","商铺id","会员id","金额","促销金额","付款金额","是否结算","备注" }, 
				null, null, data, contents), model);
	}
}
