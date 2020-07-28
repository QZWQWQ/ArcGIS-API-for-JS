/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.exception.RrException;
import com.njwpdi.common.validator.AbstractAssert;
import com.njwpdi.modules.app.dao.UserDao;
import com.njwpdi.modules.app.entity.UserEntity;
import com.njwpdi.modules.app.form.LoginForm;
import com.njwpdi.modules.app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * @author sunjing
 * @description
 * @date 2019-12-15 2:34 下午
 **/
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public UserEntity queryByMobile(String mobile) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
    }

    @Override
    public long login(LoginForm form) {
        UserEntity user = queryByMobile(form.getMobile());
        AbstractAssert.isNull(user, "手机号或密码错误");

        // 密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            throw new RrException("手机号或密码错误");
        }

        return user.getUserId();
    }
}
