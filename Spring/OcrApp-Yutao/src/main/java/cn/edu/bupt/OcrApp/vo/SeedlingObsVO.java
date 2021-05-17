package cn.edu.bupt.OcrApp.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class SeedlingObsVO {
    private String observationId;

    private String materialType;

    private String materialNumber;

    private String plantNumber;

    private String location;

    private Date investigatingTime;

    private String investigator;

    private String image;

    private String cotyledonSize;

    private List<String> cotyledonSizeDescription;

    private String cotyledonSizeMeasurement;

    private String cotyledonColor;

    private List<String> cotyledonColorDescription;

    private String cotyledonColorMeasurement;

    private String cotyledonNumber;

    private List<String> cotyledonNumberDescription;

    private String cotyledonNumberMeasurement;

    private String cotyledonShape;

    private List<String> cotyledonShapeDescription;

    private String cotyledonShapeMeasurement;

    private String colorOfHeartLeaf;

    private List<String> colorOfHeartLeafDescription;

    private String colorOfHeartLeafMeasurement;

    private String trueLeafColor;

    private List<String> trueLeafColorDescription;

    private String trueLeafColorMeasurement;

    private String trueLeafLength;

    private List<String> trueLeafLengthDescription;

    private String trueLeafLengthMeasurement;

    private String trueLeafWidth;

    private List<String> trueLeafWidthDescription;

    private String trueLeafWidthMeasurement;
}
