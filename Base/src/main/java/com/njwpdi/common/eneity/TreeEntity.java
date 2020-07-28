package com.njwpdi.common.eneity;

import java.util.List;

/**
 * @author sunjing
 * @description
 * @date 2019-12-15 2:34 下午
 **/
public class TreeEntity {

    private String label;

    private String labelId;

    private List<TreeEntity> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public List<TreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TreeEntity> children) {
        this.children = children;
    }

    public TreeEntity() {}

    public TreeEntity(String label, String labelId, List<TreeEntity> children) {
        this.label = label;
        this.labelId = labelId;
        this.children = children;
    }
}
