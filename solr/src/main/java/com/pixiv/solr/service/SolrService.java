package com.pixiv.solr.service;



import com.pixiv.solr.entity.Image;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SolrService {

    public void addImages(List<Image> images);

    public void addOrUpdateImage(Image image);

    public void deleteImage(int id) throws IOException, SolrServerException;


}
