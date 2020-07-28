package com.njwpdi.modules.survey.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.njwpdi.common.validator.group.AddGroup;
import com.njwpdi.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("SURVEY")
public class SurveyEntity implements Serializable {
    @TableId
    private String prjId;

    @NotBlank(message = "工程名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String prjName;

    @NotBlank(message = "勘测编号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String surveyNum;

    private String conNum;

    private Date time;

    private String stage;

    private String landform;

    private String geoFeature;

    private String river;

    private String location;

    public String getPrjId() {
        return prjId;
    }

    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getSurveyNum() {
        return surveyNum;
    }

    public void setSurveyNum(String surveyNum) {
        this.surveyNum = surveyNum;
    }

    public String getConNum() {
        return conNum;
    }

    public void setConNum(String conNum) {
        this.conNum = conNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getLandform() {
        return landform;
    }

    public void setLandform(String landform) {
        this.landform = landform;
    }

    public String getGeoFeature() {
        return geoFeature;
    }

    public void setGeoFeature(String geoFeature) {
        this.geoFeature = geoFeature;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
