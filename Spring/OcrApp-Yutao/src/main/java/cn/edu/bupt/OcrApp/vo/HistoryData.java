package cn.edu.bupt.OcrApp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class HistoryData {
    private String observationId;

    private String materialType;

    private String materialNumber;

    private String plantNumber;

    private String location;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date investigatingTime;

    private String investigator;

    private String obsPeriod;
}
