package com.huangguizhao.v12centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.entity.TProduct;
import com.huangguizhao.v12.vo.TProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huangguizhao
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductApi productApi;

    @GetMapping("get/{id}")
    @ResponseBody
    public TProduct get(@PathVariable("id") Long id){
        return productApi.selectByPrimaryKey(id);
    }

    @GetMapping("list")
    public String list(Model model){
        List<TProduct> list = productApi.list();
        model.addAttribute("list",list);
        return "product/list";
    }

    @GetMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model,@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize){
        PageInfo<TProduct> pageInfo = productApi.page(pageIndex,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "product/list";
    }

    @PostMapping("add")
    public String add(TProductVO vo){
        Long newId = productApi.add(vo);
        //
        return "redirect:product/page/1/1";
    }



}
