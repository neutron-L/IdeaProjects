/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.edu.bupt.OcrApp.controller;

import cn.edu.bupt.OcrApp.config.UploadProperties;
import cn.edu.bupt.OcrApp.dao.QiniuDao;
import cn.edu.bupt.OcrApp.domain.generated.ToolQiniuConfig;
import cn.edu.bupt.OcrApp.domain.generated.ToolQiniuPicture;
import cn.edu.bupt.OcrApp.service.QiNiuService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("qiNiuContent")
@EnableConfigurationProperties(UploadProperties.class)
@Api(tags = "七牛云存储管理")
public class QiniuController {
    @Autowired
    private UploadProperties pro;
    private final QiNiuService qiNiuService;
    private final QiniuDao qiniuDao;

    @GetMapping(value = "/config")
    @ApiOperation("查询七牛云存储配置")
    public CommonResult queryConfig() {
        return new CommonResult().success(qiNiuService.find());
    }


    @ApiOperation("配置七牛云存储")
    @PutMapping(value = "/config")
    public ResponseEntity<Object> updateConfig(@Validated @RequestBody ToolQiniuConfig qiniuConfig) {
        qiNiuService.config(qiniuConfig);
        qiNiuService.update(qiniuConfig.getType());
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @ApiOperation("查询文件")
//    @GetMapping
//    public ResponseEntity<Object> query(QiniuQueryCriteria criteria, Pageable pageable){
//        return new ResponseEntity<>(qiNiuService.queryAll(criteria,pageable), HttpStatus.OK);
//    }

    @GetMapping("photoList")
    @ApiOperation("根据观测时期，具体性状 模糊查询照片 查询条件可为空")
    public CommonResult photoList(@RequestParam(required = false) String obsPeriod, @RequestParam(required = false) String specCharacter,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ToolQiniuPicture> photoList = qiniuDao.photoList(obsPeriod, specCharacter);
        return new CommonResult().pageSuccess(photoList);
    }

    @GetMapping("specCharacterPhoto")
    @ApiOperation("根据具体性状和观测id查询照片")
    public CommonResult specCharacterPhoto(@RequestParam String specCharacter,
                                           @RequestParam String observationId) {

        ToolQiniuPicture specCharacterPhoto = qiniuDao.specCharacterPhoto(specCharacter, observationId);
        if (specCharacterPhoto == null) {
            throw new BadRequestException("请输入正确的具体性状及对应的观测id");
        }
        return new CommonResult().success(specCharacterPhoto);
    }

    @GetMapping("getPhotosByObservationId")
    @ApiOperation("根据观测id获取照片")
    public CommonResult getPhotosByObservationId(@RequestParam String ObservationId) {
        List<ToolQiniuPicture> photosByObservationId = qiniuDao.getPhotosByObservationId(ObservationId);
        if (CollectionUtils.isEmpty(photosByObservationId)) {
            throw new CabbageException(ExceptionEnum.OBSERVATION_ID_NOT_EXIST);
        }
        return new CommonResult().success(photosByObservationId);
    }

    @ApiOperation("上传图片")
    @PostMapping
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
        ToolQiniuPicture qiniuContent = qiNiuService.upload(file, qiNiuService.find(), obsPeriod, specCharacter, observationId);
        Map<String, Object> map = new HashMap<>(3);
        map.put("content", qiniuContent);
        map.put("errno", 0);
        map.put("url", new String[]{qiniuContent.getUrl()});
        return new CommonResult().success(map);
    }


    @ApiOperation("同步七牛云数据")
    @PostMapping(value = "/synchronize")
    @ApiIgnore
    public CommonResult synchronize() {
        qiNiuService.synchronize(qiNiuService.find());
        return new CommonResult().success("同步成功");
    }


    @ApiOperation("下载文件")
    @GetMapping(value = "/download/{id}")
    public CommonResult download(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("url", qiNiuService.download(qiNiuService.findByContentId(id), qiNiuService.find()));
        return new CommonResult().success(map);
    }


    @ApiOperation("删除图片")
    @DeleteMapping(value = "/{id}")
    public CommonResult delete(@PathVariable Long id) {
        qiNiuService.delete(qiNiuService.findByContentId(id), qiNiuService.find());
        return new CommonResult().success("删除成功");
    }


    @ApiOperation("删除多张图片")
    @DeleteMapping
    public CommonResult deleteAll(@RequestBody Long[] ids) {
        qiNiuService.deleteAll(ids, qiNiuService.find());
        return new CommonResult().success("删除成功");
    }
}
