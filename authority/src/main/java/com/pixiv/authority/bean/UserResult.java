package com.pixiv.authority.bean;

import com.pixiv.authority.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResult {

    private List<User> userList;

    private Integer pageSize;

    private Integer total;
}
