package cn.edu.bupt.OcrApp.service.serviceImpl;

import cn.edu.bupt.OcrApp.dto.ObsJoinMaterialInfo;
import cn.edu.bupt.OcrApp.vo.CharacterMeasurementVO;
import cn.edu.bupt.OcrApp.dao.CharacterDao;
import cn.edu.bupt.OcrApp.model.CharacterMeasurement;
import cn.edu.bupt.OcrApp.service.CharacterService;
import cn.edu.bupt.OcrApp.vo.HistoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterDao characterDao;

    @Override
    public CharacterMeasurementVO ShowDifferentCharacterData(String specificCharacter) {
        CharacterMeasurementVO characterMeasurementVO = new CharacterMeasurementVO();
        CharacterMeasurement measurement = characterDao.findMeasurementBySpecificCharacter(specificCharacter);
        List<String> description = characterDao.findCharacterDescriptionBySpecificCharacter(specificCharacter);
        characterMeasurementVO.setSpecificCharacter(measurement.getSpecificCharacter());
        characterMeasurementVO.setCharacterDescription(description);
        characterMeasurementVO.setMeasurementBasis(measurement.getMeasurementBasis());
        characterMeasurementVO.setObservationMethod(measurement.getObservationMethod());
        return characterMeasurementVO;

    }

    @Override
    public List<CharacterMeasurementVO> ShowDifferentCharacterDataByObservationPeriod(String observationPeriod) {
        List<CharacterMeasurement> measurementList = characterDao.findMeasurementByObservationPeriod(observationPeriod);
        List<CharacterMeasurementVO> characterMeasurementVOList = new ArrayList<>();
        for (CharacterMeasurement characterMeasurement : measurementList) {
            CharacterMeasurementVO characterMeasurementVO = new CharacterMeasurementVO();
            CharacterMeasurement measurement = characterDao.findMeasurementBySpecificCharacter(characterMeasurement.getSpecificCharacter());
            List<String> description = characterDao.findCharacterDescriptionBySpecificCharacter(characterMeasurement.getSpecificCharacter());
            characterMeasurementVO.setSpecificCharacter(measurement.getSpecificCharacter());
            characterMeasurementVO.setCharacterDescription(description);
            characterMeasurementVO.setMeasurementBasis(measurement.getMeasurementBasis());
            characterMeasurementVO.setObservationMethod(measurement.getObservationMethod());
            characterMeasurementVOList.add(characterMeasurementVO);
        }
        return characterMeasurementVOList;
    }

    @Override
    public List<HistoryData> findHistoryDataByUser(Integer userId) {
        List<HistoryData> dataList = characterDao.findHistoryDataByUser(userId);
        return dataList;
    }

    @Override
    public List<HistoryData> findAllHistoryObsData() {
        List<HistoryData> dataList = characterDao.findAllHistoryObsData();
        return dataList;
    }

    @Override
    public List<ObsJoinMaterialInfo> findHistoryDataByUserAndSearchCriteria(Integer userId, String year, String plantNumber, String materialNumberRemoveYear) {
        List<ObsJoinMaterialInfo> dataList = characterDao.findHistoryDataByUserAndSearchCriteria(userId, year, plantNumber, materialNumberRemoveYear);
        return dataList;
    }


    @Override
    public List<ObsJoinMaterialInfo> findHistoryDataByUserAndSearchCriteriaGeneralEvalPeriod(Integer userId, String year, String materialNumberRemoveYear) {
        List<ObsJoinMaterialInfo> dataList = characterDao.findHistoryDataByUserAndSearchCriteriaGeneralEvalPeriod(userId, year, materialNumberRemoveYear);
        return dataList;
    }

}
