/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 分页查询
     * 
     * @param params
     *            参数
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存定时任务
     * 
     * @param scheduleJob
     *            需要保存的对象
     */
    void saveJob(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     * 
     * @param scheduleJob
     *            需要更新的对象
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     * 
     * @param jobIds
     *            任务id
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     * 
     * @param jobIds
     *            任务id
     * @param status
     *            任务状态
     * @return
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     * 
     * @param jobIds
     *            任务id
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     * 
     * @param jobIds
     *            任务主键
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     * 
     * @param jobIds
     *            任务主键
     */
    void resume(Long[] jobIds);
}
