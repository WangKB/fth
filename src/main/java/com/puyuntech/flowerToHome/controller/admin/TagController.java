package com.puyuntech.flowerToHome.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.entity.Tag;
import com.puyuntech.flowerToHome.service.ProductService;
import com.puyuntech.flowerToHome.service.TagService;

/**
 *
 * Controller - Tag. Created on 2015-11-10 上午11:05:48
 *
 * @author 王凯斌
 */
@Controller("adminTagController")
@RequestMapping("/admin/tag")
public class TagController extends BaseController {

    /**
     * 写入service对象
     */
    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    
    @Resource(name = "productServiceImpl")
    private ProductService productService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {

        /**
         * 将数据写入数据模型
         */
        model.addAttribute("page", tagService.findPage(pageable));
        return "/admin/tag/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("products",productService.findAll());
        return "/admin/tag/add";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id,ModelMap model) {

        /**
         * 将数据写入数据模型
         */
    	model.addAttribute("products",productService.findAll());
    	model.addAttribute("tag",tagService.find(id));
        return "/admin/tag/edit";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Tag tag) {

    	tagService.save(tag);
        return "redirect:list.jhtml";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Tag tag) {

    	tagService.update(tag);
        return "redirect:list.jhtml";
    }
    
}