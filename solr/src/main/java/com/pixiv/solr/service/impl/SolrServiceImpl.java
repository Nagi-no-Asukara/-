package com.pixiv.solr.service.impl;


import com.pixiv.solr.entity.Image;
import com.pixiv.solr.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    SolrClient solrClient;

    @Override
    public void addImages(List<Image> imageList) {
        try {
            for (Image image : imageList) {
                SolrInputDocument document = new SolrInputDocument();

                document.addField("id", image.getId());
                document.addField("image_title", image.getTitle());
                document.addField("image_author", image.getAuthor());
                document.addField("image_popularity", image.getLoved());
                document.addField("image_url", image.getUrl());
                solrClient.add(document);
            }
            solrClient.commit();
        }
           catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    public void addOrUpdateImage(Image image) {
        try {

                SolrInputDocument document = new SolrInputDocument();

                document.addField("id", image.getId());
                document.addField("image_title", image.getTitle());
                document.addField("image_author", image.getAuthor());
                document.addField("image_popularity", image.getLoved());
                document.addField("image_url", image.getUrl());
                solrClient.add(document);
                solrClient.commit();
            }
        catch (SolrServerException | IOException ex) {
            ex.printStackTrace();
    }

    }

    @Override
    public void deleteImage(int id) throws IOException, SolrServerException {
        solrClient.deleteById(String.valueOf(id));
    }

}
