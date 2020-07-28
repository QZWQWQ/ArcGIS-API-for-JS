package com.njwpdi.modules.kance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;
import com.njwpdi.modules.kance.dao.KanceDao;
import com.njwpdi.modules.kance.entity.KanceEntity;
import com.njwpdi.modules.kance.service.KanceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service("KanceService")
public class KanceServiceImpl extends ServiceImpl<KanceDao, KanceEntity> implements KanceService {
    @Autowired
    private KanceService kanceService;

    @Override
    public PageUtils  queryPage(Map<String, Object> params) {
        String prjName = (String)params.get("prjName");

        IPage<KanceEntity> page = this.page(new Query<KanceEntity>().getPage(params),
                new QueryWrapper<KanceEntity>()
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
    public void addPrj(KanceEntity prj) {
        this.save(prj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePrj(KanceEntity prj) {
        this.updateById(prj);
    }

    @Override
    public void deleteBatch(String[] prjId) {
        this.removeByIds(Arrays.asList(prjId));
    }


//    @Override
//    public KanceEntity queryByPrjName(String prjName) {
//        return baseMapper.queryByPrjName(prjName);
//    }


    //    @Override
//    public List<KanceEntity> queryByName(KanceEntity kanceEntity){
//        return kanceDao.queryByName(kanceEntity);
//    }



//    @Autowired
//    private KanceDao kanceDao;
//
//    @Override
//    public int addKance(KanceEntity kanceEntity){
//        return kanceDao.addKance(kanceEntity);
//    }
//
//    @Override
//    public int deleteKanceById(String id){
//        return kanceDao.deleteKanceById(id);
//    }
//
//    @Override
//    public int updateKanceById(KanceEntity kanceEntity){
//        return kanceDao.updateKanceById(kanceEntity);
//    }
//
//    @Override
//    public List<KanceEntity> queryKanceList() {
//        return kanceDao.queryKanceList();
//    }


}
