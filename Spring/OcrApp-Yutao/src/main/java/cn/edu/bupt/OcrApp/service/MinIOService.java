package cn.edu.bupt.OcrApp.service;

import cn.edu.bupt.OcrApp.domain.generated.ToolMinioPicture;
import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {
    ToolMinioPicture upload(MultipartFile file, String obsPeriod, String specCharacter, String observationId);

    String display(ToolMinioPicture content);

    ToolMinioPicture findByContentId(Long id);

    void delete(ToolMinioPicture byContentId);

    void deleteAll(Long[] ids);

}
