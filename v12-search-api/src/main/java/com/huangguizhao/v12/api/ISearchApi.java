package com.huangguizhao.v12.api;

import com.huangguizhao.v12.common.pojo.ResultBean;

/**
 * @author huangguizhao
 */
public interface ISearchApi {

    public ResultBean syncAllData();

    public ResultBean searchByKeyWord(String keyWord);
}
