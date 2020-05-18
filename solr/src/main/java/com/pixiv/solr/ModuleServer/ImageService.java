package com.pixiv.solr.ModuleServer;


import com.pixiv.solr.entity.Image;
import com.pixiv.solr.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/search/ModuleServer")
/*
搜索库与数据库同步  以前视频是用activemq 不过我觉得跟接口调用没有区别
 */
public class ImageService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SolrService solrService;

    public void addOrUpdateImage(Image image){
        solrService.addOrUpdateImage(image);
    }

    public void deleteImage(Integer id) throws IOException, SolrServerException {
        solrService.deleteImage(id);
    }
}
