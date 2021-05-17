package cn.edu.bupt.OcrApp.vo;

import lombok.Data;

import java.util.List;
@Data
public class CharacterMeasurementVO  {
    private String specificCharacter;

    private List<String> characterDescription;

    private String measurementBasis;

    private String observationMethod;


}
