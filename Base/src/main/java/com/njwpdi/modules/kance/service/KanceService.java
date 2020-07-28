package com.njwpdi.modules.kance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.kance.entity.KanceEntity;

import java.util.Map;

public interface KanceService extends IService<KanceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addPrj(KanceEntity prj);

    void updatePrj(KanceEntity prj);

    void deleteBatch(String[] prjIds);

    //    KanceEntity queryByPrjName(String prjName);

    //        List<KanceEntity> queryKanceList();

    //     List<KanceEntity> queryByName(KanceEntity kanceEntity);

}
