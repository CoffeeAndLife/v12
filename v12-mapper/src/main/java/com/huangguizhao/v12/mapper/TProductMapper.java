package com.huangguizhao.v12.mapper;

import com.huangguizhao.v12.common.base.IBaseDao;
import com.huangguizhao.v12.entity.TProduct;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct> {
    Integer updateFlagToFalse(List<Long> ids);
}