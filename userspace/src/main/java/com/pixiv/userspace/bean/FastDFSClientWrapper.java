package com.pixiv.userspace.bean;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

    public void deleteFileByURL(String  url){
        String src=url.substring(deleteNum);
        storageClient.deleteFile(src);
    }

    public void deleteFileByPath(String  path){
        storageClient.deleteFile(path);

    }
}