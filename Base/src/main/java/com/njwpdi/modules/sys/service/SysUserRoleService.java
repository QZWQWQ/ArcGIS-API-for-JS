/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 新增或修改用户角色关联数据
     * 
     * @param userId
     *            用户id
     * @param roleIdList
     *            角色数据
     */
    void saveOrUpdate(String userId, List<String> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     * 
     * @param userId
     *            用户id
     * @return 查询到的角色数据
     */
    List<String> queryRoleIdList(String userId);

    /**
     * 根据角色ID数组，批量删除
     * 
     * @param roleIds
     *            角色Id数组
     * @return 删除的条数
     */
    int deleteBatch(String[] roleIds);
}
