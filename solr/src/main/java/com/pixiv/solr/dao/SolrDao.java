package com.pixiv.solr.dao;

import com.pixiv.solr.bean.SearchResult;
import com.pixiv.solr.entity.Image;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SolrDao {

    @Autowired
    SolrClient solrClient;


    public SearchResult search(SolrQuery query) throws Exception {

        //根据query查询索引库
        QueryResponse queryResponse = solrClient.query(query);
        //取查询结果。
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //取查询结果总记录数
        long numFound = solrDocumentList.getNumFound();
        SearchResult result = new SearchResult();
        result.setTotal(numFound);

        //取商品列表，需要取高亮显示
        List<Image> imageList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            Image item = new Image();
            item.setId(Integer.parseInt((String) solrDocument.get("id")));
            item.setTitle((String) solrDocument.get("image_title"));
            item.setLoved((Integer) solrDocument.get("image_popularity"));
            item.setAuthor((String) solrDocument.get("image_author"));
            item.setUrl((String) solrDocument.get("image_url"));
            //添加到商品列表
            imageList.add(item);
        }
        result.setList(imageList);

        return result;
    }
}
