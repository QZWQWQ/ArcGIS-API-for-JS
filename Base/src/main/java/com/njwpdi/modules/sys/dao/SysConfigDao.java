/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njwpdi.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key，查询value
     * 
     * @param paramKey
     *            key
     * @return value
     */
    SysConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，查询value
     * 
     * @param paramKey
     *            key
     * @param paramValue
     *            value
     * @return 返回结果
     */
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

}