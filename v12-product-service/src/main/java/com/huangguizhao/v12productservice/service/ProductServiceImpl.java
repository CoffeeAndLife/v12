package com.huangguizhao.v12productservice.service;

import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.common.base.BaseServiceImpl;
import com.huangguizhao.v12.common.base.IBaseDao;
import com.huangguizhao.v12.entity.TProduct;
import com.huangguizhao.v12.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangguizhao
 */
@Component
@com.alibaba.dubbo.config.annotation.Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductApi{

    @Autowired
    private TProductMapper productMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }
}
