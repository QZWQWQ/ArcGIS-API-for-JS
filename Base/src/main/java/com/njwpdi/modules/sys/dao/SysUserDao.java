/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njwpdi.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 查询用户的所有权限
     * 
     * @param userId
     *            用户ID
     * @return 返回的权限
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     * 
     * @param userId
     *            用户id
     * @return 菜单ID列表
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 根据用户名，查询系统用户
     * 
     * @param username
     *            用户名
     * @return 用户对象
     */
    SysUserEntity queryByUserName(String username);

}