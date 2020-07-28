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
import com.njwpdi.modules.sys.entity.SysConfigEntity;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 分页
     * 
     * @param params
     *            参数
     * @return 返回的分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     * 
     * @param config
     *            参数
     */
    public void saveConfig(SysConfigEntity config);

    /**
     * 更新配置信息
     * 
     * @param config
     *            参数
     */
    public void update(SysConfigEntity config);

    /**
     * 根据key，更新value
     * 
     * @param key
     *            key
     * @param value
     *            value
     */
    public void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     * 
     * @param ids
     *            主键
     */
    public void deleteBatch(String[] ids);

    /**
     * 根据key，获取配置的value值
     * 
     * @param key
     * @return value
     */
    public String getValue(String key);

    /**
     * 根据key，获取value的Object对象
     * 
     * @param key
     *            key
     * @param clazz
     *            Object对象
     * @return value 结果集
     */
    public <T> T getConfigObject(String key, Class<T> clazz);

}
