package com.pixiv.manager.utils;

import java.io.IOException;
import java.io.InputStream;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import com.github.tobato.fastdfs.service.FastFileStorageClient;

@Component
public class FastDFSClientWrapper {

    private String ip="http://192.168.30.129";

    private int deleteNum=22;

    @Autowired
    private FastFileStorageClient storageClient;

    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile((InputStream)file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return storePath.getFullPath();
    }

    // 封装文件完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = ip+ "/" + storePath.getFullPath();
        return fileUrl;
    }

    public Result deleteFileByUrl(String  url){
        String src=url.substring(deleteNum);
        storageClient.deleteFile(src);
        return Result.ok();
    }

    public void deleteFileByPath(String  path){
        storageClient.deleteFile(path);
    }
}