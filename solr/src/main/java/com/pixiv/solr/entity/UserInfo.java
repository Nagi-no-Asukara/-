package com.pixiv.solr.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "users", type = "userInfo") //根据entity 搜索的默认index type都规定好了
public class UserInfo {

    private Integer id;//elasticSearch会自动选id作主键 成为信息的唯一标识符

    private String name;

    private String username;

    private String avatar;

    private Integer sex;



}
