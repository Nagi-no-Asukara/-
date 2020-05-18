package com.pixiv.sso.bean;

import com.pixiv.sso.entity.UserInfo;
import lombok.Data;

@Data
public class SsoVO {


    UserInfo userInfo;

    String token;

}
