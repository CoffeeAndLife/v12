package com.huangguizhao.v12centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.common.pojo.ResultBean;
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
        return "redirect:/product/page/1/1";
    }

    @PostMapping("del/{id}")
    @ResponseBody
    public ResultBean del(@PathVariable("id") Long id){
        int count = productApi.deleteByPrimaryKey(id);
        if(count > 0){
            return new ResultBean(200,"删除成功");
        }
        return new ResultBean(500,"删除失败");
    }

    @PostMapping("delByIds")
    @ResponseBody
    public ResultBean delByIds(@RequestParam List<Long> ids){
        System.out.println(ids);
        int count = productApi.delByIds(ids);
        if(count > 0){
            return new ResultBean(200,"删除成功");
        }
        return new ResultBean(500,"删除失败");
    }
}
