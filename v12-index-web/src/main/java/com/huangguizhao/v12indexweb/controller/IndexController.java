package com.huangguizhao.v12indexweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.api.IProductTypeApi;
import com.huangguizhao.v12.entity.TProduct;
import com.huangguizhao.v12.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author huangguizhao
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeApi productTypeApi;

    @GetMapping("/")
    public String showIndex(Model model){
        List<TProductType> list = productTypeApi.list();
        model.addAttribute("list",list);
        return "home";
    }
}
