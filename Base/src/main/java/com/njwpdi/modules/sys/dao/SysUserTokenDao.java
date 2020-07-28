/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.njwpdi.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    /**
     * 查询token对象
     * 
     * @param token
     *            用户token
     * @return 返回的token对象
     */
    SysUserTokenEntity queryByToken(String token);

}
