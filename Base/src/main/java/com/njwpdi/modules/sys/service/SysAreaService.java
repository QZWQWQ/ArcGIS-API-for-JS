package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.modules.sys.entity.SysAreaEntity;

import java.util.Map;

/**
 * 区域表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-12-13 14:53:01
 */
public interface SysAreaService extends IService<SysAreaEntity> {
    /**
     * 分页查询
     * 
     * @param params
     *            参数
     * @return 查询结果
     */
    PageUtils queryPage(Map<String, Object> params);
}
