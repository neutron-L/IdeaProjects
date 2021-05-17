package cn.edu.bupt.OcrApp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ObsJoinMaterialInfo {
    private String observationId;

    private String materialType;

    private String materialNumber;

    private String plantNumber;

    private String location;

    private Date investigatingTime;

    private Date inputTime;

    private String investigator;

    private String obsPeriod;

    private String year;

    private String season;

    private String origin;

    private String feature;

    private String oddLeeds;

    private String experiment;

    private String paternal;

    private String maternal;
}
