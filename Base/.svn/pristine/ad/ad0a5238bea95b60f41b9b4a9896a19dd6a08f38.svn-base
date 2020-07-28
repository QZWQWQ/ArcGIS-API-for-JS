/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 新建或更新角色菜单关联数据
     * 
     * @param roleId
     *            角色id
     * @param menuIdList
     *            菜单列表
     */
    void saveOrUpdate(String roleId, List<String> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     * 
     * @param roleId
     *            角色id
     * @return 菜单ID列表
     */
    List<String> queryMenuIdList(String roleId);

    /**
     * 根据角色ID数组，批量删除
     * 
     * @param roleIds
     *            角色Id数组
     * @return 删除条数
     */
    int deleteBatch(String[] roleIds);

}
