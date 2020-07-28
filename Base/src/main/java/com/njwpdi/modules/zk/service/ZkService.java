package com.njwpdi.modules.zk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.zk.entity.ZkEntity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-16 10:23:51
 */
public interface ZkService extends IService<ZkEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<ZkEntity> getByPrjId(ZkEntity zkEntity);
    int deleteByPrjId(String[]  prjIds);
    int saveExcel(List<ZkEntity> zkEntityList);
}

