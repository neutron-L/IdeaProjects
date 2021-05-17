/**
 * All rights Reserved, Designed By 溪云阁
 * Copyright:    Copyright(C) 2016-2020
 */

package cn.edu.bupt.OcrApp.model;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
 
import lombok.Data;
 
/**
 * minio属性文件
 * @author：溪云阁
 * @date：2020年6月7日
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioData {
 
    /**
     * minio地址+端口号
     */
    private String url;
 
    /**
     * minio用户名
     */
    private String accessKey;
 
    /**
     * minio密码
     */
    private String secretKey;
 
    /**
     * 文件桶的名称
     */
    private String bucketName;
 
}