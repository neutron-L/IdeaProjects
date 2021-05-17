package cn.edu.bupt.OcrApp.vo;


import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
public class GerminationObsVO  {
    private String observationId;

    private String materialType;

    private String materialNumber;

    private String plantNumber;

    private String location;

    private Date investigatingTime;

    private String investigator;

    private String image;

    private String germinationRate;

    private List<String> germinationRateDescription;

    private String germinationRateMeasurement;


}
