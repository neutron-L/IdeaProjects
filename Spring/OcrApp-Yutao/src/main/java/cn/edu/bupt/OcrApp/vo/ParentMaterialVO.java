package cn.edu.bupt.OcrApp.vo;

import lombok.Data;

import java.util.List;

@Data
public class ParentMaterialVO {
    private String name;

    private List<ParentMaterialVO> parentMaterialVO;

    public List<ParentMaterialVO> getParentMaterialVO() {
        return parentMaterialVO;
    }

    public void setParentMaterialVO(List<ParentMaterialVO> parentMaterialVO) {
        this.parentMaterialVO = parentMaterialVO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
