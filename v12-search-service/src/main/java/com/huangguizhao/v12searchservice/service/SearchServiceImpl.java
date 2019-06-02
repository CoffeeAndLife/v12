package com.huangguizhao.v12searchservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huangguizhao.v12.api.ISearchApi;
import com.huangguizhao.v12.common.pojo.ResultBean;
import com.huangguizhao.v12.entity.TProduct;
import com.huangguizhao.v12.mapper.TProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangguizhao
 */
@Service
public class SearchServiceImpl implements ISearchApi{

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean syncAllData() {
        //1.获取商品数据
        List<TProduct> list = productMapper.list();
        //2.将商品数据同步到索引库中
        for (TProduct product : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",product.getId());
            document.setField("product_name",product.getName());
            document.setField("product_price",product.getPrice());
            document.setField("product_sale_point",product.getSalePoint());
            document.setField("product_images",product.getImages());
            //
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean(500,"数据同步失败！");
            }
        }
        //3.
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean(500,"数据同步失败！");
        }
        return new ResultBean(200,"数据同步成功！");
    }

    @Override
    public ResultBean searchByKeyWord(String keyWord) {
        //1.构建查询条件
        SolrQuery condition = new SolrQuery();
        if(!StringUtils.isAllEmpty(keyWord)){
            condition.setQuery("product_keywords:"+keyWord);
        }else{
            condition.setQuery("product_keywords:*");
        }
        //2.设置高亮
        condition.setHighlight(true);
        condition.addHighlightField("product_name");
        condition.setHighlightSimplePre("<font color='red'>");
        condition.setHighlightSimplePost("</font>");
        //3.执行查询
        List<TProduct> results = null;
        //4.拼装查询结果
        try {
            QueryResponse response = solrClient.query(condition);
            //
            SolrDocumentList list = response.getResults();
            //
            results = new ArrayList<>(list.size());
            //
            for (SolrDocument document : list) {
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setName(document.getFieldValue("product_name").toString());
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                //
                results.add(product);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean(500,null);
        }
        return new ResultBean(200,results);
    }
}
