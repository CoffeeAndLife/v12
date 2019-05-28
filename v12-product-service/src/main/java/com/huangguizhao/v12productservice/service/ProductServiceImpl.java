package com.huangguizhao.v12productservice.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huangguizhao.v12.api.IProductApi;
import com.huangguizhao.v12.common.base.BaseServiceImpl;
import com.huangguizhao.v12.common.base.IBaseDao;
import com.huangguizhao.v12.entity.TProduct;
import com.huangguizhao.v12.entity.TProductDesc;
import com.huangguizhao.v12.mapper.TProductDescMapper;
import com.huangguizhao.v12.mapper.TProductMapper;
import com.huangguizhao.v12.vo.TProductVO;
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

    @Autowired
    private TProductDescMapper productDescMapper;

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

    @Override
    public Long add(TProductVO vo) {
        //1.添加商品的基本信息，主键回填
        TProduct product = vo.getProduct();
        productMapper.insertSelective(product);
        //2.添加商品的描述信息
        TProductDesc desc = new TProductDesc();
        desc.setProductDesc(vo.getProductDesc());
        desc.setProductId(product.getId());
        productDescMapper.insertSelective(desc);
        return product.getId();
    }
}
