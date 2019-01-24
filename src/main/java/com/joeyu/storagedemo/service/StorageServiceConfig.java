package com.joeyu.storagedemo.service;

import com.joeyu.storagedemo.service.impl.AliyunStorageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageServiceConfig {
    @Bean
    public StorageService aliyunStorageService() {
        return new AliyunStorageServiceImpl();
    }
}
