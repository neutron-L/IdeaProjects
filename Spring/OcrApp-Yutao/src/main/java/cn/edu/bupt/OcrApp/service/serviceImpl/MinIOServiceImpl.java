package cn.edu.bupt.OcrApp.service.serviceImpl;

import cn.edu.bupt.OcrApp.config.UploadProperties;
import cn.edu.bupt.OcrApp.dao.MinIODao;
import cn.edu.bupt.OcrApp.domain.generated.ToolMinioPicture;
import cn.edu.bupt.OcrApp.mapper.generated.ToolMinioPictureMapper;
import cn.edu.bupt.OcrApp.model.MinioData;
import cn.edu.bupt.OcrApp.service.MinIOService;
import cn.edu.bupt.common.exception.BadRequestException;
import cn.edu.bupt.common.utils.FileUtil;
import cn.edu.bupt.common.utils.ValidationUtil;

import io.minio.MinioClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

@Service
public class MinIOServiceImpl implements MinIOService {
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private UploadProperties pro;
    @Autowired
    private MinioData minioData;
    @Value("${minio.max-size}")
    private Long maxSize;
    @Autowired
    private ToolMinioPictureMapper toolMinioPictureMapper;
    @Autowired
    private MinIODao minIODao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ToolMinioPicture upload(MultipartFile file, String obsPeriod, String specCharacter, String observationId) {
        FileUtil.checkSize(maxSize, file.getSize());
        try {
            final String BUCKET_NAME = minioData.getBucketName();
            // 得到文件流
            final InputStream is = file.getInputStream();
            // 文件名
            final String fileName = file.getOriginalFilename();

            //存入bucket不存在则创建，并设置为只读
            if (!minioClient.bucketExists(minioData.getBucketName())) {
                minioClient.makeBucket(minioData.getBucketName());
            }
            System.out.println(minioClient.getBucketPolicy(BUCKET_NAME));
            // 把文件放到minio的boots桶里面
//            minioClient.putObject(minioData.getBucketName(), fileName, is, new PutObjectOptions(is.available(), -1));
            minioClient.putObject(BUCKET_NAME, fileName, is, null, null, null, file.getContentType());
//            minioClient.putObject(BUCKET_NAME, fileName, is, file.getSize(),file.getContentType());
            String url = minioClient.getObjectUrl(BUCKET_NAME, fileName);
            ToolMinioPicture content = minIODao.findByPictureName(FileUtil.getFileNameNoEx(fileName));
            if (content == null) {
                //存入数据库
                ToolMinioPicture minIOContent = new ToolMinioPicture();
                minIOContent.setSuffix(FileUtil.getExtensionName(fileName));
                minIOContent.setBucket(BUCKET_NAME);
                minIOContent.setName(FileUtil.getFileNameNoEx(fileName));
                minIOContent.setUrl(url);
                minIOContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize() + "")));
                minIOContent.setUpdateTime(new Date());
                minIOContent.setSpecCharacter(specCharacter);
                minIOContent.setObsPeriod(obsPeriod);
                minIOContent.setObservationId(observationId);
                toolMinioPictureMapper.insertSelective(minIOContent);
                return minIOContent;
            }
            return content;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public String display(ToolMinioPicture content) {
        return content.getUrl();
    }

    @Override
    public ToolMinioPicture findByContentId(Long id) {
        ToolMinioPicture minIOContent = minIODao.findPictureById(id).orElseGet(ToolMinioPicture::new);
        ValidationUtil.isNull(minIOContent.getPictureId(), "minIOContent", "id", id);
        return minIOContent;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(ToolMinioPicture content) {
        try {
            minioClient.removeObject(content.getBucket(), content.getName() + "." + content.getSuffix());
            toolMinioPictureMapper.deleteByPrimaryKey(content.getPictureId());
        } catch (Exception e) {
            toolMinioPictureMapper.deleteByPrimaryKey(content.getPictureId());
        }
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            delete(findByContentId(id));
        }
    }

}
