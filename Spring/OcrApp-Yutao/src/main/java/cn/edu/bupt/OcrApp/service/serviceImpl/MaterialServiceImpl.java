package cn.edu.bupt.OcrApp.service.serviceImpl;


import cn.edu.bupt.OcrApp.service.MaterialService;
import cn.edu.bupt.OcrApp.dao.MaterialDao;
import cn.edu.bupt.OcrApp.domain.generated.MaterialInfo;
import cn.edu.bupt.OcrApp.mapper.generated.MaterialInfoMapper;
import cn.edu.bupt.OcrApp.vo.ParentMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialInfoMapper materialInfoMapper;
	@Autowired
	private MaterialService materialService;
    @Autowired
    private MaterialDao materialDao;


	@Override
	public ParentMaterialVO showMaterialParent (String materialNumber){
        MaterialInfo materialInfo = materialInfoMapper.selectByPrimaryKey(materialNumber);
        List<MaterialInfo> allMaterial = materialDao.findAllMaterial();
        ParentMaterialVO parentMaterial = new ParentMaterialVO();
        List<ParentMaterialVO> parentMaterialVO = new ArrayList<>();
        parentMaterial.setName(materialNumber);

        ParentMaterialVO paternal = new ParentMaterialVO();
        ParentMaterialVO maternal = new ParentMaterialVO();
        for(MaterialInfo material : allMaterial){
            // 一级菜单的parentId是0

            if(material.getMaterialNumber().equals(materialInfo.getPaternal())){
                paternal.setName(material.getMaterialNumber());
                parentMaterialVO.add(paternal);
            }
            if(material.getMaterialNumber().equals(materialInfo.getMaternal())){
                maternal.setName(material.getMaterialNumber());
                parentMaterialVO.add(maternal);
            }
        }
//        parentMaterial.setParentMaterialVO(parentMaterialVO);
//        ParentMaterialVO parent1v = new ParentMaterialVO();
        for(ParentMaterialVO parent1 : parentMaterialVO) {
            // 为一级菜单设置子菜单，getChild是递归调用的
            setChild(parent1);
//            parent1v = setChild(parent1);
//            parent1 = parent1v;
//            List<ParentMaterialVO> parentMaterialVO1 = setChild(parent1);
//            parentMaterial.setParentMaterialVO(parentMaterialVO1);

        }
        parentMaterial.setParentMaterialVO(parentMaterialVO);
            return parentMaterial;
    }

    private void setChild(ParentMaterialVO parent1){

        List<MaterialInfo> allMaterial = materialDao.findAllMaterial();
        List<ParentMaterialVO> parentMaterialVO1 = new ArrayList<>();
        MaterialInfo material1 = materialInfoMapper.selectByPrimaryKey(parent1.getName());
        ParentMaterialVO paternal2 = new ParentMaterialVO();
        ParentMaterialVO maternal2= new ParentMaterialVO();
//        ParentMaterialVO parent2v= new ParentMaterialVO();
        for(MaterialInfo material : allMaterial) {
            if (material.getMaterialNumber().equals(material1.getPaternal())) {
                        paternal2.setName(material.getMaterialNumber());
                        parentMaterialVO1.add(paternal2);

            }if (material.getMaterialNumber().equals(material1.getMaternal())) {
                        maternal2.setName(material.getMaterialNumber());
                        parentMaterialVO1.add(maternal2);
                     }
                }
        for(ParentMaterialVO parent2 : parentMaterialVO1) {
            setChild(parent2);
            parent1.setParentMaterialVO(parentMaterialVO1);
        }

    }

	}




