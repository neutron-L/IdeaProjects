//package cn.edu.bupt.OcrApp.service.serviceImpl;
//
//import UploadProperties;
//import cn.edu.bupt.OcrApp.domain.generated.*;
//import cn.edu.bupt.OcrApp.mapper.generated.*;
//import PhotoService;
//import cn.edu.bupt.common.enums.ExceptionEnum;
//import cn.edu.bupt.common.exception.CabbageException;
//import cn.edu.bupt.common.utils.CommonResult;
//import cn.edu.bupt.common.utils.ReflectUtil;
//import cn.edu.bupt.common.utils.UUIDtools;
//import com.sun.org.apache.bcel.internal.generic.NEW;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//
//@Service
//@Slf4j
//@EnableConfigurationProperties(UploadProperties.class)
//@Transactional
//public class PhotoServiceImpl implements PhotoService {
//    @Autowired
//    private UploadProperties pro;
//
//    @Autowired
//    private SeedlingObsMapper seedlingObsMapper;
//    @Autowired
//    private GerminationObsMapper germinationObsMapper;
//    @Autowired
//    private RosetteObsMapper rosetteObsMapper;
//    @Autowired
//    private HeadingObsMapper headingObsMapper;
//    @Autowired
//    private HarvestObsMapper harvestObsMapper;
//    @Autowired
//    private StorageObsMapper storageObsMapper;
//    @Autowired
//    private FloweringObsMapper floweringObsMapper;
//    @Autowired
//    private SeedHarvestTimeObsMapper seedHarvestTimeObsMapper;
//
//    @Override
//    public boolean updatePhoto(String observationId, String imgUrl, int picNum, String obsPeriod) {
//        String className = "cn.edu.bupt.OcrApp.domain.generated.";
//        if(obsPeriod.equals("发芽期")){
//            className += "GerminationObs";
//        }else if(obsPeriod.equals("幼苗期")){
//            className+= "SeedlingObs";
//        }else if(obsPeriod.equals("莲座期")){
//            className+= "RosetteObs";
//        }else if(obsPeriod.equals("结球期")){
//            className+= "HeadingObs";
//        }else if(obsPeriod.equals("收获期")){
//            className+= "HarvestObs";
//        }else if(obsPeriod.equals("储藏期")){
//            className+= "StorageObs";
//        }else if(obsPeriod.equals("现蕾开花期")){
//            className+= "FloweringObs";
//        }else if(obsPeriod.equals("结荚与种子收获期")){
//            className+= "SeedHarvestTimeObs";
//        }
//        System.out.println(className);
//        Object o = null;
//        try {
//            Class<?> aClass = Class.forName(className);
//            Constructor<?> constructor = aClass.getConstructor();
//            o = constructor.newInstance();
//            ReflectUtil.setValue(o, aClass, "observationId", String.class, observationId);
//            if (picNum == 1) {
//                ReflectUtil.setValue(o, aClass, "img1", String.class, imgUrl);
//            } else if (picNum == 2) {
//                ReflectUtil.setValue(o, aClass, "img2", String.class, imgUrl);
//            } else if (picNum == 3) {
//                ReflectUtil.setValue(o, aClass, "img3", String.class, imgUrl);
//            } else if (picNum == 4) {
//                ReflectUtil.setValue(o, aClass, "img4", String.class, imgUrl);
//            } else if (picNum == 5) {
//                ReflectUtil.setValue(o, aClass, "img5", String.class, imgUrl);
//            }
//        } catch (Exception e) {
//            throw new CabbageException(ExceptionEnum.REFLECT_ERROR);
//        }
//        if (o instanceof GerminationObs) {
//            GerminationObs germinationObs = (GerminationObs) o;
//            int i = germinationObsMapper.updateByPrimaryKeySelective(germinationObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof SeedlingObs) {
//            SeedlingObs seedlingObs = (SeedlingObs) o;
//            int i = seedlingObsMapper.updateByPrimaryKeySelective(seedlingObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof RosetteObs) {
//            RosetteObs rosetteObs = (RosetteObs) o;
//            int i = rosetteObsMapper.updateByPrimaryKeySelective(rosetteObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof HeadingObs) {
//            HeadingObs headingObs = (HeadingObs) o;
//            int i = headingObsMapper.updateByPrimaryKeySelective(headingObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof HarvestObs) {
//            HarvestObs harvestObs = (HarvestObs) o;
//            int i = harvestObsMapper.updateByPrimaryKeySelective(harvestObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof StorageObs) {
//            StorageObs seedlingObs = (StorageObs) o;
//            int i = storageObsMapper.updateByPrimaryKeySelective(seedlingObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof FloweringObs) {
//            FloweringObs floweringObs = (FloweringObs) o;
//            int i = floweringObsMapper.updateByPrimaryKeySelective(floweringObs);
//            return i == 1 ? true : false;
//        } else if (o instanceof SeedHarvestTimeObs) {
//            SeedHarvestTimeObs seedHarvestTimeObs = (SeedHarvestTimeObs) o;
//            int i = seedHarvestTimeObsMapper.updateByPrimaryKeySelective(seedHarvestTimeObs);
//            return i == 1 ? true : false;
//        }
//        return false;
//    }
//
//    @Override
//    public String uploadPhoto(MultipartFile file) {
//        // 1、图片信息校验
//        // 1)校验文件类型
//        String type = file.getContentType();
//        log.info("文件类型为 contentType:{}", type);
//        if (!pro.getAllowTypes().contains(type)) {
//            log.error("非指定图片文件类型 contentType:{}", type);
//            throw new CabbageException(ExceptionEnum.INVALID_FILE_TYPE);
////            return new CommonResult().failed("无效文件类型");
//
//        }
//        // 2)校验图片内容
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(file.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (image == null) {
//            log.error("非图片文件");
//            throw new CabbageException(ExceptionEnum.INVALID_FILE_CONTENT);
//            // return new CommonResult().failed("无效文件类型");
//
//        }
//        String filename = file.getOriginalFilename().toLowerCase();
//        String[] split = file.getOriginalFilename().split("\\.");
//        String s = split[split.length - 1];
//        String timeID = UUIDtools.createTimeID();
//        String fileName = timeID + "." + s;
//        String url = null;
//        try {
//            //使用base64方式上传到七牛云
//            byte[] bytes = file.getBytes();
//            url = QiniuCloudUtil.put64image(bytes, fileName);
//
//        } catch (Exception e) {
//            log.error("上传文件失败", e);
//            throw new CabbageException(ExceptionEnum.UPLOAD_FILE_ERROR);
//        }
//        return url;
//    }
//}
