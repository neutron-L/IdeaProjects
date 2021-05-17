package cn.edu.bupt.OcrApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlantingInfoDto {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Integer plantNumber;

    private String materialNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sowingDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plantingDate;

    private String growSeedingEnvironment;

    private String location;

    private String year;

    private String season;

    private Integer userId;

    public String getPlantingName() {
        return plantingName;
    }

    public void setPlantingName(String plantingName) {
        this.plantingName = plantingName == null ? null : plantingName.trim();
    }

    private String plantingName;

    public Integer getPlantNumber() {
        return plantNumber;
    }

    public void setPlantNumber(Integer plantNumber) {
        this.plantNumber = plantNumber;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber == null ? null : materialNumber.trim();
    }

    public Date getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(String sowingDate) {
        try {
            this.sowingDate = simpleDateFormat.parse(sowingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(String plantingDate) {
        try {
            this.plantingDate = simpleDateFormat.parse(plantingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getGrowSeedingEnvironment() {
        return growSeedingEnvironment;
    }

    public void setGrowSeedingEnvironment(String growSeedingEnvironment) {
        this.growSeedingEnvironment = growSeedingEnvironment == null ? null : growSeedingEnvironment.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}