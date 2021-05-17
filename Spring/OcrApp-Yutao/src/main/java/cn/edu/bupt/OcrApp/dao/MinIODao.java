package cn.edu.bupt.OcrApp.dao;

import cn.edu.bupt.OcrApp.domain.generated.ToolMinioPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;
@Mapper
public interface MinIODao {
    @Select("select * from tool_minio_picture where picture_id = #{id}")
    Optional<ToolMinioPicture> findPictureById(@Param("id") Long id);

    @Select("SELECT * FROM tool_minio_picture WHERE name = #{name}")
    ToolMinioPicture findByPictureName(@Param("name") String name);

    List<ToolMinioPicture> photoList(@Param("obsPeriod") String obsPeriod, @Param("specCharacter") String specCharacter);

    ToolMinioPicture specCharacterPhoto(@Param("specCharacter") String specCharacter, @Param("observationId") String observationId);

    @Select("SELECT * FROM tool_minio_picture WHERE observation_id =#{ObservationId}")
    List<ToolMinioPicture> getPhotosByObservationId(@Param("ObservationId") String ObservationId);
}
