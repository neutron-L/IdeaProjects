/**
 * All rights Reserved, Designed By 溪云阁
 * Copyright:    Copyright(C) 2016-2020
 */

package cn.edu.bupt.OcrApp.config;
 
import cn.edu.bupt.OcrApp.model.MinioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
 
/**
 * minio客户端配置
 * @author：溪云阁
 * @date：2020年6月7日
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MinioData.class)
public class MinioConfig {
 
    @Autowired
    private MinioData minioData;
 
    /**
     * 初始化minio客户端,不用每次都初始化
     * @author 溪云阁
     * @return MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        try {
            return new MinioClient(minioData.getUrl(), minioData.getAccessKey(), minioData.getSecretKey());
        }
        catch (final Exception e) {
            log.error("初始化minio出现异常:{}", e.fillInStackTrace());
            throw new RuntimeException(e.fillInStackTrace());
        }
    }
 
}