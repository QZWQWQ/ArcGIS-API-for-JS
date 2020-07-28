/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;
import com.njwpdi.modules.sys.dao.SysLogDao;
import com.njwpdi.modules.sys.entity.SysLogEntity;
import com.njwpdi.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author sunjing
 * @description
 * @date 2019-12-15 2:34 下午
 **/
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage<SysLogEntity> page = this.page(new Query<SysLogEntity>().getPage(params), new QueryWrapper<SysLogEntity>()
            .like(StringUtils.isNotBlank(key), "username", key).orderByDesc("create_Date"));

        return new PageUtils(page);
    }
}
