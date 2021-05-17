package cn.edu.bupt.OcrApp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "OcrApp.character")
@Data
public class CharacterPeriodsProperties {
    private List<String> obsPeriods;
    private List<String> resistances;
}
