package cn.edu.bupt.OcrApp.controller;

import cn.edu.bupt.OcrApp.config.UploadProperties;
import cn.edu.bupt.OcrApp.dao.MinIODao;
import cn.edu.bupt.OcrApp.domain.generated.ToolMinioPicture;
import cn.edu.bupt.OcrApp.service.MinIOService;
import cn.edu.bupt.common.enums.ExceptionEnum;
import cn.edu.bupt.common.exception.BadRequestException;
import cn.edu.bupt.common.exception.CabbageException;
import cn.edu.bupt.common.utils.CommonResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("MinIOContent")
@EnableConfigurationProperties(UploadProperties.class)
@Api(tags = "MinIO存储管理")
public class MinIOController {
    @Autowired
    private MinIOService minIOService;
    @Autowired
    private UploadProperties pro;
    @Autowired
    private MinIODao minIODao;

    @ApiOperation("上传图片")
    @PostMapping("upload")
    public CommonResult upload(@RequestParam MultipartFile file,
                               @RequestParam String obsPeriod,
                               @RequestParam String specCharacter,
                               @RequestParam String observationId) {
        // 1、图片信息校验
        // 1)校验文件类型
        String type = file.getContentType();
        log.info("文件类型为 contentType:{}", type);
        if (!pro.getAllowTypes().contains(type)) {
            log.error("非指定图片文件类型 contentType:{}", type);
            throw new CabbageException(ExceptionEnum.INVALID_FILE_TYPE);
//            return new CommonResult().failed("无效文件类型");

        }
        // 2)校验图片内容
        BufferedImage image = null;
        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image == null) {
            log.error("非图片文件");
            throw new CabbageException(ExceptionEnum.INVALID_FILE_CONTENT);
            // return new CommonResult().failed("无效文件类型");

        }
        ToolMinioPicture minIOContent = minIOService.upload(file, obsPeriod, specCharacter, observationId);
        Map<String, Object> map = new HashMap<>(3);
        map.put("content", minIOContent);
        map.put("errno", 0);
        map.put("url", new String[]{minIOContent.getUrl()});
        return new CommonResult().success(map);
    }

    @ApiOperation("查看图片")
    @GetMapping(value = "display")
    public CommonResult display(@RequestParam Long id) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("url", minIOService.display(minIOService.findByContentId(id)));
        return new CommonResult().success(map);
    }

    @ApiOperation("删除图片")
    @DeleteMapping(value = "/{id}")
    public CommonResult delete(@PathVariable Long id) {
        minIOService.delete(minIOService.findByContentId(id));
        return new CommonResult().success("删除成功");
    }


    @ApiOperation("删除多张图片")
    @DeleteMapping("deleteAll")
    public CommonResult deleteAll(@RequestBody Long[] ids) {
        minIOService.deleteAll(ids);
        return new CommonResult().success("删除成功");
    }

    @GetMapping("photoList")
    @ApiOperation("根据观测时期，具体性状 模糊查询照片 查询条件可为空")
    public CommonResult photoList(@RequestParam(required = false) String obsPeriod, @RequestParam(required = false) String specCharacter,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ToolMinioPicture> photoList = minIODao.photoList(obsPeriod, specCharacter);
        return new CommonResult().pageSuccess(photoList);
    }

    @GetMapping("specCharacterPhoto")
    @ApiOperation("根据具体性状和观测id查询照片")
    public CommonResult specCharacterPhoto(@RequestParam String specCharacter,
                                           @RequestParam String observationId) {

        ToolMinioPicture specCharacterPhoto = minIODao.specCharacterPhoto(specCharacter, observationId);
        if (specCharacterPhoto == null) {
            throw new BadRequestException("请输入正确的具体性状及对应的观测id");
        }
        return new CommonResult().success(specCharacterPhoto);
    }

    @GetMapping("getPhotosByObservationId")
    @ApiOperation("根据观测id获取照片")
    public CommonResult getPhotosByObservationId(@RequestParam String ObservationId) {
        List<ToolMinioPicture> photosByObservationId = minIODao.getPhotosByObservationId(ObservationId);
        if (CollectionUtils.isEmpty(photosByObservationId)) {
            throw new CabbageException(ExceptionEnum.OBSERVATION_ID_NOT_EXIST);
        }
        return new CommonResult().success(photosByObservationId);
    }

}



