/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njwpdi.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     * 
     * @param userId
     *            用户主键
     * @return 角色id列表
     */
    List<String> queryRoleIdList(String userId);

    /**
     * 根据角色ID数组，批量删除
     * 
     * @param roleIds
     *            用户id数组
     * @return 删除条数
     */
    int deleteBatch(String[] roleIds);
}
