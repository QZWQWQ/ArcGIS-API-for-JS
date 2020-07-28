/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.njwpdi.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njwpdi.modules.sys.entity.SysCaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysCaptchaService extends IService<SysCaptchaEntity> {

    /**
     * 获取图片验证码
     * 
     * @param uuid
     *            主键
     * @return 验证码图片
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * 
     * @param uuid
     *            uuid
     * @param code
     *            验证码
     * @return true：成功 false：失败
     */
    boolean validate(String uuid, String code);
}
