package com.huangguizhao.v12.api;

import com.github.pagehelper.PageInfo;
import com.huangguizhao.v12.common.base.IBaseService;
import com.huangguizhao.v12.entity.TProduct;

import java.util.List;

/**
 * @author huangguizhao
 */
public interface IProductApi extends IBaseService<TProduct>{

    public PageInfo<TProduct> page(Integer pageIndex,Integer pageSize);
}
