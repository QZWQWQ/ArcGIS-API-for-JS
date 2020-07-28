package com.njwpdi.modules.bd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.bd.entity.BdEntity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-18 16:33:08
 */
public interface BdService extends IService<BdEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<BdEntity> getByPrjId(BdEntity bdEntity);
    int deleteByPrjId(String[]  prjIds);
}

