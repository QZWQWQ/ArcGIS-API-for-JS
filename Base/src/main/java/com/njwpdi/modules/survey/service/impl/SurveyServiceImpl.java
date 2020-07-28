package com.njwpdi.modules.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;
import com.njwpdi.modules.kance.entity.KanceEntity;
import com.njwpdi.modules.survey.dao.SurveyDao;
import com.njwpdi.modules.survey.entity.SurveyEntity;
import com.njwpdi.modules.survey.service.SurveyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service("SurveyService")
public class SurveyServiceImpl extends ServiceImpl<SurveyDao, SurveyEntity> implements SurveyService {
    @Autowired
    private SurveyService surveyService;

    @Override
    public PageUtils  queryPage(Map<String, Object> params) {
        String prjName = (String)params.get("prjName");

        IPage<SurveyEntity> page = this.page(
                new Query<SurveyEntity>().getPage(params),
                new QueryWrapper<SurveyEntity>()
                        .like(StringUtils.isNotBlank(prjName),"prj_name",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"survey_num",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"con_num",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"time",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"stage",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"landform",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"geo_feature",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"river",prjName)
                        .or()
                        .like(StringUtils.isNotBlank(prjName),"location",prjName)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPrj(SurveyEntity prj) {
        this.save(prj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePrj(SurveyEntity prj) {
        this.updateById(prj);
    }

    @Override
    public void deleteBatch(String[] prjId) {
        this.removeByIds(Arrays.asList(prjId));
    }
}
