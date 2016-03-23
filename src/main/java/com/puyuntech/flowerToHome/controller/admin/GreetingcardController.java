package com.puyuntech.flowerToHome.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Greetingcard;
import com.puyuntech.flowerToHome.service.GreetingcardService;

/**
 *
 * Controller - 门店管理. Created on 2015-11-10 上午11:05:48
 *
 * @author 王凯斌
 */
@Controller("adminGreetingcardController")
@RequestMapping("/admin/greetingcard")
public class GreetingcardController extends BaseController {

    /**
     * 写入service对象
     */
    @Resource(name = "greetingcardServiceImpl")
    private GreetingcardService greetingcardService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {

        /**
         * 将数据写入数据模型
         */
        model.addAttribute("page", greetingcardService.findPage(pageable));
        return "/admin/greetingcard/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {

        /**
         * 将数据写入数据模型
         */
        return "/admin/greetingcard/add";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id,ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("greetingcard",greetingcardService.find(id));
        return "/admin/greetingcard/edit";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Greetingcard greetingcard) {

    	greetingcardService.save(greetingcard);
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Greetingcard greetingcard) {

    	greetingcardService.update(greetingcard,"visitDate");
        return "redirect:list.jhtml";
    }
}