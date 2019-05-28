package com.huangguizhao.v12productservice.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.common.base.BaseServiceImpl;
import com.huangguizhao.v12.common.base.IBaseDao;
import com.huangguizhao.v12.entity.TProduct;
import com.huangguizhao.v12.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        //1.设置起始页码和每页数量
        PageHelper.startPage(pageIndex,pageSize);
        //2.获取列表数据
        List<TProduct> list = list();
        //3.构建分页对象
        PageInfo<TProduct> pageInfo = new PageInfo<>(list,2);
        return pageInfo;
    }
}
