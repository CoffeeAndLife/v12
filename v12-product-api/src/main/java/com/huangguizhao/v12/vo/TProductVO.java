package com.huangguizhao.v12.vo;

import com.huangguizhao.v12.entity.TProduct;

import java.io.Serializable;

/**
 * @author huangguizhao
 */

public class TProductVO implements Serializable{

    private TProduct product;

    private String productDesc;

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
