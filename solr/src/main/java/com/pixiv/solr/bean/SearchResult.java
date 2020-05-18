package com.pixiv.solr.bean;


import com.pixiv.solr.entity.Image;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {

    private List<Image> list;

    private  long total;

    private Integer pageNum;
}
