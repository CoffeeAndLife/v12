package com.huangguizhao.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huangguizhao.v12.api.IProductTypeApi;
import com.huangguizhao.v12.common.base.BaseServiceImpl;
import com.huangguizhao.v12.common.base.IBaseDao;
import com.huangguizhao.v12.entity.TProductType;
import com.huangguizhao.v12.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangguizhao
 */
@Component
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProductTypeApi{

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }
}
