/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;
import com.njwpdi.modules.oss.dao.SysOssDao;
import com.njwpdi.modules.oss.entity.SysOssEntity;
import com.njwpdi.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author sunjing
 * @description
 * @date 2019-12-15 2:34 下午
 **/
@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysOssEntity> page = this.page(new Query<SysOssEntity>().getPage(params));

        return new PageUtils(page);
    }

}
