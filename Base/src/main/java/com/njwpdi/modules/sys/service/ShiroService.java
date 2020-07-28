/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.njwpdi.modules.sys.entity.SysUserEntity;
import com.njwpdi.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     * 
     * @param userId
     *            用户id
     * @return
     */
    Set<String> getUserPermissions(String userId);

    /**
     * queryByToken
     * 
     * @param token
     *            token
     * @return
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * 
     * @param userId
     *            用户id
     * @return 用户
     */
    SysUserEntity queryUser(String userId);
}
