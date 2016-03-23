package com.puyuntech.flowerToHome.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puyuntech.flowerToHome.ExcelView;
import com.puyuntech.flowerToHome.Filter;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.entity.Coupon;
import com.puyuntech.flowerToHome.entity.CouponCode;
import com.puyuntech.flowerToHome.entity.Product;
import com.puyuntech.flowerToHome.service.AdminService;
import com.puyuntech.flowerToHome.service.CouponCodeService;
import com.puyuntech.flowerToHome.service.CouponService;
import com.puyuntech.flowerToHome.service.ProductService;

/**
 * 
 * Controller - 优惠券. Created on 2015-10-14 上午11:05:48
 * 
 * @author 王凯斌
 */
@Controller("adminCouponController")
@RequestMapping("/admin/coupon")
public class CouponController extends BaseController {

	/**
	 * 写入service对象
	 */
	@Resource(name = "couponServiceImpl")
	private CouponService couponService;
	@Resource(name = "couponCodeServiceImpl")
	private CouponCodeService couponCodeService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;
	
	/**
	 * 
	 * 进入列表页面.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午11:13:00
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
		model.addAttribute("page", couponService.findPage(pageable));
		return "/admin/coupon/list";
	}

	/**
	 * 
	 * 进入新增页面.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午2:24:07
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return "/admin/coupon/add";
	}
	
	/**
	 * 
	 * 保存优惠券.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午3:18:52
	 * @param coupon 优惠券
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Coupon coupon, Long[] productIds,RedirectAttributes redirectAttributes) {
		
		if(null!=productIds){
		}
		StringBuffer expression = new StringBuffer("price-");
		expression.append( coupon.getCountPrice() );
		
		coupon.setPriceExpression(expression.toString());
		if (!isValid(coupon)) {
			return ERROR_VIEW;
		}
		if (coupon.getBeginDate() != null && coupon.getEndDate() != null && coupon.getBeginDate().after(coupon.getEndDate())) {
			return ERROR_VIEW;
		}
		
		coupon.setGross(0);
		coupon.setResidue(0);
		
		couponService.save(coupon);
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	/**
	 * 
	 * 编辑.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午3:24:55
	 * @param id 优惠券id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, ModelMap model) {
		model.addAttribute("coupon", couponService.find(id));
		return "/admin/coupon/edit";
	}
	
	/**
	 * 
	 * 更新.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午3:39:23
	 * @param coupon 优惠券
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Coupon coupon, Long[] productIds,RedirectAttributes redirectAttributes) {
		
		if(null!=productIds){
		}
		StringBuffer expression = new StringBuffer("price-");
		expression.append(coupon.getCountPrice().toString());
		coupon.setPriceExpression(expression.toString());
		if (!isValid(coupon)) {
			return ERROR_VIEW;
		}
		if (coupon.getBeginDate() != null && coupon.getEndDate() != null && coupon.getBeginDate().after(coupon.getEndDate())) {
			return ERROR_VIEW;
		}

		couponService.update(coupon,  "orders");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 
	 * 删除.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午3:43:36
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Integer[] ids) {
		couponService.delete(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 
	 * 生产优惠码.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午3:53:26
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public String generate(Integer id, ModelMap model) {
		Coupon coupon = couponService.find(id);
		model.addAttribute("coupon", coupon);
		
		List<Filter> filters =new ArrayList<Filter>();
		filters.add(Filter.eq("couponId",coupon.getId()));
		
		model.addAttribute("totalCount", couponCodeService.findList(null, filters, null).size());
		
		filters.add(Filter.eq("isUsed",1));
		model.addAttribute("usedCount", couponCodeService.findList(null, filters, null).size());
		return "/admin/coupon/generate";
	}
	
	/**
	 * 
	 * 优惠码统计.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午3:53:26
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/statistic", method = RequestMethod.GET)
	public String statistic(Integer id, ModelMap model) {
		Coupon coupon = couponService.find(id);
		List<Filter> filters =new ArrayList<Filter>();
		filters.add(Filter.eq("couponId",coupon.getId()));
		
		model.addAttribute("coupon", coupon);
		model.addAttribute("couponCodes", couponCodeService.findList(null, filters, null));
		model.addAttribute("totalCount", couponCodeService.findList(null, filters, null).size());
		
		filters.add(Filter.ne("memberId",null));
		model.addAttribute("givenCount", couponCodeService.findList(null, filters, null).size());
		
		filters.add(Filter.eq("isUsed",1));
		model.addAttribute("usedCount", couponCodeService.findList(null, filters, null).size());
		return "/admin/coupon/statistic";
	}
	
	/**
	 * 
	 * 下载优惠吗.
	 * 
	 * author: 王凯斌
	 *   date: 2015-11-9 下午4:01:58
	 * @param id 优惠券id 
	 * @param count 数量
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ModelAndView download(Integer id, Integer count, ModelMap model) {
		if (count == null || count <= 0) {
			count = 100;
		}
		Coupon coupon = couponService.find(id);
		coupon.setGross(coupon.getGross()+count);
		coupon.setResidue(coupon.getResidue()+count);
		couponService.update(coupon);
		List<CouponCode> data = couponCodeService.generate(coupon, null, count);
		String filename = "coupon_code_" + DateFormatUtils.format(new Date(), "yyyyMM") + ".xls";
		String[] contents = new String[4];
		contents[0] = message("admin.coupon.type") + ": " + coupon.getName();
		contents[1] = message("admin.coupon.count") + ": " + count;
		contents[2] = message("admin.coupon.operator") + ": " + adminService.getCurrentUsername();
		contents[3] = message("admin.coupon.date") + ": " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		return new ModelAndView(new ExcelView(filename, null, new String[] { "code" }, new String[] { message("admin.coupon.title") }, null, null, data, contents), model);
	}
	
	/**
	 * 商品选择
	 */
	@RequestMapping(value = "/product_select", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> productSelect(@RequestParam("q") String keyword, Integer[] excludeIds, @RequestParam("limit") Integer count) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if (StringUtils.isEmpty(keyword)) {
			return data;
		}
		Set<Product> excludes = new HashSet<Product>(productService.findList(excludeIds));
		List<Filter> filters =new ArrayList<Filter>();
		filters.add(Filter.like("sn", keyword));
		List<Product> products = productService.findList(count, filters, null);
		for (Product product : products) {
			if(excludes.contains(product)){
				continue;
			}
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", product.getId());
			item.put("sn", product.getSn());
			item.put("name", product.getName());
			data.add(item);
		}
		return data;
	}
}