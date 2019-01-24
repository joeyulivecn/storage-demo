package com.joeyu.storagedemo.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.joeyu.storagedemo.service.StorageService;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class AliyunStorageServiceImpl implements StorageService {
    @Value("${aliyun.storage.endpoint}")
    private String endpoint;
    @Value("${aliyun.storage.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.storage.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.storage.bucketName}")
    private String bucketName;

    @Override
    public String putObject(InputStream inputStream, long contentLength, String contentType, String keyName) {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inStream = inputStream;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(contentType);
        ossClient.putObject(bucketName, keyName, inStream, metadata);

        ossClient.shutdown();
        String host = "";
        try {
            URL endpointURL = new URL(endpoint);
            host = endpointURL.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return MessageFormat.format("http://{0}.{1}/{2}", bucketName, host, keyName);
    }
}
