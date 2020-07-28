package com.njwpdi.modules.survey.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.survey.entity.SurveyEntity;

import java.util.Map;

public interface SurveyService extends IService<SurveyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addPrj(SurveyEntity prj);

    void updatePrj(SurveyEntity prj);

    void deleteBatch(String[] prjIds);
}
