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
import com.njwpdi.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 分页查询
     * 
     * @param params
     *            参数
     * @return 返回的分页结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有权限
     * 
     * @param userId
     *            用户ID
     * @return 返回的权限列表
     */
    List<String> queryAllPerms(String userId);

    /**
     * 查询用户的所有菜单ID
     * 
     * @param userId
     *            用户id
     * @return 用户有权限的菜单列表
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

    /**
     * 保存用户
     * 
     * @param user
     *            需要保存的用户
     */
    void saveUser(SysUserEntity user);

    /**
     * 修改用户
     * 
     * @param user
     *            需要修改的用户
     */
    void update(SysUserEntity user);

    /**
     * 删除用户
     * 
     * @param userIds
     *            需要删除的用户
     */
    void deleteBatch(String[] userIds);

    /**
     * 修改密码
     * 
     * @param userId
     *            用户ID
     * @param password
     *            原密码
     * @param newPassword
     *            新密码
     * @return 修改密码的结果
     */
    boolean updatePassword(String userId, String password, String newPassword);
}
