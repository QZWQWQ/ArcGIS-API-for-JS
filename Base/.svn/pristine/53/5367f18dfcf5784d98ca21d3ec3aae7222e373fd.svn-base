/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.modules.app.entity.UserEntity;
import com.njwpdi.modules.app.form.LoginForm;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 根据手机号码查询用户对象
     * 
     * @param mobile
     *            手机
     * @return 查询到的用户对象
     */
    UserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     * 
     * @param form
     *            登录表单
     * @return 返回用户ID
     */
    long login(LoginForm form);
}
