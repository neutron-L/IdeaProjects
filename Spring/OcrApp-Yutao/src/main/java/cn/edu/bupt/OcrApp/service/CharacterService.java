package cn.edu.bupt.OcrApp.service;

import cn.edu.bupt.OcrApp.vo.CharacterMeasurementVO;
import cn.edu.bupt.OcrApp.dto.ObsJoinMaterialInfo;
import cn.edu.bupt.OcrApp.vo.HistoryData;

import java.util.List;

public interface CharacterService {
    CharacterMeasurementVO ShowDifferentCharacterData(String specificCharacter);

    List<CharacterMeasurementVO> ShowDifferentCharacterDataByObservationPeriod(String observationPeriod);

    List<HistoryData>  findHistoryDataByUser(Integer userId);

    List<HistoryData>  findAllHistoryObsData();

    List<ObsJoinMaterialInfo>  findHistoryDataByUserAndSearchCriteria(Integer userId, String year, String plantNumber, String materialNumberRemoveYear);

    List<ObsJoinMaterialInfo>  findHistoryDataByUserAndSearchCriteriaGeneralEvalPeriod(Integer userId, String year,String materialNumberRemoveYear);

}
