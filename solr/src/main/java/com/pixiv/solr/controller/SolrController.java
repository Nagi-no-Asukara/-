package com.pixiv.solr.controller;


import com.pixiv.solr.bean.SearchResult;
import com.pixiv.solr.dao.SolrDao;
import com.pixiv.solr.entity.Image;
import com.pixiv.solr.service.ImageService;
import com.pixiv.solr.service.SolrService;
import com.pixiv.solr.utils.AjaxResponse;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SolrController {

    @Autowired
    private SolrService solrService;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    ImageService imageService;

    @Autowired
    SolrDao solrDao;


    //批量增加
    @GetMapping("/test")
    public AjaxResponse addUsers() throws IOException, SolrServerException {
        List<Image> imageList = imageService.list();
        solrClient.deleteByQuery("*:*");
        solrService.addImages(imageList);
        return AjaxResponse.success();

    }

    @GetMapping("/images")
    public AjaxResponse queryImages(String keyword,Integer pageNum,Integer pageSize) throws Exception {

        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keyword);
        //计算初始显示的图片
        query.setStart((pageNum - 1) *  pageSize);
        query.setRows(pageSize);
        //设置默认搜索域
        query.set("df", "image_keywords");
        SearchResult searchResult = solrDao.search(query);
        //计算总页数
        long recordCount = searchResult.getTotal();
        int totalPage = (int) (recordCount / pageSize);
        if (recordCount % pageSize > 0)
            totalPage ++;
        //添加到返回结果
        searchResult.setPageNum(totalPage);
        //返回结果
        return AjaxResponse.success(searchResult);

    }


    @DeleteMapping("/images")
    public void test() throws IOException, SolrServerException {
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
        solrClient.close();

    }

}
