/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.app.controller;

import com.njwpdi.common.utils.Result;
import com.njwpdi.common.validator.ValidatorUtils;
import com.njwpdi.modules.app.form.LoginForm;
import com.njwpdi.modules.app.service.UserService;
import com.njwpdi.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
@Api("APP登录接口")
public class AppLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody LoginForm form) {
        // 表单校验
        ValidatorUtils.validateEntity(form);

        // 用户登录
        long userId = userService.login(form);

        // 生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return Result.ok(map);
    }

}