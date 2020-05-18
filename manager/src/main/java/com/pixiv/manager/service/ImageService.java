package com.pixiv.manager.service;

import com.github.pagehelper.Page;
import com.pixiv.manager.entity.Image;
import com.pixiv.manager.utils.Result;

import java.util.List;

public interface ImageService {

    public Image insertImage(Image image);

    public List<Image> GetImageByAuthor(String name);

    public List<Image> getImageAll();

    public Image getImageById(String id);

    public void delete(String id);

    public void updateImage(Image image);
}
