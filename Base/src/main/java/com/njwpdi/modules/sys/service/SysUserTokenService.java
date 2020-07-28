/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.common.utils.Result;
import com.njwpdi.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     * 
     * @param userId
     *            用户ID
     * @return 创建tiken的结果
     */
    Result createToken(String userId);

    /**
     * 退出，修改token值
     * 
     * @param userId
     *            用户ID
     */
    void logout(String userId);

}
