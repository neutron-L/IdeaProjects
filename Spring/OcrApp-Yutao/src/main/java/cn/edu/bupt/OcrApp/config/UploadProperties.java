package cn.edu.bupt.OcrApp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.List;

@ConfigurationProperties(prefix = "OcrApp.upload")
@Data
public class UploadProperties {
    private List<String> allowTypes;
}