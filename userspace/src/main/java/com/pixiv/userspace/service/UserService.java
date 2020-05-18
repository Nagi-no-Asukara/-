package com.pixiv.userspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pixiv.userspace.entity.Image;
import com.pixiv.userspace.entity.UserInfo;
import com.pixiv.userspace.utils.Result;

import java.util.List;

public interface UserService extends IService<UserInfo> {

    public void updateAvatar(UserInfo userInfo);

    public UserInfo getUserInfo(Integer id);

    public boolean updateInfo(UserInfo userInfo);

    public List<Image> getFavList(Integer id);

    public List<Image> getUploadList(Integer id);

}
