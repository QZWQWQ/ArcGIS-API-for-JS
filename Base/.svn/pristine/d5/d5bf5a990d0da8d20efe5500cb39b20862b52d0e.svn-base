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
import com.njwpdi.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 分页查询
     * 
     * @param params
     *            查询参数
     * @return 返回的结果集
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存角色
     * 
     * @param role
     *            参数
     */
    void saveRole(SysRoleEntity role);

    /**
     * 更新角色
     * 
     * @param role
     *            参数
     */
    void update(SysRoleEntity role);

    /**
     * 删除角色
     * 
     * @param roleIds
     *            角色主键数组
     */
    void deleteBatch(String[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     * 
     * @param createUserId
     *            创建用户的Id
     * @return 角色别表
     */
    List<String> queryRoleIdList(String createUserId);
}
