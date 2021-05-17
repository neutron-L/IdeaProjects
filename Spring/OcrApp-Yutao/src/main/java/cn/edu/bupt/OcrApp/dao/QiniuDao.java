package cn.edu.bupt.OcrApp.dao;

import cn.edu.bupt.OcrApp.domain.generated.ToolQiniuConfig;
import cn.edu.bupt.OcrApp.domain.generated.ToolQiniuPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QiniuDao {
    @Select("select * from tool_qiniu_config where config_id = #{id}")
    Optional<ToolQiniuConfig> findConfigById(@Param("id") Long id);

    @Select("select * from tool_qiniu_picture where picture_id = #{id}")
    Optional<ToolQiniuPicture> findPictureById(@Param("id") Long id);

    @Select("SELECT * FROM tool_qiniu_picture WHERE name = #{name}")
    ToolQiniuPicture findByPictureName(@Param("name") String name);

    @Update("update QiniuConfig set type = #{type}")
    void update(@Param("type") String type);

    List<ToolQiniuPicture> photoList(@Param("obsPeriod") String obsPeriod, @Param("specCharacter") String specCharacter);

    ToolQiniuPicture specCharacterPhoto(@Param("specCharacter") String specCharacter, @Param("observationId") String observationId);

    @Select("SELECT * FROM tool_qiniu_picture WHERE observation_id =#{ObservationId}")
    List<ToolQiniuPicture> getPhotosByObservationId(@Param("ObservationId") String ObservationId);
}
