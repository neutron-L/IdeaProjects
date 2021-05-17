package cn.edu.bupt.OcrApp.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    boolean updatePhoto(String observationId, String imgUrl, int picNum,String obsPeriod);
//    String uploadPhoto(MultipartFile file);
}
