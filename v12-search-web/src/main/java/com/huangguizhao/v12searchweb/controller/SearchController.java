package com.huangguizhao.v12searchweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huangguizhao.v12.api.ISearchApi;
import com.huangguizhao.v12.common.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangguizhao
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchApi searchApi;

    @RequestMapping("searchByKeyWords")
    public String searchByKeyWords(String keyWord, Model model){
        ResultBean resultBean = searchApi.searchByKeyWord(keyWord);
        model.addAttribute("result",resultBean);

        return "result";
    }
}
