package com.huangguizhao.v12centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
