package com.njwpdi.modules.kance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njwpdi.modules.kance.entity.KanceEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KanceDao extends BaseMapper<KanceEntity> {


    //    int saveKance(KanceEntity kanceEntity);
    //    int deleteBatch(String[] kanceIds);
    //    int update(KanceEntity kanceEntity);
    //    List<KanceEntity> queryKanceList();
    //    KanceEntity1 queryByPrjName(String prjName);
    //    List<KanceEntity> queryByName(KanceEntity kanceEntity);


}
