/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     * 
     * @param parentId
     *            父菜单ID
     * @param menuIdList
     *            用户菜单ID
     * @return 返回的结果集
     */
    List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * 
     * @param parentId
     *            父菜单ID
     * @return 返回的结果集
     */
    List<SysMenuEntity> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     * 
     * @return 返回的结果集
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     * 
     * @param userId
     * @return 返回的结果集
     */
    List<SysMenuEntity> getUserMenuList(String userId);

    /**
     * 删除
     * 
     * @param menuId
     *            菜单主键
     */
    void delete(String menuId);
}
