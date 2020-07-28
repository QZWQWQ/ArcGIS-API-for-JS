/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.sys.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    /**
     * 分页查询
     * 
     * @param params
     *            查询条件
     * @return 分页查询结果
     */
    PageUtils queryPage(Map<String, Object> params);
}
