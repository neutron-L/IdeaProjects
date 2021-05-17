package cn.edu.bupt.OcrApp.service;


import cn.edu.bupt.OcrApp.dto.PlantingInfoDto;

public interface PlantingService {

	int insertBatchPlanting(PlantingInfoDto plantingInfoDto, int amount);

}
