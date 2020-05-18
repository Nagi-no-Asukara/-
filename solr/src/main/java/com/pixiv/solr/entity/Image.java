package com.pixiv.solr.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "images", type = "image")
public class Image {


    private Integer id;//elasticSearch会自动选id作主键 成为信息的唯一标识符

    private String title;

    private String url;

    private String author;

    private Date created;

    private Integer loved;


}
