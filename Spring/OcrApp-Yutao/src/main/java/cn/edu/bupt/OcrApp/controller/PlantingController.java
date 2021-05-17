package cn.edu.bupt.OcrApp.controller;
import cn.edu.bupt.OcrApp.service.PlantingService;
import cn.edu.bupt.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import cn.edu.bupt.OcrApp.dao.PlantingDao;
import cn.edu.bupt.OcrApp.domain.generated.PlantingInfo;
import cn.edu.bupt.OcrApp.dto.PlantingInfoDto;
import cn.edu.bupt.OcrApp.mapper.generated.PlantingInfoMapper;
import cn.edu.bupt.common.utils.CommonResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Api(tags = "种植信息")
@RestController
@RequestMapping("planting")
public class PlantingController {
    @Autowired
    private PlantingInfoMapper plantingInfoMapper;
    @Autowired
    private PlantingDao plantingDao;
    @Autowired
    private PlantingService plantingService;


    @ApiOperation("按照材料编号展示种植信息(传“”展示所有植株信息)")
    @GetMapping("showPlantingList")
    public CommonResult showPlantingList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "MaterialNumber", defaultValue = "") String MaterialNumber){
        PageHelper.startPage(pageNum,pageSize);
        List<PlantingInfo> plantingInfo ;
        if(MaterialNumber.equals("")){
            plantingInfo = plantingDao.findAllPlanting();
        }else {
             plantingInfo = plantingDao.findPlantingInfoByMaterialNumber(MaterialNumber);
        }
        return new CommonResult().pageSuccess(plantingInfo);
    }

    @ApiOperation("按照用户责任展示种植信息")
    @GetMapping("showUserPlanting")
    public CommonResult showUserPlanting(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        Integer userId = Objects.requireNonNull(UserUtil.getLoginUser()).getId().intValue();
        List<PlantingInfo> plantingInfo = plantingDao.findUserPlantingInfo(userId);

        return new CommonResult().pageSuccess(plantingInfo);
    }

    @ApiOperation("更新种植信息")
    @PostMapping("updatePlanting")
    public CommonResult updatePlanting(@RequestBody PlantingInfo plantingInfo){
        int i = plantingInfoMapper.updateByPrimaryKeySelective(plantingInfo);
        if(i>0){
            return new CommonResult().success(plantingInfo);
        }else{
            return new CommonResult().failed("更改种植信息失败");
        }
    }
    @ApiOperation("添加新种植信息")
    @PostMapping("addPlanting")
    public CommonResult addPlanting(@RequestBody PlantingInfoDto plantingInfoDto){
        PlantingInfo plantingInfo = new PlantingInfo();
        BeanUtils.copyProperties(plantingInfoDto,plantingInfo);
        int i = plantingInfoMapper.insertSelective(plantingInfo);
        if (i > 0) {
            return new CommonResult().success(plantingInfo);
        } else {
            return new CommonResult().failed("添加失败");
        }
    }

    @ApiOperation("批量添加新种植信息")
    @PostMapping("addBatchPlanting")
    public CommonResult addBatchPlanting(
            @RequestParam(value = "amount", defaultValue = "1") Integer amount,
            @RequestBody PlantingInfoDto plantingInfoDto){

        int i = plantingService.insertBatchPlanting(plantingInfoDto, amount);
        if (i > 0) {
            return new CommonResult().success("已添加数量："+ i);
        } else {
            return new CommonResult().failed("添加失败");
        }
    }

    @ApiOperation("删除种植信息")
    @PostMapping("deletePlanting")
    public CommonResult deletePlanting(@RequestParam(value = "plantingNumber", defaultValue = "")  int plantingNumber){
        int i = plantingInfoMapper.deleteByPrimaryKey(plantingNumber);
        if (i > 0) {
            return new CommonResult().success("删除成功");
        } else {
            return new CommonResult().failed("删除失败");
        }
    }

    @ApiOperation("按照材料编号展示用户负责的种植信息")
    @GetMapping("findUserPlantingBySearch")
    public CommonResult findUserPlantingBySearch(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "MaterialNumber", defaultValue = "") String MaterialNumber){
        PageHelper.startPage(pageNum,pageSize);
        List<PlantingInfo> plantingInfo ;
        int userId = Objects.requireNonNull(UserUtil.getLoginUser()).getId().intValue();
        plantingInfo = plantingDao.findUserPlantingInfoByMaterialNumber(MaterialNumber,userId);

        return new CommonResult().pageSuccess(plantingInfo);
    }
}
