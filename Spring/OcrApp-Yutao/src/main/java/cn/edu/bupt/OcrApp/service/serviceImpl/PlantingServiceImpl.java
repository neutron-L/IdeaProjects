package cn.edu.bupt.OcrApp.service.serviceImpl;

import cn.edu.bupt.OcrApp.domain.generated.PlantingInfo;
import cn.edu.bupt.OcrApp.dto.PlantingInfoDto;
import cn.edu.bupt.OcrApp.mapper.generated.PlantingInfoMapper;
import cn.edu.bupt.OcrApp.service.PlantingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantingServiceImpl implements PlantingService {

    @Autowired
    private PlantingInfoMapper plantingInfoMapper;


	@Override
	public int insertBatchPlanting(PlantingInfoDto plantingInfoDto, int amount){
	    int i = 0;
        for(int j = 0; j < amount; j ++) {
            PlantingInfo plantingInfo = new PlantingInfo();
            BeanUtils.copyProperties(plantingInfoDto,plantingInfo);
            int k = plantingInfoMapper.insertSelective(plantingInfo);
            if(k>0){
                i++;
            }
        }
            return i;
    }

	}




